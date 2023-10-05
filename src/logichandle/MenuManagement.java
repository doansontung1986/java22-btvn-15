package logichandle;

import entity.TimeKeepingTable;

import java.util.*;

public class MenuManagement {
    private final WorkerLogic workerLogic = new WorkerLogic();
    private final FactoryLogic factoryLogic = new FactoryLogic();
    private final TimeKeepingTableLogic timeKeepingTableLogic = new TimeKeepingTableLogic(workerLogic, factoryLogic);

    public void run() {
        while (true) {
            printMenu();
            int functionChoice = chooseFunction();
            switch (functionChoice) {
                case 1:
                    workerLogic.inputWorkers();
                    break;
                case 2:
                    workerLogic.displayWorkerList();
                    break;
                case 3:
                    factoryLogic.inputFactories();
                    break;
                case 4:
                    factoryLogic.displayFactoryList();
                    break;
                case 5:
                    timeKeepingTableLogic.inputTimeKeepingTableList();
                    break;
                case 6:
                    timeKeepingTableLogic.displayTimeKeepingTableList();
                    break;
                case 7:
                    List<TimeKeepingTable> timeKeepingTable = new ArrayList<>(timeKeepingTableLogic.getTimeKeepingList());
                    timeKeepingTable.sort((o1, o2) -> o1.getWorker().getName().compareToIgnoreCase(o2.getWorker().getName()));
                    timeKeepingTableLogic.displayTimeKeepingTableList(timeKeepingTable);
                    break;
                case 8:
                    List<TimeKeepingTable> timeKeepingTable2 = new ArrayList<>(timeKeepingTableLogic.getTimeKeepingList());
                    timeKeepingTable2.sort((o1, o2) -> {
                        int i = -1;
                        int index = 0;

                        while (index < timeKeepingTable2.size()) {
                            i = o1.getTimeKeepingList().get(index).getFactory().getFactoryName().compareToIgnoreCase(o2.getTimeKeepingList().get(index).getFactory().getFactoryName());
                            index++;
                        }

                        return i;
                    });

                    timeKeepingTableLogic.displayTimeKeepingTableList(timeKeepingTable2);
                    break;
                case 9:
                    timeKeepingTableLogic.displaySalaryAllWorkers();
                    break;
                case 10:
                    return;
            }
        }
    }

    private static int chooseFunction() {
        System.out.println("Xin mời lựa chọn chức năng: ");
        int functionChoice;
        do {
            functionChoice = new Scanner(System.in).nextInt();
            if (functionChoice > 0 && functionChoice < 11) {
                break;
            }
        } while (true);
        return functionChoice;
    }

    private static void printMenu() {
        System.out.println("------PHẦN MỀM QUẢN LÝ KHO XƯỞNG");
        System.out.println("1. Nhập công nhân mới");
        System.out.println("2. In danh sách công nhân");
        System.out.println("3. Nhập xưởng mới");
        System.out.println("4. In danh sách xưởng");
        System.out.println("5. Lập bảng phân công");
        System.out.println("6. In danh sách phân công");
        System.out.println("7. Sắp xếp danh sách phân công theo họ tên công nhân");
        System.out.println("8. Sắp xếp danh sách phân công theo xưởng");
        System.out.println("9. Lập bảng kê thu nhập cho mỗi công nhân");
        System.out.println("10. Thoát");
    }
}
