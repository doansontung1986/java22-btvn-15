package entity;

import utilities.ScannerUtility;
import utilities.ValidateUserInput;

public class Factory implements Inputable, Displayable, Comparable<Factory> {
    private static int AUTO_ID = 100;
    private int id;
    private String factoryName;
    private String description;
    private double rateOfWork;

    public Factory() {
        this.id = AUTO_ID;
        AUTO_ID++;
    }

    public int getId() {
        return id;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRateOfWork() {
        return rateOfWork;
    }

    public void setRateOfWork(double rateOfWork) {
        this.rateOfWork = rateOfWork;
    }

    @Override
    public void displayInfo() {
        System.out.printf("%-12s | %-24s | %-36s | %-12s |\n", this.id, this.factoryName, this.description, this.rateOfWork);
    }

    @Override
    public void inputInfo() {
        System.out.println("Nhập tên xưởng: ");
        this.setFactoryName(inputValidFactoryName());

        System.out.println("Nhập mô tả: ");
        this.setDescription(inputValidDescription());

        System.out.println("Nhập hệ số công việc: ");
        this.setRateOfWork(inputValidRateOfWork());
    }

    private String inputValidFactoryName() {
        String factoryName;
        do {
            factoryName = ScannerUtility.inputStringInRange(6, 35);

            if (ValidateUserInput.checkValidName(factoryName)) {
                break;
            }

            System.out.print("Tên xưởng phải có độ dài 6 - 35 ký tự. Nhập lại tên xưởng: ");
        } while (true);

        return factoryName;
    }

    private String inputValidDescription() {
        String description;
        do {
            description = ScannerUtility.inputStringInRange(6, 100);

            if (ValidateUserInput.checkValidDescription(description)) {
                break;
            }

            System.out.print("Mô tả phải có độ dài 6 - 100 ký tự. Nhập lại mô tả: ");
        } while (true);

        return description;
    }

    private double inputValidRateOfWork() {
        return ScannerUtility.inputValidNumberInRange(1, 20);
    }

    @Override
    public int compareTo(Factory factory) {
        return this.factoryName.compareToIgnoreCase(factory.getFactoryName());
    }
}
