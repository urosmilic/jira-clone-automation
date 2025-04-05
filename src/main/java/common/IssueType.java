package common;

public enum IssueType {
    BUG,
    TASK,
    STORY;

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
