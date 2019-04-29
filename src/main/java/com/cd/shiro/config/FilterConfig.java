/**
 * Copyright (C), 2016-2019
 * FileName: FilterConfig
 * Author:   cd
 * Date:     2019/4/25 8:47
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         版本号            描述
 */
package com.cd.shiro.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * @desc Filter配置
 * @author cd
 * @create 2019/4/25 8:47
 * @since 1.0.0
 */
@Configuration
public class FilterConfig {

    /**
     * 配置shiroFilter
     * @return
     */
    @Bean
    public FilterRegistrationBean delegatingFilterProxy(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }
}
