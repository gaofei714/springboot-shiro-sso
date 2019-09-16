package priv.linyu.sso.core.service;


import com.baomidou.mybatisplus.extension.service.IService;
import priv.linyu.sso.core.pojo.Menu;

import java.util.List;

/**
 * @className: MenuService
 * @author: QiuShangLin
 * @description:  权限表 服务类
 * @date: 2019/09/16 17:43
 * @version: 1.0
 **/
public interface MenuService extends IService<Menu> {

    /**
     * 根据角色id查询所有权限
     * @param roleId
     * @return
     */
    List<Menu> selectMenusByRoleId(Long roleId);

}
