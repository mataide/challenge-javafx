package co.emasters.challengejavafx.components;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * Class description here.
 *
 * author: Olavo.
 * version: 0.1
 */
class RepoStatsLabel extends Label {

  private static final Paint DARK_ORANGE = Paint.valueOf(Color.DARKORANGE.toString());

  /**
   * Creates Label with supplied text.
   *
   * @param text null text is treated as the empty string
   */
  RepoStatsLabel(String text, FontAwesomeIcon icon) {
    super(text);

    FontAwesomeIconView view = new FontAwesomeIconView(icon);
    view.setFill(DARK_ORANGE);

    this.setGraphic(view);
    this.setGraphicTextGap(10);
    this.setFont(Font.font("Roboto Bold"));
    this.setTextFill(DARK_ORANGE);
    this.setTextAlignment(TextAlignment.CENTER);
  }
}
