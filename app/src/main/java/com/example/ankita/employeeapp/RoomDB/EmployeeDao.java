package com.example.ankita.employeeapp.RoomDB;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface EmployeeDao {
    @Insert
    void insert(EmployeeEntity employeeEntity);

    @Delete
    void delete(EmployeeEntity employeeEntity);

    @Query("SELECT * FROM employee_table")
    LiveData<List<EmployeeEntity>> getAllData();

    @Query("SELECT * FROM employee_table LIMIT 1")
    EmployeeEntity getAnyEmployeeData();


}
