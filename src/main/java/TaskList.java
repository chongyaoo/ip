import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the in-memory list of {@link Task} objects and provides
 * operations to mutate and query them (add, delete, mark, find).
 *
 * <p>This class is integrated with {@link Storage} to keep the file synchronized.
 */
public class TaskList {
    /**
     * Dynamic array of Tasks which stores the Tasks that have been added so far
     */
    static List<Task> storedItems = new ArrayList<>();

    /**
     * index keeps track of the size of the dynamic array
     */
    int index = 1;

    /**
     * Deletes a task at the given index.
     *
     * @param parts command arguments containing the task index
     * @throws IllegalArgumentException if no valid index is provided
     */
    public void handleDelete(String[] parts) throws IllegalArgumentException {
        int itemToDelete = Parser.parseIndexForDelete(parts);
        if (itemToDelete < index && itemToDelete >= 1) {
            Task deletedTask = storedItems.get(itemToDelete - 1);
            storedItems.remove(itemToDelete - 1);
            Ui.printDeletedTask(deletedTask);
            try {
                Storage.writeToFile(storedItems);
            } catch (IOException e) {
                Ui.printError(e);
            }
            index--;
            Ui.handleList(storedItems, index);
            return;
        }
        System.out.println("Task " + itemToDelete + " has not been created!");
        throw new IllegalArgumentException();
    }

    /**
     * Adds an Event task, validating presence of description and "/from".
     *
     * @param parts command arguments
     * @throws IllegalArgumentException if arguments format is incorrect
     */
    public void handleEvent(String[] parts) throws IllegalArgumentException {
        if (parts.length <= 1) {
            System.out.println("Description for Event cannot be empty!");
            throw new IllegalArgumentException();
        }
        String[] words1 = parts[1].split("\\s+");
        int j = 0;
        int fromIndex = Parser.findFromIndex(words1);
        if (fromIndex != -1) {
            if (fromIndex == 0) { //no event input
                System.out.println("Please input an event!");
                throw new IllegalArgumentException();
            }
            if (fromIndex == words1.length - 1) {
                System.out.println("Please input the timing of the event!");
                throw new IllegalArgumentException();
            }
            Event eventTask = Parser.getEvent(fromIndex, words1);
            storedItems.add(index - 1, eventTask);
            Ui.printTask(eventTask, index);
            Storage.attemptAppendToFile(eventTask);
            index++;
            return;
        }
        System.out.println("Please include keyword '/from'");
        throw new IllegalArgumentException();
    }

    /**
     * Adds a Deadline task, validating presence of description and "/by".
     *
     * @param parts command arguments
     * @throws IllegalArgumentException if arguments format is incorrect
     */
    public void handleDeadline(String[] parts) throws IllegalArgumentException {
        if (parts.length <= 1) {
            System.out.println("Description for Deadline cannot be empty!");
            throw new IllegalArgumentException();
        }
        String[] words = parts[1].split("\\s+");
        int byIndex = -1;
        int i = 0;
        while (byIndex == -1 && i < words.length) {
            if (words[i].equalsIgnoreCase("/by")) {
                byIndex = i;
            }
            i++;
        }
        if (byIndex != -1) {
            if (byIndex == 0) { //no event input
                System.out.println("Please input a deadline event!");
                throw new IllegalArgumentException();
            }
            if (byIndex == words.length - 1) {
                System.out.println("Please input the deadline!");
                throw new IllegalArgumentException();
            }
            Deadline deadlineTask = Parser.getDeadline(byIndex, words);
            storedItems.add(index - 1, deadlineTask);
            Ui.printTask(deadlineTask, index);
            Storage.attemptAppendToFile(deadlineTask);
            index++;
            return;
        }
        System.out.println("Please include keyword '/by'");
        throw new IllegalArgumentException();
    }

    /**
     * Adds a ToDo task.
     *
     * @param parts command arguments
     * @throws IllegalArgumentException if arguments format is incorrect
     */
    public void handleToDo(String[] parts) throws IllegalArgumentException {
        if (parts.length <= 1) {
            System.out.println("Description for ToDo cannot be empty!");
            throw new IllegalArgumentException();
        }
        ToDo todoTask = new ToDo(parts[1], false);
        storedItems.add(index - 1, todoTask);
        Ui.printTask(todoTask, index);
        Storage.attemptAppendToFile(todoTask);
        index++;
    }

    /**
     * Marks a task as uncompleted, updating file storage.
     */
    public void handleUnmark(String[] parts) {
        int itemToUnmark;
        try {
            itemToUnmark = Parser.parseIndexForMark(parts);
        } catch (IllegalArgumentException e) {
            return;
        }
        if (itemToUnmark < index && itemToUnmark >= 1) {
            storedItems.get(itemToUnmark - 1).setMarked(false);
            Ui.printUnmarkTask(itemToUnmark, storedItems);
            try {
                Storage.writeToFile(storedItems);
            } catch (IOException e) {
                Ui.printError(e);
            }
            return;
        }
        Ui.outOfRangeError(itemToUnmark);
    }

    /**
     * Marks a task as completed, updating file storage.
     */
    public void handleMark(String[] parts) {
        int itemToMark;
        try {
            itemToMark = Parser.parseIndexForMark(parts);
        } catch (IllegalArgumentException e) {
            return;
        }
        if (itemToMark < index && itemToMark >= 1) {
            storedItems.get(itemToMark - 1).setMarked(true);
            Ui.printMarkTask(itemToMark, storedItems);
            try {
                Storage.writeToFile(storedItems);
            } catch (IOException e) {
                Ui.printError(e);
            }
            return;
        }
        Ui.outOfRangeError(itemToMark);
    }

    /**
     * Calls Ui to list the Tasks in storedItems
     */
    public void handleList() {
        Ui.handleList(storedItems, index);
    }

    /**
     * Load contents in FILE_PATH to storedItems, upon initialization
     */
    public void attemptInitialFileLoad() {
        try {
            index = Storage.recordFileContents(storedItems);
        } catch (FileNotFoundException e) {
            System.out.println("Text file not found!");
            return;
        }
    }

    /**
     * Finds tasks whose descriptions contain the search term
     * and prints them with their indices.
     *
     * @param parts command arguments, parts[1] should contain the search keyword
     */
    public void handleFind(String[] parts) {
        if (parts.length == 1) {
            Ui.findArgumentError();
            return;
        }
        Ui.printFindHeader();
        int index = 1; //to count task index
        for (Task t : storedItems) {
            String taskDescription = t.toString();
            if (taskDescription.contains(parts[1])) {
                Ui.printIndivTask(t, index);
            }
            index++;
        }
    }
}
