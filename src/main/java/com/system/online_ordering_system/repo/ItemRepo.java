package com.system.online_ordering_system.repo;

import com.system.online_ordering_system.dto.ItemDto;
import com.system.online_ordering_system.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepo extends JpaRepository<Item, Integer> {

    @Query(value="select * from items where item_id = ?1", nativeQuery = true)
    Optional<Item> findByIdNoOpt(int id);

   @Query(value="select * from items order by item_id limit 3 offset ?1 ", nativeQuery = true)
    List<Item>  getThreeItems(int page);

    @Query(value="select item_image from items", nativeQuery = true)
   List<String> findAllImages();

    @Query(value="select item_resize_image from items", nativeQuery = true)
    List<String> findAllResizeImages();
}
