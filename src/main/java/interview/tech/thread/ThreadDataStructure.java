package interview.tech.thread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * list集合线程不安全
 */
public class ThreadDataStructure {
    private Map<String, Integer> cocurrentHashMap = new ConcurrentHashMap<>();

    public void add(String key, int value) throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("Thread" + Thread.currentThread().getId() + "put: " + key + "=" + value);
        cocurrentHashMap.put(key, value);

    }

    public void remove(String key) throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("Thread" + Thread.currentThread().getId() + "remove: " + key + "=" + cocurrentHashMap.get(key));
        cocurrentHashMap.remove(key);

    }


    public static void main(String[] args) {
        ThreadDataStructure threadDataStructure = new ThreadDataStructure();

        //创建ArrayList集合
//        List<String> list = new ArrayList<>();
        //way1: Vector解决
//        List<String> list = new Vector<>();

        //way2: Collections解决
//        List<String> list = Collections.synchronizedList(new ArrayList<>());

        //way3:  CopyOnWriteArrayList解决
//        List<String> list = new CopyOnWriteArrayList<>();
//        for (int i = 0; i <30; i++) {
//            new Thread(()->{
//                //向集合添加内容
//                list.add(UUID.randomUUID().toString().substring(0,8));
//                //从集合获取内容
//                System.out.println(list);
//            },String.valueOf(i)).start();
//        }

        //演示Hashset
//        Set<String> set = new HashSet<>();
//        Set<String> set = new CopyOnWriteArraySet<>();
//        for (int i = 0; i <30; i++) {
//            new Thread(()->{
//                //向集合添加内容
//                set.add(UUID.randomUUID().toString().substring(0,8));
//                //从集合获取内容
//                System.out.println(set);
//            },String.valueOf(i)).start();
//        }

        //演示HashMap
//        Map<String,String> map = new HashMap<>();

        Map<String,String> map = new ConcurrentHashMap<>();
        for (int i = 0; i <100; i++) {
            String key = String.valueOf(i);
            final  int value = i;

            new Thread(()->{
                try {
                    threadDataStructure.add(key, value);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            },String.valueOf(i)).start();
        }
        for (int i = 0; i <10; i++) {
            String key = String.valueOf(i);
            final  int value = i;

            new Thread(()->{
                try {
                    threadDataStructure.remove(key);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            },String.valueOf(i)).start();
        }
    }
}
