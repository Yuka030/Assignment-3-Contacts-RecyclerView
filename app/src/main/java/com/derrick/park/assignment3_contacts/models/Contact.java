package com.derrick.park.assignment3_contacts.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Contact implements Parcelable, Comparable<Contact> {
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("name")
    @Expose
    private Name name;
//    @SerializedName("location")
//    @Expose
//    private Location location;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("cell")
    @Expose
    private String cell;

    public Contact(Name name, String cell){
        this.name = name;
        this.cell = cell;
        this.email = "test@test.com";
    }

    public Contact(Parcel in) {
        gender = in.readString();
        email = in.readString();
        cell = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public String getGender() {
        return gender;
    }

    public Name getName() {
        return name;
    }

//    public Location getLocation() {
//        return location;
//    }

    public String getEmail() {
        return email;
    }

    public String getCell() {
        return cell;
    }

    @Override
    public String toString() {
        //return String.format("%n%s%n%s%n%s%n%s", name, location, email, cell);
        return String.format("%n%s%n%s%n%s", name, email, cell);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(gender);
        parcel.writeString(email);
        parcel.writeString(cell);
    }

    @Override
    public int compareTo(Contact contact) {
        return this.name.compareTo(contact.name);
    }

    /**
     * Name {first: , last: }
     */
    public static class Name implements Parcelable, Comparable<Name>{
        @SerializedName("first")
        @Expose
        private String first;
        @SerializedName("last")
        @Expose
        private String last;

        public Name(String first, String last){
            this.first = first;
            this.last = last;
        }

        public Name(Parcel in) {
            first = in.readString();
            last = in.readString();
        }

        public final Creator<Name> CREATOR = new Creator<Name>() {
            @Override
            public Name createFromParcel(Parcel in) {
                return new Name(in);
            }

            @Override
            public Name[] newArray(int size) {
                return new Name[size];
            }
        };

        public String getFirst() {
            return first;
        }

        public String getLast() {
            return last;
        }

        @Override
        public String toString() {
            return first + " " + last;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(first);
            parcel.writeString(last);
        }

        @Override
        public int compareTo(Name name) {
            return this.first.compareToIgnoreCase(name.first);
        }
    }

    /**
     * Location {street: , city: , state: , postcode: }
     */
    class Location {
        @SerializedName("street")
        @Expose
        private String street;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("state")
        @Expose
        private String province;
        @SerializedName("postcode")
        @Expose
        private String postcode;

        public String getStreet() {
            return street;
        }

        public String getCity() {
            return city;
        }

        public String getProvince() {
            return province;
        }

        public String getPostcode() {
            return postcode;
        }

        @Override
        public String toString() {
            return street + ", " + city + ", " + province + " Canada " + postcode;
        }
    }
}

