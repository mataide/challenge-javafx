package co.emasters.challengejavafx.components;

import javafx.scene.control.ProgressIndicator;

/**
 * Custom Progress Indicator, extends from javafx ProgressIndicator.
 *
 * author: Olavo Holanda
 * version: 0.1
 */
public class CustomLoader extends ProgressIndicator {

  private static final Double X = 400.0;
  private static final Double Y = 10.0;

  /**
   * Creates a new indeterminate ProgressIndicator.
   */
  public CustomLoader() {
    super();
    //sets the position
    this.setLayoutX(X);
    this.setLayoutY(Y);
  }
}
