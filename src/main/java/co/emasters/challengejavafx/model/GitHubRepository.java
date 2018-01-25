package co.emasters.challengejavafx.model;

/**
 * Model class for GitHub repositories.
 *
 * author: Olavo Holanda
 * version: 0.1
 */
public class GitHubRepository {

  private String name;
  private String full_name;
  private String html_url;
  private String language;
  private String description;
  private Integer stargazers_count;
  private Integer forks_count;
  private Integer watchers_count;
  private GitHubAuthor owner;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFull_name() {
    return full_name;
  }

  public void setFull_name(String full_name) {
    this.full_name = full_name;
  }

  public String getHtml_url() {
    return html_url;
  }

  public void setHtml_url(String html_url) {
    this.html_url = html_url;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getStargazers_count() {
    return stargazers_count;
  }

  public void setStargazers_count(Integer stargazers_count) {
    this.stargazers_count = stargazers_count;
  }

  public Integer getForks_count() {
    return forks_count;
  }

  public void setForks_count(Integer forks_count) {
    this.forks_count = forks_count;
  }

  public Integer getWatchers_count() {
    return watchers_count;
  }

  public void setWatchers_count(Integer watchers_count) {
    this.watchers_count = watchers_count;
  }

  public GitHubAuthor getOwner() {
    return owner;
  }

  public void setOwner(GitHubAuthor owner) {
    this.owner = owner;
  }
}
