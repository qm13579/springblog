package cn.people.controller;

import cn.people.domain.Role;
import cn.people.service.IRoleService;
import cn.people.utils.common.Result;
import cn.people.utils.common.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/2/20  下午4:28
 * @description: 角色接口
 */
@CrossOrigin
@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private IRoleService roleService;
    /**
     *
     * @return
     */
    @ApiOperation(value = "查询所有角色")
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Result findAllRoles(){

        List<Role> rolesList= roleService.findAllRoles();
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(rolesList);
        return result;

    }

    /**
     *
     * @param role
     * @return
     */
    @ApiOperation(value = "新增角色")
    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public Result updateRole(@RequestBody Role role){
        roleService.updateRole(role);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     *
     * @param role
     * @return
     */
    @ApiOperation(value = "更新用户角色")
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Result addRole(@RequestBody Role role){
        roleService.addRole(role);
        return new Result(ResultCode.SUCCESS);
    }
}
