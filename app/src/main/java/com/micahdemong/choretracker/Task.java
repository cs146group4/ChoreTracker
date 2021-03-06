package com.micahdemong.choretracker;

import java.util.Calendar;

public class Task {
    private String name;
    private String description;
    private boolean isCompleted;
    private int daysPassed;
    private int daysRemaining;

    /**
     * @param n The name of the task
     * @param d The description of the task
     * @param b True if the task is completed, else false
     */
    public Task(String n, String d, boolean b) {
        name = n;
        description = d;
        isCompleted = b;
    }

    public Task(String name, String desc, boolean isCompleted, int daysRemaining){
        this.name = name;
        this.description = desc;
        this.isCompleted = isCompleted;
        this.daysRemaining = daysRemaining;
        this.daysPassed = 0;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean checkComplete() {
        return isCompleted;
    }

    public int getDaysPassed() {
        return daysPassed;
    }

    public int getDaysRemaining() {
        return daysRemaining;
    }

    public void setComplete() {
        isCompleted = true;
    }

    public static String daysConversion(int intDay) {



        /*
		if (intDay == 0)
			stringDay = "today";
		if (intDay == 1)
			stringDay = "tomorrow";
		if (intDay == -1)
			stringDay = "yesterday";
		if (day == 1)
			stringDay = "Sunday";
		if (day == 2)
			stringDay = "Monday";
		if (day == 3)
			stringDay = "Tuesday";
		if (day == 4)
			stringDay = "Wednesday";
		if (day == 5)
			stringDay = "Thursday";
		if (day == 6)
			stringDay = "Friday";
		if (day == 7)
			stringDay = "Saturday";
		if (day == 1 && daysPassed == -7)
			stringDay = "last Sunday";
		if (day == 2 && daysPassed == -7)
			stringDay = "last Monday";
		if (day == 3 && daysPassed == -7)
			stringDay = "last Tuesday";
		if (day == 4 && daysPassed == -7)
			stringDay = "last Wednesday";
		if (day == 5 && daysPassed == -7)
			stringDay = "last Thursday";
		if (day == 6 && daysPassed == -7)
			stringDay = "last Friday";
		if (day == 7 && daysPassed == -7)
			stringDay = "last Saturday";
		if (day == 1 && daysRemaining == 7)
			stringDay = "next Sunday";
		if (day == 2 && daysRemaining == 7)
			stringDay = "next Monday";
		if (day == 3 && daysRemaining == 7)
			stringDay = "next Tuesday";
		if (day == 4 && daysRemaining == 7)
			stringDay = "next Wednesday";
		if (day == 5 && daysRemaining == 7)
			stringDay = "next Thursday";
		if (day == 6 && daysRemaining == 7)
			stringDay = "next Friday";
		if (day == 7 && daysRemaining == 7)
			stringDay = "next Saturday";
			*/
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String stringDay = "";
        if(intDay == -1) stringDay = "Yesterday";
        else if(intDay == 0) stringDay = "Today";
        else if(intDay == 1) stringDay = "Tomorrow";
        else if(intDay < -1) stringDay = Math.abs(intDay) + " days ago";
        else if(intDay > 1) stringDay = intDay + " days from now";

        return stringDay;
    }

    public static String[] dueDateArray() {
        String[] options = new String[8];
        for (int i = 0; i <= 7; i++)
            options[i] = daysConversion(i);
        return options;
    }
}
