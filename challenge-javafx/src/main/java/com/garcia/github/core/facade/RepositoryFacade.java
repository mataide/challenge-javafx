package com.garcia.github.core.facade;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.garcia.github.core.business.RepositoryBusiness;
import com.garcia.github.core.data.entities.PullRequest;
import com.garcia.github.core.data.entities.Repo;
import com.garcia.github.core.data.entities.User;
import com.garcia.github.core.service.GithubService;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Class responsible for control requests between API x DB
 * 
 * @author Luis Garcia
 * @version 1.0
 * @since 21/01/2018
 *
 */
@Component
public class RepositoryFacade {

	@Autowired
	private RepositoryBusiness business;
	
	private Retrofit retrofit;
	public RepositoryFacade() {
		super();
		this.retrofit = new Retrofit.Builder()
				.baseUrl(GithubService.BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
	}

	private Retrofit getRetrofit() {
		return this.retrofit;
	}

	public void list(String language, String sortBy, int pageNumber) throws IOException {
		
		List<Repo> repos = business.listBy(pageNumber);
		if (repos == null || repos.size() == 0) {
			
			GithubService service = this.getRetrofit().create(GithubService.class);
			Call<JsonElement> reposJson = service.listRepos(language, sortBy, pageNumber);
			JsonElement body = reposJson.execute().body();
			
			JsonArray items = body.getAsJsonObject().get("items").getAsJsonArray();
			if (!items.isJsonNull() && items.size() > 0) {
				items.forEach(item -> {
					Repo repo = new Repo();
					User user = new User();
					
					JsonObject repoJsonObject = item.getAsJsonObject();
					repo.setName(repoJsonObject.get("name").getAsString());
					repo.setDescription(repoJsonObject.get("description").getAsString());
					repo.setForks(repoJsonObject.get("forks_count").getAsInt());
					repo.setStars(repoJsonObject.get("stargazers_count").getAsInt());
					repo.setPage(pageNumber);
					
					JsonElement ownerJsonElement = repoJsonObject.get("owner");
					JsonObject ownerJsonObject = ownerJsonElement.getAsJsonObject();
					user.setLogin(ownerJsonObject.get("login").getAsString());
					user.setAvatarUrl(ownerJsonObject.get("avatar_url").getAsString());
					
					User existingUser = business.findUserBy(user.getLogin());
					if(existingUser == null) {
						repo.setOwner(user);
					} else {
						repo.setOwner(existingUser);
					}
					
					Repo existingRepo = business.findByName(repo.getName());
					if(existingRepo == null) {
						business.add(repo);
					}
				});
				
				this.updateUserData();
			}
		}
	}
	
	public void retrievePullRequestsBy(String user, String repoName) throws IOException {
		
		Repo repo = business.findByName(repoName);
		if (repo == null || 
			repo.getPullrequests() == null ||
			repo.getPullrequests().size() == 0) {
			
			GithubService service = this.getRetrofit().create(GithubService.class);
			Call<JsonElement> reposJson = service.listPullRequests(user, repoName);
			JsonElement body = reposJson.execute().body();
			
			List<PullRequest> prs = Lists.newArrayList();
			JsonArray items = body.getAsJsonArray();
			if (!items.isJsonNull() && items.size() > 0) {
				items.forEach(item -> {
					
					PullRequest pr = new PullRequest();
					User author = new User();
					
					JsonObject prJsonObject = item.getAsJsonObject();
					pr.setDescription(prJsonObject.get("body").getAsString());
					pr.setOpen(prJsonObject.get("closed_at") != null ? false : true);
					pr.setTitle(prJsonObject.get("title").getAsString());
					pr.setAuthor(author);
					
					JsonElement userJsonElement = prJsonObject.get("user");
					JsonObject userJsonObject = userJsonElement.getAsJsonObject();
					author.setLogin(userJsonObject.get("login").getAsString());
					author.setAvatarUrl(userJsonObject.get("avatar_url").getAsString());
					
					User existingUser = business.findUserBy(author.getLogin());
					if(existingUser == null) {
						pr.setAuthor(author);
					} else {
						pr.setAuthor(existingUser);
					}
					
					prs.add(pr);
				});
				repo.setPullrequests(prs);
				this.updateUserData();
				
				business.update(repo);
			}
		}
	}
	
	private void updateUserData() {
		GithubService service = this.getRetrofit().create(GithubService.class);
		List<User> users = business.listMissingUserName();
		users.forEach(user -> {
			Call<JsonElement> userJson = service.getUserInfo(user.getLogin());
			JsonElement body = null;
			try {
				body = userJson.execute().body();
				if (body != null) {
					JsonObject item = body.getAsJsonObject();
					if (!item.isJsonNull() && item.isJsonObject()) {
						String login = item.get("login").getAsString();
						String name = item.get("name").isJsonNull() ? "" : item.get("name").getAsString();
						business.findAndUpdateUserBy(login, name);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (UnsupportedOperationException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		});
	}

}
