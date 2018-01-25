package co.emasters.challengejavafx.service.api;

import co.emasters.challengejavafx.model.GitHubPullRequest;
import co.emasters.challengejavafx.model.GitHubRepoPage;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * RetroFit API interface for GitHub calls.
 *
 * author: Olavo Holanda
 * version: 0.1
 */
public interface GitHubService {

  /**
   * Calls GitHub API to query GitHub repositories based on the language, sort, page and page size.
   *
   * @param language string with repository language, eg 'language:Java'
   * @param sort string with the sorting type, eg 'stars'
   * @param page the page number
   * @param perPage amount of repositories per page
   * @return a <code>Call<GitHubRepoPage></code> if successful, contains a page
   */
  @GET("/search/repositories")
  Call<GitHubRepoPage> listRepos(@Query("q") String language, @Query("sort") String sort,
      @Query("page") Integer page, @Query("per_page") Integer perPage);

  /**
   * Calls GitHub API to query GitHub pull requests based on the repository full name.
   *
   * @param owner string with repository login owner
   * @param repository string with the repository name
   * @return a <code>Call<List<GitHubPullRequest>></code> if successful, contains a list of
   * pull requests
   */
  @GET("/repos/{owner}/{repository}/pulls")
  Call<List<GitHubPullRequest>> listPullRequests(@Path("owner") String owner,
      @Path("repository") String repository);
}
