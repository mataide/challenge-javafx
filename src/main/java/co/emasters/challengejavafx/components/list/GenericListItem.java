package co.emasters.challengejavafx.components.list;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * Class description here.
 *
 * author: Olavo.
 * version: 0.1
 */
class GenericListItem extends GridPane {

  GenericListItem() {
    super();
    this.configureColumns();
    this.configureRows();
  }

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
