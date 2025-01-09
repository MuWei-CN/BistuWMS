package com.example.spring.util;

import com.example.spring.bean.GoodsBean;
import com.example.spring.mapper.GoodsMapper.GoodsMapper;
import org.springframework.context.ApplicationContext;

public class SpringUtil {
    private static ApplicationContext appContext;

    public static void setAppContext(ApplicationContext appContext) {
        SpringUtil.appContext = appContext;
    }

    public static ApplicationContext getAppContext() {
        return appContext;
    }
}

