package co.emasters.challengejavafx.controller;

import static org.junit.Assert.assertFalse;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;

import org.junit.Test;

/**
 * TestFX for the exit button.
 *
 * author: Olavo.
 * version: 0.1
 */
public class ExitButtonTest extends ApplicationBaseTest {

  @Test
  public void should_contain_button() {
    // expect:
    verifyThat("#exitBtn", hasText("EXIT"));
  }
}
