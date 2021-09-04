

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 */
interface createPen{
    public void createA();

    void createB(String name);

}

class A implements createPen{
    @Override
    public void createA() {
        System.out.println("创建了A文具");
    }

    @Override
    public void createB(String name) {
        System.out.println("创建了B文具");
    }
}

class CreteProxyFactory{

    public static Object getNewInstance(Object obj){
    MyInvocationHandler handler= new MyInvocationHandler();
    handler.bind(obj);
     return    Proxy.newProxyInstance(obj.getClass().getClassLoader(),
             obj.getClass().getInterfaces(),handler);
    }
}

class MyInvocationHandler implements InvocationHandler{
    private Object obj;
    //获取被代理对象的方法
    public void bind(Object obj){
        this.obj=obj;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = method.invoke(obj, args);
        return invoke;
    }
}

public class ProxyTest {
    @Test
    public  void test1() {
        createPen createPen = (createPen) CreteProxyFactory.getNewInstance(new A());
        createPen.createA();


    }
}
