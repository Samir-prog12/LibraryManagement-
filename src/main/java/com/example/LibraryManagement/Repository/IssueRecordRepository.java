package com.example.LibraryManagement.Repository;

import com.example.LibraryManagement.Entity.IssueRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRecordRepository extends JpaRepository<IssueRecord,Long> {
}
