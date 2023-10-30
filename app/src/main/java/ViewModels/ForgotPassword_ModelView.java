package ViewModels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.navigationdrawer.BR;
import com.example.navigationdrawer.forgot_password.ChangPasswordForgetActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Models.JavaMailAPI.JavaMailAPI;
import Models.MyApplication;

public class ForgotPassword_ModelView extends BaseObservable {
    private String email;
    private String code;

    private List<Integer> listNumberRandom = new ArrayList<Integer>();

    @Bindable
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email); // BR.email được sinh ra tự động bởi Data Binding
    }

    @Bindable
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
        notifyPropertyChanged(BR.code); // BR.email được sinh ra tự động bởi Data Binding
    }

    public void OnClickButtonEmail(Context context){
        SendMail(context);
    }

    public void OnClickButtonOTP(Context context){
        HandleForgotPassword(context);
    }
    public void HandleForgotPassword(Context context){
        boolean isValid_SMS = OnValid_OTP(listNumberRandom,code);
        if(isValid_SMS){
            Intent intent = new Intent(context, ChangPasswordForgetActivity.class);
            context.startActivity(intent);
            ((Activity) context).finish();
        }
        else{
            Toast.makeText(context, "OTP is not success, please try again ", Toast.LENGTH_SHORT).show();
        }
    }
    public void SendMail(Context context){
        if(TextUtils.isEmpty(email)){
            return;
        }
        else if(!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            listNumberRandom.clear();
            listNumberRandom = HandleOtp_SMS();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    listNumberRandom.clear();
                }
            }, 30000);

            JavaMailAPI java = new JavaMailAPI(context,email.trim(),listNumberRandom.toString());
            java.execute();

            MyApplication.getInstance().SetSharedData(email.trim());
        }

    }
    public List<Integer> HandleOtp_SMS(){
        List<Integer> numberRandom = new ArrayList<Integer>();
        Random random = new Random();
        int idxRandom ;
        for(int i=0;i<6;i++){
            idxRandom = random.nextInt(10);
            numberRandom.add((idxRandom));
        }
        return numberRandom;
    }

    public boolean OnValid_OTP(List<Integer> numberRandom, String numberOfUser){
        if(numberRandom.size() == 0){
            return false;
        }

        String str_numberRandom = "";
        for(int i=0;i<numberRandom.size();i++){
            str_numberRandom = str_numberRandom + numberRandom.get(i);
        }
        if(str_numberRandom.trim().equalsIgnoreCase(numberOfUser.trim())){
            Log.e("123","Correct");
            return true;
        }
        Log.e("123","User : "+numberOfUser.trim());
        Log.e("123","Random : "+str_numberRandom.trim());
        Log.e("123","InCorrect");
        return false;
    }

}
