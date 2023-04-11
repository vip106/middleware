package com.github.sniper.authsecurity.rbac.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.sniper.authsecurity.rbac.po.SystemMenu;
import com.github.sniper.authsecurity.rbac.po.SystemRole;
import com.github.sniper.authsecurity.rbac.po.SystemUser;


import java.util.List;

/**
 * @Description 系统用户DAO
 * @Author Sans
 * @CreateTime 2019/9/14 15:57
 */
public interface SysUserDao extends BaseMapper<SystemUser> {

    /**
     * 通过用户ID查询角色集合
     * @Author Sans
     * @CreateTime 2019/9/18 18:01
     * @Param  userId 用户ID
     * @Return List<SysRoleEntity> 角色名集合
     */
    List<SystemRole> selectSysRoleByUserId(Long userId);
    /**
     * 通过用户ID查询权限集合
     * @Author Sans
     * @CreateTime 2019/9/18 18:01
     * @Param  userId 用户ID
     * @Return List<SysMenuEntity> 角色名集合
     */
    List<SystemMenu> selectSysMenuByUserId(Long userId);
	
}
