package com.backend.elastic.product.model.elastic;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "products")
public class ProductElasticEntity {
    @Id
    private String id;
    @Field(type = FieldType.Text, analyzer = "synonym_analyzer")
    private String name;
    @Field(type = FieldType.Text)
    private String description;
    @Field(type = FieldType.Integer)
    private int price;
    @Field(type = FieldType.Keyword)
    private String category;
    @Field(type = FieldType.Keyword)
    private String brand;
    @Field(type = FieldType.Keyword)
    private String currency;
    @Field(type = FieldType.Integer)
    private int stock;
    @Field(type = FieldType.Keyword)
    private String ean;
    @Field(type = FieldType.Keyword)
    private String color;
    @Field(type = FieldType.Keyword)
    private String size;
    @Field(type = FieldType.Keyword)
    private String availability;
    @Field(type = FieldType.Integer)
    private int internalId;

    public ProductElasticEntity() {
    }

    public ProductElasticEntity(String name, String description, int price, String category, String brand, String currency,
                         int stock, String ean, String color, String size, String availability, int internalId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.brand = brand;
        this.currency = currency;
        this.stock = stock;
        this.ean = ean;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public int getInternalId() {
        return internalId;
    }

    public void setInternalId(int internalId) {
        this.internalId = internalId;
    }
}
