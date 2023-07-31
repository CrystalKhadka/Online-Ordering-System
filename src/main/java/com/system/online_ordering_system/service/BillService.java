package com.system.online_ordering_system.service;

import com.system.online_ordering_system.entity.Bill;

import java.util.List;

public interface BillService {

    void generateBill();

    List<Bill> getAllBillsByUser(int id);

    List<Bill> getBillForTenDays();
}
