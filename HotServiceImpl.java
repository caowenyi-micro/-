package com.springcloud.platform.web.mybatis.hotload;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HotServiceImpl implements HotService {
    @Override
    public void printMessage() {
        log.info("old message");
    }
}
