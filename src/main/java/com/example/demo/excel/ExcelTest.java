package com.example.demo.excel;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
@RequestMapping("/excel")
public class ExcelTest {

    @Resource
    private SysUserExcelExport sysUserExcelExport;


    @RequestMapping("/test")
    public void test() {
        sysUserExcelExport.exportWithBigData("test", new HashMap<>());

    }
}
