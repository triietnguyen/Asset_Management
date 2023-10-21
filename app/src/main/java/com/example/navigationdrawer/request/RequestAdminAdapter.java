package com.example.navigationdrawer.request;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawer.R;

import java.util.List;

import Models.Assignment;

public class RequestAdminAdapter extends RecyclerView.Adapter<RequestAdminAdapter.ViewHolder> {

    Context context;
    List<Assignment> assignment_list;

    public RequestAdminAdapter(Context context, List<Assignment> assignment_list) {
        this.context = context;
        this.assignment_list = assignment_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_request_layout_admin,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(assignment_list !=null && assignment_list.size()>0){
            Assignment assignment = assignment_list.get(position);
            holder.txt_request_asset_name_layout.setText(assignment.getAsset_name());
            holder.txt_request_asset_category_layout.setText(assignment.getCategory());
            holder.txt_request_asset_date_layout.setText(assignment.getAssigned_date());
            holder.txt_request_status_layout.setText(assignment.getStatus());
        }else{
            return;
        }
    }

    @Override
    public int getItemCount() {
        return assignment_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_request_asset_name_layout, txt_request_asset_category_layout, txt_request_asset_date_layout, txt_request_status_layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_request_asset_name_layout = itemView.findViewById(R.id.txt_request_asset_name_layout);
            txt_request_asset_category_layout = itemView.findViewById(R.id.txt_request_asset_category_layout);
            txt_request_asset_date_layout = itemView.findViewById(R.id.txt_request_asset_date_layout);
            txt_request_status_layout = itemView.findViewById(R.id.txt_request_status_layout);
        }
    }
}
