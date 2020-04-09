package com.mai.projects.plm.repository;

import com.mai.projects.plm.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
