package main.bocai;

/**
 * @author Bocai
 * @version V1.0
 * @ClassName: ${file_name}
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @Date ${date} ${time}
 */
public class HelloWorld {
    public HelloWorld() {
        System.out.println("HelloWorld shi  yi zhong xing yang!");
    }

    public static void show(){
        System.out.println("====== wu can  fang fa ======");
    }
    public static void sayHi(String name) {
        System.out.println("=======HelloWorld=======" + name + "==========");
    }

    public static void main(String[] args) {
        System.out.println("进入 HelloWorld main 方法");
        for (String arg : args) {
            System.out.println("运行Hello的参数：" + arg);
        }
    }
}
