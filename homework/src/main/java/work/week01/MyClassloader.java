package work.week01;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassloader  extends ClassLoader{
    final String suffix = ".xlass";

    public static void main(String[] args) throws Exception {
        final String className = "Hello";
        final String methodName = "hello";

        ClassLoader classLoader = new MyClassloader();
        Class<?> xlass = classLoader.loadClass(className);

        Object instance = xlass.getDeclaredConstructor().newInstance();

        Method method = xlass.getMethod(methodName);
        method.invoke(instance);
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        String resourcePath = name.replace(".", "/");
        System.out.println(resourcePath);
        String fileName = resourcePath + suffix;
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName)) {
            int length = inputStream.available();
            byte[] bytes = new byte[length];
            inputStream.read(bytes);
            byte[] decodeBytes = decode(bytes);
            return defineClass(name, decodeBytes, 0, decodeBytes.length);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ClassNotFoundException(name, e);
        }
    }
    
    public byte[] decode(byte[] bytes) {
        byte[] decodeBytes = new byte[bytes.length];
        for (int i = 0; i < decodeBytes.length; i++) {
            decodeBytes[i] = (byte) (255 - bytes[i]);
        }
        return decodeBytes;
    }

}
