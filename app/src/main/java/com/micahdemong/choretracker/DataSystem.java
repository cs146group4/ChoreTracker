package com.micahdemong.choretracker;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSystem {
	private ArrayList<Task> tasks;

    public static final String TASK_FILENAME = "tasks.txt";

	public DataSystem() {
		tasks = new ArrayList<Task>();
	}

	/*
	 * Creates a task, writes it to a data file, then sorts the tasks ArrayList.
	 * You may change the file path/name accordingly.
	 */
	public void createTask(String name, String description, boolean isComplete, Context context) {
		Task t = new Task(name, description, isComplete);
		tasks.add(t);
		sort(tasks);
		try {
            FileOutputStream fos = context.openFileOutput(TASK_FILENAME, Context.MODE_APPEND);
			PrintWriter out = new PrintWriter(fos);

			out.println(name);
			out.println(description);
			out.println(t.checkComplete());
			out.println(t.getDaysRemaining());

			out.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

    public void saveTask(Task t, Context context){
        tasks = sort(tasks);
		try {
            FileOutputStream fos = context.openFileOutput(TASK_FILENAME, Context.MODE_APPEND);
            PrintWriter out = new PrintWriter(fos);

            out.println(t.getName());
            out.println(t.getDescription());
            out.println(t.checkComplete());
			out.println(t.getDaysRemaining());

            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addToList(Task t){
        tasks.add(t);
        tasks = sort(tasks);
    }

	/*
	 * Reads all data from file and adds it to tasks ArrayList, then sorts it.
	 */
	public void loadTasks(Context context) {
		try {
            FileInputStream fis = context.openFileInput(TASK_FILENAME);
			Scanner in = new Scanner(fis);

			while (in.hasNextLine()) {
				String name = in.nextLine();
				String description = in.nextLine();
				String completionStatus = in.nextLine();
				String daysRemaining = in.nextLine();

				boolean isComplete = Boolean.parseBoolean(completionStatus);
				int daysRem = Integer.parseInt(daysRemaining);

				Task t = new Task(name, description, isComplete, daysRem);
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
		t.setComplete();
	}

	/*
	 *Custom selection sort to sort the tasks ArrayList and append tasks older than 6 days to the end of the ArrayList.
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

    public void saveTasks(Context context){
        try {
            FileOutputStream fos = context.openFileOutput(TASK_FILENAME, Context.MODE_PRIVATE);
            PrintWriter out = new PrintWriter(fos);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(Task t : tasks)
            saveTask(t,context);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void deleteAllTasks(Context context){
        tasks = new ArrayList<>();
        saveTasks(context);
    }
}
