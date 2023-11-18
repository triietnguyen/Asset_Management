package com.example.navigationdrawer.view.user;

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

import com.example.navigationdrawer.model.User;
import com.example.navigationdrawer.view.asset.edit.EditAssetActivity;
import com.example.navigationdrawer.view.user.edit.EditUserActivity;

public class UserAdminApdapter extends RecyclerView.Adapter<UserAdminApdapter.ViewHolder> {

    Context context;
    List<User> userList;

    public UserAdminApdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_staff_layout_admin, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(userList != null && userList.size() > 0){
            User user = userList.get(position);
            holder.txt_staff_id_layout.setText(user.getId());
            holder.txt_staff_name_layout.setText(user.getName());
            holder.txt_staff_join_date_layout.setText(user.getJoined_date());
            holder.txt_staff_type_layout.setText(user.getRole_id());
            holder.img_staff_edit_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle dataBundle = new Bundle();
                    dataBundle.putString("User_id", user.getId());
                    dataBundle.putString("Email", user.getEmail());
                    dataBundle.putString("Password", user.getPassword());
                    dataBundle.putString("Fullname", user.getName());
                    dataBundle.putString("Address", user.getAddress());
                    dataBundle.putString("Date_of_birth", user.getDate_of_birth());
                    dataBundle.putString("Gender_id", user.getGender());
                    dataBundle.putString("Joined_Date", user.getJoined_date());
                    dataBundle.putString("Image", user.getImage());
                    dataBundle.putString("Role_id", user.getRole_id());
                    dataBundle.putString("Phone", user.getPhone());
                    Intent intent = new Intent(context, EditUserActivity.class);
                    intent.putExtras(dataBundle);
                    ((Activity) context).startActivityForResult(intent, 1);
                }
            });
        }
        else{
            return;
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_staff_id_layout,txt_staff_name_layout,txt_staff_join_date_layout,txt_staff_type_layout;
        ImageView img_staff_edit_layout;;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_staff_id_layout = itemView.findViewById(R.id.txt_staff_id_layout);
            txt_staff_name_layout = itemView.findViewById(R.id.txt_staff_name_layout);
            txt_staff_join_date_layout = itemView.findViewById(R.id.txt_staff_join_date_layout);
            txt_staff_type_layout = itemView.findViewById(R.id.txt_staff_type_layout);
            img_staff_edit_layout = itemView.findViewById(R.id.img_staff_edit_layout);
        }
    }
}
