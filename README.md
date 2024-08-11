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

### [访问wiki查看更多教程](https://gitee.com/asuka2001/alatka-messages/wikis)

### github地址

项目同步更新在github；如有需要，[点击我访问](https://github.com/goGetSomeFries/alatka-messages)

### 感谢支持

如果觉得好用，欢迎推荐给身边同事同学朋友；也欢迎各位的issues和star，问题会及时回复，再次感谢大家的支持！