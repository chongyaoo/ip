public class ToDo extends Task {
    private String date;

    public ToDo(String data) {
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
