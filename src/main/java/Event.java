public class Event extends Task {
    private String dates;

    public Event (String data) {
        super(data);
        this.isMarked = false;
    }

    @Override
    public String toType() {
        return "[E]";
    }
}
