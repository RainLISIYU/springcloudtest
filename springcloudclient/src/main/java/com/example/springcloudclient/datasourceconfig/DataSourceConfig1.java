package com.example.springcloudclient.datasourceconfig;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.example.springcloudclient.jdbcutil.MyMetaObjectHandler;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @Description:数据源配置
 * @USER: 梁思禹
 * @DATE: 2022/2/20
 */
@Configuration
@MapperScan(basePackages = "com.example.springcloudclient.*.mapper", sqlSessionFactoryRef = "db1SqlSessionFactory")
public class DataSourceConfig1 {

    //日志拦截器
    private PerformanceInterceptor performanceInterceptor;

    //数据源拦截器
    private MyMetaObjectHandler myMetaObjectHandler;

    @Autowired
    public void setPerformanceInterceptor(PerformanceInterceptor performanceInterceptor) {
        this.performanceInterceptor = performanceInterceptor;
    }

    @Autowired
    public void setMyMetaObjectHandler(MyMetaObjectHandler myMetaObjectHandler) {
        this.myMetaObjectHandler = myMetaObjectHandler;
    }

    @Primary // 表示这个数据源是默认数据源, 这个注解必须要加，因为不加的话spring将分不清楚那个为主数据源（默认数据源）
    @Bean("db1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.source-one") //读取application.yml中的配置参数映射成为一个对象
    public DataSource getDb1DataSource(){
        return DataSourceBuilder.create().build();
    }
    @Primary
    @Bean("db1SqlSessionFactory")
    public MybatisSqlSessionFactoryBean db1SqlSessionFactory(@Qualifier("db1DataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // mapper的xml形式文件位置必须要配置，不然将报错：no statement （这种错误也可能是mapper的xml中，namespace与项目的路径不一致导致）
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
        //分页插件
        bean.setPlugins(new PaginationInterceptor());
        //日志输出
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.addInterceptor(performanceInterceptor);
        bean.setConfiguration(configuration);
        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setBanner(true);
        globalConfig.setMetaObjectHandler(myMetaObjectHandler);
        bean.setGlobalConfig(globalConfig);
        return bean;
    }

}