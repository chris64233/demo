package com.example.demo.excel;

import com.jingdianjichi.user.entity.po.SysUser;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 商品数据导出
 *
 * @author: ChickenWing
 * @date: 2023/3/5
 */
@Component
public class SysUserExcelExport extends BaseEasyExcelExport<com.jingdianjichi.user.entity.po.SysUser> {


    /**
     * 数据导出
     */
    public void exportWithBigData(String fileName, Map<String, Object> conditions) {
        this.exportExcel(fileName, conditions);
    }

    @Override
    protected List<List<String>> getExcelHead() {
        List<List<String>> head = new ArrayList<>();
        head.add(Collections.singletonList("用户编号"));
        head.add(Collections.singletonList("用户姓名"));
        return head;
    }

    @Override
    protected Long dataTotalCount(Map<String, Object> conditions) {
        return 100L;
    }

    @Override
    protected Long eachSheetTotalCount() {
        return 5000L;
    }

    @Override
    protected Long eachTimesWriteSheetTotalCount() {
        return 2000L;
    }

    @Override
    protected void buildDataList(List<List<String>> resultList, Map<String, Object> condition,
                                 Long pageNo, Long pageSize) {
        List<com.jingdianjichi.user.entity.po.SysUser> sysUserList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SysUser sysUser = new SysUser();
            sysUser.setId((long) i);
            sysUser.setName("i");
            sysUserList.add(sysUser);
        }
        if (CollectionUtils.isNotEmpty(sysUserList)) {
            sysUserList.forEach(sysUser -> {
                resultList.add(Arrays.asList(sysUser.getId().toString(), sysUser.getName()));
            });
        }
    }

}
