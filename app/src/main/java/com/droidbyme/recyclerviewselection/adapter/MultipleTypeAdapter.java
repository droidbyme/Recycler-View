package com.droidbyme.recyclerviewselection.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.droidbyme.recyclerviewselection.R;
import com.droidbyme.recyclerviewselection.model.MultiEmployee;

import java.util.ArrayList;

public class MultipleTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static int TYPE_CALL = 1;
    private static int TYPE_EMAIL = 2;
    private Context context;
    private ArrayList<MultiEmployee> employees;

    public MultipleTypeAdapter(Context context, ArrayList<MultiEmployee> employees) {
        this.context = context;
        this.employees = employees;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        if (viewType == TYPE_CALL) { // for call layout
            view = LayoutInflater.from(context).inflate(R.layout.item_call, viewGroup, false);
            return new CallViewHolder(view);

        } else { // for email layout
            view = LayoutInflater.from(context).inflate(R.layout.item_email, viewGroup, false);
            return new EmailViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (TextUtils.isEmpty(employees.get(position).getEmail())) {
            return TYPE_CALL;
        } else {
            return TYPE_EMAIL;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == TYPE_CALL) {
            ((CallViewHolder) viewHolder).setCallDetails(employees.get(position));
        } else {
            ((EmailViewHolder) viewHolder).setEmailDetails(employees.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    class CallViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private TextView txtAddress;

        CallViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtAddress = itemView.findViewById(R.id.txtAddress);
        }

        void setCallDetails(MultiEmployee employee) {
            txtName.setText(employee.getName());
            txtAddress.setText(employee.getAddress());
        }
    }

    class EmailViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private TextView txtAddress;

        EmailViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtAddress = itemView.findViewById(R.id.txtAddress);
        }

        void setEmailDetails(MultiEmployee employee) {
            txtName.setText(employee.getName());
            txtAddress.setText(employee.getAddress());
        }
    }

}
