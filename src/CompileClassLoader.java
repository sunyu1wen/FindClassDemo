import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.lang.reflect.Method;

/**
 * @author Bocai
 * @version V1.0
 * @ClassName: ${file_name}
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @Date ${date} ${time}
 */
public class CompileClassLoader extends ClassLoader {
    //读取文件内容
    @Test
    public byte[] getBytes(String filename) throws IOException {
        File file = new File(filename);
        long len = file.length();
        FileInputStream fin = new FileInputStream(file);
        byte[] raw = new byte[(int) len];
        //一次读取class文件的全部二进制数据
        int r = fin.read(raw);
        if (r != len) {
            throw new IOException("无法读取全部文件：" + r + " != " + len);
        }
        return raw;
    }


    private boolean compile(String javaFile) throws IOException {
        //调用系统的javac命令
        Process p = Runtime.getRuntime().exec("javac " + javaFile);
        //其他的线程都在等待这个线程完成
        try {
            p.waitFor();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            System.out.println(e);
        }
        //获取javac线程的退出值
        int ret = p.exitValue();
        return ret == 0;
    }


    protected Class<?> findClass(String name) {
        name = "main.bocai.HelloWorld";//这个参数放开则动态传值，默认赋值则可以始终读取这个class文件；
        System.out.println("进入findClass内部");
        Class clazz = null;
        String fileStub = name.replace(".", "/");
        String javaFilename = "src/" + fileStub + ".java";
        String ClassFilename = "out/production/findClassDemo/" + fileStub + ".class";
        File javaFile = new File(javaFilename);
        File classFile = new File(ClassFilename);
        //当指定的java源文件存在，且class文件不存在，或者java源文件的
        //修改时间比class文件修改时间晚时，重新编译
        if (javaFile.exists() && (classFile.exists() || javaFile.lastModified() > classFile.lastModified())) {
            try {
                if (!compile(javaFilename) || !classFile.exists()) { }
                if (classFile.exists()) {
                    byte[] raw = getBytes(ClassFilename);
                    clazz = defineClass(name, raw, 0, raw.length);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (clazz == null) {
                try {
                    throw new ClassNotFoundException(name);
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    System.out.println("找不到这个类");
                    System.out.println(e.toString());
                    System.out.println("找不到这个类");
                }
            }
        }
        return clazz;
    }

    /**
     * findClass 默认直接调用Findclass方法，没问题，可以正常调用；符合方法调用顺序
     * loadClass 则按照 BootStrapClassLoader 、ExtendClassLoader、appclassLoader的顺序，若在此基础上clazz未找到，则再调用findClass方法
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception{
        String progClass = "main.bocai.HelloWorld";
        CompileClassLoader ccl = new CompileClassLoader();
        String[] progArgs = new String[]{"嘿嘿"};
//        Class<?> clazz = ccl.findClass(progClass);//主要通过调用1
        Class<?> clazz = ccl.loadClass(progClass+"233");//调用2 两种方法来体会其中顺序
        Method main = clazz.getMethod("main", (new String[0]).getClass());
        Object[] argsArray = {progArgs};
        main.invoke(null,argsArray);
    }
    /*
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        throw new ClassNotFoundException(name);
    }
    默认是抛出异常找不到Class
    */
    /*loadClass（String name）；根据cn.com.akl.DemoController查找并加载类。先在parent或bootstrap中查找，有则给jvm加载。没有则按照findClass方法查找。
    findClass（）；默认抛出一个ClassNotFoundException，如果需要自己重新覆盖实现。
    defineClass（）；是将你定义的字节码文件经过字节数组流解密之后，将该字节流数组生成字节码文件，也就是该类的文件的类名.class。通常用在重写findClass中，返回一个Class。如果不想要把class加载到jvm中，也可以单独使用getConstructor和newInstance来实例化一个对象。
    defineClass( byte[] b ,0, b.length) ，这样生成的字节码就是默认的字节码文件。
    defineClass(String name  , byte[]  b , 0, b.length )，声明时，name 是指定该类名，这里的类名是指包含它所属的包名+类名
*/





}
