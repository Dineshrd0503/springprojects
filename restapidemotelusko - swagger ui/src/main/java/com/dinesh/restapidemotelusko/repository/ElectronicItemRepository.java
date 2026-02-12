package com.dinesh.restapidemotelusko.repository;

import com.dinesh.restapidemotelusko.model.ElectronicItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectronicItemRepository extends JpaRepository<ElectronicItem, Long> {
}
