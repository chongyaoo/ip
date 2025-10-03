/**
 * A task with a due date (rendered as "(by: <date>)").
 * Immutable aside from the inherited {@code isMarked} flag.
 */
public class Deadline extends Task {

    final String date;

    /**
     * Creates a new deadline task.
     *
     * @param data   short description of the task; non-null
     * @param date   due date string to display (no parsing enforced here); non-null
     * @param marked initial completion state
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

    /**
     * Returns the display form, e.g. {@code "<desc> (by: 2025-10-15)"}.
     *
     * @return human-readable string representation
     */
    public String toString() {
        return super.toString() + " (by: " + date + ")";
    }
}
