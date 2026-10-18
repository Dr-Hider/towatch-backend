package com.example.towatch.type.repository;

import com.example.towatch.type.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {
    boolean existsByName(String name);
}
