import java.util.*;
import java.util.Scanner;

/** Entry point of chatbot code. */
public class Grizzly {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Ui.handleGreeting(); //start with greeting
        taskList.attemptInitialFileLoad();
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while (true) {
            String[] parts = Parser.parseInput(line);
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
                case "find":
                    taskList.handleFind(parts);
                    break;
                default:
                    Ui.printInvalidCommand();
                    break;
            }
            line = in.nextLine();
        }
    }
}