//package com.example.crypsis.send;
//
//import android.os.Bundle;
//import android.support.v7.app.ActionBar;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class MainActivity extends AppCompatActivity {
//EditText e1,e2,e3,e4,e5;
//    String name,email,countryCode,password;
//    int phoneNumber;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ActionBar actionBar=getSupportActionBar();
//        actionBar.setLogo(R.drawable.s);
//        actionBar.setDisplayUseLogoEnabled(true);
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(true);
//
////        FacebookSdk.sdkInitialize(getApplicationContext());
////        AppEventsLogger.activateApp(this);
//
//    }
//    public void send(View v) {
//
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.0.125:8081/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        e1=(EditText)findViewById(R.id.editText);
//        e2=(EditText)findViewById(R.id.editText2);
//        phoneNumber=Integer.parseInt(e4.getText().toString());
//        password=e5.getText().toString();
//        e3=(EditText)findViewById(R.id.editText3);
//        e4=(EditText)findViewById(R.id.editText4);
//        e5=(EditText)findViewById(R.id.editText5);
//        name=e1.getText().toString();
//        email=e2.getText().toString();
//        countryCode=e3.getText().toString();
//
//        RegisterUser registerUser=new RegisterUser(name,email,countryCode,phoneNumber,password);
//        FileUploadInterface fileUploadService=retrofit.create(FileUploadInterface.class);
//
//                Call<RegisterUser> call = fileUploadService.createTask(registerUser);
//       call.enqueue(new Callback<RegisterUser>() {
//           @Override
//           public void onResponse(Call<RegisterUser> call, Response<RegisterUser> response) {
//               Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();
//           }
//
//           @Override
//           public void onFailure(Call<RegisterUser> call, Throwable t) {
//               Toast.makeText(getApplicationContext(),"fail",Toast.LENGTH_SHORT).show();
//           }
//       });
//
//    }
//}
