package com.jayzonsolutions.lunchboxrider.Service;

import com.jayzonsolutions.lunchboxrider.model.ApiResponse;
import com.jayzonsolutions.lunchboxrider.model.Foodmaker;
import com.jayzonsolutions.lunchboxrider.model.FoodmakerDishes;
import com.jayzonsolutions.lunchboxrider.model.ApiResponse;
import com.jayzonsolutions.lunchboxrider.model.Foodmaker;
import com.jayzonsolutions.lunchboxrider.model.FoodmakerDishes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FoodmakerService {

    @GET("foodmaker/foodmakers-list")
    Call<List<Foodmaker>> getFoodmakerList();

    @GET("foodmaker/foodmakers-nearBy-list")
    Call<List<Foodmaker>> getFoodmakerListNearBy1(@Query("miles") Integer miles, @Query("lat") Double lat, @Query("longt") Double longt);

    @GET("foodmaker_dishes/foodmakersdishes-list-byfoodmakerid")
    Call<List<FoodmakerDishes>> getDishesByFoodmakerId(@Query("foodmakerId") Integer foodmakerId);

    @GET("foodmaker/foodmakers-nearBy-list")
    Call<List<Foodmaker>> getFoodmakerListNearBy(@Query("lat") Double lat, @Query("longt") Double longt);

    @POST("foodmaker/set-ratings")
    Call<ApiResponse> setRatings(@Query("customerId") Integer customerId, @Query("foodmakerId") Integer foodmakerId, @Query("stars") Integer stars);

}
