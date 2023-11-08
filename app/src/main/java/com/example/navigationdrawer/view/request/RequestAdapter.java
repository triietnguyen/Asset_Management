package com.example.navigationdrawer.view.request;

import android.content.Context;
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

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {

    Context context;
    List<Assignment> assignment_list;


    public RequestAdapter(Context context, List<Assignment> assignment_list) {
        this.context = context;
        this.assignment_list = assignment_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_request_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User u = new User();
        Assignment assign = new Assignment();
        if(assignment_list !=null && assignment_list.size()>0){
            Assignment assignment = assignment_list.get(position);
            holder.txt_request_asset_category.setText(assignment.getCategory());
            holder.txt_request_asset_date.setText(assignment.getAssigned_date());
            holder.txt_request_status_description.setText(assignment.getDescription());
            holder.txt_request_status.setText(assignment.getStatus());
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
        TextView txt_request_asset_category, txt_request_asset_date, txt_request_status, txt_request_status_description;

        ImageView img_Cancel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_request_asset_category = itemView.findViewById(R.id.txt_request_asset_category_layout);
            txt_request_asset_date = itemView.findViewById(R.id.txt_request_asset_date_layout);
            txt_request_status_description = itemView.findViewById(R.id.txt_request_asset_decription_layout);
            txt_request_status = itemView.findViewById(R.id.txt_request_status_layout);
            img_Cancel = itemView.findViewById(R.id.img_request_cancel_layout);
        }
    }
}
