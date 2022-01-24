package site.lonelyman.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import site.lonelyman.generator.entity.GeneratorSetting;
import site.lonelyman.generator.util.GeneratorConfigUtil;

import java.util.Collections;

/**
 * <p> @Description: 代码生成代码
 * <p> @Author: LM
 * <p> @Date: 2022/1/21
 */

public class GenerateApplication {
    public static void main(String[] args) {
        GeneratorSetting config = GeneratorConfigUtil.getConfig();

        FastAutoGenerator.create(config.getUrl(), config.getUsername(), config.getPassword())
                // 全局配置
                .globalConfig(builder -> {
                    builder.author(config.getAuthor())
                            .enableSwagger()
                            .fileOverride()
                            .disableOpenDir()
                            .outputDir("./output/main/java");
                })
                // 包配置
                .packageConfig(builder -> {
                    builder.parent(config.getPackageName()) // 设置父包名
                            .moduleName(config.getModuleName()) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "./output/main/resources/mapper"));
                })
                // 模板配置
                .templateConfig(builder -> {
                    builder.entity("template/entity.java.vm")
                            .mapper("template/mapper.java.vm")
                            .mapperXml("template/mapper.xml.vm")
                            .service("template/service.java.vm")
                            .serviceImpl("template/serviceImpl.java.vm")
                            .controller("template/controller.java.vm");
                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder.addInclude(config.getTables())
                            .entityBuilder()
                            .enableLombok();
                })

                .execute();
    }
}
