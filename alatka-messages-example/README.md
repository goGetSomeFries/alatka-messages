### alatka-messages-admin 示例

访问规则相关页面

[ :point_right: 规则组](http://localhost:8088/rule/group)

[ :point_right: 规则变量](http://localhost:8088/rule/variable)

[ :point_right: 规则外部数据源](http://localhost:8088/rule/datasource)

[ :point_right: 规则](http://localhost:8088/rule)

### alatka-rule-core 示例

访问[ :point_right: 规则执行](http://localhost:8088/rule/execute)接口，传入相关参数验证规则

参数示例：

```
{
    "v_age": 60,
    "v_amount": 9000,
    "v_medicine_id": "S0003"
}
```