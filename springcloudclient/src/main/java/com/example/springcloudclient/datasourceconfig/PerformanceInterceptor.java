package com.example.springcloudclient.datasourceconfig;

import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.core.toolkit.SystemClock;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.sql.Connection;

/**
 * @Description:mybatis日志输出配置
 * @USER: 梁思禹
 * @DATE: 2022/3/30
 */
@Component
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PerformanceInterceptor implements Interceptor {

    private static final Log logger = LogFactory.getLog(PerformanceInterceptor.class);

    public Object intercept(Invocation invocation) throws Throwable {

        // 其它代码

        // 计算执行 SQL 耗时
        long start = SystemClock.now();
        Object result = invocation.proceed();
        long timing = SystemClock.now() - start;

        // 格式化 SQL 打印执行结果
        Object target = PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(target);
        MappedStatement ms = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        StringBuilder formatSql = new StringBuilder();

        //SQL语句
        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        String originalSql = boundSql.getSql();

        // 执行耗时
        formatSql.append(" Time：").append(timing);

        // Mapper接口方法完整路径
        formatSql.append(" ms - ID：").append(ms.getId());

        // SQL语句
        formatSql.append("\n Execute SQL：").append(SqlUtils.concatLike(originalSql, SqlLike.DEFAULT)).append("\n");

        logger.debug(formatSql.toString());

        /*if (this.isWriteInLog()) {
            if (this.getMaxTime() >= 1 && timing > this.getMaxTime()) {
                logger.error(formatSql.toString());
            } else {
                logger.debug(formatSql.toString());
            }
        } else {
            System.err.println(formatSql.toString());
            if (this.getMaxTime() >= 1 && timing > this.getMaxTime()) {
                throw new MybatisPlusException(" The SQL execution time is too large, please optimize ! ");
            }
        }*/
        return result;
    }
}
