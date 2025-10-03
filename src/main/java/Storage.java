import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * Persists and loads tasks from a UTF-8 text file on disk.
 *
 * <p>The file format is line-oriented. Each line encodes one task in the form:
 * <pre>
 * [T|D|E][X| ] description (by: Date/Time)   // Deadline
 * [T|D|E][X| ] description (from: Date/Time)   // Event
 * [T|D|E][X| ] description                     // ToDo
 * </pre>
 *
 */
public class Storage {
    /**
     * Relative file path to the text doc where contents of the Task array are stored.
     */
    private static final Path FILE_PATH = Paths.get("data", "lines.txt");

    /**
     * Ensures that the data directory and file exist. Creates them if missing.
     *
     * @throws IOException if the directory or file cannot be created
     */
    private static void ensureFile() throws IOException {
        Files.createDirectories(FILE_PATH.getParent());
        if (Files.notExists(FILE_PATH)) {
            Files.createFile(FILE_PATH); // empty file
        }
    }

    /**
     * Prints raw contents of the text file to {@code System.out}, prefixed with line numbers.
     *
     * @throws FileNotFoundException if the file cannot be opened
     */
    public static void printFileContents() throws FileNotFoundException {
        try {
            ensureFile();
        } catch (IOException e) {
            Ui.printError(e);
        }
        File f = new File(FILE_PATH.toUri()); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        int k = 1;
        while (s.hasNext()) {
            System.out.println(k + "." + s.nextLine());
            k++;
        }
    }


    /**
     * Converts a serialized line into a {@link Task} object
     *
     * @param line a line of text read from the storage file
     * @return the parsed {@link Task}
     */
    public static Task stringtoTask(String line) {
        char taskType = line.charAt(1);
        char marked = line.charAt(4);
        boolean isMarked = (marked == 'X');
        String task = line.substring(7);
        Task taskToReturn = new Task();
        switch (taskType) {
            case 'T': //Todo type
                taskToReturn = new ToDo(task, isMarked);
                break;
            case 'D': //deadline type
                int startDIndex = line.indexOf('(');
                int endDIndex = line.indexOf(')');
                String deadlineString = line.substring(7, startDIndex - 1);
                String dDate = line.substring(startDIndex + 5, endDIndex);
                taskToReturn = new Deadline(deadlineString, dDate, isMarked);
                break;
            case 'E': //event type
                int startEIndex = line.indexOf('(');
                int endEIndex = line.indexOf(')');
                String eventString = line.substring(7, startEIndex - 1);
                String eDate = line.substring(startEIndex + 7, endEIndex);
                taskToReturn = new Event(eventString, eDate, isMarked);
                break;
        }
        return taskToReturn;
    }

    /**
     * Reads all lines from the storage file, parses them into tasks,
     * and appends them to {@code storedItems}.
     *
     * @param storedItems list to populate with loaded tasks
     * @return the next available index (typically {@code storedItems.size() + 1})
     * @throws FileNotFoundException if the file cannot be opened
     */
    public static int recordFileContents(List<Task> storedItems) throws FileNotFoundException {
        try {
            ensureFile();
        } catch (IOException e) {
            Ui.printError(e);
        }
        File f = new File(FILE_PATH.toUri()); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        int k = 1;
        while (s.hasNext()) {
            storedItems.add(stringtoTask(s.nextLine()));
            k++;
        }
        return k;
    }

    /**
     * Appends text as a new line in the text file.
     *
     * @param textToAppend string to append (already serialized task format)
     * @throws IOException if writing fails
     */
    public static void appendToFile(String textToAppend) throws IOException {
        try {
            ensureFile();
        } catch (IOException e) {
            Ui.printError(e);
        }
        File f = new File(FILE_PATH.toUri());
        FileWriter fw = new FileWriter(FILE_PATH.toFile(), true); // create a FileWriter in append mode
        if (f.length() == 0) { //checking if the contents is empty
            fw.write(textToAppend);
            fw.close();
            return;
        }
        fw.write(System.lineSeparator() + textToAppend);
        fw.close();
    }

    /**
     * Rewrites the storage file, replacing all contents with the provided task list.
     *
     * @param storedItems tasks to write
     * @throws IOException if writing fails
     */
    public static void writeToFile(List<Task> storedItems) throws IOException {
        try {
            ensureFile();
        } catch (IOException e) {
            Ui.printError(e);
        }
        FileWriter fw = new FileWriter(FILE_PATH.toFile());
        for (int i = 0; i < storedItems.size(); i++) {
            fw.write((i == 0 ? "" : System.lineSeparator()) + storedItems.get(i).toType() + storedItems.get(i).markedBox() + storedItems.get(i).toString());
        }
        fw.close();
    }

    /**
     * Function to catch error before appendToFile
     */
    public static void attemptAppendToFile(Task task) {
        try {
            appendToFile(task.toType() + task.markedBox() + task.toString());
        } catch (IOException e) {
            Ui.printError(e);
        }
    }

    /**
     * Function to catch error before printFileContents
     */
    public static void attemptPrintFileContents() {
        try {
            printFileContents();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}