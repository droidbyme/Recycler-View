package com.droidbyme.recyclerviewselection.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.droidbyme.recyclerviewselection.R;
import com.droidbyme.recyclerviewselection.model.Employee;

import java.util.ArrayList;

public class SwipeAdapter extends RecyclerView.Adapter<SwipeAdapter.SwipeViewHolder> {

    private Context context;
    private ArrayList<Employee> employees;
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public SwipeAdapter(Context context, ArrayList<Employee> employees) {
        this.context = context;
        this.employees = employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = new ArrayList<>();
        this.employees = employees;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SwipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_employee_swipe, viewGroup, false);
        return new SwipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SwipeViewHolder swipeViewHolder, int i) {
        viewBinderHelper.setOpenOnlyOne(true);
        viewBinderHelper.bind(swipeViewHolder.swipelayout, String.valueOf(employees.get(i).getName()));
        viewBinderHelper.closeLayout(String.valueOf(employees.get(i).getName()));
        swipeViewHolder.bindData(employees.get(i));
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    class SwipeViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private TextView txtEdit;
        private TextView txtDelete;
        private SwipeRevealLayout swipelayout;

        SwipeViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            txtEdit = itemView.findViewById(R.id.txtEdit);
            txtDelete = itemView.findViewById(R.id.txtDelete);
            swipelayout = itemView.findViewById(R.id.swipelayout);

            txtEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "edit", Toast.LENGTH_SHORT).show();
                }
            });

            txtDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show();
                }
            });
        }

        void bindData(Employee employee) {
            textView.setText(employee.getName());
        }
    }
}
