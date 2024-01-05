package interview.javainterview;

public class ImplictConversion {
    public static void test(byte b){
        System.out.println("byte");
    }
    public static void test(short b){
        System.out.println("short");
    }
    public static void test(char b){
        System.out.println("byte");
    }
    public static void test(int b){
        System.out.println("int");
    }
    public static void main(String[] args) {
        byte b = 10;
        test(b);

    }
}
