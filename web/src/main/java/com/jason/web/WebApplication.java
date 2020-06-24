package com.jason.web;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import io.github.yedaxia.apidocs.plugin.markdown.MarkdownDocPlugin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        genApiDoc();
        SpringApplication.run(WebApplication.class, args);
    }

    private static void genApiDoc() {
        String projectPath = System.getProperty("user.dir") + "/web";
        File docPath = new File(projectPath + "/src/main/resources/apidoc/");
        DocsConfig config = new DocsConfig();
        // 项目根目录
        config.setProjectPath(projectPath);
        config.setProjectName("change-the-world");
        config.setApiVersion("V2.1");
        // 生成API 文档所在目录
        config.setDocsPath(docPath.getAbsolutePath());
        // 配置自动生成
        config.setAutoGenerate(Boolean.TRUE);
        // 执行生成文档
        config.addPlugin(new MarkdownDocPlugin());

        Docs.buildHtmlDocs(config);
    }

}
