package entity;

import java.util.ArrayList;
import java.util.List;

public class TimeKeepingTable implements Displayable {
    private Worker worker;
    private List<TimeKeeping> timeKeepingList;

    public TimeKeepingTable(Worker worker, List<TimeKeeping> timeKeepingList) {
        this.worker = worker;
        this.timeKeepingList = new ArrayList<>(timeKeepingList);
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public List<TimeKeeping> getTimeKeepingList() {
        return timeKeepingList;
    }

    public void setTimeKeepingList(List<TimeKeeping> timeKeepingList) {
        this.timeKeepingList = new ArrayList<>(timeKeepingList);
    }


    @Override
    public void displayInfo() {
        System.out.println("Thông tin bảng phân công của công nhân " + worker.getName());
        System.out.printf("%-16s | %-16s |\n", worker.getName(), worker.getLevel().type);
        for (TimeKeeping timeKeeping : this.timeKeepingList) {
            System.out.printf("%-16s | %-16s |\n", timeKeeping.getFactory().getFactoryName(), timeKeeping.getWorkingDays());
        }
    }

    private double getSalaryLevel() {
        return switch (worker.getLevel()) {
            case LEVEL_1 -> 1000;
            case LEVEL_2 -> 2000;
            case LEVEL_3 -> 3000;
            case LEVEL_4 -> 4000;
            case LEVEL_5 -> 5000;
            case LEVEL_6 -> 6000;
            case LEVEL_7 -> 7000;
        };
    }

    public double calculateSalary() {
        double sumSalary = 0;
        for (TimeKeeping timeKeeping : this.timeKeepingList) {
            sumSalary += 450000 * getSalaryLevel() * timeKeeping.getFactory().getRateOfWork() * ((double) timeKeeping.getWorkingDays() / 22);
        }
        return sumSalary;
    }

    public void displaySalary() {
        System.out.printf("%-12s | %-21s | %.2f |\n", worker.getId(), worker.getName(), calculateSalary());
    }
}
