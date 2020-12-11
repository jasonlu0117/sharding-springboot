package com.jl.ss.sharding.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.jl.ss.sharding.datasource.strategy.DbShardingAlgorithm;
import com.jl.ss.sharding.datasource.strategy.TbShardingAlgorithm;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 配置数据源
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.shardingsphere.datasource.order0")
    public DataSource orderDataSource0() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.shardingsphere.datasource.order1")
    public DataSource orderDataSource1() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.shardingsphere.datasource.order2")
    public DataSource orderDataSource2() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.shardingsphere.datasource.order3")
    public DataSource orderDataSource3() {
        return DruidDataSourceBuilder.create().build();
    }


    @Bean
    @Primary
    public DataSource DataSource(@Autowired @Qualifier("orderDataSource0") DataSource orderDataSource0,
                                 @Autowired @Qualifier("orderDataSource1") DataSource orderDataSource1,
                                 @Autowired @Qualifier("orderDataSource2") DataSource orderDataSource2,
                                 @Autowired @Qualifier("orderDataSource3") DataSource orderDataSource3) throws SQLException {
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("order0", orderDataSource0);
        dataSourceMap.put("order1", orderDataSource1);
        dataSourceMap.put("order2", orderDataSource2);
        dataSourceMap.put("order3", orderDataSource3);

        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration("tb_order", "order${0..3}.tb_order_${0..3}");
        // 配置分库 + 分表策略
        orderTableRuleConfig.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id", new DbShardingAlgorithm()));
        orderTableRuleConfig.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("order_id", new TbShardingAlgorithm()));
        // key生成算法
//        Properties properties = new Properties();
//        properties.setProperty("max.vibration.offset", "4");
//        orderTableRuleConfig.setKeyGeneratorConfig(new KeyGeneratorConfiguration("SNOWFLAKE", "order_id", properties));
        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);
        // 获取数据源对象
        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new Properties());
        return dataSource;
    }

}
