public class Event extends Task {
    private String date;

    public Event (String data, String date) {
        super(data);
        this.date = date;
        this.isMarked = false;
    }

    @Override
    public String toType() {
        return "[E]";
    }

    public String toString() {
        return super.toString() + " (from: " + date + ")";
    }
}
