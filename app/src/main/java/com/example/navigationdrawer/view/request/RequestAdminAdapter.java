package com.example.navigationdrawer.view.request;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawer.R;

import java.util.List;

import com.example.navigationdrawer.model.Assignment;
import com.example.navigationdrawer.model.MyApplication;
import com.example.navigationdrawer.model.User;
import com.example.navigationdrawer.view.assignment.edit.EditAssignmentActivity;
import com.example.navigationdrawer.view.request.edit.EditRequestAdminActivity;

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
        User u = new User();
        Assignment assign = new Assignment();
        if(assignment_list !=null && assignment_list.size()>0){
            Assignment assignment = assignment_list.get(position);
            holder.txt_request_asset_name_layout.setText(assignment.getAsset_name());
            holder.txt_request_asset_category_layout.setText(assignment.getCategory());
            holder.txt_request_asset_date_layout.setText(assignment.getAssigned_date());
            holder.txt_request_status_layout.setText(assignment.getStatus());
            holder.txt_request_username.setText(assignment.getAssigned_to());
            holder.txt_request_description.setText(assignment.getDescription());
            holder.txt_request_endDate.setText(assignment.getEndDate());
            holder.img_request_edit_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle dataBundle = new Bundle();
                    dataBundle.putString("categoryName", assignment.getCategory());
                    dataBundle.putString("state", assignment.getStatus());
                    dataBundle.putString("id", assignment.getId());

                    Intent intent = new Intent(context, EditRequestAdminActivity.class);

                    intent.putExtras(dataBundle);

                    ((Activity) context).startActivityForResult(intent, 1);
                }
            });
            holder.img_Cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    assignment_list.remove(assignment);
                    notifyItemRemoved(holder.getAdapterPosition());

                    assign.DeleteAssigment(assignment.getAssignmentID());
                }
            });
        }else{
            return;
        }
    }

    @Override
    public int getItemCount() {
        return assignment_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_request_asset_name_layout, txt_request_asset_category_layout, txt_request_asset_date_layout, txt_request_status_layout,
        txt_request_username,
        txt_request_description,
        txt_request_endDate;

        ImageView img_request_edit_layout,img_Cancel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_request_endDate = itemView.findViewById(R.id.txt_request_end_date_layout);
            txt_request_description = itemView.findViewById(R.id.txt_request_description_layout);
            txt_request_username = itemView.findViewById(R.id.txt_request_user_name_layout);
            txt_request_asset_name_layout = itemView.findViewById(R.id.txt_request_asset_name_layout);
            txt_request_asset_category_layout = itemView.findViewById(R.id.txt_request_asset_category_layout);
            txt_request_asset_date_layout = itemView.findViewById(R.id.txt_request_asset_date_layout);
            txt_request_status_layout = itemView.findViewById(R.id.txt_request_status_layout);

            img_request_edit_layout = itemView.findViewById(R.id.img_request_edit_layout);
            img_Cancel = itemView.findViewById(R.id.img_request_cancel_layout);
        }
    }


}
