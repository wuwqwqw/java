import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

//Cglib动态代理，实现MethodInterceptor接口
public class CglibProxy implements MethodInterceptor {
    private Object target;//需要代理的目标对象

    //重写拦截方法
    public Object intercept(Object obj, Method method, Object[] arr, MethodProxy proxy) throws Throwable {
        System.out.println("Cglib动态代理，监听开始！");
        Object invoke = method.invoke(target, arr);//方法执行，参数：target 目标对象 arr参数数组
        System.out.println("Cglib动态代理，监听结束！");
        return invoke;
    }
    //定义获取代理对象方法
    public Object getCglibProxy(Object objectTarget){
        //为目标对象target赋值
        this.target = objectTarget;
        Enhancer enhancer = new Enhancer();
        //设置父类,因为Cglib是针对指定的类生成一个子类，所以需要指定父类
        enhancer.setSuperclass(objectTarget.getClass());
        enhancer.setCallback(this);// 设置回调
        Object result = enhancer.create();//创建并返回代理对象
        return result;
    }

    public static void proxy(UserImpl userImpl) {
        CglibProxy cglib = new CglibProxy();//实例化CglibProxy对象
        User user =  (User) cglib.getCglibProxy(userImpl);//获取代理对象
        user.eat();//执行删除方法
    }

}