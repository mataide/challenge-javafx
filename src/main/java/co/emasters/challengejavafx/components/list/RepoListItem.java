package co.emasters.challengejavafx.components.list;

import co.emasters.challengejavafx.components.AuthorVBox;
import co.emasters.challengejavafx.components.RepoStatsLabel;
import co.emasters.challengejavafx.model.GitHubRepository;
import co.emasters.challengejavafx.components.utils.Constants;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Repository list item, extends GenericListItem.
 *
 * author: Olavo Holanda
 * version: 0.1
 */
public class RepoListItem extends GenericListItem {

  private static final Double HEIGHT = 100.0;
  private static final Double WIDTH = 800.0;

  private GitHubRepository repository;

  /**
   * Creates a new RepoListItem based on the GitHubRepository instance.
   *
   * @param repository a GitHubRepository instance
   */
  public RepoListItem(GitHubRepository repository) {
    super();

    this.repository = repository;

    this.setPrefHeight(HEIGHT);
    this.setPrefWidth(WIDTH);
    this.buildItem();
  }

  /**
   * Builds the title label, description label, stats VBox and Author VBox.
   */
  private void buildItem() {
    //add repository title label
    Label titleLabel = new Label(repository.getFull_name());
    titleLabel.setTextFill(Constants.DARK_CYAN);
    titleLabel.setFont(Constants.ROBOTO_BOLD);
    this.add(titleLabel, 1, 1);

    //add repository description label
    Label descLabel = new Label(repository.getDescription());
    descLabel.setTextFill(Constants.LIGHT_GREY);
    descLabel.setFont(Constants.ROBOTO);
    descLabel.setWrapText(true);
    this.add(descLabel, 1, 2);

    //add the number of forks, stars and watchers
    VBox statsBox = new VBox();
    statsBox.setAlignment(Pos.CENTER);
    statsBox.getChildren()
        .add(new RepoStatsLabel(repository.getForks_count().toString(), FontAwesomeIcon.CODE_FORK));
    statsBox.getChildren()
        .add(new RepoStatsLabel(repository.getStargazers_count().toString(), FontAwesomeIcon.STAR));
    statsBox.getChildren()
        .add(new RepoStatsLabel(repository.getWatchers_count().toString(), FontAwesomeIcon.EYE));
    statsBox.setSpacing(5);
    this.add(statsBox, 2, 2);

    AuthorVBox authorVBox = new AuthorVBox(repository.getOwner());
    this.add(authorVBox, 3, 2);
  }
}
