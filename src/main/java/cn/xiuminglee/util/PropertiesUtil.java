package cn.xiuminglee.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/11/8 15:40
 * @Version 1.0
 * @Describe:
 */
public class PropertiesUtil {
    private static Logger log = Logger.getLogger("PropertiesUtil");

    private static Properties props;

    static {
        String fileName = "conf.properties";
        props = new Properties();
        try {
            InputStream inputStream = ClassLoader.getSystemResourceAsStream(fileName);
            assert inputStream != null;
            props.load(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        } catch (IOException e) {
            log.warning("配置文件读取异常" + e.getMessage());
        }
    }

    public static String getProperty(String key){
        String value = props.getProperty(key.trim());
        if("".equals(value)){
            return null;
        }
        return value.trim();
    }

    public static String getProperty(String key,String defaultValue){

        String value = props.getProperty(key.trim());
        if("".equals(value)){
            value = defaultValue;
        }
        return value.trim();
    }
}
