import java.util.Scanner;

/**
 * Entry point of the Grizzly chatbot application.
 *
 * <p>The {@code main} method initializes the application, loads any previously
 * saved tasks, prints a greeting, and then enters a read–evaluate–print loop
 * (REPL). Each line of user input is parsed into a command word, and dispatched to
 * the corresponding handler in {@link TaskList}, {@link Storage}, or {@link Ui}.
 *
 * <p>Supported commands:
 * <ul>
 *   <li><b>todo &lt;description&gt;</b> — add a new ToDo task</li>
 *   <li><b>deadline &lt;description&gt; /by &lt;date&gt;</b> — add a new Deadline task</li>
 *   <li><b>event &lt;description&gt; /from &lt;date&gt;</b> — add a new Event task</li>
 *   <li><b>list</b> — display all tasks</li>
 *   <li><b>mark &lt;index&gt;</b> — mark a task as completed</li>
 *   <li><b>unmark &lt;index&gt;</b> — mark a task as not completed</li>
 *   <li><b>delete &lt;index&gt;</b> — delete a task</li>
 *   <li><b>find &lt;keyword&gt;</b> — search for tasks containing a keyword</li>
 *   <li><b>print</b> — print raw contents of the text file</li>
 *   <li><b>bye</b> — exit the program</li>
 * </ul>
 *
 * <p>All commands read from {@code System.in} and print results to {@code System.out}.
 */

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