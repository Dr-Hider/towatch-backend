package com.example.towatch.api.item.repository;

import com.example.towatch.api.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByUserId(Long userId);
    boolean existsByTitleIgnoreCaseAndUserId(String title, Long userId);
    boolean existsByIdAndUserId(Long id, Long userId);
}
