package com.zuiyu.encryptdemo.encry.interceptor;

import com.baomidou.mybatisplus.extension.plugins.handler.MultiDataPermissionHandler;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * https://mp.weixin.qq.com/s/AcBpx81HFhh6t0oG69CpnQ
 * 当时写的sql 拦截加解密
 */
public class CustomDataPermissionHandler implements MultiDataPermissionHandler {

    public final Logger log = LoggerFactory.getLogger(getClass());
    @Override
    public Expression getSqlSegment(Table table, Expression where, String mappedStatementId) {
        // 在此处编写自定义数据权限逻辑
        try {
            String sqlSegment = "1=1 and 2=2 and age = '18'";
            log.info("SQL  权限语句拦截123");
            return CCJSqlParserUtil.parseCondExpression(sqlSegment);
        } catch (JSQLParserException e) {
            e.printStackTrace();
            return null;
        }
    }
}