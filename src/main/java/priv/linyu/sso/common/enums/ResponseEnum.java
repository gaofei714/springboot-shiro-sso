package priv.linyu.sso.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @className: ResponseEnum
 * @author: QiuShangLin
 * @description: 响应枚举类
 * @date: 2019/09/16 17:26
 * @version: 1.0
 **/
@Getter
@AllArgsConstructor
public enum ResponseEnum {

    /**
     * {@code 200 success }
     * 成功
     */
    SUCCESS(200,"success"),

    /**
     * {@code 201 Created}
     * 创建成功
     */
    CREATED(201, "Created"),

    /**
     * {@code 204 No Content}
     * 没内容
     */
    NO_CONTENT(204, "No Content"),

    /**
     * {@code 206 Partial Content}
     * 部分内容
     */
    PARTIAL_CONTENT(206, "Partial Content"),

    /**
     * {@code 400 Bad Request}
     * 参数错误
     */
    BAD_REQUEST(400, "Bad Request"),

    /**
     * {@code 400 Bad Request}
     * 参数错误
     */
    INCORRECT_CREDENTIALS(400, "用户或者密码错误"),

    /**
     * {@code 400 Bad Request}
     * 参数错误
     */
    USER_NOT_FOUND(400, "该用户不存在"),

    /**
     * {@code 404 Not Found }
     * 没查到
     */
    NOT_FOUND(404, "Not Found"),

    /**
     * {@code 403 Forbidden }
     *  新增、修改、删除、查询失败
     */
    FORBIDDEN(403, "Forbidden"),

    /**
     * {@code 403 Forbidden }
     *  新增、修改、删除、查询失败
     */
    UN_AUTH(403, "亲,请先登录"),

    /**
     * 权限不足
     */
    AUTH_ERROR(403,"权限不足"),

    /**
     * 权限不足
     */
    LOCKED_ACCOUNT(403,"登录失败，该用户已被冻结"),

    /**
     * {@code 500 Internal Server Error }
     * 内部服务器错误
     */
    ERROR(500,"Internal Server Error"),

    /**
     * {@code 1001 Internal Server Error }
     * 参数错误异常
     */
    PARAM_FAIL_CODE(1001,"参数错误异常"),

    /**
     * {@code 1002 Internal Server Error }
     * 参数校验异常
     */
    VALIDATION_CODE (1002,"参数校验异常"),

    /**
     * {@code 1003 Internal Server Error }
     * 数据重复,请检查后提交
     */
    DUPLICATE_KEY_CODE(1003,"数据重复,请检查后提交"),

    ;

    /**
     * 状态码
     */
    private final int code;

    /**
     * 返回的消息
     */
    private final String msg;
}
