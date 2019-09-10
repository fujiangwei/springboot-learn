package com.example.springbootswagger.controller;

import com.example.springbootswagger.domain.User;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.swagger.annotations.*;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

/**
 * descripiton: Swagger
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/4/13
 * @time: 22:42
 * @modifier:
 * @since:
 */
@RestController
@Api(tags = "测试demo")
@RequestMapping(value = "swagger")
public class SwaggerController {

    @ApiOperation(value = "hello", notes = "hello测试api")
    @GetMapping(value = "hello")
    public String hello() {
        return "hello";
    }

    @ApiOperation(value = "add", notes = "路径变量测试")
    @ApiImplicitParam(name = "swaggerId",value = "测试参数id",required = true, dataType = "Integer", paramType="path")
    @PostMapping(value = "add/{swaggerId}")
    public String add(@PathVariable Integer swaggerId) {
        Assert.notNull(swaggerId, "swaggerId为空");
        return swaggerId.toString();
    }

    @ApiOperation(value = "update",notes = "多路径参数变量测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "swaggerId",value = "测试参数id",required = true, dataType = "Integer", paramType="path"),
            @ApiImplicitParam(name = "name",value = "测试参数名称",required = true, dataType = "String", paramType="path")})
    @PutMapping(value = "/update/{swaggerId}/{name}")
    public String update(@PathVariable Integer swaggerId,@PathVariable String name) {
        return String.valueOf(swaggerId + name);
    }

    @ApiOperation(value = "addUser",notes = "对象添加测试")
    @ApiImplicitParam(name = "user",value = "待添加用户信息",required = true, dataType = "User", paramType="body")
    @ApiResponse(code = 200, message = "添加成功")
    @PostMapping(value = "/addUser")
    public String addUser(@RequestBody User user){

        return user.getName();
    }

}
