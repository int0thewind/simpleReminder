package edu.illinois.cs.cs125.lib;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * The constructor for all the Task user created.
 * For
 */
public class Task implements Comparable<Task> {

    /**
     * This String stores the name of the task.
     */
    private String taskName;

    /**
     * The status about whether a task is marked as finished by user or not.
     */
    private boolean isFinish = false;

    /**
     * This is the notification time of one Task
     */
    private Calendar notification;

    /**
     * This defines the priority of this Task based on the enum Priority.
     */

    /**
     * The unique id of each task
     */
    private int id;

    /**
     * The total number of tasks created
     * This works with the unique id generation
     */
    private static int totalTask;

    /**
     * A pattern for Invalid input
     * This reject all the inputs which contains only empty spaces
     */
    private static final String INVALID_INPUT = "\\ ";

    /**
     * The name of a new task by default;
     */
    private static final String DEFAULT_TASK_NAME = "New Task";

    /**
     * Initial constructor that has no String as reference
     * Automatically name this task as New Task
     * Note that this constructor does not add task to the TaskStorage
     * You may need to indicate this in the Activity java class file
     */
    public Task() {
        try {
            this.taskName = DEFAULT_TASK_NAME;
            this.id = totalTask + 1;
            totalTask++;
        } catch (Exception e) {
            System.out.println("Invalid input, please input at least a character for a task");
        }
    }

    /**
     * Initial constructor that takes a String value as the name of the Task
     * @param setTaskName the name of the Task
     * Note that this constructor does not add task to the TaskStorage
     * You may need to indicate this in the Activity java class file
     */
    public Task(String setTaskName) {
        if (setTaskName.equals("") || setTaskName.equals(INVALID_INPUT)) {
            new Task();
            return;
        }
        try {
            this.taskName = setTaskName;
            this.id = totalTask + 1;
            totalTask++;
        } catch (Exception e) {
            System.out.println("Invalid input, please input at least a character for a task");
        }
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Calendar getNotification() {
        return notification;
    }

    public String getNotificationToString() {
        Calendar now = this.getNotification();

        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);

        return year + "." + month + "." + day + "." + hour + "." + minute;
    }


    public void setNotification(Calendar notification) {
        this.notification = notification;
    }

    public int getId() {
        return id;
    }

    public void isFinish() {
        this.isFinish = true;
    }

    public void isNotFinish() {
        this.isFinish = false;
    }

    public boolean getFinish() {
        return this.isFinish;
    }

    @Override
    public String toString() {
        if (!this.getFinish()) {
            return this.getNotificationToString() + " - " + this.getTaskName();
        }
        return "[Finished];" + this.getNotificationToString() + " - " + this.getTaskName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Task task) {
        if (task == null) {
            throw new IllegalArgumentException();
        }
        return this.getNotification().compareTo(task.getNotification());
    }

}