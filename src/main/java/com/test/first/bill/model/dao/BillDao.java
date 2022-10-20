package com.test.first.bill.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.first.bill.model.vo.Bill;

@Repository("billDao")
public class BillDao {

	@Autowired
	private SqlSessionTemplate session;
	
	public int insertBill(Bill bill) {
		return session.insert("billMapper.insertBill", bill);
	}
}
