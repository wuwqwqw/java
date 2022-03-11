import org.junit.jupiter.api.Test;


public class ProxyTest {

    @Test
    public void StaticProxyTest(){
        StaticProxy staticProxy = new StaticProxy(new UserImpl());
        staticProxy.eat();
    }

    @Test
    public void JDKProxyTest(){
        JDKProxy.proxy(new UserImpl());
    }

    @Test
    public void CglibProxyTest(){
        CglibProxy.proxy(new UserImpl());
    }
}
