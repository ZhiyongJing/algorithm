import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * DogSize 枚举 - 定义狗狗的大小
 */
enum DogSize {
    SMALL, MEDIUM, LARGE
}

/**
 * Dog 类 - 代表狗狗
 */
class Dog {
    private String id;
    private String name;
    private DogSize size;

    public Dog(String id, String name, DogSize size) {
        this.id = id;
        this.name = name;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DogSize getSize() {
        return size;
    }
}

/**
 * Playground 类 - 代表一个游乐场，按狗狗大小管理
 */
class Playground {
    private DogSize sizeCategory;
    private int capacity;
    private Set<Dog> dogs;

    public Playground(DogSize sizeCategory, int capacity) {
        this.sizeCategory = sizeCategory;
        this.capacity = capacity;
        this.dogs = new HashSet<>();
    }

    /**
     * 检查游乐场是否有空位
     */
    public boolean hasSpace() {
        return dogs.size() < capacity;
    }

    /**
     * 狗狗进入游乐场
     */
    public boolean enter(Dog dog) {
        if (dog.getSize() != this.sizeCategory) {
            System.out.println(dog.getName() + " 不能进入 " + sizeCategory + " 区域！");
            return false;
        }
        if (dogs.size() < capacity) {
            dogs.add(dog);
            System.out.println(dog.getName() + " 进入 " + sizeCategory + " 区域！");
            return true;
        } else {
            System.out.println(sizeCategory + " 区域已满，" + dog.getName() + " 不能进入！");
            return false;
        }
    }

    /**
     * 狗狗离开游乐场
     */
    public boolean exit(Dog dog) {
        if (dogs.contains(dog)) {
            dogs.remove(dog);
            System.out.println(dog.getName() + " 离开 " + sizeCategory + " 区域！");
            return true;
        } else {
            System.out.println(dog.getName() + " 不在 " + sizeCategory + " 区域！");
            return false;
        }
    }

    /**
     * 获取当前游乐场的狗狗数量
     */
    public int getCurrentCount() {
        return dogs.size();
    }
}

/**
 * DogPark 类 - 管理多个游乐场
 */
class DogPark {
    private Map<DogSize, Playground> playgrounds;

    public DogPark(int smallCapacity, int mediumCapacity, int largeCapacity) {
        playgrounds = new HashMap<>();
        playgrounds.put(DogSize.SMALL, new Playground(DogSize.SMALL, smallCapacity));
        playgrounds.put(DogSize.MEDIUM, new Playground(DogSize.MEDIUM, mediumCapacity));
        playgrounds.put(DogSize.LARGE, new Playground(DogSize.LARGE, largeCapacity));
    }

    /**
     * 狗狗进入游乐场
     */
    public boolean enterPlayground(Dog dog) {
        Playground playground = playgrounds.get(dog.getSize());
        return playground.enter(dog);
    }

    /**
     * 狗狗离开游乐场
     */
    public boolean exitPlayground(Dog dog) {
        Playground playground = playgrounds.get(dog.getSize());
        return playground.exit(dog);
    }

    /**
     * 显示所有游乐场状态
     */
    public void displayStatus() {
        for (DogSize size : DogSize.values()) {
            Playground playground = playgrounds.get(size);
            System.out.println(size + " 区域: 当前 " + playground.getCurrentCount() + " 只狗");
        }
    }
}

/**
 * 测试代码 (Main)
 */
public class Main {
    public static void main(String[] args) {
        // 创建狗狗游乐场，分别设置不同大小的区域容量
        DogPark dogPark = new DogPark(2, 3, 2);

        // 创建狗狗
        Dog dog1 = new Dog("D001", "小黑", DogSize.SMALL);
        Dog dog2 = new Dog("D002", "大黄", DogSize.LARGE);
        Dog dog3 = new Dog("D003", "小白", DogSize.SMALL);
        Dog dog4 = new Dog("D004", "旺财", DogSize.MEDIUM);
        Dog dog5 = new Dog("D005", "小花", DogSize.LARGE);
        Dog dog6 = new Dog("D006", "阿福", DogSize.MEDIUM);
        Dog dog7 = new Dog("D007", "豆豆", DogSize.MEDIUM);

        // 狗狗进入游乐场
        dogPark.enterPlayground(dog1);
        dogPark.enterPlayground(dog2);
        dogPark.enterPlayground(dog3);
        dogPark.enterPlayground(dog4);
        dogPark.enterPlayground(dog5);
        dogPark.enterPlayground(dog6);
        dogPark.enterPlayground(dog7); // 超出容量，无法进入

        // 显示当前游乐场状态
        System.out.println("\n游乐场状态:");
        dogPark.displayStatus();

        // 狗狗离开游乐场
        dogPark.exitPlayground(dog3);
        dogPark.exitPlayground(dog5);

        // 显示最终状态
        System.out.println("\n最终游乐场状态:");
        dogPark.displayStatus();
    }
}
