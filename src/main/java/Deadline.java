public class Deadline extends Task {
    private String date;

    public Deadline(String data, String date) {
        super(data);
        this.date = date;
        this.isMarked = false;
    }

    @Override
    public String toType() {
        return "[D]";
    }

    public String toString() {
        return super.toString() + " (by: " + date + ")";
    }
}
