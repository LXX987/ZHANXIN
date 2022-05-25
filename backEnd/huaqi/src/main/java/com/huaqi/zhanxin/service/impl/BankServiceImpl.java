package com.huaqi.zhanxin.service.impl;

import com.huaqi.zhanxin.entity.BankSearch;
import com.huaqi.zhanxin.mapper.BankMapper;
import com.huaqi.zhanxin.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImpl implements BankService {
    @Autowired
    BankMapper bankMapper;
    @Override
    public List<BankSearch> bankSearchList() {
        return bankMapper.bankSearchList();
    }

    @Override
    public List<BankSearch> bankSearchID(int userID) {
        return bankMapper.bankSearchID(userID);
    }

    @Override
    public List<BankSearch> bankSearchName(String userName) {
        return bankMapper.bankSearchName(userName);
    }
}
