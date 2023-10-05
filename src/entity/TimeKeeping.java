package entity;

public class TimeKeeping {
    private Factory factory;
    private int workingDays;

    public TimeKeeping(Factory factory, int workingDays) {
        this.factory = factory;
        this.workingDays = workingDays;
    }

    public Factory getFactory() {
        return factory;
    }

    public int getWorkingDays() {
        return workingDays;
    }

}
