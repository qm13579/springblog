package people.cn.controller;

import cn.people.domain.GroupDict;
import cn.people.domain.UserInfo;
import cn.people.dot.UserInfoDto;
import cn.people.service.IUserService;
import cn.people.utils.annotation.Limit;
import cn.people.utils.common.ExcelUtil;
import cn.people.utils.common.ExcelUtils;
import cn.people.utils.common.Result;
import cn.people.utils.common.ResultCode;
import cn.people.utils.factory.FileFactory;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author 查找、更新、删除用户通过用户组更新查找、用户
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private ExcelUtil excelUtil;

    @ApiOperation(value = "获取用户信息")
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Result findAllUsers(){
        List<UserInfo> userList = userService.findAllUser();
        Result result = new Result(ResultCode.SUCCESS);
        System.out.println(userList);
        result.setData(userList);
        return result;
    }

    @ApiOperation(value = "根据用户id查询用户")
    @RequestMapping(value = "/{uid}",method = RequestMethod.GET)
    public Result findUserById(@PathVariable("uid") String uid){
        UserInfo user = userService.findUserById(uid);
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(user);
        return result;
    }

    @ApiOperation(value = "更新用户")
    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public Result updateUser(@RequestBody UserInfo user){
        userService.updateUser(user);
        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation(value = "查找所有用户组")
    @RequestMapping(value = "/group/",method = RequestMethod.GET)
    public Result findGroupUser(){
        List<GroupDict> groupUserList = userService.findGroupUser();
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(groupUserList);
        return result;
    }

    @ApiOperation(value = "根据用户组id查找当前组的所有用户")
    @RequestMapping(value = "/group/{gid}",method = RequestMethod.GET)
    public Result findUsersByGroupId(@PathVariable("gid") String gid){
        List<UserInfo> usersByGroupIdList = userService.findUsersByGroupId(gid);
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(usersByGroupIdList);
        return result;
    }

    @ApiOperation(value = "根据id删除用户")
    @RequestMapping(value = "/{uid}",method = RequestMethod.DELETE)
    public Result deleteUser(@PathVariable("uid") String uid){
        userService.deleteUser(uid);
        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation(value = "增加用户")
    @RequestMapping(value = "create",method = RequestMethod.POST)
    public Result addUser(@RequestBody UserInfo user){
        Result result = userService.addUser(user);
        return result;
    }

    @ApiOperation(value = "增加部门")
    @RequestMapping(value = "/group",method = RequestMethod.POST)
    public Result addGroupToUser(@RequestBody GroupDict group){
        userService.addGroupToUser(group);
        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation(value = "更新部门")
    @RequestMapping(value = "/group",method = RequestMethod.PUT)
    public Result updateGroupToUser(@RequestBody GroupDict group){
        userService.updateGroupToUser(group);
        return new Result(ResultCode.SUCCESS);
    }
    @ApiOperation(value = "上传用户数据")
    @RequestMapping(value = "/file",method = RequestMethod.POST)
    public Result importUserFile(MultipartFile file) throws IOException, InvalidFormatException {
        excelUtil.importExcel(file);
        return new Result(ResultCode.SUCCESS);
    }
    @ApiOperation(value = "下载用户数据")
    @RequestMapping(value = "/file",method = RequestMethod.GET)
    public void getUserFile(HttpServletResponse response) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, IOException {
        List<UserInfo> allUser = userService.findAllUser();
//        Workbook workbook = excelUtil.exportExcel(allUser, "user");
//        excelUtil.setHeard(workbook,response);
        ExcelUtils excelUtils = FileFactory.getExcel();
        excelUtils.export("user",allUser,response);
    }
    @Limit(period = 60, count = 10,name = "测试DTO",key = "updateUserInfo",prefix = "user")
    @ApiOperation(value = "测试DTO")
    @RequestMapping(value = "/testDto",method = RequestMethod.PUT)
    public Result updateUserInfo(@RequestBody UserInfoDto userDto){
        return new Result(ResultCode.SUCCESS);
    }
}
