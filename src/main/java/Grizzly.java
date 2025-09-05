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
                        System.out.println(itemToMark + "." + storedItems[itemToMark - 1].toType() + storedItems[itemToMark - 1].markedBox() + storedItems[itemToMark - 1].getString());
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
                        System.out.println(itemToUnmark + "." + storedItems[itemToUnmark - 1].toType() + storedItems[itemToUnmark - 1].markedBox() + storedItems[itemToUnmark - 1].getString());
                    } else {
                        System.out.println("Task " + itemToUnmark + " has not been created!");
                    }
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i < index; i++) {
                        System.out.println(i + "." + storedItems[i - 1].toType() + storedItems[i - 1].markedBox() + storedItems[i - 1].getString());
                    }
                    break;
                case "todo":
                    ToDo todoTask = new ToDo(parts[1]);
                    storedItems[index - 1] = todoTask;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(todoTask.toType() + todoTask.markedBox() + todoTask.toString());
                    System.out.println("Now you have " + index + " tasks in the list");
                    index++;
                    break;
                case "deadline":
                    String[] words = parts[1].split("\\s+");
                    int byIndex = -1;
                    int i = 0;
                    while (byIndex == -1 && i < words.length) {
                        if (words[i].equalsIgnoreCase("/by")) {
                            byIndex = i;
                            i++;
                        }
                    }

                    if (byIndex != -1) {
                        StringBuilder eventBuilder = new StringBuilder();
                        for (int j = 0; j < byIndex; j++) {
                            eventBuilder.append(words[j]);
                            eventBuilder.append(" "); // add space between words
                        }
                        StringBuilder dateBuilder = new StringBuilder();
                        for (int k = byIndex + 1; k < words.length; k++) {
                            dateBuilder.append(words[k]);
                            dateBuilder.append(" ");
                        }
                        String date = dateBuilder.toString();
                        String event = eventBuilder.toString();
                        Deadline deadlineTask = new Deadline(event, date);
                        storedItems[index - 1] = deadlineTask;
                        System.out.println("Got it. I've added this task:");
                        System.out.println(deadlineTask.toType() + deadlineTask.markedBox() + parts[1]);
                        System.out.println("Now you have " + index + " tasks in the list");
                        index++;
                        break;
                    } else {
                        System.out.println("Please input date");
                        break;
                    }
                case "event":
                    String[] words1 = parts[1].split("\\s+");
                    int fromIndex = -1;
                    int j = 0;
                    while ((fromIndex == -1) && j < words1.length) {
                        if (words1[j].equalsIgnoreCase("/from")) {
                            fromIndex = j;
                        } //finding fromIndex
                        j++;
                    }
                    if (fromIndex != -1) {
                        StringBuilder eventBuilder = new StringBuilder();
                        for (int a = 0; a < fromIndex; a++) {
                            eventBuilder.append(words1[j]);
                            eventBuilder.append(" "); // add space between words
                        }
                        StringBuilder fromBuilder = new StringBuilder();
                        for (int k = fromIndex + 1; k < words1.length; k++) {
                            fromBuilder.append(words1[k]);
                            fromBuilder.append(" ");
                        }
                        String from = fromBuilder.toString();
                        String event = eventBuilder.toString();
                        Event eventTask = new Event(event, from);
                        storedItems[index - 1] = eventTask;
                        System.out.println("Got it. I've added this task:");
                        System.out.println(eventTask.toType() + eventTask.markedBox() + parts[1]);
                        System.out.println("Now you have " + index + " tasks in the list");
                        index++;
                        break;
                    } else {
                        System.out.println("Please input date");
                        break;
                    }
                default:
                    break;
            }
            line = in.nextLine();
        }
    }
}

