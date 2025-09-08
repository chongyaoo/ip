public class ToDo extends Task {

    public ToDo(String data) {
        super(data);
        this.isMarked = false;
    }

    @Override
    public String toType() {
        return "[D]";
    }

    public String toString() {
        return super.toString();
    }
}
