package edu.illinois.cs.cs125.lib;

import java.lang.reflect.Array;
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
     *
     * @return a list of all the task user created
     */
    public static ArrayList<Task> getAllTask() {
        return TaskStorage.getStorage();
    }

    public static void restoreAllTask(ArrayList<Task> toStore) {
        if (toStore == null) {
            TaskStorage.setStorage(new ArrayList<Task>());
        }
        TaskStorage.setStorage(toStore);
    }

    /**
     * A filter function to return a list of all the overdue task
     *
     * @return a list of overdue task
     */
    public static List<Task> isAtTheTime() {
        List<Task> toReturn = new ArrayList<>();
        for (Task task : TaskStorage.getStorage()) {
            if (task.getNotification() != null && !task.getFinish()) {
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

    /**
     * Set a specific task's finish status as finish
     * @param index index from the ListView, which is the same index to the ArrayList
     */

    public static void reverseFinish(int index) {
        try {
            Task task = TaskStorage.getStorage().get(index);
            task.reverseFinish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void reverseNotified(int index) {
        try {
            Task task = TaskStorage.getStorage().get(index);
            task.reverseNotified();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}