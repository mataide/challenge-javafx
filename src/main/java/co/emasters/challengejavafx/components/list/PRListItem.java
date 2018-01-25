package co.emasters.challengejavafx.components.list;

import co.emasters.challengejavafx.components.AuthorVBox;
import co.emasters.challengejavafx.components.RepoStatsLabel;
import co.emasters.challengejavafx.model.GitHubPullRequest;
import co.emasters.challengejavafx.components.utils.Constants;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Pull Request list item, extends GenericListItem.
 *
 * author: Olavo Holanda
 * version: 0.1
 */
public class PRListItem extends GenericListItem {

  private static final Double HEIGHT = 100.0;
  private static final Double WIDTH = 800.0;
  private static final String DATE_FORMAT = "MM/dd/yyyy";

  private GitHubPullRequest request;

  /**
   * Creates a new PRListItem based on the GitHubPullRequest instance.
   *
   * @param request a GitHubPullRequest instance
   * */
  public PRListItem(GitHubPullRequest request) {
    super();

    this.request = request;

    this.setPrefHeight(HEIGHT);
    this.setPrefWidth(WIDTH);
    this.buildItem();
  }

  /**
   * Builds the title label, description label, date VBox and Author VBox.
   * */
  private void buildItem(){
    //add repository title label
    Label titleLabel = new Label(request.getTitle());
    titleLabel.setTextFill(Constants.DARK_CYAN);
    titleLabel.setFont(Constants.ROBOTO_BOLD);
    this.add(titleLabel, 1, 1);

    //add repository description label
    Label descLabel = new Label(request.getBody());
    descLabel.setTextFill(Constants.LIGHT_GREY);
    descLabel.setFont(Constants.ROBOTO);
    descLabel.setWrapText(true);
    this.add(descLabel, 1, 2);

    DateFormat format = new SimpleDateFormat(DATE_FORMAT);
    String date = format.format(request.getCreated_at());

    //add the created_at date
    VBox statsBox = new VBox();
    statsBox.setAlignment(Pos.CENTER);
    statsBox.getChildren().add(new RepoStatsLabel(date, FontAwesomeIcon.CALENDAR));
    statsBox.setSpacing(5);
    this.add(statsBox, 2, 2);

    AuthorVBox authorVBox = new AuthorVBox(request.getUser());
    this.add(authorVBox, 3, 2);
  }
}
