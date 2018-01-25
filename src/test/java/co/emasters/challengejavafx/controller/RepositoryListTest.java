package co.emasters.challengejavafx.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javafx.scene.layout.GridPane;
import org.junit.Test;

/**
 * TestFX class for repository list.
 *
 * author: Olavo Holanda
 * version: 0.1
 */
public class RepositoryListTest extends ApplicationBaseTest {

  @Test
  public void test_repository() {
    sleep(8000);
    // expect:
    assertTrue(controller.getRepoList().isVisible());
    assertFalse(controller.getPrList().isVisible());
    assertEquals(7, controller.getRepoList().getItems().size());

    GridPane firstChild = controller.getRepoList().getItems().get(0);
    assertFalse(firstChild.getChildren().isEmpty());

    //onclick
    clickOn(firstChild);

    sleep(5000);

    // expect:
    assertFalse(controller.getRepoList().isVisible());
    assertTrue(controller.getPrList().isVisible());
  }

}
