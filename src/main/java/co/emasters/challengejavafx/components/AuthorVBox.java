package co.emasters.challengejavafx.components;

import co.emasters.challengejavafx.model.GitHubAuthor;
import co.emasters.challengejavafx.utils.Constants;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * Class description here.
 *
 * author: Olavo.
 * version: 0.1
 */
public class AuthorVBox extends VBox {

  private static final Integer IMAGE_SIZE = 60;

  private GitHubAuthor author;

  public AuthorVBox(GitHubAuthor author) {
    super();
    this.author = author;
    this.setAlignment(Pos.CENTER);

    this.configureImage();
    this.configureUsername();
  }

  private void configureImage(){
    //creates an image with size 60 pixels
    String url = getFixedSizeURL();
    Image myImage = new Image(url);
    Circle circle = new Circle(IMAGE_SIZE/2);
    ImagePattern pattern = new ImagePattern(myImage);
    circle.setFill(pattern);
    this.getChildren().add(circle);
  }

  private String getFixedSizeURL(){
    String url = author.getAvatar_url();
    url = url.substring(0, url.indexOf("?"));
    url += "?size=" + IMAGE_SIZE;
    return url;
  }

  private void configureUsername(){
    Label usernameLabel = new Label(author.getLogin());
    usernameLabel.setTextFill(Constants.DARK_CYAN);
    usernameLabel.setFont(new Font("Roboto", 12.0));
    this.getChildren().add(usernameLabel);
  }
}
