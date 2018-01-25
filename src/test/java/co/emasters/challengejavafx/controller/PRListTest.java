package co.emasters.challengejavafx.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


/**
 * TestFX class for pull request list.
 *
 * author: Olavo Holanda
 * version: 0.1
 */
public class PRListTest extends ApplicationBaseTest {

  @Test
  public void test_repository() {
    sleep(8000);
    // expect:
    assertTrue(controller.getRepoList().isVisible());
    assertFalse(controller.getPrList().isVisible());
    assertEquals(7, controller.getRepoList().getItems().size());

    //onclick
    clickOn(controller.getRepoList().getItems().get(1));

    sleep(5000);

    // expect:
    assertFalse(controller.getRepoList().isVisible());
    assertTrue(controller.getPrList().isVisible());
    assertFalse(controller.getPrList().getItems().isEmpty());
    assertFalse(controller.getPrList().getItems().get(0).getChildren().isEmpty());
  }

}
