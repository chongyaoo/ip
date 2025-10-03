/**
 * Utility for parsing raw user input into commands and arguments,
 * and for constructing {@link Task} objects from argument tokens.
 */
public class Parser {

    /**
     * Splits input into at most two parts: command word and remainder.
     *
     * @param line raw user input line
     * @return 2-element array: [command, args] (args may be missing)
     */
    public static String[] parseInput(String line) {
        String[] parts = line.split("\\s+", 2);
        String action = parts[0].toLowerCase();
        return parts;
    }

    /**
     * Parses the index argument for "delete".
     *
     * @param parts command arguments
     * @return int index of the task to delete
     * @throws IllegalArgumentException if index is missing, or not a valid integer
     */
    public static int parseIndexForDelete(String[] parts) throws IllegalArgumentException {
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
            return itemToDelete;
        } catch (NumberFormatException e) {
            System.out.println("Not a valid integer.");
            throw new IllegalArgumentException();
        }
    }

    /**
     * Parses the index argument for "unmark".
     *
     * @param parts command arguments
     * @return int index of the task to unmark
     * @throws IllegalArgumentException if index is missing, or not a valid integer
     */
    public static int parseIndexForUnmark(String[] parts) throws IllegalArgumentException {
        int itemToUnmark;
        if (parts.length > 2) {
            System.out.println("Too many arguments!");
            throw new IllegalArgumentException();
        }
        if (parts.length == 1) {
            System.out.println("Please input the index of the Task to mark!");
            throw new IllegalArgumentException();
        }
        try {
            itemToUnmark = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            System.out.println("Not a valid integer.");
            throw new IllegalArgumentException();
        }
        return itemToUnmark;
    }

    /**
     * Parses the index argument for "mark".
     *
     * @param parts command arguments
     * @return int index of the task to mark
     * @throws IllegalArgumentException if index is missing, or not a valid integer
     */
    public static int parseIndexForMark(String[] parts) throws IllegalArgumentException {
        int itemToMark;
        if (parts.length > 2) {
            System.out.println("Too many arguments!");
            throw new IllegalArgumentException();
        }
        if (parts.length == 1) {
            System.out.println("Please input the index of the Task to mark!");
            throw new IllegalArgumentException();
        }
        try {
            itemToMark = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            System.out.println("Not a valid integer.");
            throw new IllegalArgumentException();
        }
        return itemToMark;
    }

    /**
     * Returns the index of the "/from" token in Event command.
     *
     * @param words1 input arguments
     * @return index of "/from", or -1 if not present
     */
    public static int findFromIndex(String[] words1) {
        int fromIndex = -1;
        int j = 0;
        while ((fromIndex == -1) && j < words1.length) {
            if (words1[j].equalsIgnoreCase("/from")) {
                fromIndex = j;
            } //finding fromIndex
            j++;
        }
        return fromIndex;
    }

    /**
     * Constructs an {@link Event} from command Event input
     *
     * @param fromIndex index of "/from"
     * @param words1    input arguments
     * @return new Event task
     */
    public static Event getEvent(int fromIndex, String[] words1) {
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
        return new Event(event, from, false);
    }

    /**
     * Constructs a {@link Deadline} from command Deadline input
     *
     * @param byIndex index of "/by"
     * @param words   input arguments
     * @return new Deadline task
     */
    public static Deadline getDeadline(int byIndex, String[] words) {
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
        return new Deadline(event, date, false);
    }
}

