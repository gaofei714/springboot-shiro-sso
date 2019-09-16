package priv.linyu.sso.controller;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import priv.linyu.sso.common.enums.ResponseEnum;
import priv.linyu.sso.common.exception.BusinessRuntimeException;
import priv.linyu.sso.common.util.ResponseEnvelope;
import priv.linyu.sso.common.util.ShiroUtils;
import priv.linyu.sso.core.pojo.UserInfo;
import priv.linyu.sso.core.service.UserService;

/**
 * @className: UserController
 * @author: QiuShangLin
 * @description: 用户视图控制器
 * @date: 2019/09/16 20:28
 * @version: 1.0
 **/
@RestController
@Slf4j
@Api(description = "关于用户信息操作",tags = "用户模块")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param userInfo
     * @return
     */
    @ApiOperation(value = "登录",notes = "根据用户名和密码执行登录操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名",dataType = "String"),
            @ApiImplicitParam(name = "password",value = "密码",dataType = "String")
    })
    @PostMapping("login")
    public ResponseEnvelope<String> login(UserInfo userInfo){

        // 进行身份验证
        try {
            // 获取主体内容
            Subject subject = SecurityUtils.getSubject();

            // 获取令牌token
            UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUsername(),userInfo.getPassword());

            // 执行登录
            subject.login(token);
        }catch (IncorrectCredentialsException e){
            log.error("用户或者密码错误");
            throw new BusinessRuntimeException(ResponseEnum.INCORRECT_CREDENTIALS);
        }catch (LockedAccountException  e){
            log.error("登录失败，该用户已被冻结");
            throw new BusinessRuntimeException(ResponseEnum.LOCKED_ACCOUNT);
        }catch (AuthenticationException e){
            log.error("该用户不存在");
            throw new BusinessRuntimeException(ResponseEnum.USER_NOT_FOUND);
        }catch (Exception e){
            log.error("未知错误");
            throw new BusinessRuntimeException(ResponseEnum.ERROR);
        }

        return new ResponseEnvelope<>(ResponseEnum.SUCCESS, ShiroUtils.getSession().getId().toString());
    }

    /**
     * 获取当前用户信息
     * @return
     */
    @ApiOperation(value = "获取当前用户信息",notes = "获取当前用户信息")
    @GetMapping("/user/getUserInfo")
    public ResponseEnvelope<UserInfo> getUserInfo(){
        return new ResponseEnvelope<>(ResponseEnum.SUCCESS,ShiroUtils.getUserInfo());
    }

    /**
     * 未登录
     * @return
     */
    @GetMapping("unauth")
    @ApiOperation(value = "未登录",notes = "未登录")
    public ResponseEnvelope<String> unauth(){
        return new ResponseEnvelope<>(ResponseEnum.UN_AUTH);
    }

    /**
     * 查询全部用户信息,管理员和普通用户都可以查看
     * @return
     */
    @GetMapping("/user/list")
    @RequiresRoles(value = {"ADMIN","USER"},logical = Logical.OR)
    @RequiresPermissions("sys:user:info")
    @ApiOperation(value = "查询全部用户信息",notes = "查询全部用户信息")
    public ResponseEnvelope<List<UserInfo>> list(){
        return new ResponseEnvelope<>(ResponseEnum.SUCCESS,userService.list());
    }

    /**
     * 登出
     * @return
     */
    @GetMapping("/user/logout")
    @RequiresUser
    @ApiOperation(value = "登出",notes = "登出")
    public ResponseEnvelope<Void> logout(){
        ShiroUtils.logout();
        return new ResponseEnvelope<>(ResponseEnum.SUCCESS);
    }

}
