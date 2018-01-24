package co.emasters.challengejavafx.model;

import java.util.Date;

/**
 * Class description here.
 *
 * author: Olavo.
 * version: 0.1
 */
public class GitHubPullRequest {

  private String title;
  private String body;
  private Date created_at;
  private Date updated_at;
  private GitHubAuthor user;
  private String url;
  private String html_url;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public Date getCreated_at() {
    return created_at;
  }

  public void setCreated_at(Date created_at) {
    this.created_at = created_at;
  }

  public Date getUpdated_at() {
    return updated_at;
  }

  public void setUpdated_at(Date updated_at) {
    this.updated_at = updated_at;
  }

  public GitHubAuthor getUser() {
    return user;
  }

  public void setUser(GitHubAuthor user) {
    this.user = user;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getHtml_url() {
    return html_url;
  }

  public void setHtml_url(String html_url) {
    this.html_url = html_url;
  }
}
