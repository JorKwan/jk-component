关系数据库
============ 
mybatis-plus封装
- 支持多数据源自动切换
- 支持预置建库脚本
- 提供树型内部码生成工具

说明
---------------
多数据源
* 配置文件参考：com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties
* [配置文件示例](https://github.com/dynamic-datasource/dynamic-datasource-doc/blob/master/docs/guide/integration/Druid.md)
```
spring:
  datasource:
    druid:
      stat-view-servlet:
        enabled: true
        loginUsername: admin
        loginPassword: 123456
    dynamic:
      druid: #以下是支持的全局默认值
        initial-size:
        max-active:
        min-idle:
        max-wait:
        time-between-eviction-runs-millis:
        time-between-log-stats-millis:
        stat-sqlmax-size:
        min-evictable-idle-time-millis:
        max-evictable-idle-time-millis:
        test-while-idle:
        test-on-borrow:
        test-on-return:
        validation-query:
        validation-query-timeout:
        use-global-datasource-stat:
        async-init:
        clear-filters-enable:
        reset-stat-enable:
        not-full-timeout-retry-count:
        max-wait-thread-count:
        fail-fast:
        phyTimeout-millis:
        keep-alive:
        pool-prepared-statements:
        init-variants:
        init-global-variants:
        use-unfair-lock:
        kill-when-socket-read-timeout:
        connection-properties:
        max-pool-prepared-statement-per-connection-size:
        init-connection-sqls:
        share-prepared-statements:
        connection-errorretry-attempts:
        break-after-acquire-failure:
        filters: stat,wall # 注意这个值和druid原生不一致，默认启动了stat,wall
        wall:
            noneBaseStatementAllow:
            callAllow:
            selectAllow:
            selectIntoAllow:
            selectIntoOutfileAllow:
            selectWhereAlwayTrueCheck:
            selectHavingAlwayTrueCheck:
            selectUnionCheck:
            selectMinusCheck:
            selectExceptCheck:
            selectIntersectCheck:
            createTableAllow:
            dropTableAllow:
            alterTableAllow:
            renameTableAllow:
            hintAllow:
            lockTableAllow:
            startTransactionAllow:
            blockAllow:
            conditionAndAlwayTrueAllow:
            conditionAndAlwayFalseAllow:
            conditionDoubleConstAllow:
            conditionLikeTrueAllow:
            selectAllColumnAllow:
            deleteAllow:
            deleteWhereAlwayTrueCheck:
            deleteWhereNoneCheck:
            updateAllow:
            updateWhereAlayTrueCheck:
            updateWhereNoneCheck:
            insertAllow:
            mergeAllow:
            minusAllow:
            intersectAllow:
            replaceAllow:
            setAllow:
            commitAllow:
            rollbackAllow:
            useAllow:
            multiStatementAllow:
            truncateAllow:
            commentAllow:
            strictSyntaxCheck:
            constArithmeticAllow:
            limitZeroAllow:
            describeAllow:
            showAllow:
            schemaCheck:
            tableCheck:
            functionCheck:
            objectCheck:
            variantCheck:
            mustParameterized:
            doPrivilegedAllow:
            dir:
            tenantTablePattern:
            tenantColumn:
            wrapAllow:
            metadataAllow:
            conditionOpXorAllow:
            conditionOpBitwseAllow:
            caseConditionConstAllow:
            completeInsertValuesCheck:
            insertValuesCheckSize:
            selectLimit:
        stat:
          merge-sql:
          log-slow-sql:
          slow-sql-millis: 
      datasource:
        master:
          username: root
          password: 123456
          driver-class-name: com.mysql.jdbc.Driver
          url: jdbc:mysql://xx.xx.xx.xx:3306/dynamic?characterEncoding=utf8&useSSL=false
          druid: # 以下是独立参数，每个库可以重新设置
            initial-size:
            validation-query: select 1 FROM DUAL #比如oracle就需要重新设置这个
            public-key: #（非全局参数）设置即表示启用加密,底层会自动帮你配置相关的连接参数和filter，推荐使用本项目自带的加密方法。
#           ......

# 生成 publickey 和密码，推荐使用本项目自带的加密方法。
# java -cp druid-1.1.10.jar com.alibaba.druid.filter.config.ConfigTools youpassword
```

预置建库脚本
* [参考配置](https://github.com/dynamic-datasource/dynamic-datasource-doc/blob/master/docs/guide/advance/Init-Schema-Data.md)
* 脚本文件放在resources目录，在默认数据源中增加配置：
  ```
  spring:
    datasource:
      dynamic:
        primary: order
        datasource:
          order:
            # 基础配置省略...
            schema: db/order/schema.sql # 配置则生效,自动初始化表结构。
            data: db/order/data.sql # 配置则生效,自动初始化数据。
            continue-on-error: true # 默认true,初始化失败是否继续
            separator: ";" # sql默认分号分隔符，一般无需更改
  ```
* 注意事项：
  - schema： 建立连接时会执行，因此建表应使用CREATE TABLE IF NOT EXISTS确保不会重复创建。
  - data: 建立连接时会执行，应确保数据不会重复插入。

树型内部码工具
* 可对实现ITreeEntity的model自动生成innerCode内部码。
	
自定义日期类型转换
* 全局处理日期类型转换char，需要增加配置
  ```
  mybatis-plus:
    typeHandlersPackage: com.persagy.**.typehandler
  ```

最新变化
---------------

