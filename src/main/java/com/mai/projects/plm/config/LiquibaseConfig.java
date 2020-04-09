package com.mai.projects.plm.config;

import liquibase.integration.spring.SpringLiquibase;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class LiquibaseConfig {

    private final DataSource dataSource;

    private final ResourceLoader resourceLoader;

    public SpringLiquibase liquibase() throws Exception {
        //      Locate change log file
        String changelogFile = "classpath:com\\mai\\projects\\plm\\database\\migration\\smarthome.xml";
        Resource resource = resourceLoader.getResource(changelogFile);

        Assert.state(resource.exists(), "Unable to find file: " + resource.getFilename());
        // Configure Liquibase
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog(changelogFile);
        liquibase.setDataSource(dataSource);
        liquibase.setDropFirst(true);
        liquibase.setShouldRun(true);

        // Verbose logging
        Map<String, String> params = new HashMap<>();
        params.put("verbose", "true");
        return liquibase;
    }
}