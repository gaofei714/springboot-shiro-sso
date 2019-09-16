package priv.linyu.sso.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import priv.linyu.sso.core.pojo.Role;

import java.util.List;

/**
 * @className: RoleService
 * @author: QiuShangLin
 * @description: 角色表 服务类
 * @date: 2019/09/16 17:42
 * @version: 1.0
 **/
public interface RoleService  extends IService<Role> {

    /**
     * 根据用户id 查询所有角色
     * @param userId
     * @return
     */
    List<Role> selectRolesByUserId(Long userId);
}
