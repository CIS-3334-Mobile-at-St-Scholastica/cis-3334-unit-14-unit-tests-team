package css.cis3334.fishtracker;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by cssuser on 4/20/2017.
 */

public class Fish implements Serializable {
    private long id;
    private String species;
    private String weightInOz;
    private String dateCaught;
    private String locationCaughtLatitude;
    private String locationCaughtLongitude;

    public Fish() {
    }

    public Fish(long id, String species, String weightInOz, String dateCaught, String locationCaughtLatitude, String locationCaughtLongitude) {
        this.id = id;
        this.species = species;
        this.weightInOz = weightInOz;
        this.dateCaught = dateCaught;
        this.locationCaughtLatitude = locationCaughtLatitude;
        this.locationCaughtLongitude = locationCaughtLongitude;
    }

    public Fish(String species, String weightInOz, String dateCaught, String locationCaughtLatitude, String locationCaughtLongitude) {
        this.species = species;
        this.weightInOz = weightInOz;
        this.dateCaught = dateCaught;
        this.locationCaughtLatitude = locationCaughtLatitude;
        this.locationCaughtLongitude = locationCaughtLongitude;
    }

    public Fish(long id, String species, String weightInOz, String dateCaught) {
        this.id = id;
        this.species = species;
        this.weightInOz = weightInOz;
        this.dateCaught = dateCaught;
        this.locationCaughtLatitude = locationCaughtLatitude;
        this.locationCaughtLongitude = locationCaughtLongitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getWeightInOz() {
        return weightInOz;
    }

    public void setWeightInOz(String weightInOz) {
        this.weightInOz = weightInOz;
    }

    public String getDateCaught() {
        return dateCaught;
    }

    public void setDateCaught(String dateCaught) {
        this.dateCaught = dateCaught;
    }

    public String getLocationCaughtLatitude() {
        return locationCaughtLatitude;
    }

    public void setLocationCaughtLatitude(String locationCaughtLatitude) {
        this.locationCaughtLatitude = locationCaughtLatitude;
    }

    public String getLocationCaughtLongitude() {
        return locationCaughtLongitude;
    }

    public void setLocationCaughtLongitude(String locationCaughtLongitude) {
        this.locationCaughtLongitude = locationCaughtLongitude;
    }

    @Override
    public String toString() {
        return "Fish{" +
                "species='" + species + '\'' +
                ", weightInOz='" + weightInOz + '\'' +
                ", dateCaught='" + dateCaught + '\'' +
                '}';
    }
}
