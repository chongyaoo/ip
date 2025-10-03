/**
 * Base type for all task variants (e.g., {@link ToDo}, {@link Deadline}, {@link Event}).
 * <p>
 * A task has a textual description and a completion flag ({@code isMarked}).
 * Subclasses customize the printed "type" tag, the toString() method, and other variables.
 */
public class Task {

    /**
     * Description of the task as shown to the user.
     */
    protected String data;

    /**
     * Whether the task is completed (true) or not (false).
     */
    protected boolean isMarked;

    /**
     * Constructs a task with the given description, that is initially unmarked.
     *
     * @param data non-null task description
     */
    public Task(String data) {
        this.data = data;
        this.isMarked = false;
    }

    /**
     * Zero-arg constructor for frameworks/tests; leaves fields at defaults.
     */
    public Task() {
    }

    /**
     * Sets the marked/completion state.
     *
     * @param marked {@code true} to mark completed; {@code false} to unmark
     */
    public void setMarked(boolean marked) {
        this.isMarked = marked;
    }

    public String markedBox() {
        if (isMarked) {
            return "[X] ";
        } else {
            return "[ ] ";
        }
    }

    @Override
    /// Returns the Task Description
    public String toString() {
        return data;
    }

    /// Return the type of the Task
    public String toType() {
        return "type";
    }
}
