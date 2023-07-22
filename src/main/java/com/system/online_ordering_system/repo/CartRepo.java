package com.system.online_ordering_system.repo;

import com.system.online_ordering_system.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer>{
}
