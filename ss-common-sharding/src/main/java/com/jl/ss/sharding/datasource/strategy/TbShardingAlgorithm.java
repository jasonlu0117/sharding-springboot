package com.jl.ss.sharding.datasource.strategy;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class TbShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        Long index = preciseShardingValue.getValue() & 3;
        for (String dataSourceName : collection) {
            if (dataSourceName.endsWith(index + "")) {
                return dataSourceName;
            }
        }
        throw new UnsupportedOperationException();
    }
}
