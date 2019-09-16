package priv.linyu.sso.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import priv.linyu.sso.core.mapper.MenuMapper;
import priv.linyu.sso.core.pojo.Menu;
import priv.linyu.sso.core.service.MenuService;

import java.util.List;


/**
 * @className: MenuService
 * @author: QiuShangLin
 * @description:  权限表 服务实现类
 * @date: 2019/09/16 17:43
 * @version: 1.0
 **/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    /**
     * 根据角色id查询所有权限
     * @param roleId
     * @return
     */
    @Override
    public List<Menu> selectMenusByRoleId(Long roleId) {
        return this.baseMapper.selectMenusByRoleId(roleId);
    }
}
