package com.example.navigationdrawer.view.asset;

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

import com.example.navigationdrawer.model.Asset;
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
        }
        else{
            return;
        }
    }

    @Override
    public int getItemCount() {
        return assetList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_asset_id_layout,txt_asset_name_layout,txt_asset_category_layout,txt_asset_status;
        ImageView img_asset_edit_layout;;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_asset_id_layout = itemView.findViewById(R.id.txt_asset_id_layout);
            txt_asset_name_layout = itemView.findViewById(R.id.txt_asset_name_layout);
            txt_asset_category_layout = itemView.findViewById(R.id.txt_asset_category_layout);
            txt_asset_status = itemView.findViewById(R.id.txt_asset_status);
            img_asset_edit_layout = itemView.findViewById(R.id.img_asset_edit_layout);
        }
    }
}
