package entity;

import statics.Level;
import utilities.ScannerUtility;

public class Worker extends Person implements Comparable<Worker> {
    private static int AUTO_ID = 1000;
    private int id;
    private Level level;
    private int workingDays;
    public Worker() {
        this.id = AUTO_ID;
        AUTO_ID++;
    }

    public int getId() {
        return id;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public int getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(int workingDays) {
        this.workingDays = workingDays;
    }

    @Override
    public void inputInfo() {
        super.inputInfo();
        System.out.println("Chọn 1 trong 7 cấp bậc công nhân.");
        System.out.println("1. Bậc 1");
        System.out.println("2. Bậc 2");
        System.out.println("3. Bậc 3");
        System.out.println("4. Bậc 4");
        System.out.println("5. Bậc 5");
        System.out.println("6. Bậc 6");
        System.out.println("7. Bậc 7");
        int type;
        do {
            type = ScannerUtility.inputValidNumberInRange(1, 7);

            switch (type) {
                case 1 -> this.level = Level.LEVEL_1;
                case 2 -> this.level = Level.LEVEL_2;
                case 3 -> this.level = Level.LEVEL_3;
                case 4 -> this.level = Level.LEVEL_4;
                case 5 -> this.level = Level.LEVEL_5;
                case 6 -> this.level = Level.LEVEL_6;
                case 7 -> this.level = Level.LEVEL_7;
                default -> System.out.println("Loại cấp bậc đã chọn không hợp lệ. Vui lòng nhập lại.");
            }
        } while (type < 1 || type > 7);
    }

    @Override
    public void displayInfo() {
        System.out.printf("%-12s | %-21s | %-36s | %-36s | %-16s |\n", this.id, this.name, this.address, this.phoneNumber, this.level.type);
    }


    @Override
    public int compareTo(Worker worker) {
        return this.getName().compareToIgnoreCase(worker.getName());
    }
}
