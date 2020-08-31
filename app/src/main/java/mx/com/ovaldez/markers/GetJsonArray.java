package mx.com.ovaldez.markers;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import mx.com.ovaldez.markers.vo.Spot;

/**
 * Created by omar.valdez on 22/05/2017.
 */

public class GetJsonArray extends AsyncTask<String, Void, String> {

    private GoogleMap mlMap;
    ArrayList<Spot> spotList = null;
    //Location mLastLocation;
    private Bitmap smallMarker;

    public GetJsonArray(GoogleMap mmMap, Bitmap smallMarker ){
        this.mlMap = mmMap;
        this.smallMarker = smallMarker;
    }

    @Override
    protected String doInBackground(String... params) {
        spotList = new ArrayList<Spot>();
        URL url = null;
        try {
            url = new URL(params[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            in.close();

            JSONArray jArray = new JSONArray(sb.toString());
            for(int i=0;i<jArray.length();i++){
                JSONObject e = jArray.getJSONObject(i);
                Log.i("JSAONARRAY","lat"+e.getString("lat")+" lon:"+e.getString("lon")+" speedy:"+e.getString("speed")+" fecha:"+e.getString("date"));
                spotList.add(new Spot(Double.valueOf(e.getString("lat")), Double.valueOf(e.getString("lon")),Double.valueOf(e.getString("speed")), e.getString("date")));
            }
            //Log.i("JSAONARRAY","estaciones.size "+estaciones.size());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "OK";
    }

    @Override
    protected void onPostExecute(String s) {
        LatLng position = null;
        try{
        PolylineOptions lineOptions = new PolylineOptions();
        ArrayList<LatLng> points = new ArrayList<LatLng>();
        int k=1;
        int i = 0;
        mlMap.clear();
        for (Spot ga : spotList) {
            mlMap.addMarker(new MarkerOptions().position(new LatLng(ga.getLat(), ga.getLon())).title(ga.getSpdKm()+"Km/h").snippet(ga.getHora()).visible(true).icon(BitmapDescriptorFactory.fromBitmap(smallMarker))); //BitmapDescriptorFactory.fromResource(R.drawable.dot_circle)
            position = new LatLng(ga.getLat(), ga.getLon());
            points.add(position);
        }

        lineOptions.addAll(points);
        lineOptions.width(10);
        lineOptions.color(Color.RED);
        mlMap.addPolyline(lineOptions);

        CameraPosition CENTER = CameraPosition.builder().
                target(position).
                zoom(16).
                bearing(0).
                tilt(45).
                build();

        mlMap.moveCamera(CameraUpdateFactory.newCameraPosition(CENTER));
    }catch(Exception e){
            Log.e("onPostE",e.toString());
        }
    }
}
