package priv.linyu.sso.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.linyu.sso.common.enums.ResponseEnum;
import priv.linyu.sso.common.util.ResponseEnvelope;
import priv.linyu.sso.core.pojo.Menu;
import priv.linyu.sso.core.service.MenuService;

import java.util.List;

/**
 * @className: MenuController
 * @author: QiuShangLin
 * @description: 权限视图控制器
 * @date: 2019/09/16 20:45
 * @version: 1.0
 **/
@RestController
@RequestMapping("/menu/")
@Api(value = "/menu/",description = "关于权限信息操作",tags = "角色模块")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 查询全部权限信息
     * @return
     */
    @ApiOperation(value = "查询全部权限信息",notes = "查询全部权限信息")
    @GetMapping("list")
    @RequiresPermissions("sys:menu:info")
    public ResponseEnvelope<List<Menu>> list(){
        return new ResponseEnvelope<>(ResponseEnum.SUCCESS,menuService.list());
    }


}
