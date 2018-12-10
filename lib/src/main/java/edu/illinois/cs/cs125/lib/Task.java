package edu.illinois.cs.cs125.lib;

import java.text.DateFormat;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * The constructor for all the Task user created.
 * For
 */
public class Task {

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
    private GregorianCalendar notification;

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

    public GregorianCalendar getNotification() {
        return notification;
    }

    public String getNotificationToString() {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
        return dateFormat.format(this.notification);
    }

    public void setNotification(GregorianCalendar notification) {
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

    @Override
    public String toString() {
        return this.getTaskName() + " is due on" + this.getNotificationToString();
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
}