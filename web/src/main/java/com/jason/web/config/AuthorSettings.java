package com.jason.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Author: lingyou
 * date: 2019-10-11 22:43
 */
@Data
@Component
@ConfigurationProperties(prefix = "author")
public class AuthorSettings {

    private String name;
    private String age;
}
