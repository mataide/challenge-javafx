package co.emasters.challengejavafx.components;

import co.emasters.challengejavafx.components.RepoStatsLabel;
import co.emasters.challengejavafx.model.GitHubRepository;
import co.emasters.challengejavafx.utils.Constants;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 * Class description here.
 *
 * author: Olavo.
 * version: 0.1
 */
public class RepoListItem extends GridPane {

  private static final double HEIGHT = 100.0;
  private static final double WIDTH = 800.0;

  private GitHubRepository repository;

  public RepoListItem(GitHubRepository repository) {
    super();

    this.repository = repository;

    this.setPrefHeight(HEIGHT);
    this.setPrefWidth(WIDTH);

    this.configureColumns();
    this.configureRows();
    this.buildItem();
  }

  private void buildItem(){
    //add repository title label
    Label titleLabel = new Label(repository.getFull_name());
    titleLabel.setTextFill(Constants.DARK_CYAN);
    titleLabel.setFont(new Font("Roboto Bold", 12.0));
    this.add(titleLabel, 1, 1);

    //add repository description label
    Label descLabel = new Label(repository.getDescription());
    descLabel.setTextFill(Constants.LIGHT_GREY);
    descLabel.setFont(new Font("Roboto", 10.0));
    this.add(descLabel, 1, 2);

    //add the number of forks, stars and watchers
    VBox statsBox = new VBox();
    statsBox.setAlignment(Pos.CENTER);
    statsBox.getChildren().add(new RepoStatsLabel(repository.getForks_count().toString(), FontAwesomeIcon.CODE_FORK));
    statsBox.getChildren().add(new RepoStatsLabel(repository.getStargazers_count().toString(), FontAwesomeIcon.STAR));
    statsBox.getChildren().add(new RepoStatsLabel(repository.getWatchers_count().toString(), FontAwesomeIcon.EYE));
    statsBox.setSpacing(5);
    this.add(statsBox, 2, 2);

    AuthorVBox authorVBox = new AuthorVBox(repository.getOwner());
    this.add(authorVBox, 3, 2);
  }

  private void configureRows() {
    RowConstraints firstConst = new RowConstraints();
    RowConstraints secondConst = new RowConstraints();
    RowConstraints thirdConst = new RowConstraints();
    RowConstraints fourthConst = new RowConstraints();

    firstConst.setPercentHeight(5);
    secondConst.setPercentHeight(20);
    thirdConst.setPercentHeight(70);
    fourthConst.setPercentHeight(5);

    this.getRowConstraints().addAll(firstConst, secondConst, thirdConst, fourthConst);
  }

  private void configureColumns(){
    ColumnConstraints firstConst = new ColumnConstraints();
    ColumnConstraints secondConst = new ColumnConstraints();
    ColumnConstraints thirdConst = new ColumnConstraints();
    ColumnConstraints fourthConst = new ColumnConstraints();
    ColumnConstraints fifthConst = new ColumnConstraints();

    firstConst.setPercentWidth(5);
    secondConst.setPercentWidth(50);
    thirdConst.setPercentWidth(15);
    fourthConst.setPercentWidth(25);
    fifthConst.setPercentWidth(5);

    this.getColumnConstraints().addAll(firstConst, secondConst, thirdConst, fourthConst, fifthConst);
  }
}
