package com.example.navigationdrawer.view.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawer.R;

import java.util.List;

import com.example.navigationdrawer.model.User;

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
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_staff_id_layout,txt_staff_name_layout,txt_staff_join_date_layout,txt_staff_type_layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_staff_id_layout = itemView.findViewById(R.id.txt_staff_id_layout);
            txt_staff_name_layout = itemView.findViewById(R.id.txt_staff_name_layout);
            txt_staff_join_date_layout = itemView.findViewById(R.id.txt_staff_join_date_layout);
            txt_staff_type_layout = itemView.findViewById(R.id.txt_staff_type_layout);
        }
    }
}
