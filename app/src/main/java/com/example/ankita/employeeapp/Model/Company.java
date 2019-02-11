package com.example.ankita.employeeapp.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.ankita.employeeapp.RoomDB.EmployeeEntity;


@Entity(tableName = "company", foreignKeys = @ForeignKey(entity = EmployeeEntity.class,parentColumns = "uniqueID",childColumns = "company_id"))
public class Company {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="company_id")
    private long companyId;

    @NonNull
    @ColumnInfo(name = "CompanyName")
    private String name;
    @Ignore
    @NonNull
    @ColumnInfo(name = "catchPhrase")
    private String cathchPhrase;

    @NonNull
    @ColumnInfo(name = "bs")
    private String bs;

    public Company(@NonNull long companyId, @NonNull String name/*, @NonNull String cathchPhrase*/, @NonNull String bs) {
        this.companyId = companyId;
        this.name = name;
       // this.cathchPhrase = cathchPhrase;
        this.bs = bs;
    }

    @NonNull
    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(@NonNull long companyId) {
        this.companyId = companyId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getCathchPhrase() {
        return cathchPhrase;
    }

    public void setCathchPhrase(@NonNull String cathchPhrase) {
        this.cathchPhrase = cathchPhrase;
    }

    @NonNull
    public String getBs() {
        return bs;
    }

    public void setBs(@NonNull String bs) {
        this.bs = bs;
    }
}


