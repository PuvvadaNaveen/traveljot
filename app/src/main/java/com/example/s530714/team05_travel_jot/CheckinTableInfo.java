package com.example.s530714.team05_travel_jot;

/**
 * Created by S530714 on 4/3/2018.
 */

public class CheckinTableInfo {

    String Name;
    String EmailID;
    String PersonalNotes;
    String Checkin_Type;
    String Tagged_Email_id;
    String Location;
    double Latitude;
    double Longitude;
    String Datevalue;

    public CheckinTableInfo() {

    }

    public CheckinTableInfo(String name, String emailID, String personalNotes, String checkin_Type, String tagged_Email_id, String location, double latitude,double longitude, String datevalue) {
        Name = name;
        EmailID = emailID;
        PersonalNotes = personalNotes;
        Checkin_Type = checkin_Type;
        Tagged_Email_id = tagged_Email_id;
        Location = location;
        Latitude = latitude;
        Longitude = longitude;
        Datevalue = datevalue;

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmailID() {
        return EmailID;
    }

    public void setEmailID(String emailID) {
        EmailID = emailID;
    }

    public String getPersonalNotes() {
        return PersonalNotes;
    }

    public void setPersonalNotes(String personalNotes) {
        PersonalNotes = personalNotes;
    }

    public String getCheckin_Type() {
        return Checkin_Type;
    }

    public void setCheckin_Type(String checkin_Type) {
        Checkin_Type = checkin_Type;
    }

    public String getTagged_Email_id() {
        return Tagged_Email_id;
    }

    public void setTagged_Email_id(String tagged_Email_id) {
        Tagged_Email_id = tagged_Email_id;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public String getDatevalue() {
        return Datevalue;
    }

    public void setDatevalue(String datevalue) {
        Datevalue = datevalue;
    }

    @Override
    public String toString() {
        return "CheckinTableInfo{" +
                "Name='" + Name + '\'' +
                ", EmailID='" + EmailID + '\'' +
                ", PersonalNotes='" + PersonalNotes + '\'' +
                ", Checkin_Type='" + Checkin_Type + '\'' +
                ", Tagged_Email_id='" + Tagged_Email_id + '\'' +
                ", Location='" + Location + '\'' +
                ", Latitude=" + Latitude +
                ", Longitude=" + Longitude +
                ", Date=" + Datevalue +
                '}';
    }
}
