/**
 * A task that occurs at a specified time (rendered as "(from: <date>)").
 * Immutable aside from the inherited {@code isMarked} flag.
 */
public class Event extends Task {

    final String date;

    /**
     * Creates a new event task.
     *
     * @param data   short description of the event; non-null
     * @param date   time/date string to display (no parsing enforced here); non-null
     * @param marked initial completion state
     */
    public Event(String data, String date, boolean marked) {
        super(data);
        this.date = date;
        this.isMarked = marked;
    }

    @Override
    public String toType() {
        return "[E]";
    }


    /**
     * Returns the display form, e.g. {@code "<desc> (from: 2025-10-20 14:00)"}.
     *
     * @return human-readable string representation
     */
    public String toString() {
        return super.toString() + " (from: " + date + ")";
    }
}
