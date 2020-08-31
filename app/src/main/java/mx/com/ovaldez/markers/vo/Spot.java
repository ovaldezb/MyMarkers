package mx.com.ovaldez.markers.vo;

/**
 * Created by omar.valdez on 03/06/2017.
 */

public class Spot {

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getSpdKm() {
        return spdKm;
    }

    public void setSpdKm(double spdKm) {
        this.spdKm = spdKm;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    private double lon;
    private double lat;
    private double spdKm;
    private String hora;

    public Spot(double lat, double lon, double spdKm, String hora) {
        this.lon = lon;
        this.lat = lat;
        this.spdKm = spdKm;
        this.hora = hora;
    }
}
