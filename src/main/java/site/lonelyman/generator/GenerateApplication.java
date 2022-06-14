package site.lonelyman.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.apache.commons.lang3.ObjectUtils;
import site.lonelyman.generator.entity.BaseEntity;
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
                                    .fileOverride()
                                    .disableOpenDir()
                                    .outputDir("./output/main/java");
                            if (config.isEnableSwagger()) {
                                builder.enableSwagger();
                            }
                        }
                )
                // 包配置
                .packageConfig(builder ->
                        builder.parent(config.getPackageName()) // 设置父包名
                                .moduleName(config.getModuleName()) // 设置父包模块名
                                .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "./output/main/resources/mapper"))
                )
                // 模板配置
                .templateConfig(builder ->
                        builder.entity("template/entity.java.vm")
                                .mapper("template/mapper.java.vm")
                                .mapperXml("template/mapper.xml.vm")
                                .service("template/service.java.vm")
                                .serviceImpl("template/serviceImpl.java.vm")
                                .controller("template/controller.java.vm")
                )
                // 策略配置
                .strategyConfig(builder -> {
                            builder.entityBuilder()
                                    .superClass(BaseEntity.class)
                                    .disableSerialVersionUID()
                                    .enableChainModel()
                                    .addSuperEntityColumns(
                                            "id",
                                            "create_time",
                                            "update_time",
                                            "create_user_id",
                                            "update_user_id",
                                            "is_deleted"
                                    )
                                    .enableLombok()
                                    .controllerBuilder()
                                    .enableRestStyle()
                                    .mapperBuilder()
                                    .enableMapperAnnotation();
                            if (CollectionUtils.isNotEmpty(config.getTables()) && config.getTables().size() > 1) {
                                builder.addInclude(config.getTables());
                            }
                        }
                )
                .execute();
    }
}
