package com.blog.controller;

import com.blog.domain.Permission;
import com.blog.domain.Role;
import com.blog.domain.UserInfo;
import com.blog.service.impl.AdminManageServiceImpl;
import com.blog.utils.common.Result;
import com.blog.utils.common.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminMangeController {

    @Autowired
    private AdminManageServiceImpl adminManageService;

    /**
     * date:2019.12.20
     * @param role
     * @return
     * role新增
     */
    @ApiOperation(value = "新增用户角色")
    @RequestMapping(value = "roleSave",method = RequestMethod.POST)
    public Result roleSave(@RequestBody Role role){

        adminManageService.roleSave(role);

        return new Result(ResultCode.SUCCESS);
    }

    /**
     *role更新
     * @param role
     * @return
     */
    @ApiOperation(value = "角色更新")
    @RequestMapping(value = "roleUpdate",method = RequestMethod.PUT)
    public Result roleUpdate(@RequestBody Role role){
        adminManageService.roleUpdate(role);
        return new Result(ResultCode.SUCCESS);
    }
    /**
     * role查询
     * @return
     */
    @ApiOperation(value = "查询所有")
    @RequestMapping(value = "roleFindAll",method = RequestMethod.GET)
    public Result roleFindAll(){
        List<Role> listRole= adminManageService.findAll();
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(listRole);
        return result;
    }


    public Result permission(){
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 更新用户角色
     * @param userInfo
     * @return
     * date:2019.12.20 17:26
     */
    @RequestMapping(value = "saveUserByRoles",method = RequestMethod.POST)
    @ApiOperation(value = "用户角色更新")
    public Result saveUserByRoles(@RequestBody UserInfo userInfo){
        adminManageService.saveUserByRoles(userInfo);
        return new Result(ResultCode.SUCCESS);
    }
    @ApiOperation(value = "新增权限")
    @RequestMapping(value = "permissionSave",method = RequestMethod.POST)
    public Result permissionSave(Permission permission){
        adminManageService.permissionSave(permission);
        return new Result(ResultCode.SUCCESS);
    }
    @ApiOperation(value = "权限更新")
    @RequestMapping(value = "permissionUpdate",method = RequestMethod.PUT)
    public Result permissionUpdate(Permission permission){
        adminManageService.permissionUpdate(permission);
        return new Result(ResultCode.SUCCESS);
    }
    @ApiOperation(value = "权限删除")
    @RequestMapping(value = "permissionDelete",method = RequestMethod.DELETE)
    public Result permissionDelete(Permission permission){
        adminManageService.permissionDelete(permission);
        return new Result(ResultCode.SUCCESS);
    }
    @ApiOperation(value = "权限查找")
    @RequestMapping(value = "permissionFindAll",method = RequestMethod.GET)
    public Result permissionFindAll(){
        List<Permission> permissionList= adminManageService.permissionFindAll();
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(permissionList);
        return result;
    }

}
