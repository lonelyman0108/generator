package site.lonelyman.generator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * <p> @Description: 代码生成器配置实体
 * <p> @Author: LM
 * <p> @Date: 2022/1/21
 */

@Data
@AllArgsConstructor
public class GeneratorSetting {
    private String url;
    private String username;
    private String password;
    private String author;
    private String packageName;
    private String moduleName;
    private List<String> tables;
}
