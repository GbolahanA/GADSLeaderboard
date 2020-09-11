package com.gads.gbolahan.gadsleaderboard;

import com.gads.gbolahan.gadsleaderboard.retro.Learner;
import com.gads.gbolahan.gadsleaderboard.retro.SkillIQ;
import com.gads.gbolahan.gadsleaderboard.retro.SubmissionDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {

    @GET("/api/hours")
    Call<List<Learner>> getLearners();

    @GET("/api/skilliq")
    Call<List<SkillIQ>> getSkillIQ();

    @FormUrlEncoded
    @POST("/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    Call<SubmissionDetails> submit(@Field("entry.1877115667") String firstName,
                                   @Field("entry.2006916086") String lastName,
                                   @Field("entry.1824927963") String emailAddress,
                                   @Field("entry.284483984") String link);
}