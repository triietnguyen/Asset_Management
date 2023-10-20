package com.example.navigationdrawer.assignment;

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

public class AssignmentApdapter extends RecyclerView.Adapter<AssignmentApdapter.ViewHolder> {

    Context context;
    List<Assignment> assignmentList;

    public AssignmentApdapter(Context context, List<Assignment> assignmentList) {
        this.context = context;
        this.assignmentList = assignmentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_management_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(assignmentList != null && assignmentList.size() > 0){
            Assignment assignment = assignmentList.get(position);
            holder.txt_id.setText(assignment.getAsset_code());
            holder.txt_asset.setText(assignment.getAsset_name());
            holder.txt_category.setText(assignment.getCategory());
            holder.txt_date.setText(assignment.getAssigned_date());
            holder.txt_state.setText(assignment.getStatus());
        }
    }

    @Override
    public int getItemCount() {
        return assignmentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_id,txt_asset,txt_category,txt_date,txt_state;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_id = itemView.findViewById(R.id.txt_id);
            txt_asset = itemView.findViewById(R.id.txt_asset);
            txt_category = itemView.findViewById(R.id.txt_category);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_state = itemView.findViewById(R.id.txt_state);
        }
    }
}
