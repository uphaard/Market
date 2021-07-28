package com.example.market2.repository;

import com.example.market2.entity.TransactionalLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionalLogsRepository extends JpaRepository<TransactionalLogs,Integer> {

}
