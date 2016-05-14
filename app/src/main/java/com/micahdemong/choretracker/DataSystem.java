package com.micahdemong.choretracker;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSystem {
	private ArrayList<Task> tasks;
	private User user;

	public DataSystem() {
		tasks = new ArrayList<Task>();
		user = null;
	}

	public void createUser(String name) {
		User u = new User(name);
		user = u;
	}

	/*
	 * Creates a task, writes it to a data file, then sorts the tasks ArrayList.
	 * You may change the file path/name accordingly.
	 */
	public void createTask(String name, String description, boolean isComplete) {
		Task t = new Task(name, description, isComplete);
		tasks.add(t);
		t.setCreator(user.getName());
		sort(tasks);
		try {
			PrintWriter out = new PrintWriter("tasks.txt");

			out.println(name);
			out.println(description);
			out.println(t.checkComplete());
			out.println(t.getCreator());
			out.println(t.getCompletor());

			out.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 * Reads all data from file and adds it to tasks ArrayList, then sorts it.
	 */
	public void loadTasks(String filename) {
		try {
			File inputFile2 = new File(filename);
			Scanner in = new Scanner(inputFile2);

			while (in.hasNextLine()) {
				String name = in.nextLine();
				String description = in.nextLine();
				String completionStatus = in.nextLine();
				String creator = in.nextLine();
				String completor = in.nextLine();
				boolean isComplete = Boolean.parseBoolean(completionStatus);

				Task t = new Task(name, description, isComplete);
				t.setCreator(creator);
				t.setCompletor(completor);
				tasks.add(t);
			}
			sort(tasks);
			in.close();
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
	
	public void clearOldTasks() {
		int index = 0;
		for (Task t : tasks) {
			if (t.getDaysPassed() == -7 || t.getDaysRemaining() == 7) {
				tasks.remove(index);
			}
			index++;
		}
	}

	public void completeTask(Task t) {
		user.completedTask();
		t.setComplete();
		t.setCompletor(user.getName());
	}

	/*
	 * Custom selection sort to sort the tasks ArrayList and append tasks older than 6 days to the end of the ArrayList.
	 */
	public ArrayList<Task> sort(ArrayList<Task> t) {
		for (int i = 0; i < t.size() - 1; i++) {
			int index = i;
			for (int j = i + 1; j < t.size(); j++) {
				if (t.get(j).getDaysPassed() < t.get(index).getDaysPassed()) {
					index = j;
				}
			}
			Task smaller = t.get(index);
			t.set(index, t.get(i));
			t.set(i, smaller);
			if (t.get(i).getDaysRemaining() >= 7 || t.get(i).getDaysPassed() <= -7) {
				t.set(t.size(), t.get(i));
			}
		}
		return t;
	}

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
