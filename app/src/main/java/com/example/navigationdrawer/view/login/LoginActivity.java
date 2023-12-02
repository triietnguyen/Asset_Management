package com.example.navigationdrawer.view.login;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.navigationdrawer.BR;


import com.example.navigationdrawer.model.MyApplication;
import com.example.navigationdrawer.model.User;
import com.example.navigationdrawer.view.forgot_password.ForgotPasswordActivity;
import com.example.navigationdrawer.R;


import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.example.navigationdrawer.view.homepage.HomePageActivity;
import com.example.navigationdrawer.view.main.MainActivity;
import com.example.navigationdrawer.view.main.MainAdminActivity;
import com.example.navigationdrawer.viewmodel.Login_ModelView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;


public class LoginActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 1000;
    Button googleSignIn;
    TextView txt_Forgot;
    private Login_ModelView loginModelView = new Login_ModelView();
    GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewDataBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityMainBinding.setVariable(BR.Login_ModelView, loginModelView);
        AnhXa();
        Handle_Component();
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }
    private void handleSignInResult(@NonNull Task<GoogleSignInAccount> completedTask) {
        try {
            // Lấy ngày tháng năm hiện tại
            LocalDateTime ngayThangNamHienTai = LocalDateTime.now();
            // Định dạng ngày tháng năm
            DateTimeFormatter dinhDang = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String idToken = account.getIdToken();
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            String personEmail = acct.getEmail();
            String personName = acct.getDisplayName();
            String currentDate = ngayThangNamHienTai.format(dinhDang);
            MyApplication.getInstance().SetSharedData(personEmail.trim());
            User user = new User();
            if(user.isEmailInDatabase(personEmail)==0){
                user = new User(null,personEmail,null,personName,null,null,"1",currentDate,null,"2",null);
                user.AddUser();
                goToHome();
            }
            else{
                goToHome();
            }
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "handleSignInResult:error", e);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null){
            goToHome();
        }
    }

    public void goToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void AnhXa() {
        txt_Forgot = (TextView) findViewById(R.id.txt_ForgotPassword_LoginPage);
        googleSignIn = (Button) findViewById(R.id.sign_in_button);
    }

    public void Handle_Component() {
        txt_Forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
        googleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignIn();
            }
        });

    }

    public void goToSignIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

}

