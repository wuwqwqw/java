public class InnerClassTest {

    public int field1 = 1;

    public class InnerClassA{
        public int field = 2;
    }

    public InnerClassTest(){

    }

    public static void main(String[] args) {
        InnerClassTest innerClassTest = new InnerClassTest();
        InnerClassA innerClassA = innerClassTest.new InnerClassA();
    }
}
