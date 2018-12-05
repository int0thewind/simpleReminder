package edu.illinois.cs.cs125.lib;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A class that holds all the task helper function
 * Note that all the function is not static. Whether the methods should be static or not
 * is dependent on how the Activity classes behave
 * All the filter function should be implemented periodically by the Activity class
 */
public class TaskHelper {

    /**
     * A filter function to return a list of task that has its end date as today.
     * @return a list of task that ends today
     */
    public List<Task> isToday() {
        List<Task> toReturn = new ArrayList<Task>();
        for (Task toCheck : Task.getStorage()) {
            if (toCheck.getEndTime() == new Date()) {
                toReturn.add(toCheck);
            }
        }
        return toReturn;
    }

    /**
     * A filter function to return a list of all the starred (favourite) task.
     * @return a list of all starred task
     */
    public List<Task> isStarred() {
        List<Task> toReturn = new ArrayList<Task>();
        for (Task task : Task.getStorage()) {
            if (task.isStar()) {
                toReturn.add(task);
            }
        }
        return toReturn;
    }

    /**
     * A filter function to return a list of all the overdue task
     * @return a list of overdue task
     */
    public List<Task> isOverdue() {
        List<Task> toReturn = new ArrayList<Task>();
        for (Task task : Task.getStorage()) {
            if (task.getEndTime().compareTo(new Date()) > 0) {
                toReturn.add(task);
            }
        }
        return toReturn;
    }

    /**
     * Remove a Task based on its ID.
     * @param id the ID to locate the task to remove
     */
    public void removeTask(int id) {
        for (Task toCheck : Task.getStorage()) {
            if (toCheck.getId() == id) {
                Task.getStorage().remove(toCheck);
            }
        }
    }

    /**
     * Remove a Task by its own reference
     * @param task the task to remove
     */
    public void removeTask(Task task) {
        Task.getStorage().remove(task);
    }
}