package com.example.blooddonation;

public class Recievers {

    String id;
    String name1;
    String blood_grp1;
    String location1;
    String contact_r;
    String email_r;

    public String getContact_r() {
        return contact_r;
    }

    public void setContact_r(String contact_r) {
        this.contact_r = contact_r;
    }


    public String getEmail_r() {
        return email_r;
    }

    public void setEmail_r(String email_r) {
        this.email_r = email_r;
    }


    public Recievers(){}
    public Recievers(String name1,String blood_grp1,String location1,String email_r,String contact_r){
        this.name1 = name1;
        this.email_r=email_r;
        this.blood_grp1 = blood_grp1;
        this.location1 = location1;
        this.contact_r=contact_r;
    }
    public String getName() {
        return this.name1;
    }

    public void setName(String name) {
        this.name1 = name1;
    }

    public String getBlood_grp(){return blood_grp1;}

    public String getLocation(){return location1;}

}
