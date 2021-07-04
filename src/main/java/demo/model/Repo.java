package demo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Repo {

	@SerializedName("name")
	@Expose
	private String name;
	
	@SerializedName("full_name")
	@Expose
	private String full_name;

	@SerializedName("owner")
	@Expose
	private Owner owner;
	@SerializedName("forks_url")
	@Expose
	private String forks_url;
	@SerializedName("private")
	@Expose
	private boolean _private;
	@SerializedName("html_url")
	@Expose
	private String htmlUrl;
	@SerializedName("description")
	@Expose
	private String description;
	@SerializedName("forks_count")
	@Expose
	private String forks_count;
	
	@SerializedName("url")
	@Expose
	private String url;

	@SerializedName("score")
	@Expose
	private float score;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public boolean is_private() {
		return _private;
	}

	public void set_private(boolean _private) {
		this._private = _private;
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getForks_url() {
		return forks_url;
	}

	public void setForks_url(String forks_url) {
		this.forks_url = forks_url;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getForks_count() {
		return forks_count;
	}

	public void setForks_count(String forks_count) {
		this.forks_count = forks_count;
	}
	



}
