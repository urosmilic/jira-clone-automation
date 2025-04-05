package common;

public enum Priority {
    HIGHEST,
    HIGH,
    MEDIUM,
    LOW,
    LOWEST;

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
