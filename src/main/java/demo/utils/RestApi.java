package demo.utils;

import java.util.List;

import demo.model.GithubRepo;
import demo.model.PullRequest;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RestApi {

	private static final String GITHUB_ENDPOINT = "https://api.github.com";
	private static GitHubService service;

	public interface GitHubService {
		@GET("/search/repositories")
		Call <GithubRepo> listRepos(@Query("q") String q, @Query("short") String shor, @Query("page") String page);
		
		@GET("/repos/{author}/{name}/pulls")
		Call <List<PullRequest>> listPulls(@Path("author") String author,@Path("name") String name);
		
	}

	public RestApi() {
		
		if(service==null)
		{
			OkHttpClient httpClient = new OkHttpClient();
			HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
			logging.setLevel(Level.BODY);
			//httpClient.addInterceptor(logging);
			
			
			Retrofit.Builder builder = new Retrofit.Builder().baseUrl(GITHUB_ENDPOINT)
					.addConverterFactory(GsonConverterFactory.create()).client(httpClient);
			Retrofit retrofit = builder.build();
			service = retrofit.create(GitHubService.class);
		}
	}

	public GitHubService getService() {
		return service;
	}

}



