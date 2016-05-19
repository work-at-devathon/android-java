package com.example.crypsis.send;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FileUploadInterface {
    @POST("/test")
    Call<RegisterUser> createTask(@Body RegisterUser registerUser);
    @POST("/addUser/")
    Call<InviteTeamMember> invite(@Body InviteTeamMember inviteTeamMember);
    @POST("/customers/phonenumber")
    Call<AddCustomerPhoneNumber> addPhone(@Body AddCustomerPhoneNumber addCustomerPhoneNumber);
}
