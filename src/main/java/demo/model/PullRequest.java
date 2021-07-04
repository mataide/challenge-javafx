package demo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PullRequest {
	@SerializedName("title")
	@Expose
	private String title;
	@SerializedName("created_at")
	@Expose
	private String created_at;
	@SerializedName("body")
	@Expose
	private String body;
	
	@SerializedName("user")
	@Expose
	private PullUser user;
	
	@SerializedName("url")
	@Expose
	private String url;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public PullUser getUser() {
		return user;
	}

	public void setUser(PullUser user) {
		this.user = user;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
