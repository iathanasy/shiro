/**
 * Copyright (C), 2016-2019
 * FileName: ShiroApplication
 * Author:   cd
 * Date:     2019/4/23 10:20
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         版本号            描述
 */
package com.cd.shiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @desc
 * @author cd
 * @create 2019/4/23 10:20
 * @since 1.0.0
 */
@SpringBootApplication
public class ShiroApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ShiroApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ShiroApplication.class);
    }
}
