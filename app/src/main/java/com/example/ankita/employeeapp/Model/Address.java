package com.example.ankita.employeeapp.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.ankita.employeeapp.RoomDB.EmployeeEntity;

@Entity(tableName = "Address", foreignKeys = @ForeignKey(entity = EmployeeEntity.class,parentColumns = "uniqueID",childColumns = "address_id"))
public class Address {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="address_id")
    private long addressId;


    @Ignore
    @NonNull
    private String zipcode;

    @Ignore
    @NonNull
    private Geo geo;


    @Ignore
    @NonNull
    private String suite;

    @ColumnInfo(name="City")
    @NonNull
    private String city;


   @Ignore
    @NonNull
    private String street;

    public Address(@NonNull long addressId, /*@NonNull String zipcode, @NonNull Geo geo, @NonNull String suite,*/ @NonNull String city/*, @NonNull String street*/) {
        this.addressId = addressId;
       // this.zipcode = zipcode;
        //this.geo = geo;
        //this.suite = suite;
        this.city = city;
        //this.street = street;
    }

    @NonNull
    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(@NonNull long addressId) {
        this.addressId = addressId;
    }

    @NonNull
    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(@NonNull String zipcode) {
        this.zipcode = zipcode;
    }

    @NonNull
    public Geo getGeo() {
        return geo;
    }

    public void setGeo(@NonNull Geo geo) {
        this.geo = geo;
    }

    @NonNull
    public String getSuite() {
        return suite;
    }

    public void setSuite(@NonNull String suite) {
        this.suite = suite;
    }

    @NonNull
    public String getCity() {
        return city;
    }

    public void setCity(@NonNull String city) {
        this.city = city;
    }

    @NonNull
    public String getStreet() {
        return street;
    }

    public void setStreet(@NonNull String street) {
        this.street = street;
    }
}
