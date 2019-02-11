package com.example.ankita.employeeapp.RoomDB;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.example.ankita.employeeapp.Model.Address;
import com.example.ankita.employeeapp.Model.Company;

import java.lang.reflect.Array;
import java.util.Arrays;


@Entity(tableName = "employee_table")
public class EmployeeEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="uniqueID")
    private int uniqueID;

    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    @ColumnInfo(name = "username")
    private String username;

    @NonNull
    @ColumnInfo(name = "email")
    private String email;


    @NonNull
    @ColumnInfo(name = "phone")
    private String phone;

    @NonNull
    @ColumnInfo(name = "website")
    private String website;

   @Embedded
    @NonNull
    private Company company;


   @Embedded
    @NonNull
    private Address address;



    public EmployeeEntity(@NonNull int uniqueID, @NonNull String id, @NonNull String name, @NonNull String username, @NonNull String email, @NonNull String phone, @NonNull String website, @NonNull Company company, @NonNull Address address) {
        this.uniqueID = uniqueID;
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.website = website;
        this.company = company;
        this.address = address;
    }



    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getPhone() {
        return phone;
    }

    public void setPhone(@NonNull String phone) {
        this.phone = phone;
    }

    @NonNull
    public String getWebsite() {
        return website;
    }

    public void setWebsite(@NonNull String website) {
        this.website = website;
    }

    @NonNull
    public Company getCompany() {
        return company;
    }

    public void setCompany(@NonNull Company company) {
        this.company = company;
    }

    public void setAddress(@NonNull Address address) {
        this.address = address;
    }

    @NonNull
    public Address getAddress() {
        return address;
    }

    @NonNull
    public int getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(@NonNull int uniqueID) {
        this.uniqueID = uniqueID;
    }


}

