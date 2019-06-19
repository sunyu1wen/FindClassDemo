package main.reflect;

import main.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author Bocai
 * @version V1.0
 * @ClassName: ${file_name}
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @Date ${date} ${time}
 */
public class GetMethodDemo {

    public static void main(String[] args) throws Exception {
        //方式1
//        String className = "main.Person";
//        Class<?> c = Class.forName(className);
        //方式2
        Person p = new Person();
        Class<?> c = p.getClass();

        //获取公共方法：
        Method[] pubMethods = c.getMethods();

        //获取私有方法：
        Method[] priMethods = c.getDeclaredMethods();

        //获取单个方法：按方法名和参数获取

        //获取单个の静态方法：function1
        Method staMethod = c.getMethod("function1",null);
        //获取单个の无参数方法：function2
        Method nullMethod = c.getMethod("function2",null);
        //获取单个の有参数方法：function3
        Method moreMethod = c.getMethod("function3",String.class,int.class);
        //获取单个の私有方法：function4
        Method priMethod = c.getDeclaredMethod("function4",null);

        //打印查看效果
        System.out.println("[Person类的公共方法及父类方法:]");
        for(Method m:pubMethods){
            System.out.println(m);
        }
        System.out.println("[Person类的私有方法:]");
        for(Method m:priMethods){
            System.out.println(m);
        }
        System.out.println("[按方法名和参数类型获取的方法4个方法:]");
        System.out.println(staMethod);
        System.out.println(nullMethod);
        System.out.println(moreMethod);
        System.out.println(priMethod);
    }

    /**
     *方式1 和方式 2  1比2 多了一个构造函数的过程；
     * @throws Exception
     */
    @Test
    public void testInstance() throws Exception {
//        方式1
//        Person p = new Person();
//        Class clazz = p.getClass();
        // 方式2
        String classStr = "main.Person";
        Class clazz = Class.forName(classStr);
        Person test = (Person) clazz.newInstance();
        test.function2();
    }

    /**
     * 测试反射中的有参构造方法
     * @throws Exception
     */
    @Test
    public void testInstance2()throws Exception{
        String classStr = "main.Person";
        Class clazz2 = Class.forName(classStr);
        Constructor con = clazz2.getConstructor(String.class, Integer.class);
        Person obj = (Person) con.newInstance("Sunyw", 24);
        obj.function2();
    }

    /**
     * 测试method invoke方法
     * @throws Exception
     */
    @Test
    public void testInvoke() throws Exception{
        String classStr = "main.Person";
        Class clazz = Class.forName(classStr);
        Object person = clazz.newInstance();
        Person person2 = (Person) clazz.newInstance();
//      Method[] methods = clazz.getDeclaredMethods();
        Method function1 =clazz.getMethod("function1",null);
        function1.invoke(person2,null);
        function1.invoke(person,null);
        Method function3 =clazz.getMethod("function3", String.class, int.class,long.class);
        function3.invoke(person,"test",12,120l);
    }

}
