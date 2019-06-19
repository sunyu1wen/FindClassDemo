package main;

/**
 * @author Bocai
 * @version V1.0
 * @ClassName: ${file_name}
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @Date ${date} ${time}
 */
public class Person {

    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Person(){
        System.out.println("无参 构造函数");
    }

    public Person(String name ,Integer age){
        System.out.println("有参 构造函数"+name+"名字"+age+"年龄");
        this.name = name;
        this.age = age;
    }

    public static void function1(){
        System.out.println("function1");
    }
    public void function2(){
        System.out.println("function2=="+this.name+"==="+this.age+"====");
    }
    public void function3(String s,int i,long l){
        System.out.println(s+":::"+i+"long:"+l+"===");
    }
    @SuppressWarnings("unused")
    private void function4(){
        System.out.println("function4");
    }
}
