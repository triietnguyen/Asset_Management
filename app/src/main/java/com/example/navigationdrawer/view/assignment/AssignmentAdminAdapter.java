package com.example.navigationdrawer.view.assignment;

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

public class AssignmentAdminAdapter extends RecyclerView.Adapter<AssignmentAdminAdapter.ViewHolder> {

    Context context;
    List<Assignment> assignment_list;

    public AssignmentAdminAdapter(Context context, List<Assignment> assignment_list) {
        this.context = context;
        this.assignment_list = assignment_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_assignment_layout_admin,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(assignment_list !=null && assignment_list.size()>0){
            Assignment assignment = assignment_list.get(position);
            holder.txt_id_admin.setText(assignment.getId());
            holder.txt_asset_id_admin.setText(assignment.getAsset_code());
            holder.txt_asset_name_admin.setText(assignment.getAsset_name());
            holder.txt_category_name_admin.setText(assignment.getCategory());
            holder.txt_assigned_to_admin.setText(assignment.getAssigned_to());
            holder.txt_assigned_by_admin.setText(assignment.getAssigned_by());
            holder.txt_assigned_date_admin.setText(assignment.getAssigned_date());
            holder.txt_endDate_admin.setText(assignment.getEndDate());
            holder.txt_state_admin.setText(assignment.getStatus());

//            holder.img_request_update.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
        }else{
            return;
        }
    }

    @Override
    public int getItemCount() {
        return assignment_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_id_admin, txt_asset_id_admin, txt_asset_name_admin, txt_assigned_to_admin, txt_assigned_by_admin, txt_assigned_date_admin,txt_state_admin;
        TextView txt_category_name_admin,txt_endDate_admin;
        ImageView img_request_update;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_id_admin = itemView.findViewById(R.id.txt_id_admin);
            txt_asset_id_admin = itemView.findViewById(R.id.txt_asset_id_admin);
            txt_asset_name_admin = itemView.findViewById(R.id.txt_asset_name_admin);
            txt_category_name_admin = itemView.findViewById(R.id.txt_category_name_admin);
            txt_assigned_to_admin = itemView.findViewById(R.id.txt_assigned_to_admin);
            txt_assigned_by_admin = itemView.findViewById(R.id.txt_assigned_by_admin);
            txt_assigned_date_admin = itemView.findViewById(R.id.txt_assigned_date_admin);
            txt_endDate_admin = itemView.findViewById(R.id.txt_end_date_admin);
            txt_state_admin = itemView.findViewById(R.id.txt_state_admin);


//            img_request_update = itemView.findViewById(R.id.img_request_check_layout);
        }
    }
}
