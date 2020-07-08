package people.cn.system.controller;

import org.springframework.web.bind.annotation.*;
import people.cn.bean.Role;
import people.cn.common.Result;
import people.cn.common.ResultCode;
import people.cn.system.server.IRoleService;

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
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Result addRole(@RequestBody Role role){
        roleService.addRole(role);
        return new Result(ResultCode.SUCCESS);
    }
}
