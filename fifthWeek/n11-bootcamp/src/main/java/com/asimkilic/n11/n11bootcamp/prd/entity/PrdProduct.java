package com.asimkilic.n11.n11bootcamp.prd.entity;

import com.asimkilic.n11.n11bootcamp.gen.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name="PRD_PRODUCT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrdProduct implements BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String shortName;

    private Long categoryId;

    private Long vendorUserId;

    private String imageUrl;

    private BigDecimal price;

    private BigDecimal additionalDiscount;
}
