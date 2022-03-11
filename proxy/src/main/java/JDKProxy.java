import jdk.nashorn.internal.objects.annotations.Setter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy implements InvocationHandler {

    private Object object;

    public JDKProxy(Object object) {
        this.object = object;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before method");
        Object result = method.invoke(object, args);
        System.out.println("after method");
        return result;
    }

    public static void proxy(Object object){
        JDKProxy jdkProxy = new JDKProxy(object);
        User user = (User) Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), jdkProxy);
        user.eat();
    }

}
