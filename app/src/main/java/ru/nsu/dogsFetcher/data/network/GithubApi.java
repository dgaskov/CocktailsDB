package ru.nsu.dogsFetcher.data.network;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface GithubApi {
//    @GET("/search/users")
//    Single<UserList> getUserList(@Query("q") String filter);
//
//    @GET("/users/{username}/repos")
//    Single<List<Repo>> getRepos(@Path("username") String username);

    @GET("/api/shibes?count=10")
    Single<List<String>> getShibesList();
}
