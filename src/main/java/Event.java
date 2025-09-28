/**
 * Event extends Task
 */
public class Event extends Task {

    final String date;

    /**
     * Constructor of an Event
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

    public String toString() {
        return super.toString() + " (from: " + date + ")";
    }
}
