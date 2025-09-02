
import java.util.Objects;
import java.util.Scanner;

public class Grizzly {
    public static void main(String[] args) {
        String line;
        String[] storedItems = new String[100];
        int index = 1;
        int itemToMark = 0;
        int itemToUnmark = 0;
        boolean[] marked = new boolean[100];
        Scanner in = new Scanner(System.in);
        String logo = """
                Hi, I'm your friendly assistant, Grizzly!
                What can I do for you today?
                ____________________________________________________________""";
        System.out.println(logo);
        line = in.nextLine();
        while (!Objects.equals(line, "bye")) {
            if (Objects.equals(line, "list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i < index; i++) {
                    if (marked[i]) {
                        System.out.println(i + ".[" + "X" + "] " + storedItems[i]);
                    } else {
                        System.out.println(i + ".[" + " " + "] " + storedItems[i]);
                    }
                }
                line = in.nextLine();
            } else if (line.contains("unmark")) {
                String[] items = line.split(" ");
                itemToUnmark = Integer.parseInt(items[1]);
                if (itemToUnmark < index) {
                    marked[itemToUnmark] = false;
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[ ] " + storedItems[itemToUnmark]);
                    line = in.nextLine();
                }
                else {
                    System.out.println("Task " + itemToUnmark + " has not been created!");
                    line = in.nextLine();
                }
            } else if (line.contains("mark")) {
                String[] items = line.split(" ");
                itemToMark = Integer.parseInt(items[1]);
                if (itemToMark < index) {
                    marked[itemToMark] = true;
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[X] " + storedItems[itemToMark]);
                    line = in.nextLine();
                } else {
                    System.out.println("Task " + itemToMark + " has not been created!");
                    line = in.nextLine();
                }
            } else {
                System.out.println("added: " + line);
                storedItems[index] = line;
                index++;
                line = in.nextLine();
            }
        }
        logo = """
                Bye. Hope to see you again soon!
                """;
        System.out.println(logo);
    }
}
