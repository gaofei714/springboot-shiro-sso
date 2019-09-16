package priv.linyu.sso.core.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import priv.linyu.sso.core.mapper.UserMapper;
import priv.linyu.sso.core.pojo.UserInfo;
import priv.linyu.sso.core.service.UserService;

/**
 * @className: MenuService
 * @author: QiuShangLin
 * @description:  系统用户表 服务实现类
 * @date: 2019/09/16 17:43
 * @version: 1.0
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserInfo> implements UserService {

}
