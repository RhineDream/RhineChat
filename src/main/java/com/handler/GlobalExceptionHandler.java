package com.handler;

import com.google.common.collect.Maps;
import com.utils.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;


/**
 * 全局异常处理器
 * 
 * 异常增强，以JSON的形式返回给客服端
 * 异常增强类型：NullPointerException,RunTimeException,ClassCastException,
 * NoSuchMethodException,IOException,IndexOutOfBoundsException
 * 以及springmvc自定义异常等，如下： SpringMVC自定义异常对应的status code Exception HTTP Status Code
 * ConversionNotSupportedException 500 (Internal Server Error)
 * HttpMessageNotWritableException 500 (Internal Server Error)
 * HttpMediaTypeNotSupportedException 415 (Unsupported Media Type)
 * HttpMediaTypeNotAcceptableException 406 (Not Acceptable)
 * HttpRequestMethodNotSupportedException 405 (Method Not Allowed)
 * NoSuchRequestHandlingMethodException 404 (Not Found) TypeMismatchException
 * 400 (Bad Request) HttpMessageNotReadableException 400 (Bad Request)
 * MissingServletRequestParameterException 400 (Bad Request)
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	// 运行时异常
	@ExceptionHandler(RuntimeException.class)
	public ResponseResult runtimeExceptionHandler(RuntimeException runtimeException) {
		logger.error("服务器异常", runtimeException);

		
		return ResponseResult.build(1000, "服务器异常");
	}

	// 空指针异常
	@ExceptionHandler(NullPointerException.class)
	public ResponseResult nullPointerExceptionHandler(NullPointerException ex) {
		logger.error("空指针异常", ex);

		
		return ResponseResult.build(1001, "空指针异常");
	}

	// 类型转换异常
	@ExceptionHandler(ClassCastException.class)
	public ResponseResult classCastExceptionHandler(ClassCastException ex) {
		logger.error("类型转换异常", ex);
		
		return ResponseResult.build(1002, "类型转换异常");
	}

	// IO异常
	@ExceptionHandler(IOException.class)
	public ResponseResult iOExceptionHandler(IOException ex) {
		logger.error("IO异常", ex);
		
		return ResponseResult.build(1003, "IO异常");
	}

	// 未知方法异常
	@ExceptionHandler(NoSuchMethodException.class)
	public ResponseResult noSuchMethodExceptionHandler(NoSuchMethodException ex) {
		logger.error("未知方法异常", ex);
		
		return ResponseResult.build(1004, "未知方法异常");
	}

	// 数组越界异常
	@ExceptionHandler(IndexOutOfBoundsException.class)
	public ResponseResult indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
		logger.error("角标越界异常", ex);
		
		return ResponseResult.build(1005, "角标越界异常");
	}

	// 400错误
	@ExceptionHandler({ HttpMessageNotReadableException.class })
	public ResponseResult requestNotReadable(HttpMessageNotReadableException ex) {
		logger.error("400错误", ex);
		
		return ResponseResult.build(400, "400错误", ex.getMessage());
	}

	// 400错误
	@ExceptionHandler({ TypeMismatchException.class })
	public ResponseResult requestTypeMismatch(TypeMismatchException ex) {
		logger.error("请求方法参数类型不匹配", ex);
		
		return ResponseResult.build(400, Constants.REQUEST_METHOD_ARGUMENT_TYPE_MISMATCHEXCEPTION, ex.getMessage());
	}

	// 400错误
	@ExceptionHandler({ MissingServletRequestParameterException.class })
	public ResponseResult requestMissingServletRequest(MissingServletRequestParameterException ex) {
		logger.error("请求方法缺失参数", ex);
		
		return ResponseResult.build(1006, Constants.REQUEST_MISSING_PARAM_EXCEPTION, ex.getMessage());
	}

	// 405错误
	@ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
	public ResponseResult request405(HttpServletRequest req) {
		
		String method = req.getMethod();
		
		return ResponseResult.build(400, Constants.REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION, method);
	}

	// 406错误
	@ExceptionHandler({ HttpMediaTypeNotAcceptableException.class })
	public ResponseResult request406(HttpMediaTypeNotSupportedException ex) {
		System.out.println("406...");
		return ResponseResult.build(406, ex.getMessage());
	}
	
	@ExceptionHandler({ HttpMediaTypeNotSupportedException.class })
	public ResponseResult request415(HttpMediaTypeNotSupportedException ex) {
		logger.error("不支持的ContentType: " + ex.getContentType());
		return ResponseResult.build(415, ex.getMessage());
	}

	// 500错误
	@ExceptionHandler({ ConversionNotSupportedException.class, HttpMessageNotWritableException.class })
	public ResponseResult server500(RuntimeException runtimeException) {
		logger.error("500错误", runtimeException);
		
		return ResponseResult.build(500, null);
	}
	
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseResult requestHandlingNoHandlerFound(HttpServletRequest req, NoHandlerFoundException ex) {
	    Map<String, String> data = Maps.newHashMap();
		data.put("httpMethod", ex.getHttpMethod());
	    data.put("errorURL", ex.getRequestURL());
	    
	    return ResponseResult.build(404, Constants.REQUEST_URL_NOT_FOUND_EXCEPTION, data);
	}
	
	/*@ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
	public ResponseResult resolveAuthorizationException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        logger.warn("User does not have permission " + ex.getMessage());
        
        return ResponseResult.build(Constants.REQUEST_DOES_NOT_HAVE_PERMISSION, "您没有权限访问！");
    }*/
	
	
	/**
	 * 接口访问次数 超过限定次数
	 * 
	 * @param req
	 * @param ex
	 * @return
	 */
	/*@ExceptionHandler(LimitIPRequestException.class)
	public ResponseResult limitIPRequestException(HttpServletRequest req, LimitIPRequestException ex) {

	    
	    return ResponseResult.build(400, ex.getMessage());
	}*/


	/**
	 * 参数异常
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseResult resolveIllegalArgumentException(IllegalArgumentException ex) {
		return ResponseResult.build(400, ex.getMessage());
	}
	
}