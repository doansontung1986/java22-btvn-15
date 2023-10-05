package logichandle;

import entity.Worker;
import utilities.ScannerUtility;

import java.util.ArrayList;
import java.util.List;

public class WorkerLogic {
    private List<Worker> workerList;

    public WorkerLogic() {
        workerList = new ArrayList<>();
    }

    public void inputWorkers() {
        System.out.print("Nhập số lượng công nhân cần thêm: ");
        int numberOfWorker = ScannerUtility.inputValidInteger();
        for (int i = 0; i < numberOfWorker; i++) {
            System.out.println("Nhập thông tin công nhân thứ " + (i + 1));
            Worker worker = new Worker();

            if (checkExistWorker(worker)) {
                continue;
            }

            worker.inputInfo();
            saveWorker(worker);
        }
    }

    public void displayWorkerList() {
        if (this.workerList.isEmpty()) {
            System.out.println("-------------------------");
            System.out.println("Danh sách công nhân rỗng");
            System.out.println("-------------------------");
            return;
        }
        System.out.printf("%-12s | %-21s | %-36s | %-36s | %-16s |\n", "Mã công nhân", "Tên công nhân", "Địa chỉ", "Số điện thoại", "Cấp bậc");
        for (Worker worker : this.workerList) {
            worker.displayInfo();
        }
    }

    private void saveWorker(Worker worker) {
        this.workerList.add(worker);
    }

    private boolean checkExistWorker(Worker newWorker) {
        if (this.workerList.isEmpty()) {
            return false;
        }

        for (Worker worker : this.workerList) {
            return worker.getId() == newWorker.getId();
        }
        return false;
    }

    public Worker searchWorkerById(int workerId) {
        for (Worker worker : this.workerList) {
            if (worker.getId() == workerId) {
                return worker;
            }
        }
        return null;
    }
}
