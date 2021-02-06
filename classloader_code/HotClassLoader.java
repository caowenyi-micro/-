package com.springcloud.platform.web.mybatis.hotload;

import java.io.*;
import java.net.URL;

public class HotClassLoader extends ClassLoader {
    private String rootPath;

    public HotClassLoader(String rootPath) {
        super(ClassLoader.getSystemClassLoader());
        this.rootPath = rootPath;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if(name.equalsIgnoreCase("com.springcloud.platform.web.mybatis.hotload.HotServiceImpl")){
            return findClass(name);
        }
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        Class clazz;
        byte[] classData = getClassData(name);    //根据类的二进制名称,获得该class文件的字节码数组
        if (classData == null) {
            throw new ClassNotFoundException();
        }
        clazz = defineClass(name, classData, 0, classData.length);    //将class的字节码数组转换成Class类的实例
        return clazz;
    }

    private byte[] getClassData(String name) {
        InputStream is = null;
        try {
            String path = classNameToPath(name);
            byte[] buff = new byte[1024 * 4];
            int len = -1;
            is=new FileInputStream(new File(path));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((len = is.read(buff)) != -1) {
                baos.write(buff, 0, len);
            }
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private String classNameToPath(String name) {
        return rootPath + "/" + name.replace(".", "/") + ".class";
    }

}
