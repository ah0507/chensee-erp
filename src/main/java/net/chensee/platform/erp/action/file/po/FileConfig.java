package net.chensee.platform.erp.action.file.po;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author ah
 * @title: FileConfig
 * @date 2019/11/12 15:49
 */
@Data
@Component
@ConfigurationProperties(prefix = "file")
@PropertySource(value = "classpath:fileConfig.yml", encoding = "UTF-8")
public class FileConfig {

    private String url;

    private Map<String,String> format;

    private List<String> view;
}
