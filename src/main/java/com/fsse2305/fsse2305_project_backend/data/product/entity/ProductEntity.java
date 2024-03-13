package com.fsse2305.fsse2305_project_backend.data.product.entity;


import com.fsse2305.fsse2305_project_backend.data.cartItem.entity.CartItemEntity;
import com.fsse2305.fsse2305_project_backend.data.product.domainObject.CreateProductData;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product" )
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;
    @Column(nullable = false)
    private String name;
    @Lob
    @Column(columnDefinition="TEXT")
    private String description;
    @Column(name= "stripe_price_id")
    private String priceId;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "image_url_2")
    private String imageUrl2;
    @Column(name = "image_url_3")
    private String imageUrl3;
    @Column(name = "image_url_4")
    private String imageUrl4;
    @Column(name = "image_url_5")
    private String imageUrl5;
    @Column(name = "image_url_6")
    private String imageUrl6;
    @Column(name = "image_url_7")
    private String imageUrl7;
    @Column(name = "image_url_8")
    private String imageUrl8;
    @Column(name = "image_url_9")
    private String imageUrl9;
    @Column(name = "image_url_10")
    private String imageUrl10;

    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Integer stock;

    @OneToMany(mappedBy = "product")
    @Column(nullable = false)
    private List<CartItemEntity> cartItemBelonging = new ArrayList<>();

    public ProductEntity() {
    }

    public ProductEntity(CreateProductData data) {
        this.name = data.getName();
        this.description = data.getDescription();
        this.imageUrl = data.getImageUrl();
        this.price = data.getPrice();
        this.stock = data.getStock();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }

    public String getImageUrl2() {
        return imageUrl2;
    }

    public void setImageUrl2(String imageUrl2) {
        this.imageUrl2 = imageUrl2;
    }

    public String getImageUrl3() {
        return imageUrl3;
    }

    public void setImageUrl3(String imageUrl3) {
        this.imageUrl3 = imageUrl3;
    }

    public String getImageUrl4() {
        return imageUrl4;
    }

    public void setImageUrl4(String imageUrl4) {
        this.imageUrl4 = imageUrl4;
    }

    public String getImageUrl5() {
        return imageUrl5;
    }

    public void setImageUrl5(String imageUrl5) {
        this.imageUrl5 = imageUrl5;
    }

    public String getImageUrl6() {
        return imageUrl6;
    }

    public void setImageUrl6(String imageUrl6) {
        this.imageUrl6 = imageUrl6;
    }

    public String getImageUrl7() {
        return imageUrl7;
    }

    public void setImageUrl7(String imageUrl7) {
        this.imageUrl7 = imageUrl7;
    }

    public String getImageUrl8() {
        return imageUrl8;
    }

    public void setImageUrl8(String imageUrl8) {
        this.imageUrl8 = imageUrl8;
    }

    public String getImageUrl9() {
        return imageUrl9;
    }

    public void setImageUrl9(String imageUrl9) {
        this.imageUrl9 = imageUrl9;
    }

    public String getImageUrl10() {
        return imageUrl10;
    }

    public void setImageUrl10(String imageUrl10) {
        this.imageUrl10 = imageUrl10;
    }
}
