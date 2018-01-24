package co.emasters.challengejavafx.model;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Class description here.
 *
 * author: Olavo.
 * version: 0.1
 */
@Entity
public class GitHubPullRequest {

  @Id
  @GeneratedValue
  private Long id;
  private String repoId;
  private String title;
  @Column(columnDefinition = "TEXT")
  private String body;
  private Date created_at;
  private Date updated_at;
  private String url;
  private String html_url;
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private GitHubAuthor user;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRepoId() {
    return repoId;
  }

  public void setRepoId(String repoId) {
    this.repoId = repoId;
  }

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
