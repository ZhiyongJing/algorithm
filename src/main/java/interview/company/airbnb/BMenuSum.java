package interview.company.airbnb;

import java.util.*;

class BMenuSum {

    // 定义一个小的容差值，用于浮点数比较
    private final static double EPSILON = .001;

    public static void main(String[] args) {
        // 创建一个菜单列表，包含多个菜品及其价格
        List<List<String>> menu = new ArrayList<>();
        menu.add(Arrays.asList("A", "12.2")); // 菜品A，价格12.2
        menu.add(Arrays.asList("B", "11.3")); // 菜品B，价格11.3
        menu.add(Arrays.asList("C", "10.4")); // 菜品C，价格10.4
        menu.add(Arrays.asList("D", "8.5"));  // 菜品D，价格8.5
        menu.add(Arrays.asList("E", "1.6"));  // 菜品E，价格1.6

        // 创建一个映射，保存菜品名称及其价格
        Map<String, Double> map = new HashMap<>();
        map.put("A", 12.2);
        map.put("B", 11.3);
        map.put("C", 10.4);
        map.put("D", 8.5);
        map.put("E", 1.6);

        // 创建BMenuSum类的实例
        BMenuSum sol = new BMenuSum();
        // 调用order方法，目标价格设为100
        List<List<String>> res = sol.order(menu, 100);

        // 打印所有符合条件的菜品组合
        for (List<String> list : res) {
            System.out.println(list); // 输出菜品组合
        }
        // 打印符合条件的组合数量
        System.out.println(res.size());
    }

    // 根据菜单和目标价格生成所有符合条件的菜品组合
    public List<List<String>> order(List<List<String>> lists, int target) {
        // 创建一个菜品列表，用于存储Dish对象
        List<Dish> menu = new ArrayList<>();
        // 遍历输入的菜单列表，将其转换为Dish对象
        for (List<String> list : lists) {
            String id = list.get(0); // 菜品名称
            double price = Double.parseDouble(list.get(1)); // 菜品价格
            menu.add(new Dish(id, price)); // 将菜品添加到菜单列表中
        }
        // 存储结果的列表
        List<List<String>> res = new ArrayList<>();
        // 当前菜品组合的列表
        List<String> list = new ArrayList<>();
        // 调用findOrder方法查找所有组合
        findOrder(res, list, menu, 0, target);
        return res; // 返回所有符合条件的组合
    }

    // 递归查找符合条件的菜品组合
    private void findOrder(List<List<String>> res, List<String> list, List<Dish> menu, int level, double rem) {
        // 如果已遍历所有菜品
        if (level == menu.size()) {
            // 检查剩余金额是否为0，符合条件
            if (Math.abs(rem) < EPSILON) {
                res.add(new ArrayList<>(list)); // 将当前组合加入结果
            }
            return; // 结束递归
        }
        // 获取当前菜品的名称和价格
        String item = menu.get(level).id;
        double price = menu.get(level).price;

        // 尝试选择0个或多个当前菜品
        for (int i = 0; i * price < rem || Math.abs(i * price - rem) < EPSILON; i++) {
            // 将当前菜品添加i次到组合列表中
            for (int j = 0; j < i; j++) {
                list.add(item);
            }
            // 递归查找下一个菜品
            findOrder(res, list, menu, level + 1, rem - i * price);
            // 从组合列表中移除已添加的当前菜品
            for (int j = 0; j < i; j++) {
                list.remove(list.size() - 1);
            }
        }
    }
}

// 菜品类，包含名称和价格
class Dish {
    String id; // 菜品名称
    double price; // 菜品价格

    public Dish(String id, double price) {
        this.id = id; // 构造函数，初始化菜品名称
        this.price = price; // 构造函数，初始化菜品价格
    }
}
























