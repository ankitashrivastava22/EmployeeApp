package com.example.ankita.employeeapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ankita.employeeapp.RoomDB.EmployeeEntity;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.MyHolder> {

    private Context context;
    private List<EmployeeEntity> employeeList;

    public EmployeeAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        if (employeeList != null) {
            EmployeeEntity current = employeeList.get(i);
            myHolder.name.setText(current.getName());
            myHolder.email.setText(current.getEmail());
            myHolder.username.setText(current.getUsername());
            myHolder.phone.setText(current.getPhone());
            myHolder.website.setText(current.getWebsite());
            myHolder.company.setText(current.getCompany().getName());
            myHolder.address.setText(current.getAddress().getCity());


        } else {
            myHolder.name.setText("No Word");
        }


    }

    @Override
    public int getItemCount() {
        if (employeeList != null)
            return employeeList.size();
        else

            return 0;
    }

    public void setData(List<EmployeeEntity> emp) {
        employeeList = emp;
        notifyDataSetChanged();


    }

    public EmployeeEntity getEmployeeAtPosition(int i) {
        return employeeList.get(i);
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView name, username, email, phone, website, company, address;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_name);
            username = (TextView) itemView.findViewById(R.id.tv_username);
            email = (TextView) itemView.findViewById(R.id.tv_email);
            phone = (TextView) itemView.findViewById(R.id.tv_phone);
            website = (TextView) itemView.findViewById(R.id.tv_website);
            company = (TextView) itemView.findViewById(R.id.tv_company);
            address = (TextView) itemView.findViewById(R.id.tv_address);

        }
    }
}
