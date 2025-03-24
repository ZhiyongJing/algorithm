package interview.company.bloomberg;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// 权限控制器类：负责维护每个客户可订阅的资产列表
class PermissionManager {
    // key: 客户名, value: 该客户有权限订阅的资产集合
    private final Map<String, Set<String>> clientPermissions = new HashMap<>();

    // 授权某个客户可以订阅某个资产
    public void grantPermission(String clientName, String ticker) {
        clientPermissions.computeIfAbsent(clientName, k -> new HashSet<>()).add(ticker);
    }

    // 检查客户是否有权限订阅某个资产
    public boolean hasPermission(String clientName, String ticker) {
        return clientPermissions.getOrDefault(clientName, Collections.emptySet()).contains(ticker);
    }
}

// 客户端终端类：模拟客户接收行情推送
class ClientTerminalV2 {
    // 客户名称（标识符）
    private final String clientName;

    // 构造函数初始化客户名称
    public ClientTerminalV2(String name) {
        this.clientName = name;
    }

    // 获取客户名称
    public String getClientName() {
        return clientName;
    }

    // 客户接收到行情推送后的回调函数
    public void onMarketData(String ticker, Map<String, Double> data) {
        System.out.printf("📬【%s】收到行情 %s: %s%n", clientName, ticker, data);
    }
}

// 推送系统类：维护订阅关系，并根据行情进行推送
class MarketDataPublisherV2 {
    // 外层Map的key是资产名，内层Map的key是客户名，value是客户订阅的字段集合
    private final Map<String, Map<String, Set<String>>> subscriptions = new ConcurrentHashMap<>();

    // 客户名到客户端对象的映射（用于推送回调）
    private final Map<String, ClientTerminalV2> clientMap = new HashMap<>();

    // 权限管理器（注入）
    private final PermissionManager permissionManager;

    // 构造函数：注入权限管理器
    public MarketDataPublisherV2(PermissionManager permissionManager) {
        this.permissionManager = permissionManager;
    }

    // 注册客户端对象
    public void registerClient(ClientTerminalV2 client) {
        clientMap.put(client.getClientName(), client);
    }

    // 客户发起订阅请求（资产 + 字段）
    public void subscribe(String ticker, String clientName, Set<String> fields) {
        // 首先检查客户是否有权限订阅该资产
        if (!permissionManager.hasPermission(clientName, ticker)) {
            System.out.printf("❌ 客户 %s 无权限订阅 %s%n", clientName, ticker);
            return;
        }

        // 将客户的字段级订阅记录到映射表中
        subscriptions.computeIfAbsent(ticker, k -> new ConcurrentHashMap<>()).put(clientName, fields);
        System.out.printf("✅ 客户 %s 成功订阅 %s 字段 %s%n", clientName, ticker, fields);
    }

    // 系统发布行情数据时调用该方法
    public void publish(String ticker, Map<String, Double> data) {
        // 查找所有订阅了该资产的客户及其字段
        Map<String, Set<String>> clientFieldsMap = subscriptions.getOrDefault(ticker, Collections.emptyMap());
        System.out.printf("📢 收到行情更新：%s 数据 = %s，推送给 %d 个客户%n", ticker, data, clientFieldsMap.size());

        // 遍历每个订阅该资产的客户
        for (Map.Entry<String, Set<String>> entry : clientFieldsMap.entrySet()) {
            String clientName = entry.getKey();           // 客户名
            Set<String> fields = entry.getValue();        // 客户订阅的字段
            ClientTerminalV2 client = clientMap.get(clientName); // 获取客户对象

            // 构造一个 Map，仅包含客户订阅的字段
            Map<String, Double> filtered = new HashMap<>();
            for (String field : fields) {
                if (data.containsKey(field)) {
                    filtered.put(field, data.get(field));
                }
            }

            // 打印推送信息
            System.out.printf("➡️ 推送给客户端 %s: %s%n", clientName, filtered);

            // 回调客户接口，进行数据推送
            client.onMarketData(ticker, filtered);
        }
    }
}

// 行情模拟器类：定时自动生成随机行情数据
class MarketDataSimulator {
    // 引用推送系统对象
    private final MarketDataPublisherV2 publisher;

    // 定时线程池，用于每隔一段时间生成行情
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    // 随机数生成器
    private final Random random = new Random();

    // 构造函数
    public MarketDataSimulator(MarketDataPublisherV2 publisher) {
        this.publisher = publisher;
    }

    // 启动定时任务，每隔2秒推送一组行情
    public void start() {
        scheduler.scheduleAtFixedRate(() -> {
            // 模拟两个资产：AAPL 和 TSLA
            String[] tickers = {"AAPL", "TSLA"};
            for (String ticker : tickers) {
                // 随机生成价格和成交量
                Map<String, Double> data = new HashMap<>();
                data.put("PRICE", 100 + random.nextDouble() * 50); // 价格在100~150之间
                data.put("VOLUME", (double) (1_000_000 + random.nextInt(1_000_000))); // 成交量在100~200万之间

                // 调用推送方法，分发行情
                publisher.publish(ticker, data);
            }
        }, 0, 2, TimeUnit.SECONDS); // 每2秒执行一次
    }
}

// 主程序入口
public class MarketDataSystemWithTimer {
    public static void main(String[] args) {
        // 创建权限管理器
        PermissionManager permissionManager = new PermissionManager();

        // 创建推送系统，并注入权限管理器
        MarketDataPublisherV2 publisher = new MarketDataPublisherV2(permissionManager);

        // 创建两个客户对象
        ClientTerminalV2 alice = new ClientTerminalV2("Alice");
        ClientTerminalV2 bob = new ClientTerminalV2("Bob");

        // 注册客户到推送系统
        publisher.registerClient(alice);
        publisher.registerClient(bob);

        // 配置客户的资产权限
        permissionManager.grantPermission("Alice", "AAPL"); // Alice 只能订阅 AAPL
        permissionManager.grantPermission("Bob", "AAPL");   // Bob 可订阅 AAPL 和 TSLA
        permissionManager.grantPermission("Bob", "TSLA");

        // 客户发起订阅（资产 + 字段）
        publisher.subscribe("AAPL", "Alice", new HashSet<>(Arrays.asList("PRICE"))); // Alice 只订阅 AAPL 的价格
        publisher.subscribe("AAPL", "Bob", new HashSet<>(Arrays.asList("PRICE", "VOLUME"))); // Bob 订阅 AAPL 全部字段
        publisher.subscribe("TSLA", "Alice", new HashSet<>(Arrays.asList("PRICE"))); // Alice 没有权限订阅 TSLA，会被拒绝
        publisher.subscribe("TSLA", "Bob", new HashSet<>(Arrays.asList("PRICE"))); // Bob 可订阅 TSLA 的价格

        // 启动行情模拟器，每2秒生成一次行情
        MarketDataSimulator simulator = new MarketDataSimulator(publisher);
        simulator.start();
    }
}

