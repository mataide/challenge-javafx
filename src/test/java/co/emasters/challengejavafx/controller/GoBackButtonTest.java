package co.emasters.challengejavafx.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * TestFX for the load more repositories button.
 *
 * author: Olavo.
 * version: 0.1
 */
public class GoBackButtonTest extends ApplicationBaseTest {

  @Test
  public void test_button() {
    sleep(8000);
    // expect:
    assertFalse(controller.getGoBackBtn().isVisible());
    assertTrue(controller.getRepoList().isVisible());

    //onclick
    clickOn(controller.getRepoList().getItems().get(0));

    // expect:
    assertTrue(controller.getGoBackBtn().isVisible());
    assertFalse(controller.getRepoList().isVisible());

    sleep(5000);

    //onclick
    clickOn(controller.getGoBackBtn());

    // expect:
    assertFalse(controller.getGoBackBtn().isVisible());
    assertTrue(controller.getRepoList().isVisible());
  }
}
