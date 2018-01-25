package co.emasters.challengejavafx.controller;

import co.emasters.challengejavafx.components.CustomLoader;
import co.emasters.challengejavafx.components.list.PRListItem;
import co.emasters.challengejavafx.components.list.RepoListItem;
import co.emasters.challengejavafx.model.GitHubPullRequest;
import co.emasters.challengejavafx.model.GitHubRepository;
import co.emasters.challengejavafx.service.QueryService;
import co.emasters.challengejavafx.components.utils.Constants;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Controller class for the main view of the application.
 *
 * author: Olavo Holanda
 * version: 0.1
 */
public class AppController {

  /** ----- STATIC FINAL VARIABLES ----- */
  private static final String LOAD_TITLE = "LOAD MORE";
  private static final String LOADING_TITLE = "LOADING...";
  private static final String PR_TILE = "List of Pull Request for: ";
  private static final String REPO_TILE = "List of Java Popular Repositories";
  private static final String LIST_VIEW_CSS = "repo-list";

  /** ----- CLASS VARIABLES ----- */
  private final QueryService queryService = new QueryService();
  //controls the page number of repository list
  private Integer pageNumber = 1;


  /** ----- APPLICATION COMPONENTS ----- */
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

  /**
   * Initializes the controller once all components in the view were loaded
   *
   * */
  public void initialize() {
    repoList.getStyleClass().add(LIST_VIEW_CSS);

    FontAwesomeIconView view = new FontAwesomeIconView(FontAwesomeIcon.ARROW_LEFT);
    view.setScaleX(1.5);
    view.setScaleY(1.5);
    view.setFill(Constants.WHITE);
    goBackBtn.setGraphic(view);

    CustomLoader customLoader = new CustomLoader();
    paneList.getChildren().add(customLoader);
    this.loadRepoItems();
  }

  /**
   * Load more action for the load more button. Loads more repositories based on the current page
   * number.
   * */
  @FXML
  private void loadMore() {
    loadMoreBtn.setText(LOADING_TITLE);
    loadMoreBtn.setDisable(true);
    pageNumber++;
    this.loadRepoItems();
  }

  /**
   * Go back action for the back to repository list button.
   * */
  @FXML
  private void backToRepoList(){
    goBackBtn.setVisible(false);
    repoList.setVisible(true);
    prList.setVisible(false);
    titleLabel.setText(REPO_TILE);
    loadMoreBtn.setVisible(true);
  }

  /**
   * Exit action. Shutdowns the query service and exits the system.
   * */
  @FXML
  private void exit() {
    Stage stage = (Stage) paneList.getScene().getWindow();
    stage.close();
    queryService.shutdownDatabase();
  }

  /**
   * Creates a list of Repository Item components based on a list of GitHubRepository items.
   *
   * @param items a list of <code>GitHubRepository</code>
   * */
  private List<RepoListItem> createRepositoryList(List<GitHubRepository> items) {
    List<RepoListItem> repoListItems = new ArrayList<>();
    for (GitHubRepository r : items) {
      RepoListItem listItem = new RepoListItem(r);
      listItem.setOnMouseClicked(t -> loadPRItems(r));
      repoListItems.add(listItem);
    }
    return repoListItems;
  }

  /**
   * Creates a list of PR Item components based on a list of GitHubPullRequest items.
   *
   * @param items a list of <code>GitHubPullRequest</code>
   * */
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

  /**
   * Loads repository items on the UI. Executes as a thread so a custom loader progress indicator
   * can notify the user that something is be loading in background.
   * */
  private void loadRepoItems() {
    CustomLoader customLoader = getLoader();

    Runnable runnable = () -> {
      List<GitHubRepository> repositories = queryService.retrieveStarredJavaRepositories(pageNumber);
      List<RepoListItem> items = createRepositoryList(repositories);
      Platform.runLater(
          () -> {
            repoList.getItems().addAll(items);
            customLoader.setVisible(false);
            loadMoreBtn.setVisible(true);
            if(loadMoreBtn.isDisable()){
              loadMoreBtn.setText(LOAD_TITLE);
              loadMoreBtn.setDisable(false);
            }
          }
      );
    };

    Thread thread = new Thread(runnable);
    thread.setDaemon(true);
    thread.start();
  }

  /**
   * Loads pull request items on the UI. Executes as a thread so a custom loader progress indicator
   * can notify the user that something is be loading in background.
   *
   * @param r  a instance of GitHubRepository
   * */
  private void loadPRItems(GitHubRepository r) {
    CustomLoader customLoader = getLoader();
    customLoader.setVisible(true);
    repoList.setVisible(false);
    goBackBtn.setVisible(true);
    prList.getItems().clear();
    loadMoreBtn.setVisible(false);
    titleLabel.setText(PR_TILE + r.getFull_name());

    Runnable runnable = () -> {
      List<GitHubPullRequest> requests = queryService.retrievePullRequestsFromRepository(r.getOwner().getLogin(), r.getName());
      List<PRListItem> listItems = createPRList(requests);
      Platform.runLater(
          () -> {
            prList.getItems().addAll(listItems);
            prList.setVisible(true);
            customLoader.setVisible(false);
          }
      );
    };

    Thread thread = new Thread(runnable);
    thread.setDaemon(true);
    thread.start();
  }

  /**
   * Gets the custom loader progress indicator from the UI.
   * */
  private CustomLoader getLoader(){
    return (CustomLoader) paneList.getChildren().get(2);
  }
}
