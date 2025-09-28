public class Task {
    protected String data;
    protected boolean isMarked;

    public Task(String data) {
        this.data = data;
        this.isMarked = false;
    }

    public Task() {}

    public String getDescription() {
        return data;
    }

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
    public String toString() {
        return data;
    }

    public String toType() {
        return "type";
    }
}

//Created Level-7-branch: Save function