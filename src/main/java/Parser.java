/**
 * Parses input from the user into 2 parts: the first word which is a command, and a String[] array
 */
public class Parser {
    public static String[] parseInput (String line) {
        String[] parts = line.split("\\s+", 2);
        String action = parts[0].toLowerCase();
        return parts;
    }

    /**
     * Returns the index of the Task to be deleted
     */
    public static int parseIndexForDelete (String[] parts) throws IllegalArgumentException{
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
     * Returns the index of the Task to be unmarked
     */
    public static int parseIndexForUnmark (String[] parts) throws IllegalArgumentException {
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
     * Returns the index of the Task to be marked
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
     * Returns the index of the "from" keyword in an Event Task
     */
    public static int findFromIndex (String[] words1) {
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
     * Parses a string into an Event Task
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
     * Parses a string into a Deadline Task
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

