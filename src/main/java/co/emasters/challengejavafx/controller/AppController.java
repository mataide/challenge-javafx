package co.emasters.challengejavafx.controller;

import co.emasters.challengejavafx.components.list.PRListItem;
import co.emasters.challengejavafx.components.list.RepoListItem;
import co.emasters.challengejavafx.model.GitHubPullRequest;
import co.emasters.challengejavafx.model.GitHubRepoPage;
import co.emasters.challengejavafx.model.GitHubRepository;
import co.emasters.challengejavafx.service.GitHubService;
import co.emasters.challengejavafx.service.QueryService;
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
  private JFXButton goBackBtn;
  @FXML
  private JFXButton loadMoreBtn;
  @FXML
  private Label titleLabel;

  private final QueryService queryService = new QueryService();

  private Integer pageNumber = 1;

  public void initialize() {

    FontAwesomeIconView view = new FontAwesomeIconView(FontAwesomeIcon.ARROW_LEFT);
    view.setScaleX(1.5);
    view.setScaleY(1.5);
    view.setFill(Constants.WHITE);
    goBackBtn.setGraphic(view);


    repoList.getStyleClass().add("mylistview");

    ProgressIndicator progressIndicator = new ProgressIndicator();
    progressIndicator.setLayoutX(400.0);
    progressIndicator.setLayoutY(10.0);
    progressIndicator.setVisible(true);
    paneList.getChildren().add(progressIndicator);
    this.loadRepoItems();
  }

  private List<RepoListItem> createRepositoryList(List<GitHubRepository> items) {
    List<RepoListItem> repoListItems = new ArrayList<>();
    for (GitHubRepository r : items) {
      RepoListItem listItem = new RepoListItem(r);
      listItem.setOnMouseClicked(t -> loadPRItems(r));
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

  @FXML
  private void loadMore() {
    loadMoreBtn.setText("LOADING ...");
    loadMoreBtn.setDisable(true);
    pageNumber++;
    this.loadRepoItems();
  }

  @FXML
  private void backToRepoList(){
    goBackBtn.setVisible(false);
    repoList.setVisible(true);
    prList.setVisible(false);
    titleLabel.setText("List of Popular Java Repositories");
    loadMoreBtn.setVisible(true);
  }

  @FXML
  private void exit() {
    queryService.shutdownDatabase();
    System.exit(0);
  }

  private void loadRepoItems() {
    // start displaying the loading indicator at the Application Thread
    ProgressIndicator progressIndicator = getProgress();

    Runnable runnable = () -> {
      List<GitHubRepository> repositories = queryService.retrieveStarredJavaRepositories(pageNumber);
      List<RepoListItem> items = createRepositoryList(repositories);
      Platform.runLater(
          () -> {
            repoList.getItems().addAll(items);
            progressIndicator.setVisible(false);
            loadMoreBtn.setVisible(true);
            if(loadMoreBtn.isDisable()){
              loadMoreBtn.setText("LOAD MORE");
              loadMoreBtn.setDisable(false);
            }
          }
      );
    };

    Thread thread = new Thread(runnable);
    thread.setDaemon(true);
    thread.start();
  }

  private void loadPRItems(GitHubRepository r) {
    // start displaying the loading indicator at the Application Thread
    ProgressIndicator progressIndicator = getProgress();
    progressIndicator.setVisible(true);
    repoList.setVisible(false);
    goBackBtn.setVisible(true);
    prList.getItems().clear();
    loadMoreBtn.setVisible(false);
    titleLabel.setText("List of Pull Requests for: " + r.getFull_name());

    Runnable runnable = () -> {
      List<GitHubPullRequest> requests = queryService.retrievePullRequestsFromRepository(r.getOwner().getLogin(), r.getName());
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

  private ProgressIndicator getProgress(){
    return (ProgressIndicator) paneList.getChildren().get(2);
  }
}
