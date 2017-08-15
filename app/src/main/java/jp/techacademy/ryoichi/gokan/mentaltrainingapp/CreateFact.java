package jp.techacademy.ryoichi.gokan.mentaltrainingapp;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;

/**
 * Created by houxianliangyi on 2017/08/15.
 */

public interface CreateFact {
    @POST("/facts.json")
    Call<Fact> createFact(
            @Body HashMap<String, Fact>fact
    );
}
