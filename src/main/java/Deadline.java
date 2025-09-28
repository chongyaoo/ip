/**
 * Deadline Task
 */
public class Deadline extends Task {

    final String date;

    /**
     * Constructor of a Deadline
     */
    public Deadline(String data, String date, boolean marked) {
        super(data);
        this.date = date;
        this.isMarked = marked;
    }

    @Override
    public String toType() {
        return "[D]";
    }

    public String toString() {
        return super.toString() + " (by: " + date + ")";
    }
}
