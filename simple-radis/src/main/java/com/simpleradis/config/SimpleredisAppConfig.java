package com.simpleradis.config;

import com.simpleradis.service.SimpleradisService;
import com.simpleradis.service.SimpleredisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan(basePackages = {"com.simpleradis"})
public class SimpleredisAppConfig {

    @Bean //("simpleradisService")
    public SimpleradisService simpleradisService() {
        return new SimpleredisServiceImpl();
    }

}
