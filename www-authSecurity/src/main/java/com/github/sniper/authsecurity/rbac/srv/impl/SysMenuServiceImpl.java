package com.github.sniper.authsecurity.rbac.srv.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.sniper.authsecurity.rbac.dao.SysMenuDao;
import com.github.sniper.authsecurity.rbac.po.SystemMenu;
import com.github.sniper.authsecurity.rbac.srv.SysMenuService;
import org.springframework.stereotype.Service;

/**
 * @Description 权限业务实现
 * @Author Sans
 * @CreateTime 2019/9/14 15:57
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SystemMenu> implements SysMenuService {


}