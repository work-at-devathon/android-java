package com.example.crypsis.send;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
EditText e1,e2,e3,e4,e5;
    String fullname,email,countrycode,password;
    int phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);

    }
    public void send(View v) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.135:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        e3=(EditText)findViewById(R.id.editText3);
        e4=(EditText)findViewById(R.id.editText4);
        e5=(EditText)findViewById(R.id.editText5);
        fullname=e1.getText().toString();
        email=e2.getText().toString();
        countrycode=e3.getText().toString();
        phone=Integer.parseInt(e4.getText().toString());
        password=e5.getText().toString();

        RegisterUser registerUser=new RegisterUser(fullname,email,countrycode,phone,password);
        FileUploadService fileUploadService=retrofit.create(FileUploadService.class);

                Call<RegisterUser> call = fileUploadService.createTask(registerUser);
       call.enqueue(new Callback<RegisterUser>() {
           @Override
           public void onResponse(Call<RegisterUser> call, Response<RegisterUser> response) {
               Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();
           }

           @Override
           public void onFailure(Call<RegisterUser> call, Throwable t) {
               Toast.makeText(getApplicationContext(),"fail",Toast.LENGTH_SHORT).show();
           }
       });

    }
}
