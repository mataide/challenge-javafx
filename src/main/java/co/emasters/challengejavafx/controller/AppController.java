package co.emasters.challengejavafx.controller;

import co.emasters.challengejavafx.components.list.PRListItem;
import co.emasters.challengejavafx.components.list.RepoListItem;
import co.emasters.challengejavafx.model.GitHubPullRequest;
import co.emasters.challengejavafx.model.GitHubRepoPage;
import co.emasters.challengejavafx.model.GitHubRepository;
import co.emasters.challengejavafx.service.GitHubService;
import co.emasters.challengejavafx.utils.Constants;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class description here.
 *
 * author: Olavo.
 * version: 0.1
 */
public class AppController {

  @FXML
  private JFXListView<GridPane> repoList;
  @FXML
  private JFXListView<GridPane> prList;
  @FXML
  private Pane paneList;
  @FXML
  private JFXButton goBackButton;
  @FXML
  private Label titleLabel;

  private Integer pageNumber = 1;

  public void initialize() {

    FontAwesomeIconView view = new FontAwesomeIconView(FontAwesomeIcon.ARROW_LEFT);
    view.setFill(Constants.WHITE);
    goBackButton.setGraphic(view);


    repoList.getStyleClass().add("mylistview");

    final ProgressIndicator progressIndicator = new ProgressIndicator();
    progressIndicator.setLayoutX(400.0);
    progressIndicator.setLayoutY(10.0);
    paneList.getChildren().add(progressIndicator);
    this.loadRepoItems(progressIndicator);
  }

  private List<RepoListItem> createRepositoryList(List<GitHubRepository> items, final ProgressIndicator progressIndicator) {
    List<RepoListItem> repoListItems = new ArrayList<>();
    for (GitHubRepository r : items) {
      RepoListItem listItem = new RepoListItem(r);
      listItem.setOnMouseClicked(t -> loadPRItems(progressIndicator, r));
      repoListItems.add(listItem);
    }
    return repoListItems;
  }

  private List<PRListItem> createPRList(List<GitHubPullRequest> items) {
    List<PRListItem> prList = new ArrayList<>();
    for (GitHubPullRequest p : items) {
      PRListItem listItem = new PRListItem(p);
      listItem.setOnMouseClicked(t -> {
        try {
          Desktop.getDesktop().browse(new URL(p.getHtml_url()).toURI());
        } catch (IOException | URISyntaxException e){
          e.printStackTrace();
        }
      });
      prList.add(listItem);
    }
    return prList;
  }

  private List<GitHubPullRequest> loadPullRequests(String owner, String repository) {

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    GitHubService service = retrofit.create(GitHubService.class);
    Call<List<GitHubPullRequest>> call = service.listPullRequests(owner, repository);
    try {
      Response<List<GitHubPullRequest>> execute = call.execute();
      if (execute.isSuccessful()) {
        return execute.body();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  private List<GitHubRepository> loadRepositories() {

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    GitHubService service = retrofit.create(GitHubService.class);
    Call<GitHubRepoPage> call = service.listRepos("language:Java", "stars", pageNumber, 7);
    try {
      Response<GitHubRepoPage> execute = call.execute();
      if (execute.isSuccessful()) {
        GitHubRepoPage page = execute.body();
        return page.getItems();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  @FXML
  private void loadMore() {
    final ProgressIndicator progressIndicator = (ProgressIndicator) paneList.getChildren().get(1);
    if(!progressIndicator.isVisible()){
      System.out.println("load more");
      pageNumber++;
      this.loadRepoItems(progressIndicator);
    }
  }

  @FXML
  private void backToRepoList(){
    goBackButton.setVisible(false);
    repoList.setVisible(true);
    prList.setVisible(false);
    titleLabel.setText("List of Popular Java Repositories");
  }

  @FXML
  private void exit() {
    System.exit(0);
  }

  private void loadRepoItems(final ProgressIndicator progressIndicator) {
    // start displaying the loading indicator at the Application Thread
    progressIndicator.setVisible(true);

    Runnable runnable = () -> {
      List<GitHubRepository> repositories = loadRepositories();
      List<RepoListItem> items = createRepositoryList(repositories, progressIndicator);
      Platform.runLater(
          () -> {
            repoList.getItems().addAll(items);
            progressIndicator.setVisible(false);
          }
      );
    };

    Thread thread = new Thread(runnable);
    thread.setDaemon(true);
    thread.start();
  }

  private void loadPRItems(final ProgressIndicator progressIndicator, GitHubRepository r) {
    // start displaying the loading indicator at the Application Thread
    progressIndicator.setVisible(true);
    repoList.setVisible(false);
    goBackButton.setVisible(true);
    prList.getItems().clear();
    titleLabel.setText("List of Pull Requests for: " + r.getFull_name());

    Runnable runnable = () -> {
      List<GitHubPullRequest> requests = loadPullRequests(r.getOwner().getLogin(), r.getName());
      List<PRListItem> listItems = createPRList(requests);
      Platform.runLater(
          () -> {
            prList.getItems().addAll(listItems);
            prList.setVisible(true);
            progressIndicator.setVisible(false);
          }
      );
    };

    Thread thread = new Thread(runnable);
    thread.setDaemon(true);
    thread.start();
  }
}
