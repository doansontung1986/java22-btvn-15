package logichandle;

import entity.Factory;
import entity.TimeKeeping;
import entity.TimeKeepingTable;
import entity.Worker;
import utilities.ScannerUtility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TimeKeepingTableLogic {
    private WorkerLogic workerLogic;
    private FactoryLogic factoryLogic;
    private List<TimeKeepingTable> timeKeepingTableList;

    public TimeKeepingTableLogic(WorkerLogic workerLogic, FactoryLogic factoryLogic) {
        this.workerLogic = workerLogic;
        this.factoryLogic = factoryLogic;
        this.timeKeepingTableList = new ArrayList<>();
    }

    public List<TimeKeepingTable> getTimeKeepingList() {
        return timeKeepingTableList;
    }

    public void inputTimeKeepingTableList() {
        System.out.println("Nhập số lượng bảng phân công: ");
        int numberOfTimeKeepingTable = ScannerUtility.inputValidInteger();
        for (int i = 0; i < numberOfTimeKeepingTable; i++) {
            Worker worker = inputWorkerInfo();
            if (worker.getWorkingDays() == 30) {
                System.out.println("Công nhân " + worker.getName() + "đã đủ số ngày công (30 ngày)");
                break;
            }
            List<TimeKeeping> timeKeepingList = inputTimeKeepingTables(worker);
            TimeKeepingTable timeKeepingTable = new TimeKeepingTable(worker, timeKeepingList);
            saveTimeKeepingTable(timeKeepingTable);
        }
    }

    public void displayTimeKeepingTableList() {
        for (TimeKeepingTable timeKeepingTable : timeKeepingTableList) {
            timeKeepingTable.displayInfo();
            System.out.println("-----------------------------------------------------");
        }
    }

    public void displayTimeKeepingTableList(List<TimeKeepingTable> timeKeepingTableList) {
        for (TimeKeepingTable timeKeepingTable : timeKeepingTableList) {
            timeKeepingTable.displayInfo();
            System.out.println("-----------------------------------------------------");
        }
    }

    private void saveTimeKeepingTable(TimeKeepingTable timeKeepingTable) {
        this.timeKeepingTableList.add(timeKeepingTable);
    }

    private List<TimeKeeping> inputTimeKeepingTables(Worker worker) {
        System.out.println("Nhập số lượng xưởng muốn phân công:");
        int numberOfAssignedFactory = ScannerUtility.inputValidInteger();
        List<TimeKeeping> timeKeepingList = new ArrayList<>();
        for (int i = 0; i < numberOfAssignedFactory; i++) {
            System.out.println("Xưởng phân công thứ " + (i + 1));
            Factory factory;
            do {
                System.out.println("Nhập mã xưởng");
                int factoryId = ScannerUtility.inputValidInteger();
                factory = factoryLogic.searchWorkerById(factoryId);
                if (factory != null) {
                    break;
                }
                System.out.println("Không tồn tại xưởng mang mã " + factoryId + ", vui lòng nhập lại.");
            } while (true);

            System.out.println("Nhập số ngày muốn phân công");

            int workingDays;

            do {

                workingDays = ScannerUtility.inputValidInteger();

                if (workingDays >= 0 && workingDays < 31) {
                    int sumWorkingDays = worker.getWorkingDays() + workingDays;
                    if (sumWorkingDays > 30) {
                        System.out.println("Vượt quá số ngày công tối đa. Công nhân này chỉ còn " + (30 - worker.getWorkingDays()) + " ngày công có thể làm");
                        System.out.println("Vui lòng nhập lại.");
                    } else {
                        worker.setWorkingDays(sumWorkingDays);
                        break;
                    }
                } else {
                    System.out.println("Tổng số ngày công tối đa là 30");
                }
            } while (true);

            TimeKeeping timeKeeping = new TimeKeeping(factory, workingDays);
            timeKeepingList.add(timeKeeping);
        }

        return timeKeepingList;
    }

    private Worker inputWorkerInfo() {
        Worker worker;
        do {
            System.out.println("Nhập mã công nhân");
            int workerId = ScannerUtility.inputValidInteger();
            worker = workerLogic.searchWorkerById(workerId);
            if (worker != null) {
                break;
            }
            System.out.println("Không tồn tại công nhân mang mã " + workerId + ", vui lòng nhập lại.");
        } while (true);

        return worker;
    }

    public void displaySalaryAllWorkers() {
        System.out.printf("%-12s | %-21s | %-12s |\n", "Mã công nhân", "Tên công nhân", "Lương");
        for (int i = 0; i < this.timeKeepingTableList.size(); i++) {
            this.timeKeepingTableList.get(i).displaySalary();
        }
    }
}
