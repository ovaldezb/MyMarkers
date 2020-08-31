package mx.com.ovaldez.markers;

import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by omar.valdez on 22/05/2017.
 */

public class HttpAsyncTask {// extends AsyncTask<String, Void, String> {

    /*private GasData data;
    Location mLastLocation;
    private String gasPriceValue, barRate;

    HttpAsyncTask(Location lastLocation, String gasPriceValue , String barRate){
        this.mLastLocation = lastLocation;
        this.barRate = barRate;
        this.gasPriceValue = gasPriceValue;
    }

    @Override
    protected String doInBackground(String... urls) {
        data = new GasData();
        if(mLastLocation!=null){
            data.setLat(String.valueOf(mLastLocation.getLatitude()));
            data.setLon(String.valueOf(mLastLocation.getLongitude()));
            data.setPrice(gasPriceValue);
            data.setBarRate(barRate);
        }

        Log.i("DataSend","Lat: "+data.getLat()+" Lon: "+data.getLon());
        return POST(urls[0],data);
    }

    private static String POST(String link, GasData data){
        URL url = null;
        try {
            url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("lat", data.getLat())
                    .appendQueryParameter("lon", data.getLon())
                    .appendQueryParameter("price", data.getPrice())
                    .appendQueryParameter("calif",data.getBarRate());
            String query = builder.build().getEncodedQuery();
            Log.i("DATA_SEND","Query:"+query);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            int responseCode=conn.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                /*String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }*/
          /*      Log.i("SEND","Envio correcto: "+responseCode);
            }
            else {
                //response="";
                Log.i("SEND","Envio incorrecto: "+responseCode);
            }
            conn.connect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "OK";
    }*/
}
