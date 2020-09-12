package com.gads.gbolahan.gadsleaderboard.retro;

import com.google.gson.annotations.SerializedName;

public class SubmissionDetails {
    @SerializedName("entry.1877115667")
    public String firstName;
    @SerializedName("entry.2006916086")
    public String lastName;
    @SerializedName("entry.1824927963")
    public String emailAddress;
    @SerializedName("entry.284483984")
    public String projectLink;

    public SubmissionDetails (String firstName, String lastName, String emailAddress, String projectLink) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.projectLink = projectLink;
    }
}
