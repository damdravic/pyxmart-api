package ro.pyxsmart.api.models;

import lombok.Data;

@Data
public class Product {

    private Long id;

    // =========================
    // IDENTITATE
    // =========================

    private String name;
    private String code;
    private String model;
    private String slug;
    private String manufacturerCode;
    private String manufacturerPartNumber;
    private String ean;
    private String gtin;

    // =========================
    // DESCRIERE
    // =========================

    private String shortDescription;
    private String description;
    private String technicalDescription;

    // =========================
    // ORGANIZARE
    // =========================

    private Category category;
    private Brand brand;


    // =========================
    // STATUS
    // =========================


    private ProductStatus status;
    private boolean active;
    private boolean featured;
    private boolean visible;

    // =========================
    // SEO
    // =========================

    private String metaTitle;
    private String metaDescription;
    private String metaKeywords;

    // =========================
    // LIVRARE / DIMENSIUNI
    // =========================

    private BigDecimal weight;
    private BigDecimal length;
    private BigDecimal width;
    private BigDecimal height;
    private BigDecimal packageWeight;
    private BigDecimal packageLength;
    private BigDecimal packageWidth;
    private BigDecimal packageHeight;

    // =========================
    // GARANȚIE
    // =========================

    private Integer warrantyMonths;

    // =========================
    // TVA / FISCAL
    // =========================

    private BigDecimal vatRate;

    // =========================
    // TIMPURI
    // =========================

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;
}
