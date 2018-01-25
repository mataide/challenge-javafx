package co.emasters.challengejavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Main application, starts the Javafx application.
 *
 * author: Olavo Holanda
 * version: 0.1
 */
public class ChallengeApp extends Application {

  /**
   * The main entry point for all JavaFX applications.
   * The start method is called after the init method has returned,
   * and after the system is ready for the application to begin running.
   *
   * <p>
   * NOTE: This method is called on the JavaFX Application Thread.
   * </p>
   *
   * @param primaryStage the primary stage for this application, onto which
   * the application scene can be set. The primary stage will be embedded in
   * the browser if the application was launched as an applet.
   * Applications may create other stages, if needed, but they will not be
   * primary stages and will not be embedded in the browser.
   */
  @Override
  public void start(Stage primaryStage) throws Exception {

    Parent root = FXMLLoader.load(getClass().getResource("/view/App.fxml"));

    Scene scene = new Scene(root);
    scene.getStylesheets().add(getClass().getResource("/css/jfxcomponents.css").toExternalForm());

    primaryStage.initStyle(StageStyle.UNDECORATED);
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.show();
  }

  public static void main(String[] args) { launch(args); }
}
