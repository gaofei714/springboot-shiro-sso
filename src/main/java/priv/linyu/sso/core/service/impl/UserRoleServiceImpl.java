package priv.linyu.sso.core.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import priv.linyu.sso.core.mapper.UserRoleMapper;
import priv.linyu.sso.core.pojo.UserRole;
import priv.linyu.sso.core.service.UserRoleService;

/**
 * @className: MenuService
 * @author: QiuShangLin
 * @description:  用户与角色关系表 服务实现类
 * @date: 2019/09/16 17:43
 * @version: 1.0
 **/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
