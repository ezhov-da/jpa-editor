package ru.ezhov.jpa.editor.domain.model

import java.math.BigDecimal
import javax.persistence.*


@Entity
@Table(name = "Product")
data class Product(
        @Id
        var id: Int,

        @Column(name = "PRODUCT_NAME")
        var name: String? = null,

        @Column(name = "UNIT_PRICE")
        var unitPrice: BigDecimal? = null,

        @Column(name = "PACKAGE")
        var packagee: String? = null,

        @Column(name = "IS_DISCONTINUED")
        var discontinued: Boolean = false,
) {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SUPPLIER_ID")
    lateinit var supplier: Supplier
}