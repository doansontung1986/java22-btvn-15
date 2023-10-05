package logichandle;

import entity.Factory;
import entity.Worker;
import utilities.ScannerUtility;

import java.util.ArrayList;
import java.util.List;

public class FactoryLogic {
    private List<Factory> factoryList;

    public FactoryLogic() {
        this.factoryList = new ArrayList<>();
    }

    public List<Factory> getFactoryList() {
        return factoryList;
    }

    public void setFactoryList(List<Factory> factoryList) {
        this.factoryList = new ArrayList<>(factoryList);
    }

    public void inputFactories() {
        System.out.print("Nhập số lượng xưởng cần thêm: ");
        int numberOfWorker = ScannerUtility.inputValidInteger();
        for (int i = 0; i < numberOfWorker; i++) {
            System.out.println("Nhập thông tin xưởng thứ " + (i + 1));
            Factory factory = new Factory();

            if (checkExistFactory(factory)) {
                continue;
            }

            factory.inputInfo();
            saveFactory(factory);
        }
    }

    public void displayFactoryList() {
        if (this.factoryList.isEmpty()) {
            System.out.println("-------------------------");
            System.out.println("Danh sách xưởng rỗng");
            System.out.println("-------------------------");
            return;
        }

        System.out.printf("%-12s | %-24s | %-36s | %-12s |\n", "Mã xưởng", "Tên xưởng", "Mô tả", "Hệ số công việc");
        for (Factory factory : this.factoryList) {
            factory.displayInfo();
        }
    }

    private void saveFactory(Factory factory) {
        this.factoryList.add(factory);
    }

    private boolean checkExistFactory(Factory newFactory) {
        if (this.factoryList.isEmpty()) {
            return false;
        }

        for (Factory factory : this.factoryList) {
            if (factory.getId() == newFactory.getId()) {
                return true;
            }
        }
        return false;
    }

    public Factory searchWorkerById(int factoryId) {
        for (Factory factory : this.factoryList) {
            if (factory.getId() == factoryId) {
                return factory;
            }
        }
        return null;
    }
}
