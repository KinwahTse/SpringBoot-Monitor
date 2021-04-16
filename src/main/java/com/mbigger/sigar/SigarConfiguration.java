package com.mbigger.sigar;

import org.hyperic.sigar.Sigar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SigarConfiguration {

    @Bean
    public Sigar sigar() {
        return new Sigar();
    }
}
