package com.zuiyu.encryptdemo.encry.config;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author zuiyu
 */
public class EncryptCondition {
    public static class DbCondition implements Condition {
        @Override
        public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
            String type = conditionContext.getEnvironment().getProperty("encrypt.type");
            return "DB".equalsIgnoreCase(type);
        }
    }
    public static class EsCondition implements Condition {
        @Override
        public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
            String type = conditionContext.getEnvironment().getProperty("encrypt.type");
            return "ES".equalsIgnoreCase(type);
        }
    }
}
