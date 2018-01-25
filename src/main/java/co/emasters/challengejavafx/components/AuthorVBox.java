package co.emasters.challengejavafx.components;

import co.emasters.challengejavafx.model.GitHubAuthor;
import co.emasters.challengejavafx.components.utils.Constants;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * VBox component representing the user avatar and login.
 *
 * author: Olavo Holanda
 * version: 0.1
 */
public class AuthorVBox extends VBox {

  private static final Integer IMAGE_SIZE = 60;
  private GitHubAuthor author;

  /**
   * Creates VBox with inner components based on author properties.
   *
   * @param author a GitHubAuthor instance
   */
  public AuthorVBox(GitHubAuthor author) {
    super();
    this.author = author;
    this.setAlignment(Pos.CENTER);

    this.configureImage();
    this.configureUsername();
  }

  /**
   * Creates a circle image pattern with user's avatar url.
   *
   */
  private void configureImage(){
    //
    String url = getFixedSizeURL();
    Image myImage = new Image(url);
    Circle circle = new Circle(IMAGE_SIZE / 2);
    ImagePattern pattern = new ImagePattern(myImage);
    circle.setFill(pattern);
    this.getChildren().add(circle);
  }

  /**
   * Modifies user's avatar url to get specific size image.
   *
   */
  private String getFixedSizeURL(){
    String url = author.getAvatar_url();
    url = url.substring(0, url.indexOf("?"));
    url += "?size=" + IMAGE_SIZE;
    return url;
  }

  /**
   * Creates a Label for the username.
   *
   */
  private void configureUsername(){
    Label usernameLabel = new Label(author.getLogin());
    usernameLabel.setTextFill(Constants.DARK_CYAN);
    usernameLabel.setFont(Constants.ROBOTO);
    this.getChildren().add(usernameLabel);
  }
}
