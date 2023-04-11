package com.github.sniper.authsecurity.authen;

import com.github.sniper.authsecurity.rbac.po.SystemMenu;
import com.github.sniper.authsecurity.rbac.po.SystemRole;
import com.github.sniper.authsecurity.rbac.po.SystemUser;
import com.github.sniper.common.model.rpc.RpcRequest;
import com.github.sniper.common.model.rpc.RpcResponse;

import java.util.List;

/**
 * @author Sniper_lw
 * @version 1.0.0
 * @description TODO
 * @date 2023/4/10 12:32
 */

public interface AccountProcessApi {

    /**
     * 根据用户名查询实体
     * @Author Sans
     * @CreateTime 2019/9/14 16:30
     * @Param  username 用户名
     * @Return SysUserEntity 用户实体
     */
    RpcResponse<SystemUser> selectUserByName(RpcRequest<String> username);

    /**
     * 根据用户ID查询角色集合
     * @Author Sans
     * @CreateTime 2019/9/18 18:01
     * @Param  userId 用户ID
     * @Return List<SysRoleEntity> 角色名集合
     */
    RpcResponse<List<SystemRole>> selectSysRoleByUserId(RpcRequest<Long> userId);

    /**
     * 根据用户ID查询权限集合
     * @Author Sans
     * @CreateTime 2019/9/18 18:01
     * @Param  userId 用户ID
     * @Return List<SysMenuEntity> 角色名集合
     */
    RpcResponse<List<SystemMenu>> selectSysMenuByUserId(RpcRequest<Long> userId);


}