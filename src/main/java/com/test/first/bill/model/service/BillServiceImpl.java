package com.test.first.bill.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.first.bill.model.dao.BillDao;
import com.test.first.bill.model.vo.Bill;

@Service("billService")
public class BillServiceImpl implements BillService{
	@Autowired
	private BillDao billDao;

	@Override
	public int insertBill(Bill bill) {
		return billDao.insertBill(bill);
	}
	
	
}
