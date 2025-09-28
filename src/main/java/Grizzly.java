import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;


public class Grizzly {
    private static final String FILE_PATH = "src/main/lines.txt";

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Parser parser = new Parser();
        Ui.handleGreeting(); //start with greeting
        taskList.attemptInitialFileLoad();
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while (true) {
            String[] parts = parser.parseInput(line);
            switch (parts[0]) {
                case "delete":
                    try {
                        taskList.handleDelete(parts);
                    } catch (IllegalArgumentException e) {
                        break;
                    }
                    break;
                case "bye":
                    Ui.caseBye();
                    return;
                case "mark":
                    taskList.handleMark(parts);
                    break;
                case "unmark":
                    taskList.handleUnmark(parts);
                case "list":
                    taskList.handleList();
                    break;
                case "todo":
                    try {
                        taskList.handleToDo(parts);
                    } catch (IllegalArgumentException e) {
                        break;
                    }
                    break;
                case "deadline":
                    try {
                        taskList.handleDeadline(parts);
                    } catch (IllegalArgumentException e) {
                        break;
                    }
                    break;
                case "event":
                    try {
                        taskList.handleEvent(parts);
                    } catch (IllegalArgumentException e) {
                        break;
                    }
                    break;
                case "print":
                    Storage.attemptPrintFileContents();
                    break;
                default:
                    Ui.printInvalidCommand();
                    break;
            }
            line = in.nextLine();
        }
    }
}