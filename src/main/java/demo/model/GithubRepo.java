package demo.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GithubRepo {
	@SerializedName("total_count")
	@Expose
	private int totalCount;

	@SerializedName("items")
	@Expose
	private List<Repo> items = null;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<Repo> getItems() {
		return items;
	}

	public void setItems(List<Repo> items) {
		this.items = items;
	}



}
