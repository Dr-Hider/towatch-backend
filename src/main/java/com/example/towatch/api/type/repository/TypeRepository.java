package com.example.towatch.api.type.repository;

import com.example.towatch.api.type.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {
    boolean existsByName(String name);
}
