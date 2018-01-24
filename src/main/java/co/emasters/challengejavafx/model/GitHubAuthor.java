package co.emasters.challengejavafx.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Class description here.
 *
 * author: Olavo.
 * version: 0.1
 */
@Entity
public class GitHubAuthor {

  @Id
  private String login;
  private String avatar_url;
  private String url;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  private Set<GitHubPullRequest> requests = new HashSet<>();

  public Set<GitHubPullRequest> getRequests() {
    return requests;
  }

  public void setRequests(Set<GitHubPullRequest> requests) {
    this.requests = requests;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getAvatar_url() {
    return avatar_url;
  }

  public void setAvatar_url(String avatar_url) {
    this.avatar_url = avatar_url;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
