package com.example.towatch.api.status.repository;

import com.example.towatch.api.status.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
    public boolean existsByNameIgnoreCase(String name);
}
