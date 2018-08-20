package com.jayzonsolutions.lunchboxrider.Service;

import com.jayzonsolutions.lunchboxrider.model.ApiResponse;
import com.jayzonsolutions.lunchboxrider.model.Customer;
import com.jayzonsolutions.lunchboxrider.model.Order;
import com.jayzonsolutions.lunchboxrider.model.ApiResponse;
import com.jayzonsolutions.lunchboxrider.model.Customer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CustomerService {

    @POST("customer/login")
    @FormUrlEncoded
    Call<Customer> customerLogin(@Field("customerEmail") String customerEmail, @Field("customerPassword") String customerPassword);

    @POST("customer/signup")
    Call<ApiResponse> customerSignup(@Body Customer customer);


}
