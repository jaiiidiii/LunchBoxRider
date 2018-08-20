package com.jayzonsolutions.lunchboxrider;

import com.jayzonsolutions.lunchboxrider.Service.APIService;
import com.jayzonsolutions.lunchboxrider.Service.CustomerService;
import com.jayzonsolutions.lunchboxrider.Service.FoodmakerService;
import com.jayzonsolutions.lunchboxrider.Service.OrderService;
import com.jayzonsolutions.lunchboxrider.Service.APIService;
import com.jayzonsolutions.lunchboxrider.Service.CustomerService;
import com.jayzonsolutions.lunchboxrider.Service.FoodmakerService;
import com.jayzonsolutions.lunchboxrider.Service.OrderService;
import com.jayzonsolutions.lunchboxrider.Service.RiderService;

public class ApiUtils {

    public static final String BASE_URL = "http://192.168.0.108:8080/";
//    public static final String BASE_URL = "http://192.168.1.110:8080/";


    private ApiUtils() {
    }

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

     static CustomerService getCustomerService() {
        return RetrofitClient.getClient(BASE_URL).create(CustomerService.class);
    }

     public static FoodmakerService getFoodmakerService() {
        return RetrofitClient.getClient(BASE_URL).create(FoodmakerService.class);
    }
    public static OrderService getOrderService(){
        return  RetrofitClient.getClient(BASE_URL).create(OrderService.class);
    }
    public static RiderService getRiderService(){
        return  RetrofitClient.getClient(BASE_URL).create(RiderService.class);
    }
}