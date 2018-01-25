package co.emasters.challengejavafx.components.list;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * The application uses the same base for GridPane to represent both a repository item as well as
 * a pull request item. This abstract class extends GridPane and configures itself as this base.
 *
 * author: Olavo Holanda
 * version: 0.1
 */
abstract class GenericListItem extends GridPane {

  /**
   * Creates a GridPane layout with configured rows and columns.
   */
  GenericListItem() {
    super();
    this.configureColumns();
    this.configureRows();
  }

  /**
   * Defines rows layout.
   */
  private void configureRows() {
    RowConstraints firstConst = new RowConstraints();
    RowConstraints secondConst = new RowConstraints();
    RowConstraints thirdConst = new RowConstraints();
    RowConstraints fourthConst = new RowConstraints();

    firstConst.setPercentHeight(5);
    secondConst.setPercentHeight(20);
    thirdConst.setPercentHeight(70);
    fourthConst.setPercentHeight(5);

    this.getRowConstraints().addAll(firstConst, secondConst, thirdConst, fourthConst);
  }

  /**
   * Defines column layout.
   */
  private void configureColumns(){
    ColumnConstraints firstConst = new ColumnConstraints();
    ColumnConstraints secondConst = new ColumnConstraints();
    ColumnConstraints thirdConst = new ColumnConstraints();
    ColumnConstraints fourthConst = new ColumnConstraints();
    ColumnConstraints fifthConst = new ColumnConstraints();

    firstConst.setPercentWidth(5);
    secondConst.setPercentWidth(50);
    thirdConst.setPercentWidth(15);
    fourthConst.setPercentWidth(25);
    fifthConst.setPercentWidth(5);

    this.getColumnConstraints().addAll(firstConst, secondConst, thirdConst, fourthConst, fifthConst);
  }
}
