package com.github.sniper.authsecurity.rbac.srv.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.sniper.authsecurity.rbac.dao.SysUserRoleDao;
import com.github.sniper.authsecurity.rbac.po.SystemUserRole;
import com.github.sniper.authsecurity.rbac.srv.SysUserRoleService;

import org.springframework.stereotype.Service;

/**
 * @Description 用户与角色业务实现
 * @Author Sans
 * @CreateTime 2019/9/14 15:57
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SystemUserRole> implements SysUserRoleService {

}