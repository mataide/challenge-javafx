package co.emasters.challengejavafx.model;

import java.util.List;

/**
 * Class description here.
 *
 * author: Olavo.
 * version: 0.1
 */
public class GitHubRepoPage {

  private List<GitHubRepository> items;
  private Integer total_count;

  public List<GitHubRepository> getItems() {
    return items;
  }

  public void setItems(List<GitHubRepository> items) {
    this.items = items;
  }

  public Integer getTotal_count() {
    return total_count;
  }

  public void setTotal_count(Integer total_count) {
    this.total_count = total_count;
  }
}
