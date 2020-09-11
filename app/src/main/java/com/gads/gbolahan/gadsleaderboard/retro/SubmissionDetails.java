package com.gads.gbolahan.gadsleaderboard.retro;

import com.google.gson.annotations.SerializedName;

public class SubmissionDetails {
    @SerializedName("name")
    public String firstName;
    @SerializedName("lastName")
    public String lastName;
    @SerializedName("emailAddress")
    public String emailAddress;
    @SerializedName("link")
    public String link;

    public SubmissionDetails (String firstName, String lastName, String emailAddress, String link) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.link = link;
    }
}
