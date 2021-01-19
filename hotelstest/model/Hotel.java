package com.anadolstudio.hotelstest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Hotel {

    private long id;
    private String name;
    private String address;
    private int stars;
    private double distance;
    private String image;

    @SerializedName("suites_availability")
    @Expose
    private String suitesAvailability;

    private double lat;
    private double lon;

    private Hotel(long id, String name, String address, int stars, double distance, String suitesAvailability) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.stars = stars;
        this.distance = distance;
        this.suitesAvailability = suitesAvailability;
    }

    public Hotel(long id, String name, String address, int stars, String image, double distance, String suitesAvailability, double lat, double lon) {
        this(id, name, address, stars, distance, suitesAvailability);
        this.image = image;
        this.lat = lat;
        this.lon = lon;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getStars() {
        return stars;
    }

    public double getDistance() {
        return distance;
    }

    public String getImage() {
        return image;
    }

    public String getSuitesAvailability() {
        return suitesAvailability;
    }

    public int getCountSuitesAvailability() {
        String[] suites = suitesAvailability.split(":");
        return suites.length;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return id == hotel.id &&
                stars == hotel.stars &&
                suitesAvailability.equals(hotel.suitesAvailability);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stars, suitesAvailability);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", stars=" + stars +
                ", distance=" + distance +
                ", suitesAvailability='" + suitesAvailability + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }


}
