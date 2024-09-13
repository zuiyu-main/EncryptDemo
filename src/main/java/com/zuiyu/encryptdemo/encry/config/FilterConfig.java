package com.zuiyu.encryptdemo.encry.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cxt
 * @version 1.0
 * @description: TODO
 * @date 2024/9/9 17:02
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<TenantContextWebFilter> tenantContextWebFilter() {
        FilterRegistrationBean<TenantContextWebFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TenantContextWebFilter());
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
