package com.garcia.github.form.pullrequests;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
/**
 * Class responsible for controlling pull-request list
 * 
 * @author Luis Garcia
 * @version 1.0
 * @since 05/05/2018
 *
 */
@Controller
public class PullRequestsController implements Initializable {

	public static final String FORM_LOCATION = "/com/garcia/github/form/pullrequests/pull-requests.fxml";

	@FXML
	private ListView<HBox> listView;
	@FXML
	private Label lblOpened;
	@FXML
	private Label lblClosed;
	@FXML
	private Label lblTitle;
	@FXML
	private Button btnBack;
	
	private Stage previousScene;
	private Stage currentScene;

	public ListView<HBox> getListView() {
		return listView;
	}
	public void setListView(List<HBox> list) {
		ObservableList<HBox> observableList = FXCollections.observableList(list);
        this.listView.setItems(observableList);
	}
	
	public Label getLblOpened() {
		return lblOpened;
	}
	public void setLblOpened(int opened) {
		this.lblOpened.setText(String.valueOf(opened).concat(" opened"));
	}
	public Label getLblClosed() {
		return lblClosed;
	}
	public void setLblClosed(int closed) {
		this.lblClosed.setText(String.valueOf(closed).concat(" closed"));
	}
	public Label getLblTitle() {
		return lblTitle;
	}
	public void setLblTitle(String title) {
		this.lblTitle.setText(title);
	}
	
	public Button getBtnBack() {
		return btnBack;
	}
	public void setBtnBack(Button btnBack) {
		this.btnBack = btnBack;
	}
	public void setPreviousScene(Stage previousScene) {
		this.previousScene = previousScene;
	}
	public void setCurrentScene(Stage stage) {
		this.currentScene = stage;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.btnBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				currentScene.close();
				previousScene.show();
			}
		});
	}

}
