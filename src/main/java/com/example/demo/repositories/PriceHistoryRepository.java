package com.example.demo.repositories;

import com.example.demo.entities.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceHistoryRepository extends JpaRepository<PriceHistory, Integer> {
}