package com.newjerseysoftware.hederaDemo.model;

public class Test {
    String user;
    String vendor;
    int fee;

public Test(String user, String vendor, int fee) {
    this.user = user;
    this.vendor = vendor;
    this.fee = fee;

}public Test(){}
public void setUser(String user){
    this.user=user;
}
public void setVendor(String vendor){
    this.vendor=vendor;

}
public void setFee(int fee){
    this.fee=fee;
}
public int getFee(){
    return this.fee;
}
public String getUser(){
    return this.user;
}
public String getVendor(){
    return this.vendor;
 }

}