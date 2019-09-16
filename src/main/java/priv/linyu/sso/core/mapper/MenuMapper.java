package priv.linyu.sso.core.mapper;
import	java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import priv.linyu.sso.core.pojo.Menu;

/**
 * @className: MenuMapper
 * @author: QiuShangLin
 * @description: 权限表 Mapper 接口
 * @date: 2019/09/16 17:46
 * @version: 1.0
 **/
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据角色id查询所有权限
     * @param roleId
     * @return
     */
    List<Menu> selectMenusByRoleId(@Param("roleId") Long roleId);

}
