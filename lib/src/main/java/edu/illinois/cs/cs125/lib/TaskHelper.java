package edu.illinois.cs.cs125.lib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
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
    public static List<Task> isAtTheTime() {
        List<Task> toReturn = new ArrayList<>();
        for (Task task : TaskStorage.getStorage()) {
            if (task.getNotification() != null || task.getFinish() == false) {
                if (task.getNotification().compareTo(new GregorianCalendar()) >= 0) {
                    toReturn.add(task);
                }
            }
        }
        Collections.sort(toReturn);
        return toReturn;
    }

    public static String[] isAtTheTimeTitle() {
        List<Task> toCheck = isAtTheTime();
        String[] toReturn = new String[toCheck.size()];
        if (toCheck.size() == 0) {
            return toReturn;
        }
        for (int i = 0; i < toReturn.length; i++) {
            toReturn[i] = toCheck.get(i).getTaskName();
        }
        return toReturn;
    }

    public static String[] isAtTheTimeDate() {
        List<Task> toCheck = isAtTheTime();
        String[] toReturn = new String[toCheck.size()];
        if (toCheck.size() == 0) {
            return toReturn;
        }
        for (int i = 0; i < toReturn.length; i++) {
            toReturn[i] = toCheck.get(i).getNotificationToString();
        }
        return toReturn;
    }

    /*public static boolean isZero() {
        return TaskStorage.getStorage().size() == 0;
    }*/

    /*public static String[] returnTaskName() {
        if (isZero()) {
            return new String[0];
        }

        String[] strings = new String[TaskStorage.getStorage().size()];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = TaskStorage.getStorage().get(i).toString();
        }
        return strings;
    }*/


    /******************/

    public static void setFinish(int id) {
        for (Task toCheck : TaskStorage.getStorage()) {
            if (toCheck.getId() == id) {
                toCheck.isFinish();
            }
        }
    }

    public static void setFinish(Task task) {
        for (Task toCheck : TaskStorage.getStorage()) {
            if (toCheck.equals(task)) {
                toCheck.isFinish();
            }
        }
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
