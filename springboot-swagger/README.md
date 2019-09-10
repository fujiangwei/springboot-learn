# Swagger 

## pom依赖
        
    <!--swagger-->
    <dependency>
    	<groupId>io.springfox</groupId>
    	<artifactId>springfox-swagger2</artifactId>
    	<version>2.9.2</version>
    </dependency>
    <dependency>
    	<groupId>io.springfox</groupId>
    	<artifactId>springfox-swagger-ui</artifactId>
    	<version>2.9.2</version>
    </dependency>

## 配置文件application.properties
    # 控制开启或关闭swagger
    swagger.enabled=true
    
## swagger配置类
```详见SwaggerConfig类``` 

## 添加注解@EnableSwagger2
```text
加在启动类或者配置类SwaggerConfig上都可以
```

## 添加测试url
```text
编写controller添加测试url
```

## 启动访问
http://127.0.0.1:8080/swagger-ui.html

## 相关注解
* @Api：用在类上，标志此类是Swagger资源
  	value：接口说明
  	tags：接口说明，可以在页面中显示。可以配置多个，当配置多个的时候，在页面中会显示多个接口的信息
* @ApiOperation：用在方法上，描述方法的作用
* @ApiImplicitParams：包装器：包含多个ApiImplicitParam对象列表
* @ApiImplicitParam：定义在@ApiImplicitParams注解中，定义单个参数详细信息
		○ paramType：参数放在哪个地方
			§ header-->请求参数的获取：@RequestHeader
			§ query-->请求参数的获取：@RequestParam
			§ path（用于restful接口）-->请求参数的获取：@PathVariable
			§ body（以流的形式提交 仅支持POST）
			§ form（以form表单的形式提交 仅支持POST）
		○ name：参数名
		○ dataType：参数的数据类型 只作为标志说明，并没有实际验证
			§ Long
			§ String
		○ required：参数是否必须传
			§ true
			§ false
		○ value：参数的意义
		○ defaultValue：参数的默认值
* @ApiModel：描述一个Swagger Model的额外信息
* @ApiModel用在类上，表示对类进行说明，用于实体类中的参数接收说明
* @ApiModelProperty：在model类的属性添加属性说明
* @ApiParam：用于Controller中方法的参数说明
* @ApiResponses：包装器：包含多个ApiResponse对象列表
* @ApiResponse：定义在@ApiResponses注解中，一般用于描述一个错误的响应信息
 		○ code：错误码，例如400
 		○ message：信息，例如"请求参数没填好"
 		○ response：抛出异常的类
* @Authorization	Declares an authorization scheme to be used on a resource or an operation.
* @AuthorizationScope	Describes an OAuth2 authorization scope.

## 打印banner自定义
```text
在resources目录下添加banner.txt文件即可，这样在启动项目时就会加载banner.txt文件而不是 默认的banner
```

