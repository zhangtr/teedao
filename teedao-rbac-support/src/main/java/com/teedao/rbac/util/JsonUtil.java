package com.teedao.rbac.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Torry.Zhang
 * @since 2017/5/12
 */
public class JsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    public static ObjectMapper JSON_MAPPER = new ObjectMapper(); //转换器
    static {
        /*允许注释*/
        JSON_MAPPER.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        /*属性名称允许为非双引号*/
        JSON_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        /*允许使用单引号包住属性和值*/
        JSON_MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        /*允许接受所有引号引起来的字符*/
        JSON_MAPPER.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        /*允许值丢失*/
        JSON_MAPPER.configure(JsonParser.Feature.ALLOW_MISSING_VALUES, true);
        /*允许数字以多个0开头*/
        JSON_MAPPER.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
    }

    /**
     * 对象转json串
     *
     * @param o 支持：JavaBean/Map/List/Array等
     * @return
     */
    public static String beanToJson(Object o) {
        String json = null;
        try {
            json = JSON_MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            logger.error("对象转换JSON字符串失败", e);
        }
        return json;
    }

    /**
     * json串转对象
     *
     * @param json   json串
     * @param valueType 对象类型
     * @param <T>
     * @return
     */
    public static <T> T jsonToBean(String json, Class<T> valueType) {
        T t = null;
        try {
            t = JSON_MAPPER.readValue(json, valueType);
        } catch (IOException e) {
            logger.error("Json字符串转对象失败", e);
        }
        return t;
    }

    /**
     * json串转Map
     *
     * @param json json串
     * @return
     */
    public static Map jsonToMap(String json) {
        Map t = null;
        try {
            t = JSON_MAPPER.readValue(json, Map.class);
        } catch (IOException e) {
            logger.error("Json字符串转Map失败", e);
        }
        return t;
    }

    /**
     * jsonArray转换成Array数组：
     *
     * @param jsonArray
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T[] jsonToArray(String jsonArray, Class<T> valueType) {
        T[] ts = null;
        ArrayType arrayType = JSON_MAPPER.getTypeFactory().constructArrayType(valueType);
        try {
            ts = JSON_MAPPER.readValue(jsonArray, arrayType);
        } catch (IOException e) {
            logger.error("Json字符串转数组失败", e);
        }
        return ts;
    }

    //jsonArray转换成List<>泛型：
    public static <T> List<T> jsonToList(String jsonArray, Class<T> valueType) {
        CollectionType listType = JSON_MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, valueType);
        List<T> list = null;
        try {
            list = JSON_MAPPER.readValue(jsonArray, listType);
        } catch (IOException e) {
            logger.error("Json字符串转List失败", e);
        }
        return list;
    }

}
