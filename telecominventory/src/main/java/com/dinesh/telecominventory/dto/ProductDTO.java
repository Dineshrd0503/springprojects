package com.dinesh.telecominventory.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String type;
    private int quantity;
    private String location;
    private String status;
}