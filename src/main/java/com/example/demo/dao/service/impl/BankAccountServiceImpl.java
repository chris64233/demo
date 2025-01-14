package com.example.demo.dao.service.impl;

import com.example.demo.dao.entity.BankAccount;
import com.example.demo.dao.mapper.BankAccountMapper;
import com.example.demo.dao.service.BankAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author yangchi
* @since 2024-12-10
*/
@Service
public class BankAccountServiceImpl extends ServiceImpl<BankAccountMapper, BankAccount> implements BankAccountService {

}
