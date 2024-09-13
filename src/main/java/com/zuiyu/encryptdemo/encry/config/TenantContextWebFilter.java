package com.zuiyu.encryptdemo.encry.config;


import cn.hutool.core.util.NumberUtil;
import com.zuiyu.encryptdemo.encry.service.TenantContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class TenantContextWebFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        // 设置
        Long tenantId = getTenantId(request);
        if (tenantId != null) {
            TenantContextHolder.setTenantId(tenantId);
        }
        try {
            chain.doFilter(request, response);
        } finally {
            // 清理
            TenantContextHolder.clear();
        }
    }
    public static Long getTenantId(HttpServletRequest request) {
        String tenantId = request.getHeader("tenant-id");
        return NumberUtil.isNumber(tenantId) ? Long.valueOf(tenantId) : null;
    }
}
