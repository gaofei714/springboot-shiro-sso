package priv.linyu.sso.core.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import priv.linyu.sso.core.mapper.RoleMapper;
import priv.linyu.sso.core.pojo.Role;
import priv.linyu.sso.core.service.RoleService;

import java.util.List;

/**
 * @className: MenuService
 * @author: QiuShangLin
 * @description:  角色表 服务实现类
 * @date: 2019/09/16 17:43
 * @version: 1.0
 **/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    /**
     * 根据用户id 查询所有角色
     * @param userId
     * @return
     */
    @Override
    public List<Role> selectRolesByUserId(Long userId) {
        return this.baseMapper.selectRolesByUserId(userId);
    }
}
