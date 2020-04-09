package com.mai.projects.plm.repository;

import com.mai.projects.plm.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
