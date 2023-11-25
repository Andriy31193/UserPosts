package edu.itstep.myapplic09;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("/posts/{id}")
    Call<Post> getPostById(@Path("id") int id);

    @GET("/posts")
    Call<List<Post>>getAll();

    @GET("/users/{id}")
    Call<User> getUserById(@Path("id") int id);

}
