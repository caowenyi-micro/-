package com.springcloud.platform.web.mybatis.controller;

import com.springcloud.platform.common.core.model.BaseResponse;
import com.springcloud.platform.web.mybatis.hotload.HotUserBiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;


@RestController
@RequestMapping("/api")
@Slf4j
public class HotLoadController {
    private HotUserBiz hotUserBiz = new HotUserBiz();

    @GetMapping("/reload")
    public BaseResponse<String> reload() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        hotUserBiz.reload();
        return BaseResponse.success("");
    }

    @GetMapping("/running")
    public BaseResponse<String> running() {
        hotUserBiz.running();
        return BaseResponse.success("成功");
    }

}
