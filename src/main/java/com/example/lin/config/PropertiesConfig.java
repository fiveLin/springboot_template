package com.example.lin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wujm on 2017/6/30.
 */
@Configuration
public class PropertiesConfig {

    private static final String APP_NAME_KEY = "app.name";
    private static final String APP_CONF_KEY = "app.conf";

    private static final String APP_NAME = "wechat";

    private static final String fileUploadPath = "c:\\lin\\";

    private static final String CONFIG_ROOT_LINUX = "/lin/config";
    private static final String CONFIG_ROOT_WINDOWS = "c:\\lin";

    public static Map<String, String> configMap = new ConcurrentHashMap<> ();

    public static Map<String, String> test = new ConcurrentHashMap<> ();

    public static Map<String, String> skinMap = new ConcurrentHashMap<> ();

    public static Map<String, Map<String, String>> pTable = new ConcurrentHashMap<> ();


    private static final Pattern PATTERN = Pattern
            .compile("\\$\\{([^\\}]+)\\}");

    private static final Pattern PATTERN3 = Pattern
            .compile("\\{[^\\}]+\\}");

    public static final String MAP_CONFIG = "config";
//    public static final String MAP_SKIN = "skin";
    public static final String MAP_DEFAULT = "default";
    @PostConstruct
    public void init() throws IOException {
        pTable.put(MAP_CONFIG,configMap);
//        pTable.put(MAP_SKIN,skinMap);

        initConfig("/config.properties",MAP_CONFIG,false);
//        initConfig("/skin.properties",MAP_SKIN,false);
//        initConfig("/default.properties",MAP_DEFAULT,false);
    }


    public static void reload(String fileName, String table) throws IOException {
        String configRootDir = getConfigRootDir();
        String appConfigDir = configRootDir + File.separator + APP_NAME;

        String encoding = "UTF-8";
        Properties properties = null;
        InputStream inputStream = null;
        inputStream = new FileInputStream (appConfigDir+fileName);

        loadProperties(fileName, table, inputStream);
    }

    public void initConfig(String fileName, String table, boolean inner) throws IOException {

        String configRootDir = getConfigRootDir();
        String appConfigDir = configRootDir + File.separator + APP_NAME;

        String encoding = "UTF-8";
        Properties properties = null;
        InputStream inputStream = null;
        if(inner){
            inputStream = new FileInputStream (new ClassPathResource(fileName).getFile());
        }else {
            inputStream = new FileInputStream (appConfigDir+fileName);
        }

        loadProperties(fileName, table, inputStream);
    }

    private static void  loadProperties(String fileName, String table, InputStream inputStream) throws IOException {
        Properties properties;
        if (inputStream == null) {
            throw new IllegalArgumentException ("Properties file not found in classpath: " + fileName);
        }
        properties = new Properties ();

        PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
        propertiesPersister.load(properties, inputStream);

        Map<String, String> reKeyMap = new HashMap<> ();
        Map<String, String> reReyMap = new HashMap<> ();
        Enumeration<?> e = properties.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = properties.getProperty(key);
            Matcher matcher = PATTERN.matcher(value);
            if (matcher.find()) {
                reReyMap.put(key, value);
            } else {
                reKeyMap.put(key, value);
            }
        }

        for (String in : reReyMap.keySet()) {
            String str = reReyMap.get(in);
            Matcher matcher = PATTERN3.matcher(str);
            while (matcher.find()) {
                String key2 = matcher.group().replaceAll("[\\{\\}]", "");
                str = str.replace("${" + key2 + "}", reKeyMap.get(key2));
            }
            reReyMap.put(in, str);
        }


        reReyMap.putAll(reKeyMap);
        pTable.put(table, reReyMap);
        test.putAll(reReyMap);
    }

    /**
     * 获取配置文件根目录
     *
     * @return
     */
    @Bean
    public static String getConfigRootDir() {
        boolean isLinuxSystem = System.getProperty("os.name").toLowerCase().contains("linux");

        String configRootDir;
        if (isLinuxSystem) {
            configRootDir = CONFIG_ROOT_LINUX;
        } else {
            configRootDir =  CONFIG_ROOT_WINDOWS;
        }

        return configRootDir;
    }

    public static String getVal(String key, String defaultValue) {
        String val = pTable.get(MAP_CONFIG).get(key);
        return (val == null) ? defaultValue : val;
    }

    public static String getVal(String key) {
        String val = pTable.get(MAP_CONFIG).get(key);
        return val;
    }


    public static Integer getInt(String map, String key, int defaultValue) {
        String val = pTable.get(map).get(key);
        return (val == null) ? defaultValue : Integer.parseInt(val);
    }
    public static Integer getInt(String map, String key) {
        String val = pTable.get(map).get(key);
        return (val == null) ? 0 : Integer.parseInt(val);
    }


    public static String get(String map, String key, String defaultValue) {
        String val = pTable.get(map).get(key);
        return (val == null) ? defaultValue : val;
    }
    public static String get(String map, String key) {
        String val = pTable.get(map).get(key);
        return (val == null) ? "" : val;
    }



    public static Boolean getBoolean(String key, Boolean defaultValue) {
        String val = pTable.get(MAP_CONFIG).get(key);
        if (val != null) {
            val = val.toLowerCase().trim();
            if ("true".equals(val)){
                return true;
            }else if ("false".equals(val)){
                return false;
            }
            throw new RuntimeException ("The value can not parse to Boolean : " + val);
        }
        return defaultValue;
    }

    public static Map<String, String> getByPrefix(String prefix) {
        Map<String, String> map = new HashMap<String, String> ();

        if (prefix == null || prefix.isEmpty()) {
            return map;
        }
        for (String item : pTable.get(MAP_CONFIG).keySet()) {
            if (item.startsWith(prefix)) {
                map.put(item,pTable.get(MAP_CONFIG).get(item));
            }
        }
        for (String item : pTable.get(MAP_DEFAULT).keySet()) {
            if (item.startsWith(prefix)) {
                map.put(item,pTable.get(MAP_DEFAULT).get(item));
            }
        }
        return map;
    }

    public static void refreshConfig() throws Exception {

        forTreat("/config.properties",MAP_CONFIG,false);
    }

    private static void forTreat(String fileName, String table, boolean inner) throws Exception {
        String configRootDir = getConfigRootDir();
        String appConfigDir = configRootDir + File.separator + APP_NAME;

        String encoding = "UTF-8";
        Properties properties = null;
        InputStream inputStream = null;
        if(inner){
            inputStream = new FileInputStream (new ClassPathResource(fileName).getFile());
        }else {
            inputStream = new FileInputStream (appConfigDir+fileName);
        }

        loadProperties(fileName, table, inputStream);
    }

}