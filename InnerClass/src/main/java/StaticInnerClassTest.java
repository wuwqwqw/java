public class StaticInnerClassTest {

    public int field1 = 1;

    public static class StaticInnerClass{

        public int field2 = 1;
    }

    public static void main(String[] args) {
        StaticInnerClass staticInnerClass = new StaticInnerClass();
    }
}
