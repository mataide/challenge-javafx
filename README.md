# challenge-javafx

# Create a [GitHub](https://github.com) API query application #

Create an application to query the [GitHub API] (https://developer.github.com/v3/) and bring in the most popular Java repositories. Rely on the mockup provided:

![bitbucket.png](https://bitbucket.org/repo/bApLBb/images/1070562783-bitbucket.png)
### **Deve conter** ###

- __List of repositories__. Example call in API: `https://api.github.com/search/repositories?q=language:Java&sort=stars&page=1`
  * Pagination on the list screen (incrementing the `page` parameter).
  * Each repository should display Repository Name, Repository Description, Author Name / Photo, Number of Stars, Number of Forks
  * When touching an item, you should bring the Pull Requests list from the repository
- __Pull Requests from a repository__. Example call in API: `https://api.github.com/repos/<criador>/<repositório>/pulls`
  * Each item on the list should display Name / Photo of the PR author, PR Title, Date of PR and Body of PR
  * When touching an item, you should open in the browser the page of the Pull Request in question

### **The solution MUST contain** ##

* File .gitignore
* Use JavaFX with Java 8 and create a desktop application.
* Management of dependencies in the project. Ex: Gradle
* Framework for Communication with API. Ex:  Retrofit
* Internal database. Ex h2+hibernate

### **Earn + points if it contains** ###

* Unit tests in the project. Ex: TestFX

### **OBS** ###

The mockup photo is merely illustrative.
 

### **Submission process** ###

The candidate must implement the solution and send a pull request to this repository with the solution.

The Pull Request process works as follows:

1. Candidate will fork this repository (will not clone directly!)
2. Will do your project on that fork.
3. Commitará e subirá as alterações para o __YOUR__ fork.
4. Through the github interface, you will send a Pull Request.

If possible leave the repository public to facilitate inspection of the code.

### **ATTENTION** ###

You should not try to make PUSH directly into THIS repository!

xxxxxxxxxxcvssddwwqqqddd
