package com.example.towatch.api.item.repository;

import com.example.towatch.api.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
