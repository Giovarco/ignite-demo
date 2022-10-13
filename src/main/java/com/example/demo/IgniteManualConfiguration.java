package com.example.demo;

import org.apache.ignite.configuration.DataRegionConfiguration;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.springframework.boot.autoconfigure.IgniteConfigurer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IgniteManualConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "ignite")
    public IgniteConfiguration igniteConfiguration(IgniteConfigurer configurer) {
        IgniteConfiguration cfg = new IgniteConfiguration();

        //data storage configuration
        DataStorageConfiguration storageCfg = new DataStorageConfiguration();
        final DataRegionConfiguration dataRegionConfiguration = storageCfg.getDefaultDataRegionConfiguration()
            .setPersistenceEnabled(true);
        storageCfg.setDefaultDataRegionConfiguration(dataRegionConfiguration);
        cfg.setDataStorageConfiguration(storageCfg);

        configurer.accept(cfg);
        return cfg;
    }

}