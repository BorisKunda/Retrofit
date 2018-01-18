package com.example.jbt.retrofit;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;


/**
 * Created by jbt on 08/12/2017.
 */

public interface GitHubService {

    //https://api.github.com/users?since=180
    //https://api.github.com/-base URL will be given in MainActivity
    // users- API link
    //since=180 - Query String  can be changed.
    @GET("users")
    Call<List<User>> getAllUsers(@Query("since") String usersSinceID);

   // String tellMyName(String name);


}
