/**
 * A simple to-do task, with no date metadata.
 * Immutable aside from the inherited {@code isMarked} flag.
 */
public class ToDo extends Task {

    /**
     * Creates a new to-do task.
     *
     * @param data   short description of the task; non-null
     * @param marked initial completion state
     */
    public ToDo(String data, boolean marked) {
        super(data);
        this.isMarked = marked;
    }

    @Override
    public String toType() {
        return "[T]";
    }

    /**
     * Returns the task description.
     */
    public String toString() {
        return super.toString();
    }
}
