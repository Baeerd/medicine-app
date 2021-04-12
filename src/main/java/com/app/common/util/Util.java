package com.app.common.util;

import com.app.common.entity.DataConfig;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 工具类
 */
public class Util {

    /**
     * json字符串转Map
     * @param json
     * @return
     */
    public static Map<String, String> jsonToMap(String json) {
        Map<String, String> resultMap = new HashMap<>();
        JSONObject jsonObject = JSONObject.fromObject(json);
        return (Map)jsonObject;
    }

    /** json字符串转换为对象
     * @param json
     * @param type
     * @return
     */
    public  static <T>  T jsonToBean(Object json, Class<T> type) {
        JSONObject jsonObject = JSONObject.fromObject(json);
        T obj =(T)JSONObject.toBean(jsonObject, type);
        return obj;
    }

    /**
     * json字符串转换为对象集合
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonArrToList(Object json, Class<T> type) {
        List resultList = new ArrayList();
        if(json == null || StringUtils.isEmpty(json.toString())) {
            return null;
        }
        String tempJson = json.toString().replace("[", "").replace("]", "");
        String[] jsonList = tempJson.trim().split("},");
        for (String s : jsonList) {
            T t = Util.jsonToBean(s+"}", type);
            resultList.add(t);
        }
        return resultList;
    }

    /**
     * map转换成json
     * @param StringMap
     * @return
     */
    public static String mapToJson(Map StringMap) {
        JSONObject json = JSONObject.fromObject(StringMap);
        return json==null?"":json.toString();
    }

    /**
     * 实体类转换成json
     * @param bean
     * @return
     */
    public static String beanToJson(Object bean) {
        // 处理日期类型格式（用自定义的date处理器）
        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor());
        JSONObject json = JSONObject.fromObject(bean,config);
        return json==null?"":json.toString();
    }

    /**
     * 获取当前时间，格式yyyyMMddHHssSSS
     * @return
     */
    public static String getCurrentTime() {
        DateFormat format = new SimpleDateFormat("yyyyMMddHHssSSS");
        return format.format(new Date());
    }

    /**
     * 集合转换成json
     * @param bean
     * @return
     */
    public static String arrayToJson(Object bean) {
        // 处理日期类型格式（用自定义的date处理器）
        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor());
        JSONArray json = JSONArray.fromObject(bean,config);
        return json==null?"":json.toString();
    }

    /**
     * 格式化时间，yyyy-MM-dd
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        if(date == null) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * 格式化时间，yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String formatDateTime(Date date) {
        if(date == null) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * 根据格式自定义时间格式
     * @return
     */
    public static String formatDate(Date date, String formatStr) {
        if(date == null) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(date);
    }

    public static void main(String[] args) {
        DataConfig dataConfig = new DataConfig();
        dataConfig.setName("name");
        dataConfig.setValue("value");
        dataConfig.setCreatedDt(new Date());
        String result = beanToJson(dataConfig);
        System.out.println(result);
    }

}
