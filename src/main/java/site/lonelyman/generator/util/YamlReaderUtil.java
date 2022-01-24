package site.lonelyman.generator.util;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> @Description: 解析YML配置文件工具类
 * <p> @Author: LM
 * <p> @Date: 2022/1/21
 */

public class YamlReaderUtil {

    private static Map<String, Map<String, Object>> properties = new HashMap<>();

    /**
     * 单例
     */
    public static final YamlReaderUtil instance = new YamlReaderUtil();

    static {
        Yaml yaml = new Yaml();
        try (InputStream in = YamlReaderUtil.class.getClassLoader().getResourceAsStream("application.yml");) {
            properties = yaml.loadAs(in, HashMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * get yaml property
     *
     * @param key
     * @return
     */
    public Object getValueByKey(String key) {
        String separator = ".";
        String[] separatorKeys = null;
        if (key.contains(separator)) {
            separatorKeys = key.split("\\.");
        } else {
            return properties.get(key);
        }
        Map<String, Map<String, Object>> finalValue = new HashMap<>();
        for (int i = 0; i < separatorKeys.length - 1; i++) {
            if (i == 0) {
                finalValue = (Map) properties.get(separatorKeys[i]);
                continue;
            }
            if (finalValue == null) {
                break;
            }
            finalValue = (Map) finalValue.get(separatorKeys[i]);
        }
        return finalValue == null ? null : finalValue.get(separatorKeys[separatorKeys.length - 1]);
    }
}
