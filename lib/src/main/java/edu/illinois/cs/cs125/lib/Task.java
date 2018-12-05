package edu.illinois.cs.cs125.lib;

import java.util.ArrayList;
import java.util.Date;

/**
 * The constructor for all the Task user created.
 * For
 */
public class Task {
    /**
     * A static method to store all the task the user created
     * The integer of the task linked is the unique id of the Task
     */
    private static ArrayList<Task> storage;

    /**
     * This String stores the name of the task.
     */
    private String taskName;

    /**
     * This is the start date of one Task
     */
    private Date startTime;
    /**
     * This is the end date of one Task
     */
    private Date endTime;
    /**
     * This is the notification time of one Task
     */
    private Date notification;
    /**
     * This defines the priority of this Task based on the enum Priority.
     */
    private Priority priority;

    /**
     * This enum value Priority provides three types of importance of one Task
     */
    public enum Priority {
        /**
         * The highest priority
         */
        High,
        /**
         * The medium priority
         */
        Medium,
        /**
         * The lowest priority
         */
        Low;
    }

    /**
     * Whether or not the user marked a favourite sign on the Task
     */
    private boolean star;

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
     * Initial constructor that takes a String value as the name of the Task
     * @param setTaskName the name of the Task
     */
    public Task(String setTaskName) {
        if (setTaskName.equals("") || setTaskName.equals(INVALID_INPUT)) {
            throw new IllegalArgumentException();
        }
        try {
            this.taskName = setTaskName;
            this.id = totalTask + 1;
            totalTask++;
            storage.add(this);
        } catch (Exception e) {
            System.out.println("Invalid input, please input at least a character for a task");
        }
    }

    //TODO Finish all the getter and setter


    public static ArrayList<Task> getStorage() {
        return storage;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getNotification() {
        return notification;
    }

    public void setNotification(Date notification) {
        this.notification = notification;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public boolean isStar() {
        return star;
    }

    public void setStar(boolean star) {
        this.star = star;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}