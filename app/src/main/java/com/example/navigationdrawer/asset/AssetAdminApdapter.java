package com.example.navigationdrawer.asset;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawer.R;

import java.util.List;

import Models.Asset;

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
        }
    }

    @Override
    public int getItemCount() {
        return assetList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_asset_id_layout,txt_asset_name_layout,txt_asset_category_layout,txt_asset_status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_asset_id_layout = itemView.findViewById(R.id.txt_asset_id_layout);
            txt_asset_name_layout = itemView.findViewById(R.id.txt_asset_name_layout);
            txt_asset_category_layout = itemView.findViewById(R.id.txt_asset_category_layout);
            txt_asset_status = itemView.findViewById(R.id.txt_asset_status);
        }
    }
}
