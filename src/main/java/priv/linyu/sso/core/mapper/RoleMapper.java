package priv.linyu.sso.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import priv.linyu.sso.core.pojo.Role;

import java.util.List;

/**
 * @className: RoleMapper
 * @author: QiuShangLin
 * @description:
 * @date: 2019/09/16 17:45
 * @version: 1.0
 **/
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户id 查询所有角色
     * @param userId
     * @return
     */
    List<Role> selectRolesByUserId(@Param("userId") Long userId);

}
