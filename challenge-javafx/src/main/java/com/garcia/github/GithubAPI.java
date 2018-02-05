package com.garcia.github;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jboss.jandex.Main;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringUtils;

import com.garcia.github.core.SpringConfig;
import com.garcia.github.core.business.RepositoryBusiness;
import com.garcia.github.core.data.entities.Repo;
import com.garcia.github.core.facade.RepositoryFacade;
import com.garcia.github.form.repositories.RepositoriesController;
import com.garcia.github.form.repository.RepositoryController;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Class responsible for load spring beans
 * 
 * @author Luis Garcia
 * @version 1.0
 * @since 05/05/2018
 *
 */
public class GithubAPI extends Application {

	private static final String LANGUAGE = "language:Java";
	private static final String ORDER_BY = "stars";
	private static final int FIRST_PAGE = 1;
	
	public static void main(String[] args) throws IOException {
		launch(args);
	}
	
	@Override
    public void start(Stage primaryStage) throws Exception {
		
		//spring ioc
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		RepositoryFacade facade = context.getBean(RepositoryFacade.class);
		facade.list(LANGUAGE, ORDER_BY, FIRST_PAGE);
		
		//get data
		RepositoryBusiness business = context.getBean(RepositoryBusiness.class);
		List<Repo> repos = business.listBy(FIRST_PAGE);
		
        //repositories page
        List<HBox> list = new ArrayList<>();
        
        //adding repositories
        repos.forEach(repo -> {
        		//start form
            FXMLLoader repositoryForm = new FXMLLoader();
            repositoryForm.setLocation(Main.class.getResource(RepositoryController.FORM_LOCATION));
            
            //controller
            try {
				repositoryForm.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
            RepositoryController controller = repositoryForm.getController();
        	
        		//create image
            if (!StringUtils.isEmpty(repo.getOwner().getAvatarUrl())) {
            		Image image = new Image(repo.getOwner().getAvatarUrl());
            		controller.setImgAvatar(image);
            }
            controller.setLblDescription(repo.getDescription());
            controller.setLblForks(repo.getForks());
            controller.setLblLogin(repo.getOwner().getLogin());
            controller.setLblName(repo.getOwner().getName());
            controller.setLblStars(repo.getStars());
            controller.setLblTitle(repo.getName().toUpperCase());
            
            list.add(controller.getHboxContainer());
        });
        
        
        FXMLLoader repositoriesForm = new FXMLLoader();
        repositoriesForm.setLocation(Main.class.getResource(RepositoriesController.FORM_LOCATION));
        Parent root = repositoriesForm.load();
        RepositoriesController controller = repositoriesForm.getController();
        
        controller.setListView(list);
        controller.setCurrentScene(primaryStage);
        
        controller.getBtnFind().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				controller.getListView().getItems().clear();
				
				//repositories page
		        List<HBox> list2 = new ArrayList<>();
		        
		        //adding repositories
		        int page = controller.getCmbPages().getValue().intValue();
		        try {
					facade.list(LANGUAGE, ORDER_BY, page);
					List<Repo> repos2 = business.listBy(page);
					repos2.forEach(repo -> {
						//start form
						FXMLLoader repositoryForm = new FXMLLoader();
						repositoryForm.setLocation(Main.class.getResource(RepositoryController.FORM_LOCATION));
						
						//controller
						try {
							repositoryForm.load();
						} catch (IOException e) {
							e.printStackTrace();
						}
						RepositoryController controller = repositoryForm.getController();
						
						//create image
						if (!StringUtils.isEmpty(repo.getOwner().getAvatarUrl())) {
							Image image = new Image(repo.getOwner().getAvatarUrl());
							controller.setImgAvatar(image);
						}
						controller.setLblDescription(repo.getDescription());
						controller.setLblForks(repo.getForks());
						controller.setLblLogin(repo.getOwner().getLogin());
						controller.setLblName(repo.getOwner().getName());
						controller.setLblStars(repo.getStars());
						controller.setLblTitle(repo.getName().toUpperCase());
						
						list2.add(controller.getHboxContainer());
					});
					controller.setListView(list2);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
        
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        
    }
	
}
