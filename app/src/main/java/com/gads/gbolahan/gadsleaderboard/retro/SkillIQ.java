package com.gads.gbolahan.gadsleaderboard.retro;

import com.google.gson.annotations.SerializedName;

public class SkillIQ {

    @SerializedName("name")
    private String name;
    @SerializedName("score")
    private String score;
    @SerializedName("country")
    private String country;
    @SerializedName("badgeUrl")
    private String badgeUrl;

    public SkillIQ (String name, String score, String country, String badgeUrl) {
        this.name = name;
        this.score = score;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public String getName () {
        return name;
    }

    public String getScore () {
        return score;
    }

    public String getCountry () {
        return country;
    }

    public String getBadgeUrl () {
        return badgeUrl;
    }
}
