package people.cn.system.controller;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import people.cn.bean.GroupDict;
import people.cn.bean.UserInfo;
import people.cn.bean.UserInfoDto;
import people.cn.common.ExcelUtil;
import people.cn.common.ExcelUtils;
import people.cn.common.Result;
import people.cn.common.ResultCode;
import people.cn.common.annotation.Limit;
import people.cn.common.factory.FileFactory;
import people.cn.system.server.IUserService;

import javax.annotation.Resource;
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
    @Resource
    private ExcelUtil excelUtil;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Result findAllUsers(){
        List<UserInfo> userList = userService.findAllUser();
        Result result = new Result(ResultCode.SUCCESS);
        System.out.println(userList);
        result.setData(userList);
        return result;
    }

    @RequestMapping(value = "/{uid}",method = RequestMethod.GET)
    public Result findUserById(@PathVariable("uid") String uid){
        UserInfo user = userService.findUserById(uid);
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(user);
        return result;
    }

    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public Result updateUser(@RequestBody UserInfo user){
        userService.updateUser(user);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/group/",method = RequestMethod.GET)
    public Result findGroupUser(){
        List<GroupDict> groupUserList = userService.findGroupUser();
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(groupUserList);
        return result;
    }

    @RequestMapping(value = "/group/{gid}",method = RequestMethod.GET)
    public Result findUsersByGroupId(@PathVariable("gid") String gid){
        List<UserInfo> usersByGroupIdList = userService.findUsersByGroupId(gid);
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(usersByGroupIdList);
        return result;
    }

    @RequestMapping(value = "/{uid}",method = RequestMethod.DELETE)
    public Result deleteUser(@PathVariable("uid") String uid){
        userService.deleteUser(uid);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "create",method = RequestMethod.POST)
    public Result addUser(@RequestBody UserInfo user){
        Result result = userService.addUser(user);
        return result;
    }

    @RequestMapping(value = "/group",method = RequestMethod.POST)
    public Result addGroupToUser(@RequestBody GroupDict group){
        userService.addGroupToUser(group);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/group",method = RequestMethod.PUT)
    public Result updateGroupToUser(@RequestBody GroupDict group){
        userService.updateGroupToUser(group);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/file",method = RequestMethod.POST)
    public Result importUserFile(MultipartFile file) throws IOException, InvalidFormatException {
        excelUtil.importExcel(file);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/file",method = RequestMethod.GET)
    public void getUserFile(HttpServletResponse response) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, IOException {
        List<UserInfo> allUser = userService.findAllUser();
//        Workbook workbook = excelUtil.exportExcel(allUser, "user");
//        excelUtil.setHeard(workbook,response);
        ExcelUtils excelUtils = FileFactory.getExcel();
        excelUtils.export("user",allUser,response);
    }

    @Limit(period = 60, count = 10,name = "测试DTO",key = "updateUserInfo",prefix = "user")
    @RequestMapping(value = "/testDto",method = RequestMethod.PUT)
    public Result updateUserInfo(@RequestBody UserInfoDto userDto){
        return new Result(ResultCode.SUCCESS);
    }
}
