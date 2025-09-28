/**
 * Base Class for task types
 */
public class Task {
    /**
     * Description of the Task
     */
    protected String data;

    /**
     * Boolean tracker of whether the Task is marked
     */
    protected boolean isMarked;

    /**
     * Constructor for the base class Task
     */
    public Task(String data) {
        this.data = data;
        this.isMarked = false;
    }

    public Task() {
    }

    /**
     * Function to mark the Task
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

//Created Level-7-branch: Save function