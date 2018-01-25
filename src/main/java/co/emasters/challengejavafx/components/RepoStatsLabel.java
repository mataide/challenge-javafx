package co.emasters.challengejavafx.components;

import co.emasters.challengejavafx.components.utils.Constants;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;

/**
 * Custom label for repository stats.
 *
 * author: Olavo Holanda
 * version: 0.1
 */
public class RepoStatsLabel extends Label {

  private static final Integer TEXT_GAP = 10;

  /**
   * Creates Label with supplied text and FontAwesomeIcon.
   *
   * @param text the label String
   * @param icon the graphic FontAwesome icon
   */
  public RepoStatsLabel(String text, FontAwesomeIcon icon) {
    super(text);

    FontAwesomeIconView view = new FontAwesomeIconView(icon);
    view.setFill(Constants.DARK_ORANGE);

    this.setGraphic(view);
    this.setGraphicTextGap(TEXT_GAP);
    this.setFont(Constants.ROBOTO_BOLD);
    this.setTextFill(Constants.DARK_ORANGE);
    this.setTextAlignment(TextAlignment.CENTER);
  }
}
