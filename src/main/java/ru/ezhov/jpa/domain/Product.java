package ru.ezhov.jpa.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Product")
public class Product {
    @Id
    private int id;

    @Column(name = "PRODUCT_NAME")
    private String name;

    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;

    @Column(name = "PACKAGE")
    private String packagee;

    @Column(name = "IS_DISCONTINUED")
    private boolean discontinued;

    @ManyToOne()
    @JoinColumn(name = "SUPPLIER_ID")
    private Supplier supplier;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", supplierId=" + supplier.getId() +
                ", name=" + name +
                ", unitPrice=" + unitPrice +
                ", packagee=" + packagee +
                ", discontinued=" + discontinued +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getPackagee() {
        return packagee;
    }

    public void setPackagee(String packagee) {
        this.packagee = packagee;
    }

    public boolean isDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
