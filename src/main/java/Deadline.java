public class Deadline extends Task{
    private String date;

    public Deadline (String data) {
        super(data);
        this.isMarked = false;
    }

    @Override
    public String toType() {
        return "[D]";
    }
}
