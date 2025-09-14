import java.util.*;

public class Grizzly {
    private static void caseBye() {
        String logo = """
                Bye. Hope to see you again soon!
                """;
        System.out.println(logo);
    }

    private static void handleGreeting() {
        String logo = """
                Hi, I'm your friendly assistant, Grizzly!
                What can I do for you today?
                ____________________________________________________________""";
        System.out.println(logo);
    }

    private static void handleMark(List<Task> storedItems, int index, String[] parts) {
        int itemToMark;
        if (parts.length > 2) {
            System.out.println("Too many arguments!");
            return;
        }
        if (parts.length == 1) {
            System.out.println("Please input the index of the Task to mark!");
            return;
        }
        try {
            itemToMark = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            System.out.println("Not a valid integer.");
            return;
        }
        if (itemToMark < index && itemToMark >= 1) {
            storedItems.get(itemToMark - 1).setMarked(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(itemToMark + "." + storedItems.get(itemToMark - 1).toType() + storedItems.get(itemToMark - 1).markedBox() + storedItems.get(itemToMark - 1).toString());
            return;
        }
        System.out.println("Task " + itemToMark + " has not been created!");
    }

    private static void handleUnmark(List<Task> storedItems, int index, String[] parts) {
        int itemToUnmark;
        if (parts.length > 2) {
            System.out.println("Too many arguments!");
            return;
        }
        if (parts.length == 1) {
            System.out.println("Please input the index of the Task to mark!");
            return;
        }
        try {
            itemToUnmark = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            System.out.println("Not a valid integer.");
            return;
        }
        if (itemToUnmark < index && itemToUnmark >= 1) {
            storedItems.get(itemToUnmark - 1).setMarked(false);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(itemToUnmark + "." + storedItems.get(itemToUnmark - 1).toType() + storedItems.get(itemToUnmark - 1).markedBox() + storedItems.get(itemToUnmark - 1).toString());
            return;
        }
        System.out.println("Task " + itemToUnmark + " has not been created!");
    }

    private static void handleList(List<Task> storedItems, int index) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < index; i++) {
            System.out.println(i + "." + storedItems.get(i - 1).toType() + storedItems.get(i - 1).markedBox() + storedItems.get(i - 1).toString());
        }
    }

    private static void handleToDo(List<Task> storedItems, int index, String[] parts) throws IllegalArgumentException {
        if (parts.length <= 1) {
            System.out.println("Description for ToDo cannot be empty!");
            throw new IllegalArgumentException();
        }
        ToDo todoTask = new ToDo(parts[1]);
        storedItems.add(index - 1, todoTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(todoTask.toType() + todoTask.markedBox() + todoTask.toString());
        System.out.println("Now you have " + index + " tasks in the list");
    }


    private static void handleDeadline(List<Task> storedItems, int index, String[] parts) throws IllegalArgumentException {
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
            Deadline deadlineTask = getDeadline(byIndex, words);
            storedItems.add(index - 1, deadlineTask);
            System.out.println("Got it. I've added this task:");
            System.out.println(deadlineTask.toType() + deadlineTask.markedBox() + deadlineTask.toString());
            System.out.println("Now you have " + index + " tasks in the list");
            return;
        }
        System.out.println("Please include keyword '/by'");
        throw new IllegalArgumentException();
    }

    private static Deadline getDeadline(int byIndex, String[] words) {
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
        date = date.substring(0, date.length() - 1); //removing the last space
        String event = eventBuilder.toString();
        event = event.substring(0, event.length() - 1); //removing the last space
        return new Deadline(event, date);
    }

    private static void handleEvent(List<Task> storedItems, int index, String[] parts) throws IllegalArgumentException {
        if (parts.length <= 1) {
            System.out.println("Description for Event cannot be empty!");
            throw new IllegalArgumentException();
        }
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
            if (fromIndex == 0) { //no event input
                System.out.println("Please input an event!");
                throw new IllegalArgumentException();
            }
            if (fromIndex == words1.length - 1) {
                System.out.println("Please input the timing of the event!");
                throw new IllegalArgumentException();
            }
            Event eventTask = getEvent(fromIndex, words1);
            storedItems.add(index - 1, eventTask);
            System.out.println("Got it. I've added this task:");
            System.out.println(eventTask.toType() + eventTask.markedBox() + eventTask.toString());
            System.out.println("Now you have " + index + " tasks in the list");
            return;
        }
        System.out.println("Please include keyword '/from'");
        throw new IllegalArgumentException();
    }

    private static Event getEvent(int fromIndex, String[] words1) {
        StringBuilder eventBuilder = new StringBuilder();
        for (int a = 0; a < fromIndex; a++) {
            eventBuilder.append(words1[a]);
            eventBuilder.append(" "); // add space between words
        }
        StringBuilder fromBuilder = new StringBuilder();
        for (int k = fromIndex + 1; k < words1.length; k++) {
            fromBuilder.append(words1[k]);
            fromBuilder.append(" ");
        }
        String from = fromBuilder.toString();
        from = from.substring(0, from.length() - 1); //removing the last space
        String event = eventBuilder.toString();
        event = event.substring(0, event.length() - 1); //removing the last space
        return new Event(event, from);
    }

    private static void handleDelete(List<Task> storedItems, int index, String[] parts) throws IllegalArgumentException {
        int itemToDelete;
        if (parts.length > 2) {
            System.out.println("Too many arguments!");
            throw new IllegalArgumentException();
        }
        if (parts.length == 1) {
            System.out.println("Please input the index of the Task to delete!");
            throw new IllegalArgumentException();
        }
        try {
            itemToDelete = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            System.out.println("Not a valid integer.");
            throw new IllegalArgumentException();
        }
        if (itemToDelete < index && itemToDelete >= 1) {
            Task deletedTask = storedItems.get(itemToDelete - 1);
            storedItems.remove(itemToDelete - 1);
            System.out.println("I've deleted this item from the list:");
            System.out.println(deletedTask.toType() + deletedTask.markedBox() + deletedTask.toString());
            return;
        }
        System.out.println("Task " + itemToDelete + " has not been created!");
        throw new IllegalArgumentException();
    }

    public static void main(String[] args) {
        handleGreeting(); //start with greeting
        Scanner in = new Scanner(System.in);
        String line;
        int index = 1;
        List<Task> storedItems = new ArrayList<>();
        line = in.nextLine();

        while (true) {
            String[] parts = line.split("\\s+", 2);
            String action = parts[0].toLowerCase();
            switch (action) {
                case "delete":
                    try {
                        handleDelete(storedItems, index, parts);
                        index--;
                        handleList(storedItems, index);
                        break;
                    } catch (IllegalArgumentException e) {
                        break;
                    }
                case "bye":
                    caseBye();
                    return;
                case "mark":
                    handleMark(storedItems, index, parts);
                    break;
                case "unmark":
                    handleUnmark(storedItems, index, parts);
                    break;
                case "list":
                    handleList(storedItems, index);
                    break;
                case "todo":
                    try {
                        handleToDo(storedItems, index, parts);
                        index++; //index needs to be incremented outside of function
                        break;
                    } catch (IllegalArgumentException e) {
                        break; //no increment of index
                    }
                case "deadline":
                    try {
                        handleDeadline(storedItems, index, parts);
                        index++; //index needs to be incremented outside of function
                    } catch (IllegalArgumentException e) {
                        break; //no increment of index
                    }
                    break;
                case "event":
                    try {
                        handleEvent(storedItems, index, parts);
                        index++; //index needs to be incremented outside of function
                    } catch (IllegalArgumentException e) {
                        break; //no increment of index
                    }
                    break;
                default:
                    System.out.println("Sorry, I do not understand that command."); //not valid command
                    break;
            }
            line = in.nextLine();
            //committing for week 5??
        }
    }
}

