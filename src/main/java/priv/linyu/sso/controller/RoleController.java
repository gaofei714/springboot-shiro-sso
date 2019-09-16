package priv.linyu.sso.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.linyu.sso.common.enums.ResponseEnum;
import priv.linyu.sso.common.util.ResponseEnvelope;
import priv.linyu.sso.core.pojo.Role;
import priv.linyu.sso.core.service.RoleService;

import java.util.List;

/**
 * @className: RoleController
 * @author: QiuShangLin
 * @description: 角色视图控制器
 * @date: 2019/09/16 20:55
 * @version: 1.0
 **/
@RestController
@RequestMapping("/role/")
@Api(value = "/role/",description = "关于角色信息操作",tags = "角色模块")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查询全部角色信息
     * @return
     */
    @ApiOperation(value = "查询全部角色信息",notes = "查询全部角色信息")
    @GetMapping("list")
    @RequiresRoles("USER")
    @RequiresPermissions("sys:role:info")
    public ResponseEnvelope<List<Role>> list(){
        return new ResponseEnvelope<>(ResponseEnum.SUCCESS,roleService.list());
    }

}
