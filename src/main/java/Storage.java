import java.io.*;
import java.util.*;

public class Storage {
    private static final String FILE_PATH = "src/main/lines.txt";

    public static void printFileContents() throws FileNotFoundException {
        File f = new File(FILE_PATH); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        int k = 1;
        while (s.hasNext()) {
            System.out.println(k + "." + s.nextLine());
            k++;
        }
    }

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

    public static int recordFileContents(List<Task> storedItems) throws FileNotFoundException {
        File f = new File(FILE_PATH); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        int k = 1;
        while (s.hasNext()) {
            storedItems.add(stringtoTask(s.nextLine()));
            k++;
        }
        return k;
    }


    public static void appendToFile(String textToAppend) throws IOException {
        File f = new File(FILE_PATH);
        FileWriter fw = new FileWriter(FILE_PATH, true); // create a FileWriter in append mode
        if (f.length() == 0) { //checking if the contents is empty
            fw.write(textToAppend);
            fw.close();
            return;
        }
        fw.write(System.lineSeparator() + textToAppend);
        fw.close();
    }

    public static void writeToFile(List<Task> storedItems) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (int i = 0; i < storedItems.size(); i++) {
            fw.write((i == 0 ? "" : System.lineSeparator()) + storedItems.get(i).toType() + storedItems.get(i).markedBox() + storedItems.get(i).toString());
        }
        fw.close();
    }

    public static void attemptAppendToFile (Task task) {
        try {
            appendToFile(task.toType() + task.markedBox() + task.toString());
        } catch (IOException e) {
            Ui.printError(e);
        }
    }

    public static void attemptPrintFileContents () {
        try {
            printFileContents();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}