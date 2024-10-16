package com.example.demo;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>
 * 测试生成代码
 * </p>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("local")
public class GeneratorServiceEntity_Order {

    private String datasourceUrl;

    private String username;

    private String password;

    public static void main(String[] args) {
        String packageName = "com.example.demo.code_generator";
        boolean serviceNameStartWithI = false;//user -> UserService, 设置成true: user -> IUserService
        String moduleName = "";
        GeneratorServiceEntity_Order entity = new GeneratorServiceEntity_Order();
        entity.initParam();
        String[] tables = {
                "o_package_rule_relation"
        };
        entity.generateByTables(serviceNameStartWithI, packageName, moduleName,
                tables);
    }

    private void initParam() {
        password = "root";
        username = "root";
        datasourceUrl = "jdbc:mysql://10.124.68.142:3306/qhcx_order?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=true&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&tinyInt1isBit=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true";
    }

    private void generateByTables(boolean serviceNameStartWithI, String packageName, String moduleName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(datasourceUrl)
                .setUsername(username)
                .setPassword(password)
                .setDriverName("com.mysql.cj.jdbc.Driver");
        String projectPath = System.getProperty("user.dir");
        String outputDir = projectPath + "/src/test/java";

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(true)
                //.setDbColumnUnderline(true)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setNaming(NamingStrategy.underline_to_camel)
                .setTablePrefix("o")
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
        config.setActiveRecord(false)
                .setAuthor("yangchi")
                .setOutputDir(outputDir)
                //.setSwagger2(true)
                .setDateType(DateType.ONLY_DATE)
                .setFileOverride(true)
                .setBaseResultMap(true)
                .setBaseColumnList(true);
        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller" + moduleName)
                                .setEntity("entity" + moduleName)
                                .setMapper("mapper" + moduleName)
                                .setService("service" + moduleName)
                                .setServiceImpl("service.impl" + moduleName)
                                .setXml("mapper" + moduleName)
                ).execute();
    }
}
