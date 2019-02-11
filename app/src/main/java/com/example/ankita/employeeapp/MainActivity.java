package com.example.ankita.employeeapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ankita.employeeapp.RoomDB.EmployeeEntity;
import com.example.ankita.employeeapp.ViewModel.EmployeeViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    RecyclerView mRecyclerView;
    EmployeeAdapter adapter;
    EmployeeViewModel employeeViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initViews();
        employeeViewModel = ViewModelProviders.of(MainActivity.this).get(EmployeeViewModel.class);
        adapter = new EmployeeAdapter(MainActivity.this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        if (employeeViewModel != null) {
            LiveData<List<EmployeeEntity>> employeeEntities = employeeViewModel.getEmployeeList();
            if (employeeEntities == null) return;
            employeeEntities.observe(MainActivity.this, new Observer<List<EmployeeEntity>>() {
                @Override
                public void onChanged(@Nullable List<EmployeeEntity> employees) {
                    /* relativeLayout.setVisibility(View.GONE);*/
                    mRecyclerView.setVisibility(View.VISIBLE);
                    adapter.setData(employees);
                    mRecyclerView.setAdapter(adapter);

                }
            });
        }
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                int position = viewHolder.getAdapterPosition();
                EmployeeEntity employeeEntity = adapter.getEmployeeAtPosition(position);
                employeeViewModel.DeleteEmpData(employeeEntity);
                Snackbar snackbar = Snackbar.make(linearLayout, "Deleted Successfully", Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });

        itemTouchHelper.attachToRecyclerView(mRecyclerView);


    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        linearLayout = (LinearLayout) findViewById(R.id.lnr_parent);

    }


}
