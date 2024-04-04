# 固定格式/8583报文解析工具

## 一、背景介绍

### 1.写在开头

目前市面上的固定格式/8583解析工具存在以下问题：

- 不支持子域解析；
- 报文域只支持String类型映射，类型转换需手动进行二次处理；
- 大部分通过java注解方式进行配置，无法做热部署；

该工具包对以上不足进行了优化升级，完美解决以上问题。

### 2.功能概述

- 支持固定格式和8583报文解析，配置方式和解析方式做到统一；
- 支持8583**子域**及其**嵌套子域**解析；
- 支持报文域值类型**映射java类型**（Boolean/Integer/String/LocalDate/BigDecimal/List/Map/POJO...）
- 支持**YAML文件**、**注解**、**数据库表**（待实现）三种配置形式；
- **YAML文件**、**数据库表**支持动态配置，可做热更新；

## 二、快速入门

### 1.maven引入

```xml

<dependencies>
    <dependency>
        <groupId>com.alatka</groupId>
        <artifactId>alatka-messages</artifactId>
        <version>[version]</version>
    </dependency>

    <!-- yaml配置方式 -->
    <dependency>
        <groupId>com.fasterxml.jackson.dataformat</groupId>
        <artifactId>jackson-dataformat-yaml</artifactId>
        <version>[version]</version>
    </dependency>
    <!-- annotation配置方式 -->
    <dependency>
        <groupId>org.reflections</groupId>
        <artifactId>reflections</artifactId>
        <version>[version]</version>
    </dependency>
    <!-- TODO 数据库配置方式 -->
</dependencies>
```

### 2.报文格式定义配置

以固定格式4118（客户以及账户标签内容交易）报文为例，**YAML配置**、**注解配置**、**数据库配置**三选一。

#### 2.1.YAML配置

##### 2.1.1.YAML文件内容

```yaml
# YAML固定前缀
alatka.messages:
  # 报文描述/备注
  remark: 客户以及账户标签内容交易
  # 报文类型：fixed/iso
  type: fixed
  # 报文分组，指代机构代码
  group: "0601"
  # 报文代码
  code: 4118
  #报文内容
  message:
    # 请求体
    # domainNo：报文域号/索引号
    # name：报文域名称
    # length：报文域字节长度
    # remark：报文域描述
    # clazz：报文域字段映射java类型
    request:
      - { "domainNo": 1, "name": "trxType", "length": 4, "remark": "交易代码", "clazz": "java.lang.String" }
      - { "domainNo": 2, "name": "retCode", "length": 6, "remark": "响应码", "clazz": "java.lang.String" }
      - { "domainNo": 3, "name": "bnkNbr", "length": 4, "remark": "银行代号", "clazz": "java.lang.String" }
      - { "domainNo": 4, "name": "source", "length": 2, "remark": "交易来源", "clazz": "java.lang.String" }
      - { "domainNo": 5, "name": "brnNo", "length": 6, "remark": "网点代号", "clazz": "java.lang.String" }
      - { "domainNo": 6, "name": "opeNo", "length": 6, "remark": "操作员号", "clazz": "java.lang.String" }
      - { "domainNo": 7, "name": "seqNo", "length": 6, "remark": "流水号", "clazz": "java.lang.Integer" }
      - { "domainNo": 8, "name": "option", "length": 8, "remark": "标志位", "clazz": "java.lang.String" }
      - { "domainNo": 9, "name": "custNbr", "length": 19, "remark": "证件号码", "clazz": "java.lang.String" }
      - { "domainNo": 10, "name": "cardNbr", "length": 19, "remark": "卡号", "clazz": "java.lang.String" }
      - { "domainNo": 11, "name": "tagType", "length": 5, "remark": "标签类型", "clazz": "java.lang.String" }
      - { "domainNo": 12, "name": "tagCode", "length": 4, "remark": "标签代码", "clazz": "java.lang.String" }
      - { "domainNo": 13, "name": "tagInfo", "length": 20, "remark": "标签内容", "clazz": "java.lang.String" }
      - { "domainNo": 14, "name": "resvd", "length": 500, "remark": "保留域", "clazz": "java.lang.String" }
    # 响应体
    response:
      - { "domainNo": 1, "name": "trxType", "length": 4, "remark": "交易代码", "clazz": "java.lang.String" }
      - { "domainNo": 2, "name": "retCode", "length": 6, "remark": "响应码", "clazz": "java.lang.String" }
      - { "domainNo": 3, "name": "bnkNbr", "length": 4, "remark": "银行代号", "clazz": "java.lang.String" }
      - { "domainNo": 4, "name": "source", "length": 2, "remark": "交易来源", "clazz": "java.lang.String" }
      - { "domainNo": 5, "name": "brnNo", "length": 6, "remark": "网点代号", "clazz": "java.lang.String" }
      - { "domainNo": 6, "name": "opeNo", "length": 6, "remark": "操作员号", "clazz": "java.lang.String" }
      - { "domainNo": 7, "name": "seqNo", "length": 6, "remark": "流水号", "clazz": "java.lang.Integer" }
      - { "domainNo": 8, "name": "option", "length": 8, "remark": "标志位", "clazz": "java.lang.String" }
      - { "domainNo": 9, "name": "custNbr", "length": 19, "remark": "证件号码", "clazz": "java.lang.String" }
      - { "domainNo": 10, "name": "cardNbr", "length": 19, "remark": "卡号", "clazz": "java.lang.String" }
      - { "domainNo": 11, "name": "tagType", "length": 5, "remark": "标签类型", "clazz": "java.lang.String" }
      - { "domainNo": 12, "name": "tagCode", "length": 4, "remark": "标签代码", "clazz": "java.lang.String" }
      - { "domainNo": 13, "name": "tagInfo", "length": 20, "remark": "标签内容", "clazz": "java.lang.String" }
      - { "domainNo": 14, "name": "resvd", "length": 500, "remark": "保留域", "clazz": "java.lang.String" }
```

##### 2.1.2.YAML文件名

```text
0601.4118.fixed.yml
```

- **0601**：同`alatka.messages.group`取值一致
- **4118**：同`alatka.messages.code`取值一致
- **fixed**：同`alatka.messages.type`取值一致
- **yml**：文件后缀，指代YAML文件

**注：建议依据以上规则命名YAML文件，方便维护管理，但不强制要求（.fixed.yml后缀除外）。**

#### 2.2.注解配置

##### 2.2.1.请求体定义

```java

@MessageMeta(
        type = MessageDefinition.Type.fixed,   // 同YAML文件 alatka.messages.type
        group = "0601",                        // 同YAML文件 alatka.messages.group
        code = "4118",                         // 同YAML文件 alatka.messages.code
        kind = MessageDefinition.Kind.request, // 同YAML文件 alatka.messages.message.request
        remark = "客户以及账户标签内容交易")     // 同YAML文件 alatka.messages.remark
public class Fixed4118Req extends FixedHeader {

    @FixedFieldMeta(domainNo = 8, length = 8, remark = "标志位")
    private String option;
    @FixedFieldMeta(domainNo = 9, length = 19, remark = "证件号码")
    private String custNbr;
    @FixedFieldMeta(domainNo = 10, length = 19, remark = "卡号")
    private String cardNbr;
    @FixedFieldMeta(domainNo = 11, length = 5, remark = "标签类型")
    private String tagType;
    @FixedFieldMeta(domainNo = 12, length = 4, remark = "标签代码")
    private String tagCode;
    @FixedFieldMeta(domainNo = 13, length = 20, remark = "标签内容")
    private String tagInfo;
    @FixedFieldMeta(domainNo = 14, length = 500, remark = "保留域")
    private String resvd;

    // getter setter...
}
```

##### 2.2.2.响应体定义

```java

@MessageMeta(
        type = MessageDefinition.Type.fixed,   // 同YAML文件 alatka.messages.type
        group = "0601",                        // 同YAML文件 alatka.messages.group
        code = "4118",                         // 同YAML文件 alatka.messages.code
        kind = MessageDefinition.Kind.response, // 同YAML文件 alatka.messages.message.response
        remark = "客户以及账户标签内容交易")     // 同YAML文件 alatka.messages.remark
public class Fixed4118Res extends FixedHeader {

    @FixedFieldMeta(domainNo = 8, length = 8, remark = "标志位")
    private String option;
    @FixedFieldMeta(domainNo = 9, length = 19, remark = "证件号码")
    private String custNbr;
    @FixedFieldMeta(domainNo = 10, length = 19, remark = "卡号")
    private String cardNbr;
    @FixedFieldMeta(domainNo = 11, length = 5, remark = "标签类型")
    private String tagType;
    @FixedFieldMeta(domainNo = 12, length = 4, remark = "标签代码")
    private String tagCode;
    @FixedFieldMeta(domainNo = 13, length = 20, remark = "标签内容")
    private String tagInfo;
    @FixedFieldMeta(domainNo = 14, length = 500, remark = "保留域")
    private String resvd;

    // getter setter...
}
```

##### 2.2.3.共用字段定义

```java

// 共用字段可单独定义一个类
public class FixedHeader {

    @FixedFieldMeta(domainNo = 1, length = 4, remark = "交易代码")
    private String trxType;
    @FixedFieldMeta(domainNo = 2, length = 6, remark = "响应码")
    private String retCode;
    @FixedFieldMeta(domainNo = 3, length = 4, remark = "银行代号")
    private String bnkNbr;
    @FixedFieldMeta(domainNo = 5, length = 2, remark = "网点代号")
    private String source;
    @FixedFieldMeta(domainNo = 4, length = 6, remark = "交易来源")
    private String brnNo;
    @FixedFieldMeta(domainNo = 6, length = 6, remark = "操作员号")
    private String opeNo;
    @FixedFieldMeta(domainNo = 7, length = 6, remark = "流水号")
    private Integer seqNo;

    // getter setter...
}
```

#### 2.3.数据库配置

TODO

### 3.报文打包解包

#### 3.1.基于YAML配置方式

```java
import holder.com.alatka.messages.MessageHolder;

public class Test {
  public static void main(String[] args) {
    // 1.加载配置
    String filePath = ""; // 0601.4118.fixed.yml文件classpath
    new FixedYamlMessageDefinitionBuilder(filePath).build();

    // 2.接收处理请求报文
    byte[] reqBytes = null; // 需要解包的字节数据
    String reqKey = "fixed:0601:4118:request"; // [type]:[group]:[code]:[kind] 唯一标识一类报文
    MessageHolder req = MessageBuilder.init(reqKey).unpack(reqBytes); // 解包为对应MessageHolder实体

    // 3.业务处理...

    // 4.响应实体序列化为字节数组
    MessageHolder res = null; // 响应实体
    String resKey = "fixed:0601:4118:response"; // [type]:[group]:[code]:[kind] 唯一标识一类报文
    byte[] resBytes = MessageBuilder.init(resKey).pack(res); // 打包响应MessageHolder实体为字节数组

    // 5.响应给客户端...
  }
}
```

#### 3.2.基于注解配置方式

```java
public class Test {
    public static void main(String[] args) {
        // 1.加载配置
        String packagePath = ""; // Fixed4118Req Fixed4118Res FixedHeader 类路径
        new FixedAnnotationMessageDefinitionBuilder(packagePath).build();

        // 2.接收处理请求报文
        byte[] reqBytes = null; // 需要解包的字节数据
        String reqKey = "fixed:0601:4118:request"; // [type]:[group]:[code]:[kind] 唯一标识一类报文
        Fixed4118Req req = MessageBuilder.init(reqKey).unpack(reqBytes); // 解包为对应实体

        // 3.业务处理...

        // 4.响应实体序列化为字节数组
        Fixed4118Res res = null; // 响应实体
        String resKey = "fixed:0601:4118:response"; // [type]:[group]:[code]:[kind] 唯一标识一类报文
        byte[] resBytes = MessageBuilder.init(resKey).pack(res); // 打包响应实体为字节数组

        // 5.响应给客户端...
    }
}
```

#### 3.3.基于数据库配置方式

TODO

## 三、基础介绍

### 1.maven引入

可以直接引入最新版本`alatka-messages`包，YAML/注解/数据库三种方式需单独引入三方依赖包，并指定其版本；
或者引入`alatka-dependencies.pom`，则无需其他依赖包版本配置。

#### 1.1.直接引入

```xml

<dependencies>
  <dependency>
    <groupId>com.alatka</groupId>
    <artifactId>alatka-messages</artifactId>
    <version>[version]</version>
  </dependency>

  <!-- yaml配置方式 -->
  <dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-yaml</artifactId>
    <version>[version]</version>
  </dependency>
  <!-- annotation配置方式 -->
  <dependency>
    <groupId>org.reflections</groupId>
    <artifactId>reflections</artifactId>
    <version>[version]</version>
  </dependency>
  <!-- TODO 数据库配置方式 -->
</dependencies>
```
#### 1.2.导入alatka dependencies pom

```xml

<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>com.alatka</groupId>
      <artifactId>alatka-dependencies</artifactId>
      <version>[version]</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>

<dependencies>
  <dependency>
    <groupId>com.alatka</groupId>
    <artifactId>alatka-messages</artifactId>
  </dependency>
  
  <!-- yaml配置方式 -->
  <dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-yaml</artifactId>
  </dependency>
  <!-- annotation配置方式 -->
  <dependency>
    <groupId>org.reflections</groupId>
    <artifactId>reflections</artifactId>
  </dependency>
  <!-- TODO 数据库配置方式 -->
</dependencies>
```

### 2.报文格式定义

报文格式定义以**报文类型**和**配置方式**两个维度描述，**报文类型**包括8583和固定格式；**配置方式**包括YAML、注解和数据库；

#### 2.1.YAML配置

##### 2.1.1.固定格式

YAML文件内容
```yaml
alatka.messages:
  remark: 信用卡账单明细查询
  type: fixed
  group: "0802"
  code: 3006
  message:
    request:
      - { "domainNo": 1, "name": "trxType", "length": 4, "remark": "交易代码", "clazz": "java.lang.String" }
      - { "domainNo": 2, "name": "retCode", "length": 6, "remark": "响应码", "clazz": "java.lang.String" }
      - { "domainNo": 3, "name": "bnkNbr", "length": 4, "remark": "银行代号", "clazz": "java.lang.String" }
      - { "domainNo": 4, "name": "source", "length": 2, "remark": "交易来源", "clazz": "java.lang.String" }
      - { "domainNo": 5, "name": "brnNo", "length": 6, "remark": "网点代号", "clazz": "java.lang.String" }
      - { "domainNo": 6, "name": "opeNo", "length": 6, "remark": "操作员号", "clazz": "java.lang.String" }
      - { "domainNo": 7, "name": "seqNo", "length": 6, "remark": "流水号", "clazz": "java.lang.Integer" }
      - { "domainNo": 8, "name": "cardNbr", "length": 19, "remark": "卡号", "clazz": "java.lang.String" }
      - { "domainNo": 9, "name": "tranYM", "length": 4, "remark": "账单年月", "clazz": "java.time.YearMonth", "pattern": "yyMM" }
      - { "domainNo": 10, "name": "option", "length": 1, "remark": "币种选项", "clazz": "java.lang.String" }
      - { "domainNo": 11, "name": "rtnInd", "length": 1, "remark": "翻页标志", "clazz": "java.lang.String" }
      - { "domainNo": 12, "name": "valDate", "length": 8, "remark": "入账日期", "clazz": "java.time.LocalDate", "pattern": "yyyyMMdd" }
      - { "domainNo": 13, "name": "purDate", "length": 8, "remark": "记录日期", "clazz": "java.time.LocalDate", "pattern": "yyyyMMdd" }
      - { "domainNo": 14, "name": "purTime", "length": 8, "remark": "消费时间", "clazz": "java.time.LocalTime", "pattern": "HHmmssSS" }
      - { "domainNo": 15, "name": "tranNo", "length": 6, "remark": "交易流水号", "clazz": "java.lang.Integer" }
      - { "domainNo": 16, "name": "pinFlag", "length": 1, "remark": "是否检查密码标志", "clazz": "java.lang.String" }
      - { "domainNo": 17, "name": "pin", "length": 8, "remark": "密码", "clazz": "[B" }
      - { "domainNo": 18, "name": "enqFlag", "length": 1, "remark": "查询条件", "clazz": "java.lang.String" }
      - { "domainNo": 19, "name": "orderFlag", "length": 1, "remark": "显示顺序标志", "clazz": "java.lang.String" }
    response:
      - { "domainNo": 1, "name": "trxType", "length": 4, "remark": "交易代码", "clazz": "java.lang.String" }
      - { "domainNo": 2, "name": "retCode", "length": 6, "remark": "响应码", "clazz": "java.lang.String" }
      - { "domainNo": 3, "name": "bnkNbr", "length": 4, "remark": "银行代号", "clazz": "java.lang.String" }
      - { "domainNo": 4, "name": "source", "length": 2, "remark": "交易来源", "clazz": "java.lang.String" }
      - { "domainNo": 5, "name": "brnNo", "length": 6, "remark": "网点代号", "clazz": "java.lang.String" }
      - { "domainNo": 6, "name": "opeNo", "length": 6, "remark": "操作员号", "clazz": "java.lang.String" }
      - { "domainNo": 7, "name": "seqNo", "length": 6, "remark": "流水号", "clazz": "java.lang.Integer" }
      - { "domainNo": 8, "name": "cardNbr", "length": 19, "remark": "卡号", "clazz": "java.lang.String" }
      - { "domainNo": 9, "name": "tranYM", "length": 4, "remark": "账单年月", "clazz": "java.time.YearMonth", "pattern": "yyMM" }
      - { "domainNo": 10, "name": "option", "length": 1, "remark": "币种选项", "clazz": "java.lang.String" }
      - { "domainNo": 11, "name": "counts", "length": 2, "remark": "本次返回交易条数", "clazz": "java.lang.Integer" }
      - { "domainNo": 12, "name": "rtnInd", "length": 1, "remark": "翻页标志", "clazz": "java.lang.String" }
      - { "domainNo": 13, "name": "elements", "fixed": false, "length": 141, "remark": "集合", "clazz": "java.util.ArrayList", "pageSizeName": "counts", "existSubdomain": true, "subdomainType": "LIST" }
    subPayload:
      F13:
        - { "domainNo": 1, "name": "monthNbr", "length": 3, "remark": "月份序号", "clazz": "java.lang.Integer" }
        - { "domainNo": 2, "name": "valDate", "length": 8, "remark": "入账日期", "clazz": "java.time.LocalDate", "pattern": "yyyyMMdd" }
        - { "domainNo": 3, "name": "purDate", "length": 8, "remark": "记录日期", "clazz": "java.time.LocalDate", "pattern": "yyyyMMdd" }
        - { "domainNo": 4, "name": "purTime", "length": 8, "remark": "消费时间", "clazz": "java.time.LocalTime", "pattern": "HHmmssSS" }
        - { "domainNo": 5, "name": "tranNo", "length": 6, "remark": "交易流水号", "clazz": "java.lang.Integer" }
        - { "domainNo": 6, "name": "tranType", "length": 4, "remark": "交易类型", "clazz": "java.lang.String" }
        - { "domainNo": 7, "name": "amount", "length": 12, "remark": "交易金额", "clazz": "java.math.BigDecimal" }
        - { "domainNo": 8, "name": "amountFl", "length": 1, "remark": "交易金额符号", "clazz": "java.lang.String" }
        - { "domainNo": 9, "name": "authCode", "length": 6, "remark": "授权代码", "clazz": "java.lang.String" }
        - { "domainNo": 10, "name": "desline1", "length": 42, "remark": "交易描述1", "clazz": "java.lang.String" }
        - { "domainNo": 11, "name": "desline2", "length": 25, "remark": "交易描述2", "clazz": "java.lang.String" }
        - { "domainNo": 12, "name": "cardEnd", "length": 4, "remark": "卡号后四位", "clazz": "java.lang.String" }
        - { "domainNo": 13, "name": "currNum", "length": 3, "remark": "货币代码", "clazz": "java.lang.String" }
        - { "domainNo": 14, "name": "mpFlag", "length": 1, "remark": "保留字段1", "clazz": "java.lang.String" }
        - { "domainNo": 15, "name": "purDate1", "length": 4, "remark": "消费日期", "clazz": "java.time.MonthDay", "pattern": "MMdd" }
        - { "domainNo": 16, "name": "revInd", "length": 1, "remark": "撤销冲正标志", "clazz": "java.lang.Integer" }
        - { "domainNo": 17, "name": "mcc", "length": 4, "remark": "商户类别", "clazz": "java.lang.String" }
        - { "domainNo": 18, "name": "facePayFlag", "length": 1, "remark": "人脸支付标志", "clazz": "java.lang.Integer" }
```

YAML文件命名
```text
0802.3006.fixed.yml
```

- **0802**：同`alatka.messages.group`取值一致
- **3006**：同`alatka.messages.code`取值一致
- **fixed**：同`alatka.messages.type`取值一致
- **yml**：文件后缀，指代YAML文件

**注：建议依据以上规则命名YAML文件，方便维护管理，但不强制要求（.fixed.yml后缀除外）。**

##### 2.1.2.8583

YAML文件内容
```yaml

alatka.messages:
  remark: 银联8583报文模板
  type: iso
  group: cups
  code: test
  message:
    header:
      - { "domainNo": 1, "name": "headerLength", "fixed": true, "length": 1, "remark": "报文头长度", "clazz": "java.lang.Integer", "parseType": "BINARY" }
      - { "domainNo": 2, "name": "version", "fixed": true, "length": 1, "remark": "头标识和版本号", "clazz": "java.lang.String", "parseType": "BINARY" }
      - { "domainNo": 3, "name": "messageLength", "fixed": true, "length": 4, "remark": "报文长度", "clazz": "java.lang.Integer" }
      - { "domainNo": 4, "name": "destinationId", "fixed": true, "length": 11, "remark": "目的ID", "clazz": "java.lang.String" }
      - { "domainNo": 5, "name": "sourceId", "fixed": true, "length": 11, "remark": "源ID", "clazz": "java.lang.String" }
      - { "domainNo": 6, "name": "reserved", "fixed": true, "length": 3, "remark": "保留使用", "clazz": "[B" }
      - { "domainNo": 7, "name": "batchNum", "fixed": true, "length": 1, "remark": "批次号", "clazz": "java.lang.String", "parseType": "BINARY" }
      - { "domainNo": 8, "name": "transInfo", "fixed": true, "length": 8, "remark": "交易信息", "clazz": "java.lang.String" }
      - { "domainNo": 9, "name": "userInfo", "fixed": true, "length": 1, "remark": "用户信息", "clazz": "java.lang.String", "parseType": "BINARY" }
      - { "domainNo": 10, "name": "rejectCode", "fixed": true, "length": 5, "remark": "拒绝码", "clazz": "java.lang.String" }
    payload:
      - { "domainNo": 0, "name": "messageType", "fixed": true, "length": 4, "remark": "报文类型", "clazz": "java.lang.String" }
      - { "domainNo": 1, "name": "bitMap", "fixed": true, "length": 16, "remark": "位图", "clazz": "java.util.HashMap", "parseType": "BINARY" }
      - { "domainNo": 2, "name": "primaryAcctNum", "fixed": false, "length": 2, "maxLength": 19, "remark": "主账号", "clazz": "java.lang.String" }
      - { "domainNo": 3, "name": "processingCode", "fixed": true, "length": 6, "remark": "交易处理码", "clazz": "java.lang.String" }
      - { "domainNo": 4, "name": "amtTrans", "fixed": true, "length": 12, "remark": "交易金额", "clazz": "java.math.BigDecimal" }
      - { "domainNo": 5, "name": "amtSettlmt", "fixed": true, "length": 12, "remark": "清算金额", "clazz": "java.math.BigDecimal" }
      - { "domainNo": 6, "name": "amtCdhldrBil", "fixed": true, "length": 12,  "remark": "持卡人扣账金额", "clazz": "java.math.BigDecimal" }
      - { "domainNo": 7, "name": "transmsnDateTime", "fixed": true, "length": 10, "remark": "交易传输时间", "clazz": "java.time.LocalDateTime", "pattern": "MMddHHmmss" }
      - { "domainNo": 9, "name": "convRateSettlmt", "fixed": true, "length": 8, "remark": "清算汇率", "clazz": "java.lang.Integer" }
      - { "domainNo": 10, "name": "convRateCdhldrBil", "fixed": true, "length": 8, "remark": "持卡人扣账汇率", "clazz": "java.lang.Integer" }
      - { "domainNo": 11, "name": "sysTraceAuditNum", "fixed": true, "length": 6, "remark": "系统跟踪号", "clazz": "java.lang.Integer" }
      - { "domainNo": 12, "name": "timeLocalTrans", "fixed": true, "length": 6, "remark": "受卡方所在地时间", "clazz": "java.time.LocalTime", "pattern": "HHmmss" }
      - { "domainNo": 13, "name": "dateLocalTrans", "fixed": true, "length": 4, "remark": "受卡方所在地日期", "clazz": "java.time.MonthDay", "pattern": "MMdd" }
      - { "domainNo": 14, "name": "dateExpr", "fixed": true, "length": 4, "remark": "卡有效期", "clazz": "java.time.YearMonth", "pattern": "yyMM" }
      - { "domainNo": 15, "name": "dateSettlmt", "fixed": true, "length": 4, "remark": "清算日期", "clazz": "java.time.MonthDay", "pattern": "MMdd" }
      - { "domainNo": 16, "name": "dateConv", "fixed": true, "length": 4, "remark": "兑换日期", "clazz": "java.time.MonthDay", "pattern": "MMdd" }
      - { "domainNo": 18, "name": "mchntType", "fixed": true, "length": 4, "remark": "商户类型", "clazz": "java.lang.String" }
      - { "domainNo": 19, "name": "mchntCntryCode", "fixed": true, "length": 3, "remark": "商户国家代码", "clazz": "java.lang.String" }
      - { "domainNo": 22, "name": "posEntryModeCode", "fixed": true, "length": 3, "remark": "服务点输入方式码", "clazz": "java.lang.String" }
      - { "domainNo": 23, "name": "cardSeqId", "fixed": true, "length": 3, "remark": "卡序列号", "clazz": "java.lang.String" }
      - { "domainNo": 25, "name": "posCondCode", "fixed": true, "length": 2, "remark": "服务点条件码", "clazz": "java.lang.String" }
      - { "domainNo": 26, "name": "posPinCaptrCode", "fixed": true, "length": 2, "remark": "服务点PIN获取码", "clazz": "java.lang.String" }
      - { "domainNo": 28, "name": "amtTransFee", "fixed": true, "length": 9, "remark": "交易费", "clazz": "java.lang.String" }
      - { "domainNo": 32, "name": "acqInstIdCode", "fixed": false, "length": 2, "maxLength": 11, "remark": "代理机构标识码", "clazz": "java.lang.String" }
      - { "domainNo": 33, "name": "fwdInstIdCode", "fixed": false, "length": 2, "maxLength": 11, "remark": "发送机构标识码", "clazz": "java.lang.String" }
      - { "domainNo": 35, "name": "tracK2Data", "fixed": false, "length": 2, "maxLength": 37, "remark": "第二磁道数据", "clazz": "java.lang.String" }
      - { "domainNo": 36, "name": "tracK3Data", "fixed": false, "length": 3, "maxLength": 104,  "remark": "第三磁道数据", "clazz": "java.lang.String" }
      - { "domainNo": 37, "name": "retrivlRefNum", "fixed": true, "length": 12, "remark": "检索参考号", "clazz": "java.lang.String" }
      - { "domainNo": 38, "name": "authrIdResp", "fixed": true, "length": 6, "remark": "授权标识应答码", "clazz": "java.lang.String" }
      - { "domainNo": 39, "name": "respCode", "fixed": true, "length": 2, "remark": "应答码", "clazz": "java.lang.String" }
      - { "domainNo": 41, "name": "cardAccptrTermnlId", "fixed": true, "length": 8, "remark": "受卡机终端标识码", "clazz": "java.lang.String" }
      - { "domainNo": 42, "name": "cardAccptrId", "fixed": true, "length": 15, "remark": "受卡方标识码", "clazz": "java.lang.String" }
      - { "domainNo": 43, "name": "cardAccptrNameLoc", "fixed": true, "length": 40, "remark": "受卡方名称地址", "clazz": "java.lang.String" }
      - { "domainNo": 44, "name": "addtnlRespCode", "fixed": false, "length": 2, "maxLength": 25, "remark": "附加响应数据", "clazz": "java.lang.String" }
      - { "domainNo": 45, "name": "tracK1Data", "fixed": false, "length": 2, "maxLength": 76, "remark": "第一磁道数据", "clazz": "java.lang.String" }
      - { "domainNo": 48, "name": "addtnlDataPrivate", "fixed": false, "length": 3, "maxLength": 512, "remark": "附加数据——私有", "existSubdomain": true, "subdomainType": "UV" }
      - { "domainNo": 49, "name": "currcyCodeTrans", "fixed": true, "length": 3, "remark": "交易货币代码", "clazz": "java.lang.String" }
      - { "domainNo": 50, "name": "currcyCodeSettlmt", "fixed": true, "length": 3, "remark": "清算货币代码", "clazz": "java.lang.String" }
      - { "domainNo": 51, "name": "currcyCodeCdhldrBil", "fixed": true, "length": 3, "remark": "持卡人帐户货币代码", "clazz": "java.lang.String" }
      - { "domainNo": 52, "name": "pinData", "fixed": true, "length": 8, "remark": "个人标识码数据", "clazz": "[B" }
      - { "domainNo": 53, "name": "secRelatdCtrlInfo", "fixed": true, "length": 16, "remark": "安全控制信息", "existSubdomain": true, "subdomainType": "DEFAULT" }
      - { "domainNo": 54, "name": "addtnlAmt", "fixed": false, "length": 3, "maxLength": 40, "remark": "实际余额", "existSubdomain": true, "subdomainType": "DEFAULT" }
      - { "domainNo": 55, "name": "iccData", "fixed": false, "length": 3, "maxLength": 255, "remark": "IC卡数据域", "existSubdomain": true, "subdomainType": "TLV" }
      - { "domainNo": 56, "name": "addtnlData56", "fixed": false, "length": 3, "maxLength": 512, "remark": "附加信息", "existSubdomain": true, "subdomainType": "ULV" }
      - { "domainNo": 57, "name": "addtnlData57", "fixed": false, "length": 3, "maxLength": 100, "remark": "附加交易信息", "existSubdomain": true, "subdomainType": "UV" }
      - { "domainNo": 59, "name": "detailInqrng", "fixed": false, "length": 3,  "maxLength": 600, "remark": "明细查询数据", "existSubdomain": true, "subdomainType": "UV" }
      - { "domainNo": 60, "name": "reserved", "fixed": false, "length": 3, "maxLength": 100, "remark": "自定义域", "existSubdomain": true, "subdomainType": "DEFAULT" }
      - { "domainNo": 61, "name": "chAuthInfo", "fixed": false, "length": 3, "maxLength": 200, "remark": "持卡人身份认证信息", "existSubdomain": true, "subdomainType": "DEFAULT" }
      - { "domainNo": 62, "name": "switchingData", "fixed": false, "length": 3, "maxLength": 200, "remark": "交换中心数据", "existSubdomain": true, "subdomainType": "UV" }
      - { "domainNo": 63, "name": "finaclNetData", "fixed": false, "length": 3, "maxLength": 512, "remark": "金融网络数据", "existSubdomain": true, "subdomainType": "ULV" }
      - { "domainNo": 70, "name": "netwkMgmtInfoCode", "fixed": true, "length": 3, "remark": "网络管理信息码", "clazz": "java.lang.String" }
      - { "domainNo": 90, "name": "origDataElemts", "fixed": true, "length": 42, "remark": "原始数据元", "existSubdomain": true, "subdomainType": "DEFAULT" }
      - { "domainNo": 96, "name": "msgSecurityCode", "fixed": true, "length": 8, "remark": "报文安全码", "clazz": "[B" }
      - { "domainNo": 100, "name": "rcvgInstIdCode", "fixed": false, "length": 2, "maxLength": 11, "remark": "接收机构标识码", "clazz": "java.lang.String" }
      - { "domainNo": 102, "name": "acctId1", "fixed": false, "length": 2, "maxLength": 28, "remark": "帐户标识1", "clazz": "java.lang.String" }
      - { "domainNo": 103, "name": "acctId2", "fixed": false, "length": 2, "maxLength": 28, "remark": "帐户标识2", "clazz": "java.lang.String" }
      - { "domainNo": 104, "name": "addtnlData104", "fixed": false, "length": 3, "maxLength": 512, "remark": "附加信息", "existSubdomain": true, "subdomainType": "ULV" }
      - { "domainNo": 113, "name": "addtnlData113", "fixed": false, "length": 3, "maxLength": 512, "remark": "附加信息", "existSubdomain": true, "subdomainType": "ULV" }
      - { "domainNo": 116, "name": "addtnlData116", "fixed": false, "length": 3, "maxLength": 512, "remark": "附加信息", "existSubdomain": true, "subdomainType": "ULV" }
      - { "domainNo": 117, "name": "addtnlData117", "fixed": false, "length": 3, "maxLength": 256, "remark": "附加信息", "existSubdomain": true, "subdomainType": "ULV" }
      - { "domainNo": 121, "name": "nationalSwResved", "fixed": false, "length": 3, "maxLength": 100, "remark": "银联处理中心保留", "existSubdomain": true, "subdomainType": "DEFAULT" }
      - { "domainNo": 122, "name": "acqInstResvd", "fixed": false, "length": 3, "maxLength": 100, "remark": "受理方保留", "existSubdomain": true, "subdomainType": "DEFAULT" }
      - { "domainNo": 123, "name": "issrInstResvd", "fixed": false, "length": 3, "maxLength": 100, "remark": "发卡方保留", "clazz": "[B" }
      - { "domainNo": 125, "name": "addtnlData125", "fixed": false, "length": 3, "maxLength": 256, "remark": "附加信息", "existSubdomain": true, "subdomainType": "ULV" }
      - { "domainNo": 126, "name": "addtnlData126", "fixed": false, "length": 3, "maxLength": 256, "remark": "附加信息", "existSubdomain": true, "subdomainType": "ULV" }
      - { "domainNo": 128, "name": "msgAuthnCode", "fixed": true, "length": 8, "remark": "报文鉴别码", "clazz": "[B" }
    subPayload:
      F55$TLV:
        - { "domainNo": 0x9F26, "name": "crypt", "fixed": false, "remark": "应用密文", "clazz": "[B" }
        - { "domainNo": 0x9F27, "name": "cryptInfoData", "fixed": false, "remark": "密文信息数据", "clazz": "[B" }
        - { "domainNo": 0x9F10, "name": "issuerAppData", "fixed": false, "remark": "发卡行应用数据", "clazz": "[B" }
        - { "domainNo": 0x9F37, "name": "unpredictableNumber", "fixed": false, "remark": "不可预知数", "clazz": "[B" }
        - { "domainNo": 0x9F36, "name": "appTransCounter", "fixed": false, "remark": "应用交易计数器", "clazz": "[B" }
        - { "domainNo": 0x95, "name": "termVerificationResult", "fixed": false, "remark": "终端验证结果", "clazz": "[B" }
        - { "domainNo": 0x9A, "name": "transDate", "fixed": false, "remark": "交易日期", "clazz": "java.time.LocalDate", "pattern": "yyMMdd", "parseType": "BCD" }
        - { "domainNo": 0x9C, "name": "transType", "fixed": false, "remark": "交易类型", "clazz": "java.lang.Integer", "parseType": "BCD" }
        - { "domainNo": 0x9F02, "name": "transAmt", "fixed": false, "remark": "授权金额", "clazz": "java.math.BigDecimal", "parseType": "BCD" }
        - { "domainNo": 0x5F2A, "name": "transCurrencyCode", "fixed": false, "remark": "交易货币代码", "clazz": "java.lang.Integer", "parseType": "BCD" }
        - { "domainNo": 0x82, "name": "appInterchangeProfile", "fixed": false, "remark": "应用交互特征", "clazz": "[B" }
        - { "domainNo": 0x9F1A, "name": "termCountryCode", "fixed": false, "remark": "终端国家代码", "clazz": "java.lang.Integer", "parseType": "BCD" }
        - { "domainNo": 0x9F03, "name": "otherAmt", "fixed": false, "remark": "其它金额", "clazz": "java.math.BigDecimal", "parseType": "BCD" }
        - { "domainNo": 0x9F33, "name": "termCap", "fixed": false, "remark": "终端性能", "clazz": "[B" }
        - { "domainNo": 0x9F34, "name": "cardholderVerificationMethodResults", "fixed": false, "remark": "持卡人验证方法结果", "clazz": "[B" }
        - { "domainNo": 0x9F35, "name": "termType", "fixed": false, "remark": "终端类型", "clazz": "java.lang.Integer", "parseType": "BCD" }
        - { "domainNo": 0x9F1E, "name": "interfaceDeviceSerialNumber", "fixed": false, "remark": "接口设备序列号", "clazz": "java.lang.String" }
        - { "domainNo": 0x84, "name": "dedicatedFileName", "fixed": false, "remark": "专用文件名称", "clazz": "[B" }
        - { "domainNo": 0x9F09, "name": "termAppVersionNumber", "fixed": false, "remark": "应用版本号", "clazz": "[B" }
        - { "domainNo": 0x9F41, "name": "transSequenceCounter", "fixed": false, "remark": "交易序列计数器", "clazz": "java.lang.Integer", "parseType": "BCD" }
        - { "domainNo": 0x91, "name": "issuerAuthenticationData", "fixed": false, "remark": "发卡行认证数据", "clazz": "[B" }
        - { "domainNo": 0x71, "name": "issuerScriptTemplate1", "fixed": false, "remark": "发卡行脚本1", "clazz": "[B" }
        - { "domainNo": 0x72, "name": "issuerScriptTemplate2", "fixed": false, "remark": "发卡行脚本2", "clazz": "[B" }
        - { "domainNo": 0xDF31, "name": "issuerScriptResults", "fixed": false, "remark": "发卡方脚本结果", "clazz": "[B" }
        - { "domainNo": 0x9F74, "name": "issuerAuthorizationCode", "fixed": false, "remark": "电子现金发卡行授权码", "clazz": "java.lang.String" }
        - { "domainNo": 0x9F63, "name": "cardProductIdentification", "fixed": false, "remark": "卡产品标识信息", "clazz": "[B" }
        - { "domainNo": 0x8A, "name": "authorizationResponseCode", "fixed": false, "remark": "授权响应码", "clazz": "java.lang.String" }
      F56@PR$TLV:
        - { "domainNo": 0x01, "name": "tag01", "fixed": false, "remark": "PAR", "clazz": "java.lang.String" }
      F57@AB:
        - { "domainNo": 1, "name": "addInfo", "fixed": true, "length": 20, "remark": "发卡方附加交易信息", "clazz": "java.lang.String" }
        - { "domainNo": 2, "name": "cupsAddInfo", "fixed": true, "length": 20, "remark": "CUPS附加交易信息", "clazz": "java.lang.String" }
        - { "domainNo": 3, "name": "reserved", "fixed": true, "length": 56, "remark": "保留使用", "clazz": "java.lang.String" }
      F59@QL:
        - { "domainNo": 1, "name": "number", "fixed": true, "length": 3, "remark": "当前明细顺序号", "clazz": "java.lang.Integer" }
      F59@QD:
        - { "domainNo": 1, "name": "number", "fixed": true, "length": 3, "remark": "当前明细顺序号", "clazz": "java.lang.Integer" }
        - { "domainNo": 2, "name": "beginDate", "fixed": true, "length": 8, "remark": "明细起始日期", "clazz": "java.time.LocalDate", "pattern": "yyyyMMdd" }
        - { "domainNo": 3, "name": "endDate", "fixed": true, "length": 8, "remark": "明细中止日期", "clazz": "java.time.LocalDate", "pattern": "yyyyMMdd" }
      F59@QR:
        - { "domainNo": 1, "name": "currencyCode", "fixed": true, "length": 3, "remark": "卡账户货币代码", "clazz": "java.lang.String" }
        - { "domainNo": 2, "name": "counts", "fixed": true, "length": 3, "remark": "所有满足查询条件的记录数", "clazz": "java.lang.Integer" }
        - { "domainNo": 3, "name": "elements", "fixed": false, "length": 50, "maxLength": 5000, "remark": "查询结果", "clazz": "java.util.ArrayList", "pageSizeName": "counts", "existSubdomain": true, "subdomainType": "LIST" }
      F59@QR_F3:
        - { "domainNo": 1, "name": "number", "fixed": true, "length": 3, "remark": "明细顺序号", "clazz": "java.lang.Integer" }
        - { "domainNo": 2, "name": "transDate", "fixed": true, "length": 8, "remark": "交易日期", "clazz": "java.time.LocalDate", "pattern": "yyyyMMdd" }
        - { "domainNo": 3, "name": "currencyCode", "fixed": true, "length": 3, "remark": "交易货币代码", "clazz": "java.lang.String" }
        - { "domainNo": 4, "name": "transAmt", "fixed": true, "length": 13, "remark": "交易金额", "clazz": "java.lang.String" }
        - { "domainNo": 5, "name": "balanceAmt", "fixed": true, "length": 13, "remark": "余额", "clazz": "java.lang.String" }
        - { "domainNo": 6, "name": "memoCode", "fixed": true, "length": 10, "remark": "备注代码", "clazz": "java.lang.String" }
      F61_F10@AR:
        - { "domainNo": 1, "name": "f61f10f1", "fixed": true, "length": 6, "remark": "SR流水号", "clazz": "java.lang.Integer" }
        - { "domainNo": 2, "name": "f61f10f2", "fixed": true, "length": 19, "remark": "验证服务时间", "clazz": "java.time.LocalDateTime", "pattern": "yyyy-MM-dd HH:mm:ss" }
        - { "domainNo": 3, "name": "f61f10f3", "fixed": true, "length": 1, "remark": "发卡机构认证结果", "clazz": "java.lang.Integer" }
      F61_F10@CR:
        - { "domainNo": 1, "name": "f61f10f1", "fixed": true, "length": 1, "remark": "CAVV校验结果代码", "clazz": "java.lang.String" }
      F62@IO$TV:
        - { "domainNo": 1, "name": "f62f1", "aliasName": "T00", "fixed": true, "length": 3, "remark": "国际信用卡公司/外资银行标志", "clazz": "java.lang.String" }
        - { "domainNo": 2, "name": "f62f2", "aliasName": "T07", "fixed": true, "length": 10, "remark": "交易日期时间", "clazz": "java.time.LocalDateTime", "pattern": "MMddHHmmss" }
        - { "domainNo": 3, "name": "f62f3", "aliasName": "T11", "fixed": true, "length": 6, "remark": "系统跟踪号码", "clazz": "java.lang.String" }
        - { "domainNo": 4, "name": "f62f4", "aliasName": "T18", "fixed": true, "length": 4, "remark": "商户类型", "clazz": "java.lang.String" }
        - { "domainNo": 5, "name": "f62f5", "aliasName": "T32", "fixed": true, "length": 11, "remark": "受理机构代码", "clazz": "java.lang.String" }
        - { "domainNo": 6, "name": "f62f6", "aliasName": "T33", "fixed": true, "length": 11, "remark": "发送机构标识码", "clazz": "java.lang.String" }
        - { "domainNo": 7, "name": "f62f7", "aliasName": "T39", "fixed": true, "length": 2, "remark": "响应码", "clazz": "java.lang.String" }
        - { "domainNo": 8, "name": "f62f8", "aliasName": "T37", "fixed": true, "length": 12, "remark": "检索参考号码", "clazz": "java.lang.String" }
        - { "domainNo": 9, "name": "f62f9", "aliasName": "T41", "fixed": true, "length": 8, "remark": "终端代码", "clazz": "java.lang.String" }
        - { "domainNo": 10, "name": "f62f10", "aliasName": "T42", "fixed": true, "length": 15, "remark": "商户代码", "clazz": "java.lang.String" }
        - { "domainNo": 11, "name": "f62f11", "aliasName": "T43", "fixed": true, "length": 40, "remark": "商户名称、地址", "clazz": "java.lang.String" }
        - { "domainNo": 12, "name": "f62f12", "aliasName": "T60", "fixed": true, "length": 7, "remark": "报文原因码", "clazz": "java.lang.String" }
      F63@SM:
        - { "domainNo": 1, "name": "pin", "fixed": true, "length": 16, "remark": "SM4算法加密的PIN数据", "clazz": "[B" }
      F63@TK$TLV:
        - { "domainNo": 0x01, "name": "tag01", "fixed": false, "remark": "是否验证过Token相关信息", "clazz": "java.lang.String" }
        - { "domainNo": 0x02, "name": "tag02", "fixed": false, "remark": "Token", "clazz": "java.lang.String" }
        - { "domainNo": 0x03, "name": "tag03", "fixed": false, "remark": "Token有效期", "clazz": "java.time.YearMonth", "pattern": "yyMM" }
        - { "domainNo": 0x04, "name": "tag04", "fixed": false, "remark": "Token担保级别", "clazz": "java.lang.String" }
        - { "domainNo": 0x05, "name": "tag05", "fixed": false, "remark": "Token应用场景标识", "clazz": "java.lang.Integer" }
        - { "domainNo": 0x06, "name": "tag06", "fixed": false, "remark": "TRID", "clazz": "java.lang.String" }
        - { "domainNo": 0x07, "name": "tag07", "fixed": false, "remark": "保留使用", "clazz": "[B" }
        - { "domainNo": 0x08, "name": "tag08", "fixed": false, "remark": "产品标识", "clazz": "[B" }
      F90:
        - { "domainNo": 1, "name": "messageType", "fixed": true, "length": 4, "remark": "原始交易的报文类型", "clazz": "java.lang.String" }
        - { "domainNo": 2, "name": "sysTraceAuditNum", "fixed": true, "length": 6, "remark": "原始系统跟踪号", "clazz": "java.lang.Integer" }
        - { "domainNo": 3, "name": "transmsnDateTime", "fixed": true, "length": 10, "remark": "原始交易传输时间", "clazz": "java.time.LocalDateTime", "pattern": "MMddHHmmss" }
        - { "domainNo": 4, "name": "acqInstIdCode", "fixed": true, "length": 11, "remark": "代理机构标识码", "clazz": "java.lang.String", "fieldType": "NUMBER" }
        - { "domainNo": 5, "name": "fwdInstIdCode", "fixed": true, "length": 11, "remark": "发送机构标识码", "clazz": "java.lang.String", "fieldType": "NUMBER" }
```

YAML文件命名

```text
cups.test.ios.yml
```

- **cups**：同`alatka.messages.group`取值一致
- **test**：同`alatka.messages.code`取值一致
- **iso**：同`alatka.messages.type`取值一致
- **yml**：文件后缀，指代YAML文件

**注：建议依据以上规则命名YAML文件，方便维护管理，但不强制要求（.iso.yml后缀除外）。**

##### 2.1.3.YAML属性注释
  
| 属性 |  备注 | 是否必填 | 默认值 |
| ---- | ---- | ------ | ------ |
|alatka.messages|根节点|/|/|
|alatka.messages.remark|接口备注|Y|/|
|alatka.messages.charset|报文字符集|N|GB18030|
|alatka.messages.type|报文类型，取值为fixed/iso|Y|/|
|alatka.messages.group|报文分组，指代机构代码|Y|/|
|alatka.messages.code|报文标识代码|Y|/|
|alatka.messages.message|报文域相关信息|/|/|
|alatka.messages.message.header|报文域头|/|/|
|alatka.messages.message.payload|报文域（8583）|/|/|
|alatka.messages.message.request|报文请求域（固定格式）|/|/|
|alatka.messages.message.response|报文应答域（固定格式）|/|/|
|alatka.messages.message.subPayload|报文子域|/|/|
|alatka.messages.message.subPayload.F[N]|N子域|/|/|
|alatka.messages.message.subPayload.F[N]@[U]|N子域usage|/|/|
|alatka.messages.message.subPayload.F[N]$[TLV/TV]|N子域TLV/TV|/|/|
|alatka.messages.message.subPayload.F[M]_F[N]|嵌套子域|/|/|
|alatka.messages.message.[header/request/response/payload/subPayload.F[N]*].domainNo|报文域号|Y|/|
|alatka.messages.message.[header/request/response/payload/subPayload.F[N]*].name|报文域名称，同一报文内部唯一|Y|/|
|alatka.messages.message.[header/request/response/payload/subPayload.F[N]*].fixed|报文域是否定长|Y|固定格式默认为true，8583无默认值|
|alatka.messages.message.[header/request/response/payload/subPayload.F[N]*].length|定长域中指数据字节长度，变长域指长度值字节长度，分页域指单页数据字节长度|Y|/|
|alatka.messages.message.[header/request/response/payload/subPayload.F[N]*].maxLength|变长域最大字节长度（8583）|N|/|
|alatka.messages.message.[header/request/response/payload/subPayload.F[N]*].remark|报文域备注|Y|/|
|alatka.messages.message.[header/request/response/payload/subPayload.F[N]*].status|状态|N|1|
|alatka.messages.message.[header/request/response/payload/subPayload.F[N]*].clazz|报文域数据类型|Y|/|
|alatka.messages.message.[header/request/response/payload/subPayload.F[N]*].pattern|报文域数据格式，日期类型域使用|N|/|
|alatka.messages.message.[header/request/response/payload/subPayload.F[N]*].existSubdomain|是否存在子域名|N|false|
|alatka.messages.message.[header/request/response/payload/subPayload.F[N]*].subdomainType|子域类型（UV ULV TLV LIST DEFAULT...）|N|/|
|alatka.messages.message.[header/request/response/payload/subPayload.F[N]*].parseType|解析方式，默认ASCII（ASCII BCD BINARY...）|N|ASCII|
|alatka.messages.message.[header/request/response/payload/subPayload.F[N]*].pageSizeName|分页域页数|N|/|

##### 2.1.4.YAML文件命名规则

**[alatka.messages.group].[alatka.messages.code].[alatka.messages.type].yml**

**注：建议依据以上规则命名YAML文件，方便维护管理，但不强制要求（.[alatka.messages.type].yml后缀除外）。**

#### 2.2.注解配置

##### 2.2.1.固定格式

```java

@MessageMeta(
        type = MessageDefinition.Type.fixed,
        group = "0305",
        code = "3006",
        kind = MessageDefinition.Kind.subPayload,
        remark = "信用卡账单明细查询")
public class Fixed3006Page {
    @FixedFieldMeta(domainNo = 1, length = 3, remark = "月份序号")
    private Integer monthNbr;
    @FixedFieldMeta(domainNo = 2, length = 8, remark = "入账日期", pattern = "yyyyMMdd")
    private LocalDate valDate;
    @FixedFieldMeta(domainNo = 3, length = 8, remark = "记录日期", pattern = "yyyyMMdd")
    private LocalDate purDate;
    @FixedFieldMeta(domainNo = 4, length = 8, remark = "消费时间", pattern = "HHmmssSS")
    private LocalTime purTime;
    @FixedFieldMeta(domainNo = 5, length = 6, remark = "交易流水号")
    private Integer tranNo;

    //....
    // getter setter
}
```

```java

@MessageMeta(
        type = MessageDefinition.Type.fixed,
        group = "0305",
        code = "3006",
        kind = MessageDefinition.Kind.response,
        remark = "信用卡账单明细查询")
public class Fixed3006Res extends FixedHeader {

    @FixedFieldMeta(domainNo = 8, length = 19, remark = "卡号")
    private String cardNbr;
    @FixedFieldMeta(domainNo = 9, length = 4, remark = "证件号码", pattern = "yyMM")
    private YearMonth tranYM;
    @FixedFieldMeta(domainNo = 10, length = 1, remark = "币种选项")
    private String option;
    @FixedFieldMeta(domainNo = 11, length = 2, remark = "本次返回交易条数")
    private Integer counts;
    @FixedFieldMeta(domainNo = 12, length = 1, remark = "翻页标志")
    private String rtnInd;
    @FixedFieldMeta(domainNo = 13, fixed = false, length = 141, remark = "集合", pageSizeName = "counts", existSubdomain = true, subdomainType = MessageDefinition.DomainType.LIST, parseType = FieldDefinition.ParseType.NONE)
    private List<Fixed3006Page> elements;

    // getter setter
}
```

```java

public class FixedHeader {

    @FixedFieldMeta(domainNo = 1, length = 4, remark = "交易代码")
    private String trxType;
    @FixedFieldMeta(domainNo = 2, length = 6, remark = "响应码")
    private String retCode;
    @FixedFieldMeta(domainNo = 3, length = 4, remark = "银行代号")
    private String bnkNbr;
    @FixedFieldMeta(domainNo = 5, length = 2, remark = "网点代号")
    private String source;
    @FixedFieldMeta(domainNo = 4, length = 6, remark = "交易来源")
    private String brnNo;
    @FixedFieldMeta(domainNo = 6, length = 6, remark = "操作员号")
    private String opeNo;
    @FixedFieldMeta(domainNo = 7, length = 6, remark = "流水号")
    private Integer seqNo;

    // getter setter...
}
```

##### 2.2.2.8583

```java

@MessageMeta(
        type = MessageDefinition.Type.iso,
        group = "cups",
        code = "test",
        header = IsoCupsHeader.class,
        kind = MessageDefinition.Kind.payload,
        remark = "银联8583报文体")
public class IsoCupsPayload {

    @IsoFieldMeta(domainNo = 0, fixed = true, length = 4, remark = "报文类型")
    private String messageType;
    @IsoFieldMeta(domainNo = 1, fixed = true, length = 16, remark = "位图", parseType = FieldDefinition.ParseType.BINARY)
    private Map<Integer, Boolean> bitmap;
    @IsoFieldMeta(domainNo = 2, fixed = false, length = 2, maxLength = 19, remark = "主账号")
    private String primaryAcctNum;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 6, remark = "交易处理码")
    private String processingCode;
    @IsoFieldMeta(domainNo = 4, fixed = true, length = 12, remark = "交易金额")
    private BigDecimal amtTrans;
    @IsoFieldMeta(domainNo = 12, fixed = true, length = 6, remark = "受卡方所在地时间", pattern = "HHmmss")
    private LocalTime timeLocalTrans;
    @IsoFieldMeta(domainNo = 13, fixed = true, length = 4, remark = "受卡方所在地日期", pattern = "MMdd")
    private MonthDay dateLocalTrans;
    @IsoFieldMeta(domainNo = 48, fixed = false, length = 3, maxLength = 512, remark = "附加数据——私有", existSubdomain = true, subdomainType = MessageDefinition.DomainType.UV)
    private UsageSubdomain<F48> addtnlDataPrivate;
    @IsoFieldMeta(domainNo = 53, fixed = true, length = 16, remark = "安全控制信息", existSubdomain = true, subdomainType = MessageDefinition.DomainType.DEFAULT)
    private F53 secRelatdCtrlInfo;
    @IsoFieldMeta(domainNo = 55, fixed = false, length = 3, maxLength = 255, remark = "IC卡数据域", existSubdomain = true, subdomainType = MessageDefinition.DomainType.TLV)
    private F55 iccData;
    @IsoFieldMeta(domainNo = 56, fixed = false, length = 3, maxLength = 512, remark = "附加信息", existSubdomain = true, subdomainType = MessageDefinition.DomainType.ULV)
    private UsageSubdomain<F56> addtnlData56;
    @IsoFieldMeta(domainNo = 59, fixed = false, length = 3, maxLength = 600, remark = "明细查询数据", existSubdomain = true, subdomainType = MessageDefinition.DomainType.UV)
    private UsageSubdomain<F59> detailInqrng;
    @IsoFieldMeta(domainNo = 61, fixed = false, length = 3, maxLength = 200, remark = "持卡人身份认证信息", existSubdomain = true, subdomainType = MessageDefinition.DomainType.DEFAULT)
    private F61 chAuthInfo;
    @IsoFieldMeta(domainNo = 62, fixed = false, length = 3, maxLength = 200, remark = "交换中心数据", existSubdomain = true, subdomainType = MessageDefinition.DomainType.UV)
    private UsageSubdomain<F62> switchingData;

    //.....
    // getter setter
}
```

```java

@MessageMeta(
        type = MessageDefinition.Type.iso,
        group = "cups",
        code = "test",
        kind = MessageDefinition.Kind.header,
        remark = "银联8583报文头")
public class IsoCupsHeader {

    @IsoFieldMeta(domainNo = 1, fixed = true, length = 1, remark = "头长度", parseType = FieldDefinition.ParseType.BINARY)
    private Integer headerLength;
    @IsoFieldMeta(domainNo = 2, fixed = true, length = 1, remark = "头标识和版本号", parseType = FieldDefinition.ParseType.BINARY)
    private String version;
    @IsoFieldMeta(domainNo = 3, fixed = true, length = 4, remark = "报文长度")
    private Integer messageLength;
    @IsoFieldMeta(domainNo = 4, fixed = true, length = 11, remark = "目的ID")
    private String destinationId;
    @IsoFieldMeta(domainNo = 5, fixed = true, length = 11, remark = "源ID")
    private String sourceId;
    @IsoFieldMeta(domainNo = 6, fixed = true, length = 3, remark = "保留使用", parseType = FieldDefinition.ParseType.NONE)
    private byte[] reserved;
    @IsoFieldMeta(domainNo = 7, fixed = true, length = 1, remark = "批次号", parseType = FieldDefinition.ParseType.BINARY)
    private String batchNum;
    @IsoFieldMeta(domainNo = 8, fixed = true, length = 8, remark = "交易信息")
    private String transInfo;
    @IsoFieldMeta(domainNo = 9, fixed = true, length = 1, remark = "用户信息", parseType = FieldDefinition.ParseType.BINARY)
    private String userInfo;
    @IsoFieldMeta(domainNo = 10, fixed = true, length = 5, remark = "拒绝码")
    private String rejectCode;
    // getter setter
}
```

```java

@MessageMeta(
        type = MessageDefinition.Type.iso,
        group = "cups",
        code = "test",
        kind = MessageDefinition.Kind.subPayload,
        domain = "F56",
        usage = "PR",
        domainType = MessageDefinition.DomainType.TLV,
        remark = "银联8583 56域usage=PR")
public class F56PR implements F56 {

    @IsoFieldMeta(domainNo = 0x01, fixed = false, remark = "PAR")
    private String tag01;
}
```

```java

public interface F56 {
}
```

##### 2.2.3.注解属性注释

|注解|属性|YAML属性映射|
|----|---|-----------|
|@MessageMeta|type|alatka.messages.type|
|@MessageMeta|group|alatka.messages.group|
|@MessageMeta|code|alatka.messages.code|
|@MessageMeta|kind|alatka.messages.message|
|@MessageMeta|domain|alatka.messages.message.subPayload.F[N]|
|@MessageMeta|usage|alatka.messages.message.subPayload.F[N]@[U]|
|@MessageMeta|domainType|alatka.messages.message.subPayload.F[N]$[TLV/TV]|
|@MessageMeta|remark|alatka.messages.remark|
|@MessageMeta|charset|alatka.messages.charset|
|@FixedFieldMeta/@IsoFieldMeta|domainNo|alatka.messages.message.[header/request/response/payload/subPayload.F[N]*].domainNo|
|@FixedFieldMeta/@IsoFieldMeta|pattern|alatka.messages.message.[header/request/response/payload/subPayload.F[N]*].pattern|
|@FixedFieldMeta/@IsoFieldMeta|fixed|alatka.messages.message.[header/request/response/payload/subPayload.F[N]*].fixed|
|@FixedFieldMeta/@IsoFieldMeta|length|alatka.messages.message.[header/request/response/payload/subPayload.F[N]*].length|
|@FixedFieldMeta/@IsoFieldMeta|maxLength|alatka.messages.message.[header/request/response/payload/subPayload.F[N]*].maxLength|
|@FixedFieldMeta/@IsoFieldMeta|existSubdomain|alatka.messages.message.[header/request/response/payload/subPayload.F[N]*].existSubdomain|
|@FixedFieldMeta/@IsoFieldMeta|subdomainType|alatka.messages.message.[header/request/response/payload/subPayload.F[N]*].subdomainType|
|@FixedFieldMeta/@IsoFieldMeta|parseType|alatka.messages.message.[header/request/response/payload/subPayload.F[N]*].parseType|
|@FixedFieldMeta/@IsoFieldMeta|pageSizeName|alatka.messages.message.[header/request/response/payload/subPayload.F[N]*].pageSizeName|
|@FixedFieldMeta/@IsoFieldMeta|remark|alatka.messages.message.[header/request/response/payload/subPayload.F[N]*].remark|
|@FixedFieldMeta/@IsoFieldMeta|status|alatka.messages.message.[header/request/response/payload/subPayload.F[N]*].status|

#### 2.3.数据库配置
TODO

### 3.报文配置解析

报文配置解析类

|    | 8583 | 固定格式 |
|----|------| ------ |
|YAML|IsoYamlMessageDefinitionBuilder|FixedYamlMessageDefinitionBuilder|
|注解|IsoAnnotationMessageDefinitionBuilder|FixedAnnotationMessageDefinitionBuilder|
|数据库|IsoDatabaseMessageDefinitionBuilder|FixedDatabaseMessageDefinitionBuilder|

#### 3.1.YAML配置方式

```java
String classpath=""; // YAML配置文件所在classpath，“”指当前classes目录
MessageDefinitionBuilder builder=new IsoYamlMessageDefinitionBuilder(classpath);
builder.build();
```

#### 3.2.注解配置方式

```java
String packagePath="com.xxx.yyy"; // 注解类所在包路径
MessageDefinitionBuilder builder=new IsoAnnotationMessageDefinitionBuilder(packagePath);
builder.build();
```

#### 3.3.数据库配置方式

```java
MessageDefinitionBuilder builder=new IsoDatabaseMessageDefinitionBuilder();
builder.build();
```

### 4.报文打包解包

- 8583/固定格式解析方式相同；
- YAML配置方式对应报文容器为MessageHolder，注解方式对应其POJO。

#### 4.1.固定格式

```java
// 解包
String reqKey="fixed:0802:3006:request"; // [type]:[group]:[code]:[kind] 唯一标识一类报文
[Fixed3006Req|MessageHolder]req=MessageBuilder.init(reqKey).unpack(reqBytes); // 解包为对应实体

// 打包
[Fixed3006Res|MessageHolder]res=null; // 响应实体
String resKey="fixed:0802:3006:response"; // [type]:[group]:[code]:[kind] 唯一标识一类报文
byte[]resBytes=MessageBuilder.init(resKey).pack(res); // 打包响应实体为字节数组
```

#### 4.2.8583

```java
// 解包
String key="iso:cups:test:payload"; // [type]:[group]:[code]:[kind] 唯一标识一类报文
[IsoCupsPayload|MessageHolder]req=MessageBuilder.init(key).unpack(reqBytes); // 解包为对应实体

// 打包
[IsoCupsPayload|MessageHolder]res=null;
byte[]resBytes=MessageBuilder.init(key).pack(res); // 打包响应实体为字节数组
```

## 四、进阶知识

### 1.YAML/注解/数据库配置方式使用场景

- YAML文件具有动态加载功能，工具包提供在线刷新方法，可手动触发或与第三方配置中心服务集成，可以做到配置热更新； 所以YAML方式适合交易转发类应用，修改配置无需重启；
- 注解方式语义清晰，适合终端系统对报文进行处理；
- 数据库配置方式同YAML文件配置方式优势基本相同，但数据库方式格式语义更强，可同后台管理系统集成，通过页面进行维护。

### 2.YAML/注解配置方式与报文容器对应关系

前文提到YAML方式对应自定义容器MessageHolder，注解方式对应其配置POJO；以上为默认映射关系，特殊场景下可手动进行变换。

#### 2.1.YAML方式指定POJO

```yaml
alatka.messages:
  remark: 信用卡账单明细查询
  type: fixed
  group: "0305"
  code: 3006
  holder:
    request: com.xxx.yyy.POJO1
    response: com.xxx.yyy.POJO2
    subPayload:
      F13: com.xxx.yyy.POJO3
  .....
```

#### 2.2.注解方式指定MessageHolder

```java

@MessageMeta(
        type = MessageDefinition.Type.fixed,
        group = "0305",
        code = "3006",
        kind = MessageDefinition.Kind.response,
        customize = true, // true: MessageHolder, false: 当前POJO
        remark = "信用卡账单明细查询")
public class Fixed3006Res extends FixedHeader {

    @FixedFieldMeta(domainNo = 8, length = 19, remark = "卡号")
    private String cardNbr;

    // getter setter
}
```

### 3.报文配置内存优化

报文配置信息在`MessageDefinitionBuilder#build()`方法执行后全部加载到内存中，在交易接口较多的场景下，内存开销较多；TODO

### 子域配置
TODO
### 注意事项

- 报文域类型不支持java原始类型（int/byte/long/short/boolean...）：原始类型有默认值，会影响报文域取值和赋值；
- TODO

## 五、内部原理

### 报文结构

报文由六部分组成唯一标识：**报文类型**/**报文分组**/**报文代码**/**报文种类**/**[报文子域]**/**[报文子域使用标识]**；其中 **[报文子域]**/**[报文子域使用标识]** 非必须存在。

1. **报文类型**：iso（8583）/fixed（固定格式）
2. **报文分组**：机构标识；例如visa、master、cups、jcb、各银行机构等
3. **报文代码**：例如固定格式交易3004（按卡号查询卡资料信息）、4101（自选卡号选择）等
4. **报文种类**：header（报文头）、request（请求报文）、response（应答报文）、payload（报文体）、subPayload（报文子域）
5. **报文子域**：分页子域、8583各类子域等
6. **报文子域使用标识**：8583 ULV/UV类型子域usage值；例如F48域AA/BC/NK，F59域QL/QD/QR 等

## 六、TODO

- header处理
- 类/方法范围
- 单元测试
- 字段域
    - 第二磁道 第三磁道 第一磁道类型
    - 未配置子域tag情况