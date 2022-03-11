public class StaticProxy {
    public UserImpl userImpl;
    public StaticProxy(UserImpl userImpl){
        this.userImpl = userImpl;
    }

    public void eat(){
        System.out.println("before method");
        userImpl.eat();
        System.out.println("after method");
    }
}
