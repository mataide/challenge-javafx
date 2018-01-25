package co.emasters.challengejavafx.service;

import co.emasters.challengejavafx.model.GitHubPullRequest;
import co.emasters.challengejavafx.model.GitHubRepoPage;
import co.emasters.challengejavafx.model.GitHubRepository;
import co.emasters.challengejavafx.persistence.HibernateUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Service for querying GitHub, can call GitHub API or get from internal cache.
 *
 * author: Olavo Holanda
 * version: 0.1
 */
public class QueryService {

  private static final String REPO_SORT = "stars";
  private static final String REPO_LANGUAGE = "language:Java";
  private static final Integer PAGE_SIZE = 7;

  private GitHubService gitHubService;

  public QueryService() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    gitHubService = retrofit.create(GitHubService.class);
  }

  /**
   * Retrieve most starred java repositories from GitHub. No cache is done here, because this method
   * is called once during the application runtime, for each page. So, cache would never be used.
   *
   * @param pageNumber the number of the page, starts with 1.
   * @return a list of <code>GitHubRepository</code>
   */
  public List<GitHubRepository> retrieveStarredJavaRepositories(Integer pageNumber) {
    Call<GitHubRepoPage> call = gitHubService
        .listRepos(REPO_LANGUAGE, REPO_SORT, pageNumber, PAGE_SIZE);
    try {
      Response<GitHubRepoPage> execute = call.execute();
      if (execute.isSuccessful()) {
        GitHubRepoPage page = execute.body();
        if (page != null) {
          return page.getItems();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  /**
   * Retrieve all pull requests from a GitHub repository. First, it checks if this results is
   * already cached on internal database, if not, calls GitHub API.
   *
   * @param owner string login of the repository owner
   * @param repository string name of the repository
   * @return a list of <code>GitHubPullRequest</code>
   */
  public List<GitHubPullRequest> retrievePullRequestsFromRepository(String owner,
      String repository) {
    List<GitHubPullRequest> requests = loadPullRequestCache(owner, repository);
    if (requests.isEmpty()) {
      requests = loadPullRequestsFromGitHub(owner, repository);
      savePullRequestsOnCache(requests);
    }
    return requests;
  }

  /**
   * Load all pull requests saved on database with repoId containing the owner and repository
   * name.
   *
   * @param owner string login of the repository owner
   * @param repository string name of the repository
   * @return a list of <code>GitHubPullRequest</code>
   */
  private List<GitHubPullRequest> loadPullRequestCache(String owner, String repository) {

    String repoId = owner + "/" + repository;

    List<GitHubPullRequest> prs = new ArrayList<>();

    SessionFactory factory = HibernateUtils.getSessionFactory();
    Session session = factory.getCurrentSession();
    try {
      session.getTransaction().begin();

      String sql = "SELECT pr FROM " + GitHubPullRequest.class.getName() + " pr "
          + " WHERE pr.repoId = '" + repoId + "'";

      Query<GitHubPullRequest> query = session.createQuery(sql, GitHubPullRequest.class);
      prs = query.getResultList();

      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
      session.getTransaction().rollback();
    }

    return prs;
  }

  /**
   * Load all pull requests of a GitHub repository from its API.
   *
   * @param owner string login of the repository owner
   * @param repository string name of the repository
   * @return a list of <code>GitHubPullRequest</code>
   */
  private List<GitHubPullRequest> loadPullRequestsFromGitHub(String owner, String repository) {
    Call<List<GitHubPullRequest>> call = gitHubService.listPullRequests(owner, repository);
    try {
      Response<List<GitHubPullRequest>> execute = call.execute();
      if (execute.isSuccessful()) {
        List<GitHubPullRequest> requests = execute.body();
        if (requests != null) {
          requests.forEach(p -> p.setRepoId(owner + "/" + repository));
          return requests;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  /**
   * Saves a list of pull requests on internal database.
   *
   * @param requests a list of <code>GitHubPullRequest</code> to save on database
   */
  private void savePullRequestsOnCache(List<GitHubPullRequest> requests) {
    SessionFactory factory = HibernateUtils.getSessionFactory();
    Session session = factory.getCurrentSession();
    try {
      session.getTransaction().begin();

      requests.forEach(session::merge);

      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
      session.getTransaction().rollback();
    }
  }

  /**
   * Shutdowns the current hibernate session and database connection.
   */
  public void shutdownDatabase() {
    HibernateUtils.shutdown();
  }
}
