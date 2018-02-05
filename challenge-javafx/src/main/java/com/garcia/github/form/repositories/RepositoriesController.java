package com.garcia.github.form.repositories;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.jboss.jandex.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.garcia.github.core.business.RepositoryBusiness;
import com.garcia.github.core.data.entities.Repo;
import com.garcia.github.core.facade.RepositoryFacade;
import com.garcia.github.form.pullrequest.PullRequestController;
import com.garcia.github.form.pullrequest.PullRequestStatus;
import com.garcia.github.form.pullrequests.PullRequestsController;
import com.google.common.collect.Lists;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * Class responsible for controlling repository list
 * 
 * @author Luis Garcia
 * @version 1.0
 * @since 05/05/2018
 *
 */
@Controller
public class RepositoriesController implements Initializable {

	public static final String FORM_LOCATION = "/com/garcia/github/form/repositories/repositories.fxml";
	
	@Autowired
	private RepositoryFacade facade;
	private RepositoryBusiness business;

	@FXML
	private ListView<HBox> listView;
	@FXML
	private ComboBox<Integer> cmbPages;
	@FXML
	private Button btnFind;
	
	private Stage currentScene;

	public ListView<HBox> getListView() {
		return listView;
	}
	public void setListView(List<HBox> list) {
		ObservableList<HBox> observableList = FXCollections.observableList(list);
        this.listView.setItems(observableList);
        
        this.listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HBox>() {
			@Override
			public void changed(ObservableValue<? extends HBox> observable, HBox oldValue, HBox newValue) {
				String repoName = ((Label)(((VBox)newValue.getChildren().get(0)).getChildren().get(0))).getText();
				String user = ((Label)(((VBox)newValue.getChildren().get(1)).getChildren().get(1))).getText();
				
				Stage nextScene = new Stage();
				
				//get data
				try {
					facade.retrievePullRequestsBy(user, repoName);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Repo repo = business.findByName(repoName);
				
		        //repositories page
		        List<HBox> list = new ArrayList<>();
		        PullRequestStatus prStatus = new PullRequestStatus();
		        
		        //adding repositories
		        repo.getPullrequests().forEach(pr -> {
		        		//start form
		            FXMLLoader pullrequestForm = new FXMLLoader();
		            pullrequestForm.setLocation(Main.class.getResource(PullRequestController.FORM_LOCATION));
		            
		            //controller
		            try {
		            	pullrequestForm.load();
					} catch (IOException e) {
						e.printStackTrace();
					}
		            PullRequestController controller = pullrequestForm.getController();
		        	
		        		//create image
		            if (!StringUtils.isEmpty(pr.getAuthor().getAvatarUrl())) {
		            		Image image = new Image(pr.getAuthor().getAvatarUrl());
		            		controller.setImgAvatar(image);
		            }
		            controller.setLblDescription(pr.getDescription());
		            controller.setLblLogin(pr.getAuthor().getLogin());
		            controller.setLblName(pr.getAuthor().getName());
		            controller.setLblTitle(pr.getTitle().toUpperCase());
		            
		            if(pr.isOpen()) {
		            		prStatus.addOpened();
		            } else {
		            		prStatus.addClosed();
		            }
		            
		            list.add(controller.getHboxContainer());
		        });
		        
		        FXMLLoader pullrequestsForm = new FXMLLoader();
		        pullrequestsForm.setLocation(Main.class.getResource(PullRequestsController.FORM_LOCATION));
		        Parent root = null;
				try {
					root = pullrequestsForm.load();
				} catch (IOException e) {
					e.printStackTrace();
				}
		        PullRequestsController controller = pullrequestsForm.getController();
		        
		        controller.setListView(list);
		        controller.setLblTitle(repo.getName());
		        controller.setLblOpened(prStatus.getOpened());
		        controller.setLblClosed(prStatus.getClosed());
		        controller.setPreviousScene(currentScene);
		        controller.setCurrentScene(nextScene);
		        
		        nextScene.setScene(new Scene(root));
		        nextScene.show();
			}
		});
	}
	
	public ComboBox<Integer> getCmbPages() {
		return cmbPages;
	}
	public void setCurrentScene(Stage stage) {
		this.currentScene = stage;
	}
	
	public Button getBtnFind() {
		return btnFind;
	}
	public void setBtnFind(Button btnFind) {
		this.btnFind = btnFind;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Integer> list = Lists.newArrayList();
        for(int i=1; i<=34; i++){ list.add(i); }
        ObservableList<Integer> observableList = FXCollections.observableList(list);
        this.cmbPages.getItems().clear();
        this.cmbPages.setItems(observableList);
        this.cmbPages.getSelectionModel().selectFirst();
        
	}

}
