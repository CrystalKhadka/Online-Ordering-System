package com.system.online_ordering_system.repo;

import com.system.online_ordering_system.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepo extends JpaRepository<Bill, Integer> {
    @Query(value = "select * from bill where user_id=?1", nativeQuery = true)
    List<Bill> findAllByUser(int id);
}
