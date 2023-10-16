package com.example.navigationdrawer.profile;

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

import com.example.navigationdrawer.databinding.ActivityUserProfileBinding;
import com.example.navigationdrawer.main.MainActivity;
import com.example.navigationdrawer.R;
import com.github.dhaval2404.imagepicker.ImagePicker;

import ViewModels.User.ProfileActivity_ModelView;


public class ProfileActivity extends AppCompatActivity {
    ImageView imgView_Back,img_User;
    Button btn_Change_Password;
    ProfileActivity_ModelView profileActivityModelView = new ProfileActivity_ModelView();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUserProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_user_profile);
        profileActivityModelView.GetData();
        binding.setProfileActivityModelView(profileActivityModelView);
        AnhXa();
        Handle_Component();
    }

    void AnhXa(){
        imgView_Back = (ImageView)findViewById(R.id.back_profile);
        img_User = (ImageView) findViewById(R.id.imgUser_ProfilePage);
        btn_Change_Password = (Button)findViewById(R.id.changepass);
    }

    void Handle_Component(){
        imgView_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        img_User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(ProfileActivity.this)
                        .galleryOnly()
                        .start();
            }
        });

        btn_Change_Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ChangePasswordProfileActivity.class);
                startActivity(intent);
            }
        });
        if(profileActivityModelView.getImage() != null){
            Uri uri = Uri.parse(profileActivityModelView.getImage());
            img_User.setImageURI(uri);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        profileActivityModelView.setImage(uri.toString());
        Toast.makeText(this, "uri "+uri, Toast.LENGTH_SHORT).show();
        img_User.setImageURI(uri);

    }
}
