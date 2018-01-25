package co.emasters.challengejavafx.service;

import static org.junit.Assert.*;

import co.emasters.challengejavafx.model.GitHubPullRequest;
import co.emasters.challengejavafx.model.GitHubRepository;
import java.util.List;
import org.junit.Test;

/**
 * Test query service. It may broken as results from GitHub API can change.
 *
 * author: Olavo Holanda
 * version: 0.1
 */
public class QueryServiceTest {

  private final QueryService queryService = new QueryService();

  @Test
  public void retrieveStarredJavaRepositories() throws Exception {
    List<GitHubRepository> repositories = queryService.retrieveStarredJavaRepositories(1);
    assertEquals(7, repositories.size());
  }

  @Test
  public void retrievePullRequestsFromRepository() throws Exception {
    List<GitHubPullRequest> requests = queryService
        .retrievePullRequestsFromRepository("TestFX", "TestFX");
    assertEquals(false, requests.isEmpty());
  }

}