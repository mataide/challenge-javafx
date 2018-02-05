package com.garcia.github.form.pullrequest;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Class responsible for controlling PR data
 * 
 * @author Luis Garcia
 * @version 1.0
 * @since 05/05/2018
 *
 */
@Controller
public class PullRequestController implements Initializable {

	public static final String FORM_LOCATION = "/com/garcia/github/form/pullrequest/pull-request.fxml";
	
	@FXML
	private Label lblTitle;
	@FXML
	private Label lblDescription;
	@FXML
	private Label lblLogin;
	@FXML
	private Label lblName;
	@FXML
	private ImageView imgAvatar;
	@FXML
	private HBox hboxContainer;
	
	public Label getLblTitle() {
		return lblTitle;
	}

	public void setLblTitle(String title) {
		this.lblTitle.setText(title);
	}

	public Label getLblDescription() {
		return lblDescription;
	}

	public void setLblDescription(String description) {
		this.lblDescription.setText(description);
	}

	public Label getLblLogin() {
		return lblLogin;
	}

	public void setLblLogin(String login) {
		this.lblLogin.setText(login);
	}

	public Label getLblName() {
		return lblName;
	}

	public void setLblName(String name) {
		this.lblName.setText(name);
	}

	public ImageView getImgAvatar() {
		return imgAvatar;
	}

	public void setImgAvatar(Image image) {
		this.imgAvatar.setImage(image);
	}

	public HBox getHboxContainer() {
		return hboxContainer;
	}

	public void setHboxContainer(HBox hboxContainer) {
		this.hboxContainer = hboxContainer;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
