import java.util.Scanner;
import java.util.*;

public class Ui {
    Scanner in = new Scanner(System.in);
    String line;

    public static void handleList(List<Task> storedItems, int index) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < index; i++) {
            System.out.println(i + "." + storedItems.get(i - 1).toType() + storedItems.get(i - 1).markedBox() + storedItems.get(i - 1).toString());
        }
    }

    public static void handleGreeting() {
        String logo = """
                Hi, I'm your friendly assistant, Grizzly!
                What can I do for you today?
                ____________________________________________________________""";
        System.out.println(logo);
    }

    public static void caseBye() {
        String logo = """
                Bye. Hope to see you again soon!
                """;
        System.out.println(logo);
    }

    public static void printTask(Task eventTask, int index) {
        System.out.println("Got it. I've added this task:");
        System.out.println(eventTask.toType() + eventTask.markedBox() + eventTask.toString());
        System.out.println("Now you have " + index + " tasks in the list");
    }

    public static void printError (Exception e) {
        System.out.println("Something went wrong: " + e.getMessage());
    }

    public static void printDeletedTask(Task deletedTask) {
        System.out.println("I've deleted this item from the list:");
        System.out.println(deletedTask.toType() + deletedTask.markedBox() + deletedTask.toString());
    }

    public static void printMarkTask (int itemToMark, List<Task> storedItems) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(itemToMark + "." + storedItems.get(itemToMark - 1).toType() + storedItems.get(itemToMark - 1).markedBox() + storedItems.get(itemToMark - 1).toString());
    }

    public static void printUnmarkTask (int itemToUnmark, List<Task> storedItems) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(itemToUnmark + "." + storedItems.get(itemToUnmark - 1).toType() + storedItems.get(itemToUnmark - 1).markedBox() + storedItems.get(itemToUnmark - 1).toString());
    }

    public static void outOfRangeError (int item) {
        System.out.println("Task " + item + " has not been created!");
    }

    public static void printInvalidCommand() {
        System.out.println("Sorry, I do not understand that command."); //not valid command
    }
}
