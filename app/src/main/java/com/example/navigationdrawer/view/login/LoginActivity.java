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

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.navigationdrawer.BR;


import com.example.navigationdrawer.model.MyApplication;
import com.example.navigationdrawer.model.User;
import com.example.navigationdrawer.view.forgot_password.ForgotPasswordActivity;
import com.example.navigationdrawer.R;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.navigationdrawer.view.homepage.HomePageActivity;
import com.example.navigationdrawer.view.main.MainActivity;
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
    ;
    Connection connect;
    String ConnectionResult = "";
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
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            goToHome();
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
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


    public boolean OnValid_OTP(List<Integer> numberRandom, String numberOfUser) {
        String str_numberRandom = "";
        for (int i = 0; i < numberRandom.size(); i++) {
            str_numberRandom = str_numberRandom + numberRandom.get(i);
        }
        if (str_numberRandom.trim().equalsIgnoreCase(numberOfUser.trim())) {
            Log.e("123", "Correct");
            return true;
        }
        Log.e("123", "User : " + numberOfUser.trim());
        Log.e("123", "Random : " + str_numberRandom.trim());
        Log.e("123", "InCorrect");
        return false;
    }

    public List<Integer> HandleOtp_SMS() {
        List<Integer> numberRandom = new ArrayList<Integer>();
        Random random = new Random();
        int idxRandom;
        for (int i = 0; i < 6; i++) {
            idxRandom = random.nextInt(10);
            numberRandom.add((idxRandom));
        }
        return numberRandom;
    }

}

