public class ToDo extends Task {


    public ToDo (String data) {
        super(data);
    }

    @Override
    public String toType() {
        return "[T]";
    }

}
