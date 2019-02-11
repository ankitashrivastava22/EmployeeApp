package com.example.ankita.employeeapp;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.ankita.employeeapp.RoomDB.EmployeeDao;
import com.example.ankita.employeeapp.RoomDB.EmployeeEntity;
import com.example.ankita.employeeapp.RoomDB.EmployeeRoomDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class EmployeeRepository {

    public EmployeeDao employeeDao;
    private LiveData<List<EmployeeEntity>> mEmployeeEntity;
    private EmployeeEntity employeeEntity;


    public EmployeeRepository(Application application) {

        EmployeeRoomDatabase employeeRoomDatabase = EmployeeRoomDatabase.getInstance(application);
        employeeDao = employeeRoomDatabase.employeeDao();
        checkData();
        //From dao
        mEmployeeEntity = employeeDao.getAllData();
    }


    public LiveData<List<EmployeeEntity>> getAllDataFromRepo() {
        loadEmployeeData();
        mEmployeeEntity = employeeDao.getAllData();
        Log.d("Data From Server", "Data from Server");
        return mEmployeeEntity;

    }

    public LiveData<List<EmployeeEntity>> getOfflineData() {
        ConnectivityManager connectivityManager = (ConnectivityManager) MyApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            mEmployeeEntity = employeeDao.getAllData();

        } else
            Toast.makeText(MyApplication.getInstance(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
        return mEmployeeEntity;
    }

    private void loadEmployeeData() {
        SharedPreferences preferences = MyApplication.getInstance().getSharedPreferences("FlagFile", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitApi.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
        Call<List<EmployeeEntity>> call = retrofitApi.getEmployee();
        call.enqueue(new Callback<List<EmployeeEntity>>() {
            @Override
            public void onResponse(Call<List<EmployeeEntity>> call, Response<List<EmployeeEntity>> response) {
                List<EmployeeEntity> emp = new ArrayList<>();
                if (response.body() != null) {
                    for (int i = 0; i < response.body().size(); i++) {
                        Log.d("Data" + i, "" + response.body().get(i));
                        emp.add(i, response.body().get(i));
                    }
                    insert(emp);
                    editor.putBoolean("FlagValue", true);
                    editor.commit();
                }
            }

            @Override
            public void onFailure(Call<List<EmployeeEntity>> call, Throwable t) {
                Log.d("Unable to get the data", "Failure ");
                Toast.makeText(MyApplication.getInstance().getApplicationContext(), "There is no network,Please check your internet connection", Toast.LENGTH_SHORT).show();
                editor.putBoolean("FlagValue", false);
                editor.commit();
            }
        });

    }


    public void insert(List<EmployeeEntity> empList) {
        new InsertEmpData(employeeDao).execute(empList);


    }


    private static class InsertEmpData extends AsyncTask<List<EmployeeEntity>, Void, Void> {

        private EmployeeDao mEmployeeDao;

        InsertEmpData(EmployeeDao dao) {
            mEmployeeDao = dao;
        }

        @Override
        protected Void doInBackground(List<EmployeeEntity>... lists) {
            for (int i = 0; i < lists[0].size(); i++) {
                mEmployeeDao.insert(lists[0].get(i));
            }
            return null;
        }
    }

    public void DeleteEmpData(EmployeeEntity employeeEntity) {
        new DeleteEntry(employeeDao).execute(employeeEntity);
    }

    private static class DeleteEntry extends AsyncTask<EmployeeEntity, Void, Void> {
        private EmployeeDao mEmployeeDao;

        public DeleteEntry(EmployeeDao dao) {
            mEmployeeDao = dao;
        }

        @Override
        protected Void doInBackground(EmployeeEntity... employeeEntities) {
            mEmployeeDao.delete(employeeEntities[0]);
            return null;
        }
    }

    public void checkData() {
        MyTask myTask = new MyTask(employeeDao);
        myTask.execute(employeeEntity);


    }

    private static class MyTask extends AsyncTask<EmployeeEntity, Void, Boolean> {
        private EmployeeDao mEmployeeDao;
        SharedPreferences preferences = MyApplication.getInstance().getSharedPreferences("FlagFile", Context.MODE_PRIVATE);

        public MyTask(EmployeeDao dao) {
            mEmployeeDao = dao;
        }

        @Override
        protected Boolean doInBackground(EmployeeEntity... employeeEntities) {
            employeeEntities[0] = mEmployeeDao.getAnyEmployeeData();
            if (employeeEntities[0] != null) {

                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.putBoolean("FlagValue", true);
                editor.apply();
                return true;
            } else {

                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.putBoolean("FlagValue", false);
                editor.apply();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }
}


