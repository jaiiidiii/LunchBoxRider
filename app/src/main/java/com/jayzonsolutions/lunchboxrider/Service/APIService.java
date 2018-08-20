package com.jayzonsolutions.lunchboxrider.Service;

import com.jayzonsolutions.lunchboxrider.model.ApiResponse;
import com.jayzonsolutions.lunchboxrider.model.Customer;
import com.jayzonsolutions.lunchboxrider.model.Customer;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {

    /*@POST("customer/login")
    @FormUrlEncoded
    Call<ApiResponse> savePost(@Field("customerEmail") String customerEmail, @Field("customerPassword") String customerPassword);
*/
    @POST("customer/login")
    @FormUrlEncoded
    Call<Customer> savePost(@Field("customerEmail") String customerEmail, @Field("customerPassword") String customerPassword
            , @Field("token") String token);

}
