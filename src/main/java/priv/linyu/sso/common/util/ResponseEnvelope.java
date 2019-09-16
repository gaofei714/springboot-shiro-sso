package priv.linyu.sso.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import priv.linyu.sso.common.enums.ResponseEnum;

import java.io.Serializable;

/**
 * @className: ResponseEnvelope
 * @author: QiuShangLin
 * @description:
 * @date: 2019/09/16 17:24
 * @version: 1.0
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "响应结果")
public class ResponseEnvelope<T> implements Serializable {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -2474117273799126604L;

    /**
     * 状态码
     */
    @ApiModelProperty(value = "返回的状态码")
    private Integer code;

    /**
     * 返回的消息
     */
    @ApiModelProperty(value = "返回的消息")
    private String msg;

    /**
     * 返回的数据
     */
    @ApiModelProperty(value = "返回的数据")
    private T data;


    public ResponseEnvelope(ResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getMsg();
    }


    public ResponseEnvelope(ResponseEnum responseEnum,T data) {
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getMsg();
        this.data = data;
    }

}