package co.emasters.challengejavafx.controller;

import co.emasters.challengejavafx.components.RepoListItem;
import co.emasters.challengejavafx.model.GitHubRepoPage;
import co.emasters.challengejavafx.model.GitHubRepository;
import co.emasters.challengejavafx.service.GitHubService;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
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
public class RepoListController {

  @FXML
  private JFXListView<GridPane> repoList;

  public void initialize() {

    repoList.getStyleClass().add("mylistview");

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    GitHubService service = retrofit.create(GitHubService.class);
    Call<GitHubRepoPage> call = service.listRepos("language=Java", "stars", 1);
    try {
      Response<GitHubRepoPage> execute = call.execute();
      if(execute.isSuccessful()){
        GitHubRepoPage page = execute.body();
        List<GitHubRepository> items = page.getItems();
        for (GitHubRepository r : items) {
          RepoListItem listItem = new RepoListItem(r);
          repoList.getItems().add(listItem);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void exit() {
    System.exit(0);
  }
}
