package com.elmaddin.TaskForWork.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookSaveRequest {
    private String name;
    private int price;
    private String author;
    private int pageCount;
}
