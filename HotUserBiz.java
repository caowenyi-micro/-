package com.springcloud.platform.web.mybatis.hotload;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class HotUserBiz {
    private HotService hotService;

    public synchronized void reload() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        HotClassLoader hotClassLoader = new HotClassLoader("/home/wycao/hotclass");
        Class<HotService> hotClass = (Class<HotService>) hotClassLoader.loadClass("com.springcloud.platform.web.mybatis.hotload.HotServiceImpl");
        Constructor<HotService> constructor = hotClass.getConstructor();
        hotService = constructor.newInstance();
    }

    public synchronized void running() {
        new Thread(() -> {
            while (true) {
                hotService.printMessage();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
