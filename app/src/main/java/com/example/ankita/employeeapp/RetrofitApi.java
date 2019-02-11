package com.example.ankita.employeeapp;

import com.example.ankita.employeeapp.RoomDB.EmployeeEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApi {
    String base_url="https://jsonplaceholder.typicode.com/";

    @GET("users")
    Call<List<EmployeeEntity>> getEmployee();
}
