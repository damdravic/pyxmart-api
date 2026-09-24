package ro.pyxsmart.api.models;

import lombok.Builder;
import lombok.Data;


import java.time.LocalDateTime;

@Data
@Builder
public class Product {

    private Long id;

    // =========================
    // IDENTITY
    // =========================

    private String name;
    private String code;
    private String slug;

    // =========================
    // DESCRIPTIONS
    // =========================

    private String shortDescription;
    private String description;


    // =========================
    // SORT
    // =========================

    private Long categoryId;
    private Long brandId;


    // =========================
    // STATUS
    // =========================


    private ProductStatus status;

    private boolean featured;


    // =========================
    // SEO
    // =========================

    private String metaTitle;
    private String metaDescription;
    private String metaKeywords;


    // =========================
    // DATES
    // =========================

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;
}
