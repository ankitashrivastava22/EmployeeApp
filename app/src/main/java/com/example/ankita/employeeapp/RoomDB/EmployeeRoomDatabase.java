package com.example.ankita.employeeapp.RoomDB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.ankita.employeeapp.Model.Address;
import com.example.ankita.employeeapp.Model.Company;


@Database(entities = {EmployeeEntity.class, Company.class,Address.class},version = 1)
public abstract class EmployeeRoomDatabase extends RoomDatabase {

    public static EmployeeRoomDatabase mInstance;
    public abstract EmployeeDao employeeDao();
   
    public static EmployeeRoomDatabase getInstance(Context context){
        if(mInstance == null){
            mInstance =Room.databaseBuilder(context.getApplicationContext(),EmployeeRoomDatabase.class,"employee_db")
                    .build();

        }
        return mInstance;
    }




}
