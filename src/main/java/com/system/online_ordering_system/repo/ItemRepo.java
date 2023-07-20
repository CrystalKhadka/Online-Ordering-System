package com.system.online_ordering_system.repo;

import com.system.online_ordering_system.dto.ItemDto;
import com.system.online_ordering_system.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepo extends JpaRepository<Item, Integer> {

    @Query(value="select * from items where item_id = ?1", nativeQuery = true)
    Optional<Item> findByIdNoOpt(int id);


        // Other methods...


    @Query(value="select item_image from items", nativeQuery = true)
   List<String> findAllImages();

    @Query(value="select item_resize_image from items", nativeQuery = true)
    List<String> findAllResizeImages();

    @Query(value = "select * from items order by item_name offset ?1 limit 3", nativeQuery = true)
    List<Item> findThreeItemsByNameAsc(int offset);

    @Query(value = "select * from items order by item_name desc offset ?1 limit 3", nativeQuery = true)
    List<Item> findThreeItemsByNameDesc(int offset);

    @Query(value = "select * from items order by item_price offset ?1 limit 3", nativeQuery = true)
    List<Item> findThreeItemsByPriceAsc(int offset);

    @Query(value = "select * from items order by item_price desc offset ?1 limit 3", nativeQuery = true)
    List<Item> findThreeItemsByPriceDesc(int offset);

    @Query(value = "select * from items order by item_id  offset ?1 limit 3", nativeQuery = true)
    List<Item> findThreeItemsByItemIdAsc(int offset);

    @Query(value = "select * from items order by item_id desc offset ?1 limit 3", nativeQuery = true)
    List<Item> findThreeItemsByItemIdDesc(int offset);
}
