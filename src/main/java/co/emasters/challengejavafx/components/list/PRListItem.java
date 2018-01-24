package co.emasters.challengejavafx.components.list;

import co.emasters.challengejavafx.components.AuthorVBox;
import co.emasters.challengejavafx.components.RepoStatsLabel;
import co.emasters.challengejavafx.model.GitHubPullRequest;
import co.emasters.challengejavafx.model.GitHubRepository;
import co.emasters.challengejavafx.utils.Constants;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Class description here.
 *
 * author: Olavo.
 * version: 0.1
 */
public class PRListItem extends GenericListItem {

  private static final double HEIGHT = 100.0;
  private static final double WIDTH = 800.0;

  private GitHubPullRequest request;

  public PRListItem(GitHubPullRequest request) {
    super();

    this.request = request;

    this.setPrefHeight(HEIGHT);
    this.setPrefWidth(WIDTH);
    this.buildItem();
  }

  private void buildItem(){
    //add repository title label
    Label titleLabel = new Label(request.getTitle());
    titleLabel.setTextFill(Constants.DARK_CYAN);
    titleLabel.setFont(new Font("Roboto Bold", 12.0));
    this.add(titleLabel, 1, 1);

    //add repository description label
    Label descLabel = new Label(request.getBody());
    descLabel.setTextFill(Constants.LIGHT_GREY);
    descLabel.setFont(new Font("Roboto", 10.0));
    descLabel.setWrapText(true);
    this.add(descLabel, 1, 2);

    DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    String date = format.format(request.getCreated_at());

    //add the created at date
    VBox statsBox = new VBox();
    statsBox.setAlignment(Pos.CENTER);
    statsBox.getChildren().add(new RepoStatsLabel(date, FontAwesomeIcon.CALENDAR));
    statsBox.setSpacing(5);
    this.add(statsBox, 2, 2);

    AuthorVBox authorVBox = new AuthorVBox(request.getUser());
    this.add(authorVBox, 3, 2);
  }
}
