package com.example.demo.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * mybatis-plus代码生成器，生成实体，mapper，mapper.xml，service，serviceImpl，controller
 * 演示例子，执行 main 方法控制台输入表名回车自动生成对应项目目录中(目录要需要自行修改)
 */
public class MybatisPlusGenerator {

    public static void main(String[] args) {
        //====================配置变量区域=====================//
        String author = "yangchi";//生成文件的作者，可以不填
        String rootPackage = "com.example.demo.dao";//生成的entity、controller、service等包所在的公共上一级包路径全限定名
        String moduleName = "";
        //数据库配置
//        String url = "jdbc:mysql://127.0.0.1:3306/iot_cabinet?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false";
        String url = "jdbc:mysql://10.124.67.226:3309/qhcx_user?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false";
        String driverClassName = "com.mysql.jdbc.Driver";//或者com.mysql.cj.jdbc.Driver
        String username = "root";
//        String password = "12345678";
        String password = "root";
        String[] tableNames = {
                "pay_allocation_rule",
                "pay_allocation_rule_detail",
                "pay_merchant_trade_setting",
                "pay_merchant_account",
                "u_maintainer_cabinet_setting",
                "pay_corp_info",
                "o_rent_order_log",
                "pay_bank_account"};//表名
        //====================配置变量区域=====================//

        // 代码生成器
        AutoGenerator generator = new AutoGenerator();
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(projectPath + "/" + moduleName + "/src/main/java");
        globalConfig.setFileOverride(true);//是否覆盖已有文件，默认false
        globalConfig.setOpen(false);//是否打开输出目录
        globalConfig.setAuthor(author);
        globalConfig.setServiceName("%sService");//去掉service接口的首字母
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setBaseResultMap(true);//开启 BaseResultMap
        globalConfig.setBaseColumnList(true);
        globalConfig.setDateType(DateType.ONLY_DATE);//只使用 java.util.date代替
        globalConfig.setIdType(IdType.ASSIGN_ID);//分配ID (主键类型为number或string）
        generator.setGlobalConfig(globalConfig);
        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(url);
        dataSourceConfig.setDbType(DbType.MYSQL);//数据库类型
        dataSourceConfig.setDriverName(driverClassName);
        dataSourceConfig.setUsername(username);
        dataSourceConfig.setPassword(password);
        generator.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
//        packageConfig.setModuleName(scanner("模块名"));
        packageConfig.setParent(rootPackage);//例：org.jeecg.modules.xqxy
        //自定义实体包名(不同的模块自己手动修改)
        packageConfig.setEntity("entity");
        //自定义mapper包名(不同的模块自己手动修改)
        packageConfig.setMapper("mapper");
        //自定义mapper.xml包名(不同的模块自己手动修改)
        packageConfig.setXml("");
        //自定义service包名(不同的模块自己手动修改)
        packageConfig.setService("service");
        //自定义serviceImpl包名(不同的模块自己手动修改)
        packageConfig.setServiceImpl("service.impl");
        //自定义controller包名(不同的模块自己手动修改)
        packageConfig.setController("controller");
        generator.setPackageInfo(packageConfig);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };

        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();


        // 调整 xml生成目录演示
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/resources/mapper/" + moduleName
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileCreate((configBuilder, fileType, filePath) -> {
            //如果是Entity则直接返回true表示写文件
            if (fileType == FileType.ENTITY) {
                return true;
            }
            //否则先判断文件是否存在
            File file = new File(filePath);
            boolean exist = file.exists();
            if (!exist) {
                file.getParentFile().mkdirs();
            }
            //文件不存在或者全局配置的fileOverride为true才写文件
            return !exist || configBuilder.getGlobalConfig().isFileOverride();
        });
        cfg.setFileOutConfigList(focList);
        generator.setCfg(cfg);


        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        //控制 不生成 controller
//        templateConfig.setController("");
        templateConfig.setXml("");
        generator.setTemplate(templateConfig);

        //注意：模板引擎在mybatisplus依赖中的templates目录下，可以依照此默认模板进行自定义

        // 策略配置：配置根据哪张表生成代码
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude(tableNames);//表名，多个英文逗号分割（与exclude二选一配置）
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);//lombok模型，@Accessors(chain = true)setter链式操作
        strategy.setRestControllerStyle(true);//controller生成@RestController
        //strategy.setEntityTableFieldAnnotationEnable(true);//是否生成实体时，生成字段注解
        // 去除表前缀
        strategy.setTablePrefix("u_");
        strategy.setEntitySerialVersionUID(false);
        generator.setStrategy(strategy);
        generator.setTemplateEngine(new FreemarkerTemplateEngine());
        generator.execute();
    }

}
