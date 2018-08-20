package com.jayzonsolutions.lunchboxrider.Service;

import com.jayzonsolutions.lunchboxrider.model.Customer;
import com.jayzonsolutions.lunchboxrider.model.Rider;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RiderService {
    @POST("rider/login")
    @FormUrlEncoded
    Call<Rider> riderLogin(@Field("riderEmail") String riderEmail, @Field("riderPassword") String riderPassword , @Field("token") String token);


}
