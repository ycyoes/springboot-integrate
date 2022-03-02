package com.turing.springbootintegrate.service.impl;

import com.turing.springbootintegrate.dao.ZyAccountMapper;
import com.turing.springbootintegrate.model.ZyAccount;
import com.turing.springbootintegrate.model.ZyUserDHRel;
import com.turing.springbootintegrate.service.ZyAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ZyAccountServiceImpl implements ZyAccountService {

	@Autowired
	private ZyAccountMapper zyAccountMapper;


	@Override
	public List<ZyAccount> getPendingAccount(String roleName) {
		return zyAccountMapper.getPendingAccount(roleName);
	}

    @Override
    public int updateStatus(String duanHao) {
        return zyAccountMapper.updateStatus(duanHao);
    }

    @Override
    public int insertUserDHRel(ZyUserDHRel zyUserDHRel) {
        return zyAccountMapper.insertUserDHRel(zyUserDHRel);
    }
}
