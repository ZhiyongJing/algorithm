package interview.company.airbnb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class NestedListIterator implements Iterator<Integer> {
    private List<List<Integer>> nestedList;  // 2D 嵌套列表
    private int outerIndex;  // 外层列表的当前索引
    private int innerIndex;  // 内层列表的当前索引
    private Integer lastReturned;  // 上一次调用 next() 返回的元素
    private boolean canDelete;  // 标志是否可以删除

    // 构造函数
    public NestedListIterator(List<List<Integer>> nestedList) {
        this.nestedList = nestedList;
        this.outerIndex = 0;
        this.innerIndex = 0;
        this.lastReturned = null;
        this.canDelete = false;
    }

    // 检查是否有下一个元素
    @Override
    public boolean hasNext() {
        // 跳过空的子列表
        while (outerIndex < nestedList.size() && (innerIndex >= nestedList.get(outerIndex).size())) {
            outerIndex++;
            innerIndex = 0;
        }
        return outerIndex < nestedList.size();
    }

    // 获取下一个元素
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements");
        }

        // 记录上一次返回的元素
        lastReturned = nestedList.get(outerIndex).get(innerIndex);
        canDelete = true;  // 设置可以删除
        innerIndex++;  // 移动到下一个元素

        return lastReturned;
    }

    // 删除上一次调用 next() 访问的元素
    @Override
    public void remove() {
        if (!canDelete) {
            throw new IllegalStateException("Next has not been called or element has already been deleted");
        }

        // 找到 lastReturned 的位置并删除
        for (int i = 0; i < nestedList.size(); i++) {
            List<Integer> innerList = nestedList.get(i);
            if (innerList.contains(lastReturned)) {
                innerList.remove(lastReturned);  // 删除元素
                // 如果删除了最后一个元素，调整 innerIndex
                if (innerIndex > 0) {
                    innerIndex--;
                }
                if (innerList.isEmpty()) {
                    outerIndex++;  // 如果内层列表空了，移动到下一个外层列表
                }
                break;
            }
        }

        canDelete = false;  // 重置删除状态
        lastReturned = null;  // 清除上一次返回的元素
    }

    // 测试代码
    public static void main(String[] args) {
        // 创建一个2D嵌套列表
        List<List<Integer>> nestedList = Arrays.asList(
                new ArrayList<>(Arrays.asList(1, 2, 3)),
                new ArrayList<>(Arrays.asList(4, 5)),
                new ArrayList<>(Arrays.asList()),
                new ArrayList<>(Arrays.asList(6, 7, 8, 9))
        );

        NestedListIterator iterator = new NestedListIterator(nestedList);
        System.out.println(nestedList);

        // 遍历并打印元素
        while (iterator.hasNext()) {
            int value = iterator.next();
            System.out.println("Next: " + value);
            // 在每次调用 next() 后删除元素
//            iterator.remove();
        }

        // 打印删除后的结果
        System.out.println("After deletion:");
        System.out.println(nestedList);
    }

}
