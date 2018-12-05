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
     * A helper function to get all task.
     * @return a list of all the task user created
     */
    public static List<Task> getAllTask() {
        return TaskStorage.getStorage();
    }

    /**
     * A filter function to return a list of all the overdue task
     * @return a list of overdue task
     */
    public static List<Task> isOverdue() {
        List<Task> toReturn = new ArrayList<>();
        for (Task task : TaskStorage.getStorage()) {
            if (task.getNotification().compareTo(new Date()) > 0) {
                toReturn.add(task);
            }
        }
        return toReturn;
    }

    /**
     * Remove a Task based on its ID.
     * @param id the ID to locate the task to remove
     */
    public static void removeTask(int id) {
        for (Task toCheck : TaskStorage.getStorage()) {
            if (toCheck.getId() == id) {
                TaskStorage.getStorage().remove(toCheck);
            }
        }
    }

    /**
     * Remove a Task by its own reference
     * @param task the task to remove
     */
    public static void removeTask(Task task) {
        TaskStorage.getStorage().remove(task);
    }
}
