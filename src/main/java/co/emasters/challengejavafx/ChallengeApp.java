package co.emasters.challengejavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Class description here.
 *
 * author: Olavo.
 * version: 0.1
 */
public class ChallengeApp extends Application {

  @Override
  public void start(Stage stage) throws Exception {

    Parent root = FXMLLoader.load(getClass().getResource("/view/App.fxml"));

    Scene scene = new Scene(root);
    scene.getStylesheets().add(getClass().getResource("/css/jfxcomponents.css").toExternalForm());

    stage.initStyle(StageStyle.UNDECORATED);
    stage.setScene(scene);
    stage.setResizable(false);
    stage.show();
  }

  public static void main(String[] args) { launch(args); }
}
