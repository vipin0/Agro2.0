package com.crazydeveloper.agro20;

class Address{
    String addressString;
    String landmark;
    String streetName;
    String city_town;
    String pincode;
    String stateName;

    public Address(String address, String landmark, String streetName, String city_town, String pincode, String stateName) {
        this.addressString = address;
        this.landmark = landmark;
        this.streetName = streetName;
        this.city_town = city_town;
        this.pincode = pincode;
        this.stateName = stateName;
    }
}

public class User{
    String fname;
    String lname;
    Address address;



    public User(String fname, String lname, String streetName, String city_town, String pincode, String stateName, String landmark,String addressStr) {
        address = new Address(addressStr,landmark,streetName,city_town,pincode,stateName);
        this.fname = fname;
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getStreetName() {
        return address.streetName;
    }

    public String getCity_town() {
        return address.city_town;
    }

    public String getPincode() {
        return address.pincode;
    }

    public String getStateName() {
        return address.stateName;
    }

    public String getLandmark() {
        return address.landmark;
    }

}
