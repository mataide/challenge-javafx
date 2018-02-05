package com.garcia.github.form.pullrequest;

/**
 * Class responsible for controlling PR closed/opened
 * 
 * @author Luis Garcia
 * @version 1.0
 * @since 05/05/2018
 *
 */
public class PullRequestStatus {

	private int opened;
	private int closed;
	
	public int getOpened() {
		return opened;
	}
	public void addOpened() {
		this.opened++;
	}
	public int getClosed() {
		return closed;
	}
	public void addClosed() {
		this.closed++;
	}
	
}
