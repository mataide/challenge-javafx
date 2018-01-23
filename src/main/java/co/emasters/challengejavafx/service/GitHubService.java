package co.emasters.challengejavafx.service;

import co.emasters.challengejavafx.model.GitHubPullRequest;
import co.emasters.challengejavafx.model.GitHubRepoPage;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Class description here.
 *
 * author: Olavo.
 * version: 0.1
 */
public interface GitHubService {

  @GET("/search/repositories")
  Call<GitHubRepoPage> listRepos(@Query("q") String language, @Query("sort") String sort,
      @Query("page") Integer page);

  @GET("/repos/{owner}/{repository}/pulls")
  Call<List<GitHubPullRequest>> listPullRequests(@Path("owner") String owner, @Path("repository") String repository);
}
