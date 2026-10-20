package com.example.LibraryManagement.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private String isbn;

    private Integer quantity;

    private Boolean isAvailable;



    @OneToMany(mappedBy = "book")
  private List<IssueRecord> issueRecordList = new ArrayList<IssueRecord>();
}
