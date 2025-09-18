public class ToDo extends Task {

    public ToDo(String data, boolean marked) {
        super(data);
        this.isMarked = marked;
    }

        @Override
    public String toType() {
        return "[T]";
    }

    public String toString() {
        return super.toString();
    }
}
