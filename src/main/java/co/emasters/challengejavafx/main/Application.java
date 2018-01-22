package co.emasters.challengejavafx.main;

import co.emasters.challengejavafx.github.GitHubService;
import co.emasters.challengejavafx.model.GitHubPullRequest;
import co.emasters.challengejavafx.model.GitHubRepoPage;
import co.emasters.challengejavafx.model.GitHubRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class description here.
 *
 * author: Olavo.
 * version: 0.1
 */
public class Application {

  public static void main(String[] args) throws IOException {

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    GitHubService service = retrofit.create(GitHubService.class);

//    Call<List<GitHubPullRequest>> call = service.listPullRequests("jquery", "jquery");
//
//    Response<List<GitHubPullRequest>> res = call.execute();
//
//    if(res.isSuccessful()){
//      List<GitHubPullRequest> list = res.body();
//      list.forEach(l -> System.out.println(l.getTitle()));
//    }

    Call<GitHubRepoPage> listCall = service.listRepos("language:Java", "stars", 1);

    GitHubRepoPage responses = listCall.execute().body();

    System.out.println(responses.getTotal_count());

    List<GitHubRepository> items = responses.getItems();

    items.forEach(i -> System.out.println(i.getFull_name()));
//
//    if(responses != null) {
//      String response = responses.get("items").toString();
//
//      Type listType = new TypeToken<ArrayList<GitHubRepository>>(){}.getType();
//
//      Gson gson = new Gson();
//      List<GitHubRepository> list = gson.fromJson(response, listType);
//
//      list.forEach(l -> System.out.println(l.getFull_name()));
//    }
  }
}
