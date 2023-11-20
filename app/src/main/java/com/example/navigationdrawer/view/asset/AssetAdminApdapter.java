package com.example.navigationdrawer.view.asset;

import static java.lang.String.*;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawer.R;

import java.util.List;

import com.example.navigationdrawer.model.Asset;
import com.example.navigationdrawer.model.MyApplication;
import com.example.navigationdrawer.view.asset.edit.EditAssetActivity;
import com.example.navigationdrawer.view.assignment.edit.EditAssignmentActivity;

public class AssetAdminApdapter extends RecyclerView.Adapter<AssetAdminApdapter.ViewHolder> {

    Context context;
    List<Asset> assetList;

    public AssetAdminApdapter(Context context, List<Asset> assetList) {
        this.context = context;
        this.assetList = assetList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_asset_layout_admin, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Asset ast = new Asset();
        if(assetList != null && assetList.size() > 0){
            Asset asset = assetList.get(position);
            holder.txt_asset_id_layout.setText(asset.getAsset_id());
            holder.txt_asset_name_layout.setText(asset.getAsset_name());
            holder.txt_asset_category_layout.setText(asset.getAsset_category());
            holder.txt_asset_status.setText(asset.getStatus());
            holder.img_asset_edit_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle dataBundle = new Bundle();
                    dataBundle.putString("Asset_id", asset.getAsset_id());
                    dataBundle.putString("Asset_Name", asset.getAsset_name());
                    dataBundle.putString("Category_id", asset.getAsset_category());
                    dataBundle.putString("Status", asset.getStatus());
                    Intent intent = new Intent(context, EditAssetActivity.class);
                    intent.putExtras(dataBundle);
                    ((Activity) context).startActivityForResult(intent, 1);
                }
            });

            holder.img_asset_delete_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int gravity = Gravity.CENTER;
                    final Dialog dialog = new Dialog(context);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.layout_diaglog);

                    Window window = dialog.getWindow();
                    if(window == null){
                        return;
                    }
                    window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    WindowManager.LayoutParams windowAttributes = window.getAttributes();
                    windowAttributes.gravity = gravity;
                    window.setAttributes(windowAttributes);

                    if(Gravity.BOTTOM ==gravity){
                        dialog.setCancelable(true);
                    }
                    else{
                        dialog.setCancelable(false);
                    }

                    Button No = dialog.findViewById(R.id.No);
                    Button Yes = dialog.findViewById(R.id.Yes);

                    No.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    Yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show();
                            ast.HandlerAsset(asset.getAsset_id());
                            dialog.dismiss();
                            Intent intent = new Intent(context, AssetAdminActivity.class);
                            ((Activity) context).startActivityForResult(intent, 1);
                        }
                    });

                    dialog.show();
                }
            });
        }
        else{
            return;
        }
    }

    private void openDialog(int gravity){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_diaglog);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if(Gravity.BOTTOM ==gravity){
            dialog.setCancelable(true);
        }
        else{
            dialog.setCancelable(false);
        }

        Button No = dialog.findViewById(R.id.No);
        Button Yes = dialog.findViewById(R.id.Yes);

        No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }
    @Override
    public int getItemCount() {
        return assetList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_asset_id_layout,txt_asset_name_layout,txt_asset_category_layout,txt_asset_status;
        ImageView img_asset_edit_layout, img_asset_delete_layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_asset_id_layout = itemView.findViewById(R.id.txt_asset_id_layout);
            txt_asset_name_layout = itemView.findViewById(R.id.txt_asset_name_layout);
            txt_asset_category_layout = itemView.findViewById(R.id.txt_asset_category_layout);
            txt_asset_status = itemView.findViewById(R.id.txt_asset_status);
            img_asset_edit_layout = itemView.findViewById(R.id.img_asset_edit_layout);
            img_asset_delete_layout = itemView.findViewById(R.id.img_asset_delete_layout);
        }
    }
}
