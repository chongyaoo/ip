public class Deadline extends Task {
    private String date;

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
