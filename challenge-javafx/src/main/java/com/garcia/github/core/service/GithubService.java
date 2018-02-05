package com.garcia.github.core.service;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Class responsible for GithubApi requests using Retrofit
 * 
 * @author Luis Garcia
 * @version 1.0
 * @since 21/01/2018
 *
 */
@Service
public interface GithubService {

	public static final String BASE_URL = "https://api.github.com/";
	
	@GET("search/repositories")
	public Call<JsonElement> listRepos(@Query("q") String language, @Query("sort") String sort, @Query("page") int page);
	
	@GET("repos/{owner}/{repo}/pulls")
	public Call<JsonElement> listPullRequests(@Path("owner") String login, @Path("repo") String repo);
	
	@GET("users/{login}")
	public Call<JsonElement> getUserInfo(@Path("login") String login);

}
