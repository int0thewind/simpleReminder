package edu.illinois.cs.cs125.lib;

import java.io.File;
import java.util.ArrayList;

public final class TaskStorage {
    /**
     * A List to Storage all the task created by the user
     */

    private static ArrayList<Task> storage;

    private static File storate;

    /**
     * A setter to get a list of all the tasks created by the user
     * @return the full list of all the tasks
     */
    public static ArrayList<Task> getStorage() {
        return storage;
    }

    public static void writeFile() {

    }
}
