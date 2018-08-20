package com.jayzonsolutions.lunchboxrider.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer {

    @SerializedName("customerId")
    @Expose
    private String customerId;
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("customerEmail")
    @Expose
    private String customerEmail;
    @SerializedName("customerPassword")
    @Expose
    private String customerPassword;
    @SerializedName("customerImagePath")
    @Expose
    private String customerImagePath;
    @SerializedName("customerNic")
    @Expose
    private String customerNic;
    @SerializedName("customerPhoneNumber")
    @Expose
    private String customerPhoneNumber;
    @SerializedName("customerAccessType")
    @Expose
    private String customerAccessType;
    @SerializedName("customerAddressId")
    @Expose
    private Address address;
    @SerializedName("customer_RegToken")
    @Expose
    private String customerRegToken;


    public Customer(String customerName, String customerEmail, String customerPassword, String customerNic, String customerPhoneNumber, String customerAccessType, Address address) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPassword = customerPassword;
        this.customerNic = customerNic;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerAccessType = customerAccessType;
        this.address = address;
    }
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerNic() {
        return customerNic;
    }

    public void setCustomerNic(String customerNic) {
        this.customerNic = customerNic;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getCustomerAccessType() {
        return customerAccessType;
    }

    public void setCustomerAccessType(String customerAccessType) {
        this.customerAccessType = customerAccessType;
    }

    public Address getCustomerAddressId() {
        return address;
    }

    public void setCustomerAddressId(Address addressId) {
        this.address = addressId;
    }

    public String getcustomerRegToken() {
        return customerRegToken;
    }

    public void setFoodmakerRegToken(String customerRegToken) {
        this.customerRegToken = customerRegToken;
    }

    public String getCustomerImagePath() {
        return customerImagePath;
    }

    public void setCustomerImagePath(String customerImagePath) {
        this.customerImagePath = customerImagePath;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCustomerRegToken() {
        return customerRegToken;
    }

    public void setCustomerRegToken(String customerRegToken) {
        this.customerRegToken = customerRegToken;
    }
}