package com.example.navigationdrawer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.navigationdrawer.databinding.ActivityAdminProfileBinding;
import com.example.navigationdrawer.databinding.ActivityUserProfileBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;

public class ProfileAdminActivity extends AppCompatActivity {
    ImageView imgView_Back,imgAdmin;
    Button btn_Change_Password;
    ProfileAdminActivity_ModelView profileActivityModelView = new ProfileAdminActivity_ModelView();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAdminProfileBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_admin_profile);
        profileActivityModelView.GetData();
        binding.setProfileAdminActivityModelView(profileActivityModelView);
        AnhXa();
        Handle_Component();
    }

    void AnhXa(){
        imgView_Back = (ImageView)findViewById(R.id.back_profile);
        btn_Change_Password = (Button)findViewById(R.id.changepass);
        imgAdmin = (ImageView) findViewById(R.id.imgAdmin_ProfilePage);
    }

    void Handle_Component(){
        imgView_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileAdminActivity.this, MainAdminActivity.class);
                startActivity(intent);
            }
        });

        btn_Change_Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileAdminActivity.this, ChangePasswordProfileActivity.class);
                startActivity(intent);
            }
        });
        imgAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(ProfileAdminActivity.this)
                        .galleryOnly()
                        .start();
            }
        });

        Uri uri = Uri.parse(profileActivityModelView.getImage());
        imgAdmin.setImageURI(uri);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        profileActivityModelView.setImage(uri.toString());
        Toast.makeText(this, "uri "+uri, Toast.LENGTH_SHORT).show();
        imgAdmin.setImageURI(uri);

    }
}
