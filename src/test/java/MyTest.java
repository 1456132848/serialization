public class MyTest {
    public static void main(String[] args) {
        char[] rotation = new char[95 * 2];
        for (char i = ' '; i <= '~'; i++) {
            rotation[i - ' '] = i;
            rotation[i + 95 - ' '] = i;
        }
        for (char c : rotation) {
            System.out.print(c);
        }
    }
}
