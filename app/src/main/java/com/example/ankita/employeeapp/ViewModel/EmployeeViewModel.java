package com.example.ankita.employeeapp.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.ankita.employeeapp.EmployeeRepository;
import com.example.ankita.employeeapp.MyApplication;
import com.example.ankita.employeeapp.RoomDB.EmployeeEntity;

import java.util.List;

public class EmployeeViewModel extends AndroidViewModel {
    private LiveData<List<EmployeeEntity>> employeeList;
    private EmployeeRepository employeeRepository;
    private LiveData<List<EmployeeEntity>> mEmployeeEntity;


    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        employeeRepository = new EmployeeRepository(application);

        SharedPreferences preferences = MyApplication.getInstance().getSharedPreferences("FlagFile", Context.MODE_PRIVATE);
        Boolean result = preferences.getBoolean("FlagValue", false);

        if (result) {
            mEmployeeEntity = employeeRepository.getOfflineData();
            Log.d("Get  data from Database", "Fetching data from db");
        } else {
            mEmployeeEntity = employeeRepository.getAllDataFromRepo();
            Log.d("Get  data from network", "Fetching data from netwrk");

        }


    }


    public void DeleteEmpData(EmployeeEntity employeeEntity) {
        employeeRepository.DeleteEmpData(employeeEntity);
    }


    public LiveData<List<EmployeeEntity>> getEmployeeList() {
        if (employeeList == null) {
            employeeList = new MutableLiveData<>();
            employeeList = mEmployeeEntity;
        }
        return employeeList;
    }


}
