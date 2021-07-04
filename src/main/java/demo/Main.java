package demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.javafx.application.HostServicesDelegate;
import demo.model.GithubRepo;
import demo.model.PullRequest;
import demo.model.Repo;
import demo.utils.MapUtil;
import demo.utils.RestApi;
import demo.utils.RestApi.GitHubService;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import retrofit2.Call;

public class Main extends Application {

	int rowsPerPage = 30;

	public ListView<String> listView;

	public Pagination pagination;

	public ListView<String> listPullsView;

	private GitHubService service;

	StackPane root;

	Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		try {
			//initialization of variables
			GithubRepo githubRepo = null;
			pagination = new Pagination();
			listView = new ListView<>();
			//point to another stage variable for later use of pull request list
			this.primaryStage = primaryStage;
			
			root = new StackPane();
			
			//Call to retrofit singleton service provider
			RestApi restApi = new RestApi();
			service = restApi.getService();
			//retrieve github repositories			
			Call<GithubRepo> call = service.listRepos("language:Java", "stars", "0");
			try {
				githubRepo = call.execute().body();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//Configuration of pagination control
			pagination.setPageCount(githubRepo.getTotalCount() / rowsPerPage + 1);
			pagination.setCurrentPageIndex(0);
			pagination.setPageFactory(new Callback<Integer, Node>() {
				@Override
				public Node call(Integer pageIndex) {

					return createPage(pageIndex);
				}
			});

			root.getChildren().add(listView);
			root.getChildren().add(pagination);
			primaryStage.setScene(new Scene(root, 400, 500));
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	private Node createPage(int pageIndex) {
		//variable initialization
		GithubRepo githubRepo = null;
		List<String> repoArray = new ArrayList<String>();
		Map<String, String> mapRepo = new HashMap<String, String>();
		Map<String, String> mapImages = new HashMap<String, String>();
		
		Call<GithubRepo> call = service.listRepos("language:Java", "stars", Integer.toString(pageIndex));
		try {
			githubRepo = call.execute().body();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<Repo> repos = githubRepo.getItems();

		for (Repo repo : repos) {
			mapRepo.put("Name", "\nName: " + repo.getName());
			mapRepo.put("Description", "\nDescription: " + repo.getDescription());
			mapRepo.put("Score", "\nScore: " + repo.getScore());
			mapRepo.put("Owner", "\nOwner: " + repo.getOwner().getLogin());

			String row = MapUtil.mapToString(mapRepo);
			mapImages.put(row, repo.getOwner().getAvatarUrl());
			repoArray.add(row);
		}

		listView.setItems(FXCollections.observableArrayList(repoArray));
		
		listView.setCellFactory(param -> new ListCell<String>() {
			
			@Override
			public void updateItem(String name, boolean empty) {
				super.updateItem(name, empty);
				if (empty) {
					setText(null);
					setGraphic(null);
				} else {
					if (mapImages.get(name) != null && !mapImages.get(name).isEmpty()) {
						Image image = new Image(mapImages.get(name));
						ImageView imageView = new ImageView(image);
						imageView.setFitWidth(25);
						imageView.setFitHeight(25);
						setGraphic(imageView);
					}
					setText(name);
				}
			}
		});
		listView.getSelectionModel().selectedItemProperty()
				.addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {

					listPullsView = new ListView<>();
					createPagePulls(new_val);
					root.getChildren().remove(listView);
					root.getChildren().remove(pagination);
					root = new StackPane();
					root.getChildren().add(listPullsView);

					this.primaryStage.setScene(new Scene(root, 400, 500));
					this.primaryStage.show();

				});
		listView.prefHeightProperty().bind(Bindings.size(FXCollections.observableArrayList(repoArray)).multiply(24));
		return listView;
	}

	private Node createPagePulls(String data) {
		List<String> dataArray = new ArrayList<String>(Arrays.asList(data.split("\n")));
		String name = null;
		String owner = null;
		for (String d : dataArray) {
			if (d.startsWith("Name: ")) {
				name = d.substring(6, d.length());
			} else if (d.startsWith("Owner: ")) {
				owner = d.substring(7, d.length());
			}
		}

		List<String> repoArray = new ArrayList<String>();
		Map<String, String> mapRepo = new HashMap<String, String>();
		Map<String, String> mapImages = new HashMap<String, String>();
		Map<String, String> mapUrl = new HashMap<String, String>();
		List<PullRequest> pRequest = null;

		Call<List<PullRequest>> pulls = service.listPulls(owner, name);
		try {
			pRequest = pulls.execute().body();
		} catch (IOException e) {		
			e.printStackTrace();
		}

		for (PullRequest pull : pRequest) {
			mapRepo.put("Title", "\nTitle: " + pull.getTitle());
			mapRepo.put("Created", "\nCreated: " + pull.getCreated_at());
			mapRepo.put("Body", "\nBody: " + pull.getBody());
			mapRepo.put("Author", "\nAuthor: " + pull.getUser().getLogin());

			mapImages.put(MapUtil.mapToString(mapRepo), pull.getUser().getAvatar_url());
			mapUrl.put(MapUtil.mapToString(mapRepo), pull.getUrl());
			repoArray.add(MapUtil.mapToString(mapRepo));
		}

		listPullsView.setItems(FXCollections.observableArrayList(repoArray));
		listPullsView.setCellFactory(param -> new ListCell<String>() {
			
			@Override
			public void updateItem(String name, boolean empty) {
				super.updateItem(name, empty);
				if (empty) {
					setText(null);
					setGraphic(null);
				} else {

					if (mapImages.get(name) != null && !mapImages.get(name).isEmpty()) {
						Image image = new Image(mapImages.get(name));

						ImageView imageView = new ImageView(image);
						imageView.setFitWidth(25);
						imageView.setFitHeight(25);
						setGraphic(imageView);
					}

					setText(name);

				}
			}
		});
		listPullsView.getSelectionModel().selectedItemProperty()
				.addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
					String urlPull = mapUrl.get(new_val);
					HostServicesDelegate hostServices = HostServicesFactory.getInstance(this);
					hostServices.showDocument(urlPull);

				});
		listPullsView.prefHeightProperty()
				.bind(Bindings.size(FXCollections.observableArrayList(repoArray)).multiply(24));
		return listPullsView;
	}
}
