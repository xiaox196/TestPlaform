# TestPlaform

# 测试平台
使用了spring boot + thymeleaf + bootstrap

第一阶段先增加设备管理
遇到的问题：
## 1.加入了thymeleaf后 html无法启动，

## 2.mybatis新增一个自定义查询需要自己加如：
  <select id="queryApiByParam" resultType="com.tool.plaform.entity.User" parameterType="com.tool.plaform.entity.UserQuery">
    select
    id,name,password
    from user
    <trim prefix="where" prefixOverrides="and">
      <if test="loginName != null and loginName !='' ">
        name = '${loginName}'
      </if>
    </trim>
  </select>
  
  
## 3.遇到问题  
Description:
Field apiService in com.tool.plaform.controller.ApiController required a bean of type 'com.tool.plaform.service.ApiService' that could not be found.
Action:
Consider defining a bean of type 'com.tool.plaform.service.ApiService' in your configuration.
Process finished with exit code 0

解决办法：
忘记加服务service

## 4.post 请求数组采用bean list形式
## 5. 加入vo层  Value Object，用于和前端解耦
## 6. IDEA使用 @Autowired和@Resource时报错
 解决办法：https://blog.csdn.net/u010679782/article/details/52094893
## 5. mybatis 新增一个表获取修改一个表，需要只对该表进行操作，只需要在generatorconfig.xml
加入如下一个：
  <!-- 表名对应生成的实体 -->
        <table tableName="api" domainObjectName="Api"
            enableCountByExample="false" enableUpdateByExample="false" enableDeleteByExample="false" enableSelectByExample="false"
            selectByExampleQueryId="false"> </table>
            