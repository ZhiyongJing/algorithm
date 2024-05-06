### 1. Design stackoverflow

### 2. Design UNIX FIND

> Implemnet linux find command as an api ,the api willl support finding files that has given size requirements and a file with a certain format like xml…
>
> 1. find all file >5mb
> 2. find all xml
>    Assume file class
>    {
>    get name()
>    directorylistfile()
>    getFile()
>    create a library flexible that is flexible
>    Design clases,interfaces.
>
> `````java
> import java.io.File;
> import java.util.ArrayList;
> import java.util.List;
> 
> // Interface to represent a filter for finding files
> interface FileFilter {
>     List<File> filter(File directory);
> }
> 
> // Class to find files based on size requirements
> class SizeFilter implements FileFilter {
>     private long sizeInBytes;
> 
>     public SizeFilter(long sizeInBytes) {
>         this.sizeInBytes = sizeInBytes;
>     }
> 
>     @Override
>     public List<File> filter(File directory) {
>         List<File> resultFiles = new ArrayList<>();
>         File[] files = directory.listFiles();
> 
>         if (files != null) {
>             for (File file : files) {
>                 if (file.isFile() && file.length() > sizeInBytes) {
>                     resultFiles.add(file);
>                 } else if (file.isDirectory()) {
>                     resultFiles.addAll(filter(file));
>                 }
>             }
>         }
>         return resultFiles;
>     }
> }
> 
> // Class to find files with a certain format
> class FormatFilter implements FileFilter {
>     private String format;
> 
>     public FormatFilter(String format) {
>         this.format = format;
>     }
> 
>     @Override
>     public List<File> filter(File directory) {
>         List<File> resultFiles = new ArrayList<>();
>         File[] files = directory.listFiles();
> 
>         if (files != null) {
>             for (File file : files) {
>                 if (file.isFile() && file.getName().endsWith(format)) {
>                     resultFiles.add(file);
>                 } else if (file.isDirectory()) {
>                     resultFiles.addAll(filter(file));
>                 }
>             }
>         }
>         return resultFiles;
>     }
> }
> 
> // Class representing the API for the find command
> public class FindCommandAPI {
>     // Method to find files based on a given filter
>     public List<File> findFiles(File directory, FileFilter filter) {
>         return filter.filter(directory);
>     }
> }
> `````

### 3. Design Pizza

> ![image-20240503222652273](OOD案例.assets/image-20240503222652273.png)
>
> ~~~java
> import java.util.List;
> 
> public enum BaseType {
>     SOFT(1),
>     MEDIUM(2),
>     HARD(3);
> 
>     private double price;
> 
>     private BaseType(double price) {
>         this.price = price;
>     }
> 
>     public double getPrice() {
>         return price;
>     }
> }
> 
> public enum Topping {
>     MEAT(2),
>     VEGGIE(1);
> 
>     private double price;
> 
>     private Topping(double price) {
>         this.price = price;
>     }
> 
>     public double getPrice() {
>         return price;
>     }
> }
> 
> public class Customer {
>     String name;
> }
> 
> public class Pizza {
>     Double price;
>     List<BaseType> bases;
>     List<Topping> toppings;
> 
>     public Double getPrice() {
>         double totalPrice = 0.0;
>         for (BaseType base : bases) {
>             totalPrice += base.getPrice();
>         }
>         for (Topping topping : toppings) {
>             totalPrice += topping.getPrice();
>         }
>         return totalPrice;
>     }
> }
> 
> public class Order {
>     List<Pizza> pizzas;
>     Double price;
>     Customer customer;
> 
>     public Order() {
>         pizzas = new ArrayList<>();
>     }
> 
>     public void addPizza(Pizza pizza) {
>         pizzas.add(pizza);
>     }
> 
>     public Double calculateTotalPrice() {
>         double totalPrice = 0.0;
>         for (Pizza pizza : pizzas) {
>             totalPrice += pizza.getPrice();
>         }
>         return totalPrice;
>     }
> }
> 
> public interface Payment {
>     void pay();
> }
> 
> public enum PayType {
>     CASH,
>     CREDIT_CARD
> }
> 
> public class PizzaSystem {
>     List<Order> orders;
> 
>     public Double calculateTotalPrice() {
>         double totalPrice = 0.0;
>         for (Order order : orders) {
>             totalPrice += order.calculateTotalPrice();
>         }
>         return totalPrice;
>     }
> 
>     public void makePayment(Order order, PayType payType) {
>         // Logic to make payment
>     }
> }
> ~~~

### 4. Design Parking Lot

> ![image-20240503150851537](OOD案例.assets/image-20240503150851537.png)
>
> ```java
> import java.util.List;
> 
> interface Vehicle {
>     int getSize();
>     float getHourlyRate();
> }
> 
> class Car implements Vehicle {
>     int size = 2;
>     @Override
>     public int getSize() {
>         return size;
>     }
>     
>     @Override
>     public float getHourlyRate() {
>         // Set a default hourly rate for cars
>         return 10.0f; // Example rate: $10 per hour
>     }
> }
> 
> class Motorcycle implements Vehicle {
>   	int size = 1;
>     @Override
>     public int getSize() {
>         return size;
>     }
>     
>     @Override
>     public float getHourlyRate() {
>         // Set a default hourly rate for motorcycles
>         return 5.0f; // Example rate: $5 per hour
>     }
> }
> 
> class Bus implements Vehicle {
>     int size = 3;
>     @Override
>     public int getSize() {
>         return size;
>     }
>     
>     @Override
>     public float getHourlyRate() {
>         // Set a default hourly rate for buses
>         return 20.0f; // Example rate: $20 per hour
>     }
> }
> 
> class ParkingLot {
>     private List<Level> levels;
>    // private float hourlyRate;
> 
>     public ParkingLot(List<Level> levels, float hourlyRate) {
>         this.levels = levels;
>         this.hourlyRate = hourlyRate;
>     }
> 
>     public int getAvailableCount() {
>         int availableCount = 0;
>         for (Level level : levels) {
>             availableCount += level.getAvailableCount();
>         }
>         return availableCount;
>     }
> 
>     public List<Spot> findSpotsForVehicle(Vehicle v) {
>         for (Level level : levels) {
>             List<Spot> spots = level.findSpotsForVehicle(v);
>             if (spots != null && !spots.isEmpty()) {
>                 return spots;
>             }
>         }
>         return null;
>     }
> 
>     public Ticket parkVehicle(Vehicle v) {
>         List<Spot> spots = findSpotsForVehicle(v);
>         if (spots == null || spots.isEmpty()) {
>             throw new ParkingLotFullException("No available spots for vehicle");
>         }
>         Ticket ticket = new Ticket(v, spots);
>         return ticket;
>     }
> 
>     public void clearSpot(Ticket t) {
>         // Implementation to clear spot based on ticket
>     }
> 
>     public float calculatePrice(Ticket t) {
>         // Implementation to calculate price based on ticket and hourly rate
>         return 0.0f;
>     }
> }
> 
> class Spot {
>     private boolean available;
>     private Level level;
> 
>     public boolean isAvailable() {
>         return available;
>     }
> 
>     public void takeSpot() {
>         available = false;
>     }
> 
>     public void leaveSpot() {
>         available = true;
>     }
> }
> 
> class Ticket {
>     private Vehicle vehicle;
>     private List<Spot> spots;
>     private Time startTime;
>   	int level;
>   	int spotId;
> 
>     public Ticket(Vehicle vehicle, int level, int spotId) {
>         this.level = level;
>       	this. spotId = spotId;
>         this.spots = spots;
>         this.startTime = Time.now(); // Assuming Time class exists
>     }
> }
> 
> class Level {
>     private List<Row> rows;
>     private int availableCount;
> 
>     public int getAvailableCount() {
>         return availableCount;
>     }
> }
> 
> class ParkingLotFullException extends RuntimeException {
>     public ParkingLotFullException(String message) {
>         super(message);
>     }
> }
> 
> class InvalidTicketException extends RuntimeException {
>     public InvalidTicketException(String message) {
>         super(message);
>     }
> }
> 
> class Row {
>     private List<Spot> spots;
> }
> 
> ```
>
> 
>
> 

### 5. Design Elevator

> ![image-20240503151457375](OOD案例.assets/image-20240503151457375.png)

### 6. Design Restaurant with Reservation

> ![image-20240503154844249](OOD案例.assets/image-20240503154844249.png)

### 7. Design Hotel with Reservation

> ![image-20240503155227459](OOD案例.assets/image-20240503155227459.png)

### 8. Design VendingMachine

> ![image-20240503162327415](OOD案例.assets/image-20240503162327415.png)

### 9. Design Coffee Maker

> ![image-20240503162455222](OOD案例.assets/image-20240503162455222.png)

### 10. Design Kindle

> ![image-20240503162647445](OOD案例.assets/image-20240503162647445.png)

### 11. Design Tic Tac Toe

> \- 玩家 - 规则 - 胜负 - 积分
>
> 对于本题:X always takes the first move 
>
> 棋牌类游戏的三种状态
>
> - Initialization (摆盘，洗牌...)
> - Play (下棋，出牌...)
> - Win/Lose check (胜负结算) + Tie (流局)
>
> Check if X win / Check if O win / Check if board full
>
> ![image-20240503220311413](OOD案例.assets/image-20240503220311413.png)
> ```java
> public class TicTacToe {
>     private Board board;
>     private char currentMove;
> 
>     public TicTacToe() {
>         board = new Board();
>         currentMove = 'X'; // X starts the game
>     }
> 
>     public void makeMove(int row, int col) {
>         board.makeMove(row, col, currentMove);
>         if (board.checkWin()) {
>             System.out.println("Player " + currentMove + " wins!");
>         } else if (board.isBoardFull()) {
>             System.out.println("It's a draw!");
>         } else {
>             changePlayer();
>         }
>     }
> 
>     private void changePlayer() {
>         currentMove = (currentMove == 'X') ? 'O' : 'X';
>     }
> }
> 
> public class Board {
>     private char[][] board;
> 
>     public Board() {
>         board = new char[3][3];
>     }
> 
>     public void makeMove(int row, int col, char currentMove) {
>         if (board[row][col] == '\0') {
>             board[row][col] = currentMove;
>         } else {
>             System.out.println("Invalid move. Cell already occupied.");
>         }
>     }
> 
>     public boolean checkWin() {
>         // Check rows
>         for (int i = 0; i < 3; i++) {
>             if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '\0') {
>                 return true;
>             }
>         }
>         // Check columns
>         for (int j = 0; j < 3; j++) {
>             if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != '\0') {
>                 return true;
>             }
>         }
>         // Check diagonals
>         if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '\0') ||
>                 (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '\0')) {
>             return true;
>         }
>         return false;
>     }
> 
>     public boolean isBoardFull() {
>         for (int i = 0; i < 3; i++) {
>             for (int j = 0; j < 3; j++) {
>                 if (board[i][j] == '\0') {
>                     return false;
>                 }
>             }
>         }
>         return true;
>     }
> }
> 
> ```

### 12. Design BattleShip

> ```java
> import java.util.ArrayList;
> import java.util.List;
> import java.util.Random;
> import java.util.Scanner;
> 
> // Represents a cell on the game board
> class Cell {
>     private boolean isOccupied;
>     private boolean isHit;
> 
>     public Cell() {
>         this.isOccupied = false;
>         this.isHit = false;
>     }
> 
>     public boolean isOccupied() {
>         return isOccupied;
>     }
> 
>     public void occupy() {
>         this.isOccupied = true;
>     }
> 
>     public boolean isHit() {
>         return isHit;
>     }
> 
>     public void hit() {
>         this.isHit = true;
>     }
> }
> 
> // Represents a ship in the game
> class Ship {
>     private int size;
>     private boolean isSunk;
> 
>     public Ship(int size) {
>         this.size = size;
>         this.isSunk = false;
>     }
> 
>     public int getSize() {
>         return size;
>     }
> 
>     public boolean isSunk() {
>         return isSunk;
>     }
> 
>     public void sink() {
>         this.isSunk = true;
>     }
> }
> 
> // Represents the game board
> class Board {
>     private Cell[][] grid;
>     private List<Ship> ships;
> 
>     public Board(int rows, int cols) {
>         this.grid = new Cell[rows][cols];
>         this.ships = new ArrayList<>();
> 
>         for (int i = 0; i < rows; i++) {
>             for (int j = 0; j < cols; j++) {
>                 this.grid[i][j] = new Cell();
>             }
>         }
>     }
> 
>     public boolean placeShip(Ship ship, int row, int col, boolean isVertical) {
>         int size = ship.getSize();
>         int endRow = isVertical ? row + size - 1 : row;
>         int endCol = isVertical ? col : col + size - 1;
> 
>         if (endRow >= grid.length || endCol >= grid[0].length) {
>             return false;
>         }
> 
>         for (int i = row; i <= endRow; i++) {
>             for (int j = col; j <= endCol; j++) {
>                 if (grid[i][j].isOccupied()) {
>                     return false;
>                 }
>             }
>         }
> 
>         for (int i = row; i <= endRow; i++) {
>             for (int j = col; j <= endCol; j++) {
>                 grid[i][j].occupy();
>             }
>         }
> 
>         ships.add(ship);
>         return true;
>     }
> 
>     public boolean isHit(int row, int col) {
>         return grid[row][col].isOccupied() && !grid[row][col].isHit();
>     }
> 
>     public void markHit(int row, int col) {
>         grid[row][col].hit();
>     }
> 
>     public boolean isGameOver() {
>         for (Ship ship : ships) {
>             if (!ship.isSunk()) {
>                 return false;
>             }
>         }
>         return true;
>     }
> }
> 
> // Represents the Battleship game
> public class BattleshipGame {
>     private Board playerBoard;
>     private Board computerBoard;
>     private Random random;
> 
>     public BattleshipGame() {
>         this.playerBoard = new Board(10, 10);
>         this.computerBoard = new Board(10, 10);
>         this.random = new Random();
>     }
> 
>     public void setupGame() {
>         // Place ships on player and computer boards
>         placeShips(playerBoard);
>         placeShips(computerBoard);
>     }
> 
>     private void placeShips(Board board) {
>         // Place ships randomly on the board for now
>         for (int i = 0; i < 5; i++) {
>             int row = random.nextInt(10);
>             int col = random.nextInt(10);
>             boolean isVertical = random.nextBoolean();
>             Ship ship = new Ship(3); // All ships have size 3 for simplicity
>             board.placeShip(ship, row, col, isVertical);
>         }
>     }
> 
>     public void playGame() {
>         Scanner scanner = new Scanner(System.in);
> 
>         while (!playerBoard.isGameOver() && !computerBoard.isGameOver()) {
>             System.out.println("Your turn:");
>             int row = scanner.nextInt();
>             int col = scanner.nextInt();
>             if (computerBoard.isHit(row, col)) {
>                 computerBoard.markHit(row, col);
>                 System.out.println("Hit!");
>             } else {
>                 System.out.println("Miss!");
>             }
> 
>             // Computer's turn
>             int compRow = random.nextInt(10);
>             int compCol = random.nextInt(10);
>             if (playerBoard.isHit(compRow, compCol)) {
>                 playerBoard.markHit(compRow, compCol);
>                 System.out.println("Computer hit your ship!");
>             } else {
>                 System.out.println("Computer missed!");
>             }
>         }
> 
>         if (playerBoard.isGameOver()) {
>             System.out.println("Congratulations! You win!");
>         } else {
>             System.out.println("Computer wins! Better luck next time!");
>         }
> 
>         scanner.close();
>     }
> 
>     public static void main(String[] args) {
>         BattleshipGame game = new BattleshipGame();
>         game.setupGame();
>         game.playGame();
>     }
> }
> ```
>

### 13. Disign Log Interface

> ```java
> import java.time.LocalDateTime;
> 
> // Enum for log levels
> enum LogLevel {
>     INFO,
>     DEBUG,
>     WARNING,
>     ERROR
> }
> 
> // Interface for a log entry
> interface LogEntry {
>     LocalDateTime getDateTime();
>     String getMessage();
>     LogLevel getLevel();
> }
> 
> // Implementation of LogEntry interface
> class SimpleLogEntry implements LogEntry {
>     private LocalDateTime dateTime;
>     private String message;
>     private LogLevel level;
> 
>     public SimpleLogEntry(LocalDateTime dateTime, String message, LogLevel level) {
>         this.dateTime = dateTime;
>         this.message = message;
>         this.level = level;
>     }
> 
>     @Override
>     public LocalDateTime getDateTime() {
>         return dateTime;
>     }
> 
>     @Override
>     public String getMessage() {
>         return message;
>     }
> 
>     @Override
>     public LogLevel getLevel() {
>         return level;
>     }
> }
> 
> // Interface for a logger
> interface Logger {
>     void log(String message, LogLevel level);
> }
> 
> // Implementation of Logger interface
> class SimpleLogger implements Logger {
>     @Override
>     public void log(String message, LogLevel level) {
>         LocalDateTime dateTime = LocalDateTime.now();
>         LogEntry entry = new SimpleLogEntry(dateTime, message, level);
>         // Implement logging logic here (e.g., writing to a file, printing to console)
>         System.out.println(entry.getDateTime() + " [" + entry.getLevel() + "] " + entry.getMessage());
>     }
> }
> 
> // Example usage
> public class Main {
>     public static void main(String[] args) {
>         Logger logger = new SimpleLogger();
>         logger.log("This is an info message", LogLevel.INFO);
>         logger.log("This is a debug message", LogLevel.DEBUG);
>         logger.log("This is a warning message", LogLevel.WARNING);
>         logger.log("This is an error message", LogLevel.ERROR);
>     }
> }
> ```

### 14. Design CheckOut Service

> OOP: Design a checkout service with given classes [coupon, payment, product] with list requirements. 
>
> 1. An order has at least a product, at most a coupon. 
> 2. With different payment method as giftcard and payment giftcard, make sure giftcard can not purchase giftcard. 
> 3. follow up: Discuss if we only have one coupon left but 5 people try to apply the coupon
>
> ```java
> import java.util.List;
> 
> public class Coupon {
>     private String code;
>     private double discountAmount;
>     private boolean valid;
> 
>     public Coupon(String code, double discountAmount) {
>         this.code = code;
>         this.discountAmount = discountAmount;
>         this.valid = true;
>     }
> 
>     public String getCode() {
>         return code;
>     }
> 
>     public double getDiscountAmount() {
>         return discountAmount;
>     }
> 
>     public boolean isValid() {
>         return valid;
>     }
> 
>     public void setValid(boolean valid) {
>         this.valid = valid;
>     }
> }
> 
> public interface Payment {
>     void processPayment(double amount);
> }
> 
> public class Product {
>     private String name;
>     private double price;
>     private int quantity;
> 
>     public Product(String name, double price, int quantity) {
>         this.name = name;
>         this.price = price;
>         this.quantity = quantity;
>     }
> 
>     public String getName() {
>         return name;
>     }
> 
>     public double getPrice() {
>         return price;
>     }
> 
>     public int getQuantity() {
>         return quantity;
>     }
> }
> 
> public class Order {
>     private List<Product> products;
>     private Coupon coupon;
>     private Payment payment;
> 
>     public Order(List<Product> products) {
>         if (products.isEmpty()) {
>             throw new IllegalArgumentException("An order must have at least one product");
>         }
>         this.products = products;
>     }
> 
>     public void applyCoupon(Coupon coupon) {
>         if (this.coupon != null) {
>             throw new IllegalStateException("An order can have at most one coupon");
>         }
>         this.coupon = coupon;
>     }
> 
>     public double calculateTotalPrice() {
>         double totalPrice = products.stream()
>           .mapToDouble(product -> product.getPrice() * product.getQuantity()).sum();
>         if (coupon != null && coupon.isValid()) {
>             totalPrice -= coupon.getDiscountAmount();
>         }
>         return totalPrice;
>     }
> 
>     public void processPayment(Payment payment) {
>         this.payment = payment;
>         double totalPrice = calculateTotalPrice();
>         payment.processPayment(totalPrice);
>     }
> }
> 
> public class GiftCardPayment implements Payment {
>     @Override
>     public void processPayment(double amount) {
>         // Logic to process payment using gift card
>         if (amount <= 0) {
>             throw new IllegalArgumentException("Invalid payment amount for gift card");
>         }
>     }
> }
> 
> public class Checkout {
>     public void processPayment(Payment payment, Order order) {
>         if (payment instanceof GiftCardPayment && Order.getProducts().contains("GiftCard")) {
>             throw new IllegalArgumentException("Cannot purchase gift card using gift card.");
>         }
>         payment.processPayment(amount);
>     }
> }
> ```
