package priv.linyu.sso.common.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import priv.linyu.sso.common.util.ShiroUtils;
import priv.linyu.sso.core.pojo.Menu;
import priv.linyu.sso.core.pojo.Role;
import priv.linyu.sso.core.pojo.UserInfo;
import priv.linyu.sso.core.service.MenuService;
import priv.linyu.sso.core.service.RoleService;
import priv.linyu.sso.core.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @className: ShiroRealm
 * @author: QiuShangLin
 * @description: 自定义realm
 * @date: 2019/09/16 20:09
 * @version: 1.0
 **/
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    private final static String PROHIBIT = "PROHIBIT";

    /**
     * 授权
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {

        UserInfo record = (UserInfo) principal.getPrimaryPrincipal();

        // 获取用户id
        Long userId = record.getUserId();

        // 进行授权和管理
        Set<String> roleSet = new HashSet<>();
        Set<String> permSet = new HashSet<>();

        List<Role> roleList = roleService.selectRolesByUserId(userId);
        roleList.forEach(role -> {
            roleSet.add(role.getRoleName());
            List<Menu> menus = menuService.selectMenusByRoleId(role.getRoleId());
            menus.forEach(menu -> permSet.add(menu.getPerms()));
        });

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permSet);
        info.setRoles(roleSet);

        return info;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // 获取用户名
        String username = (String) token.getPrincipal();

        // 查询数据库,获取user对象
        UserInfo record = userService.getOne(new QueryWrapper<UserInfo>().lambda().eq(UserInfo::getUsername,username));
        if (Objects.isNull(record)){
            throw new AuthenticationException(""+username + "");
        }

        // 判断账号是否被冻结
        if (record.getState() == null || PROHIBIT.equals(record.getState())){
            throw new LockedAccountException();
        }

        // 验证
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                record,
                record.getPassword(),
                ByteSource.Util.bytes(record.getSalt()),
                getName()
        );

        // 校验成功后,清除缓存和Session
        ShiroUtils.deleteCache(username,true);

        return info;
    }
}
