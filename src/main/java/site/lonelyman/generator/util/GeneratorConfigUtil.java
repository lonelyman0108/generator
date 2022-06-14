package site.lonelyman.generator.util;

import site.lonelyman.generator.entity.GeneratorSetting;

import java.util.List;

/**
 * <p> @Description: 代码生成器配置文件工具类
 * <p> @Author: LM
 * <p> @Date: 2022/1/21
 */

public class GeneratorConfigUtil {
    public static GeneratorSetting getConfig() {
        YamlReaderUtil instance = YamlReaderUtil.instance;
        return new GeneratorSetting(
                instance.getValueByKey("generator.datasource.url") + "",
                instance.getValueByKey("generator.datasource.username") + "",
                instance.getValueByKey("generator.datasource.password") + "",
                instance.getValueByKey("generator.author") + "",
                instance.getValueByKey("generator.packageName") + "",
                instance.getValueByKey("generator.moduleName") + "",
                (List<String>) instance.getValueByKey("generator.tables"),
                Boolean.getBoolean(instance.getValueByKey("generator.enableSwagger") + "")
        );
    }
}
