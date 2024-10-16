package com.example.demo.code_generator.service.impl;

import com.example.demo.code_generator.entity.BankAccount;
import com.example.demo.code_generator.mapper.BankAccountMapper;
import com.example.demo.code_generator.service.BankAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 银行账号信息表 服务实现类
 * </p>
 *
 * @author yangchi
 * @since 2024-10-15
 */
@Service
public class BankAccountServiceImpl extends ServiceImpl<BankAccountMapper, BankAccount> implements BankAccountService {

}
