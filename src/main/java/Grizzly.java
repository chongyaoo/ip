import java.util.*;

public class Grizzly {
    public static void main(String[] args) {
        String logo = """
                Hi, I'm your friendly assistant, Grizzly!
                What can I do for you today?
                ____________________________________________________________""";
        System.out.println(logo);

        Scanner in = new Scanner(System.in);
        String line;
        int index = 1;
        Task[] storedItems = new Task[100];
        line = in.nextLine();


        while (true) {
            String[] parts = line.split("\\s+", 2);
            String action = parts[0].toLowerCase();
            switch (action) {
                case "bye":
                    logo = """
                            Bye. Hope to see you again soon!
                            """;
                    System.out.println(logo);
                    return;
                case "mark":
                    int itemToMark;
                    try {
                        itemToMark = Integer.parseInt(parts[1]);
                    } catch (NumberFormatException e) {
                        System.out.println("Not a valid integer.");
                        break;
                    }
                    if (itemToMark < index && itemToMark >= 1) {
                        storedItems[itemToMark - 1].setMarked(true);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(itemToMark + "." + storedItems[itemToMark - 1].toType() + storedItems[itemToMark - 1].MarkedBox() + storedItems[itemToMark - 1].getString());
                    } else {
                        System.out.println("Task " + itemToMark + " has not been created!");
                    }
                    break;
                case "unmark":
                    int itemToUnmark;
                    try {
                        itemToUnmark = Integer.parseInt(parts[1]);
                    } catch (NumberFormatException e) {
                        System.out.println("Not a valid integer.");
                        break;
                    }
                    if (itemToUnmark < index && itemToUnmark >= 1) {
                        storedItems[itemToUnmark - 1].setMarked(false);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(itemToUnmark + "." + storedItems[itemToUnmark - 1].toType() + storedItems[itemToUnmark - 1].MarkedBox() + storedItems[itemToUnmark - 1].getString());
                    } else {
                        System.out.println("Task " + itemToUnmark + " has not been created!");
                    }
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i < index; i++) {
                        System.out.println(i + "." + storedItems[i - 1].toType() + storedItems[i - 1].MarkedBox() + storedItems[i - 1].getString());
                    }
                    break;
                case "todo":
                    ToDo todoTask = new ToDo(parts[1]);
                    storedItems[index - 1] = todoTask;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(todoTask.toType() + todoTask.MarkedBox() + parts[1]);
                    System.out.println("Now you have " + index + " tasks in the list");
                    index++;
                    break;
                case "deadline":
                    Deadline deadlineTask = new Deadline(parts[1]);
                    storedItems[index - 1] = deadlineTask;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(deadlineTask.toType() + deadlineTask.MarkedBox() + parts[1]);
                    System.out.println("Now you have " + index + " tasks in the list");
                    index++;
                    break;
                case "event":
                    Event eventTask = new Event(parts[1]);
                    storedItems[index - 1] = eventTask;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(eventTask.toType() + eventTask.MarkedBox() + parts[1]);
                    System.out.println("Now you have " + index + " tasks in the list");
                    index++;
                    break;
                default:
                    break;
            }
            line = in.nextLine();
        }
    }
}

