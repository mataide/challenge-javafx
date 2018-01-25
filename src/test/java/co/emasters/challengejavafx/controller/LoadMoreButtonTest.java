package co.emasters.challengejavafx.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * TestFX for the load more repositories button.
 *
 * author: Olavo.
 * version: 0.1
 */
public class LoadMoreButtonTest extends ApplicationBaseTest {

  @Test
  public void test_button() {
    sleep(8000);
    // expect:
    assertEquals("LOAD MORE", controller.getLoadMoreBtn().getText());
    assertTrue(controller.getLoadMoreBtn().isVisible());
    assertEquals(7, controller.getRepoList().getItems().size());

    //onclick
    clickOn(controller.getLoadMoreBtn());

    // expect:
    assertEquals("LOADING...", controller.getLoadMoreBtn().getText());
    assertTrue(controller.getLoadMoreBtn().isDisable());

    sleep(8000);

    // expect:
    assertEquals("LOAD MORE", controller.getLoadMoreBtn().getText());
    assertTrue(controller.getLoadMoreBtn().isVisible());
    assertEquals(14, controller.getRepoList().getItems().size());
  }
}
