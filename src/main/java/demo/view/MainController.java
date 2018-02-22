/*package demo.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import demo.model.GithubRepo;
import demo.model.Repo;
import demo.utils.RestApi;
import demo.utils.RestApi.GitHubService;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import retrofit2.Call;

public class MainController implements Initializable{
	int rowsPerPage = 30;
	
	@FXML
	public ListView<String> listView;
	@FXML
	public Pagination pagination;


	private ObservableList<String> repositories =null;
	
	private GitHubService service;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		RestApi restApi = new RestApi();
		service = restApi.getService();
		GithubRepo githubRepo=null;
		Call <GithubRepo> call = service.listRepos("language:Java","stars","1");
		try {
			githubRepo = call.execute().body();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pagination.setPageCount(githubRepo.getTotalCount()/rowsPerPage+1);
	    pagination.setCurrentPageIndex(0);
	   
		pagination.setPageFactory(new Callback<Integer, Node>() {
			
			@Override
			public Node call(Integer pageIndex) {
				
				return createPage(pageIndex);
			}
		});
		
		
		
		

	}

	private Node createPage(int pageIndex) {
		GithubRepo githubRepo = null;
		List<String> repoArray = new ArrayList<String>();
		Call <GithubRepo> call = service.listRepos("language:Java","stars",Integer.toString(pageIndex));
		try {
			githubRepo = call.execute().body();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Repo> repos  = githubRepo.getItems();
		StringBuffer item = new StringBuffer();
		for(Repo repo: repos)
		{
			item.delete(0, item.length());
			String name = repo.getName();
			String desc = repo.getDescription();
			item.append(name);
			//item.append(desc);
			repoArray.add(item.toString());
		}
		
		
	    listView.setItems(FXCollections.observableArrayList(repoArray));
	    listView.prefHeightProperty().bind(Bindings.size(FXCollections.observableArrayList(repoArray)).multiply(24));
	    return listView;
	}
	
	
	

}
*/