package co.emasters.challengejavafx.controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.testfx.framework.junit.ApplicationTest;

/**
 * Application base test where all TestFX extends.
 *
 * author: Olavo Holanda
 * version: 0.1
 */
public class ApplicationBaseTest extends ApplicationTest {

  AppController controller;
  Stage stage;

  @Override
  public void start(Stage primaryStage) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/App.fxml"));
    Parent root = loader.load();
    controller = loader.getController();
    Scene scene = new Scene(root);
    stage = primaryStage;
    primaryStage.setScene(scene);
    primaryStage.show();
  }

}
