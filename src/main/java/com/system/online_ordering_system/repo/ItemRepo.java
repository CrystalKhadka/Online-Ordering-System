package com.system.online_ordering_system.repo;

import com.system.online_ordering_system.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepo extends JpaRepository<Item, Integer> {

}
