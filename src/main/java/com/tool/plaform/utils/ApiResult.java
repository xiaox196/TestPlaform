package com.tool.plaform.utils;


/**
 * web api 返回值的基础实体，web api 的返回对象建议都使用此对象包装。
 *
 * @param <T>
 */
public class ApiResult<T> {
    public boolean success;
    public String message;
    public int retCode;
    public T data;

    public static <U> ApiResult<U> fail(String message) {
        ApiResult<U> result = new ApiResult<U>();
        result.success = false;
        result.message = message;
        result.retCode = ErrorCode.EXCEPTION;
        return result;
    }
    
    public static <U> ApiResult<U> fail(int retCode, String message) {
        ApiResult<U> result = new ApiResult<U>();
        result.success = false;
        result.message = message;
        result.retCode = retCode;
        return result;
    }

    public static <U> ApiResult<U> fail(Exception e) {
        ApiResult<U> result = new ApiResult<U>();
        result.success = false;
        result.retCode = ErrorCode.EXCEPTION;
        if (e.getLocalizedMessage()==null) {
            result.message = e.toString();
        } else {
            result.message = e.getLocalizedMessage();
        }

        return result;
    }

    public static <U> ApiResult<U> success() {
        return success(null, "success");
    }

    public static <U> ApiResult<U> success(U data) {
        return success(data, "success");
    }

    public static <U> ApiResult<U> success(U data, String message) {
        ApiResult<U> result = new ApiResult<U>();
        result.success = true;
        result.data = data;
        result.retCode = ErrorCode.OK;
        result.message = message;
        return result;
    }
}
