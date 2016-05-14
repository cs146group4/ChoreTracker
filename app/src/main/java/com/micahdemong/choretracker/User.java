package com.micahdemong.choretracker;

import android.widget.ImageView;

public class User {
	private String name;
	private int tasksCompleted;
	private ImageView image;

	public User(String n) {
		name = n;
		tasksCompleted = 0;
	}

	public ImageView getProfileImage() {
		return image;
	}

	public String getName() {
		return name;
	}

	public int getTasksCompleted() {
		return tasksCompleted;
	}

	public void setName(String n) {
		name = n;
	}

	public void setProfileImage(ImageView i) {
		image = i;
	}

	public void completedTask() {
		tasksCompleted++;
	}

	public void unCompletedTask() {
		tasksCompleted--;
	}
}
