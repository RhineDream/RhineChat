package com.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * 自定义响应结构
 * @author Rhine
 * @version 2019-07-19
 */
public class ResponseResult {

	private static final Logger logger = LoggerFactory.getLogger(ResponseResult.class);
	
	// 定义jackson对象
	private static final ObjectMapper MAPPER = new ObjectMapper();

	// jackson配置
	static {
		MAPPER.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		MAPPER.configure(SerializationFeature.INDENT_OUTPUT, true);
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        MAPPER.setDateFormat(sdf);
        
        // 空值处理为空串
        MAPPER.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>(){
 			@Override
 			public void serialize(Object value, JsonGenerator jgen,
 					SerializerProvider provider) throws IOException,
                    JsonProcessingException {
 				jgen.writeString("");
 			}
         });
    }
	
	// 响应业务状态
	private Integer status;

	// 响应消息
	private String msg;

	// 响应中的数据
	private Object data;

	public static ResponseResult build(Integer status, String msg, Object data) {
		return new ResponseResult(status, msg, data);
	}

	public static ResponseResult ok(Object data) {
		return new ResponseResult(data);
	}

	public static ResponseResult ok() {
		return new ResponseResult(null);
	}

	public ResponseResult() {

	}

	public static ResponseResult build(Integer status, String msg) {
		return new ResponseResult(status, msg, null);
	}

	public ResponseResult(Integer status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public ResponseResult(Object data) {
		this.status = 200;
		this.msg = "OK";
		this.data = data;
	}

	// public Boolean isOK() {
	// return this.status == 200;
	// }

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 将json结果集转化为CapinfoResult对象
	 * 
	 * @param jsonData
	 *            json数据
	 * @param clazz
	 *            CapinfoResult中的object类型
	 * @return
	 */
	public static ResponseResult formatToPojo(String jsonData, Class<?> clazz) {
		try {
			if (clazz == null) {
				return MAPPER.readValue(jsonData, ResponseResult.class);
			}
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (clazz != null) {
				if (data.isObject()) {
					obj = MAPPER.readValue(data.traverse(), clazz);
				} else if (data.isTextual()) {
					obj = MAPPER.readValue(data.asText(), clazz);
				}
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 没有object对象的转化
	 * 
	 * @param json
	 * @return
	 */
	public static ResponseResult format(String json) {
		try {
			return MAPPER.readValue(json, ResponseResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Object是集合转化
	 * 
	 * @param jsonData
	 *            json数据
	 * @param clazz
	 *            集合中的类型
	 * @return
	 */
	public static ResponseResult formatToList(String jsonData, Class<?> clazz) {
		try {
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (data != null && data.isArray() && data.size() > 0) {
				obj = MAPPER.readValue(data.traverse(),
						MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将jsonStr转换为对应的Bean List
	 * 
	 * @param jsonStr
	 * @param beanClass
	 * @return 
	 * 		若jsonStr为空，返回空List
	 * @throws Exception
	 */
	public static <T> List<T> jsonToList(String jsonStr, Class<T> beanClass) throws Exception {
		if (StringUtils.isBlank(jsonStr)) {
			return Lists.newArrayList();
		}

		JavaType javaType = getCollectionType(List.class, beanClass);
		List<T> lst = MAPPER.readValue(jsonStr, javaType);

		return lst;
	}
	
	public static Map<?, ?> jsonToMap(String jsonStr) {
		if (StringUtils.isBlank(jsonStr)) {
			return Maps.newHashMap();
		}

		Map<?, ?> readValue = null;
		try {
			readValue = MAPPER.readValue(jsonStr, Map.class);
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return readValue;
	}

	/**
	 * 获取泛型的Collection Type
	 * 
	 * @param collectionClass
	 *            泛型的Collection
	 * @param elementClasses
	 *            元素类
	 * @return JavaType Java类型
	 * @since 1.0
	 */
	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return MAPPER.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}
	
	/*public static JSONObject toJSONObject(String jsonStr) {
		return new JSONObject(jsonStr);
	}*/

	@Override
	public String toString() {
		String result = "";
		try {
			MAPPER.configure(Feature.QUOTE_NON_NUMERIC_NUMBERS, true);
			result = MAPPER.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage());
			
			e.printStackTrace();
		}
		
		return result;
	}

}
