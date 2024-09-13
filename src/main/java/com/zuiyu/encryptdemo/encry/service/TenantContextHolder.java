package com.zuiyu.encryptdemo.encry.service;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.extern.slf4j.Slf4j;

/**
 * @author cxt
 * @version 1.0
 * @description: TODO
 * @date 2024/9/9 16:58
 */
@Slf4j
public class TenantContextHolder {
    private static final ThreadLocal<Long> TENANT_ID = new TransmittableThreadLocal<>();

    public static Long getTenantId() {
        log.info("读取请求租户ID:[{}]",Thread.currentThread().getName());
        return TENANT_ID.get();
    }
    public static void setTenantId(Long tenantId) {
        log.info("设置请求租户ID:[{}:{}]",Thread.currentThread().getName(),tenantId);
        TENANT_ID.set(tenantId);
    }
    public static void clear() {
        TENANT_ID.remove();
    }
}
