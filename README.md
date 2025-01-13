# 固定格式/8583报文解析工具

### 写在开头

目前市面上的固定格式/8583解析工具存在以下问题：

- 不支持子域解析；
- 报文域只支持String类型映射，类型转换需手动进行二次处理；
- 大部分通过java注解方式进行配置，无法做热部署；

该工具包对以上不足进行了优化升级，完美解决以上问题。

### 功能概述

- 支持固定格式和8583报文解析，配置方式和解析方式做到处理统一；
- 支持各卡组织（visa/万事达/美运/jcb/银联/连通/网联）报文规范；
- 支持8583**子域**及其**嵌套子域**解析；
- 支持报文域值类型**映射java类型**（Boolean/Integer/String/LocalDate/BigDecimal/List/Map/POJO...）；
- 支持**YAML**、**XML**、**注解**、**数据库表**四种配置形式；
- **YAML**、**XML**、**数据库表**支持动态配置，可做热更新；

<img src="https://foruda.gitee.com/images/1716735852853560264/590b6fd0_2152177.png" width=60%>

### 版本对应关系

| alatka-messages | alatka-dependencies     | alatka                  |
|-----------------|-------------------------|-------------------------|
| 2.4.0           | 1.28.0                  | 1.28.0                  |
| 2.3.0           | 1.26.0-1.27.0           | 1.26.0-1.27.0           |
| 2.2.0           | 1.25.0                  | 1.25.0                  |
| 2.1.0           | 1.18.0-1.24.0           | 1.18.0-1.24.0           |
| 2.0.0           | 1.16.0-1.17.0           | 1.16.0-1.17.0           |
| 1.2.0           | 1.15.0                  | 1.15.0                  |
| 1.1.0           | 1.7.0-1.14.0            | 1.7.0-1.14.0            |
| 1.0.3           | 1.0.7-1.0.8、1.1.0-1.6.0 | 1.0.7-1.0.8、1.1.0-1.6.0 |
| 1.0.2           | 1.0.2-1.0.6             | 1.0.2-1.0.6             |
| 1.0.1           | 1.0.1                   | 1.0.1                   |
| 1.0.0           | 1.0.0                   | 1.0.0                   |
| 0.2.3           | 0.2.3                   | 0.2.3                   |
| 0.2.2           | 0.2.2                   | 0.2.2                   |
| 0.2.1           | 0.2.1                   | 0.2.1                   |
| 0.2.0           | 0.2.0                   | 0.2.0                   |
| 0.1.5           | 0.1.5                   | 0.1.5                   |
| 0.1.4           | 0.1.4                   | 0.1.4                   |
| 0.1.3           | 0.1.3                   | 0.1.3                   |
| 0.1.2           | 0.1.2                   | 0.1.2                   |
| 0.1.1           | 0.1.1                   | 0.1.1                   |
| 0.1.0           | 0.1.0                   | 0.1.0                   |

`alatka-messages`、`alatka-dependencies`、`alatka`相关制品已上传至阿里云仓库，如需下载可进行如下配置：:point_right: [maven引入相关依赖](https://gitee.com/asuka2001/alatka-messages/wikis/%E4%BA%94%E3%80%81%E8%BF%9B%E9%98%B6%E7%9F%A5%E8%AF%86/9.maven%E5%BC%95%E5%85%A5%E7%9B%B8%E5%85%B3%E4%BE%9D%E8%B5%96)

### [ :point_right: 访问wiki查看更多教程](https://gitee.com/asuka2001/alatka-messages/wikis)

### github地址

项目同步更新在github；如有需要， :point_right: [点击我访问](https://github.com/goGetSomeFries/alatka-messages)

### 感谢支持

如果觉得好用，欢迎推荐给身边同事同学朋友；也欢迎各位的issues和star，问题会及时回复，再次感谢大家的支持！