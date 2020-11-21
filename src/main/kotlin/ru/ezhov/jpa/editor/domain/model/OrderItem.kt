package ru.ezhov.jpa.editor.domain.model

import java.math.BigDecimal
import javax.persistence.*


@Entity
@Table(name = "ORDER_ITEM")
data class OrderItem(
        @Id
        var id: Int,

        @OneToOne
        var product: Product? = null,

        @Column(name = "UNIT_PRICE")
        var unitPrice: BigDecimal? = null,

        @Column(name = "QUANTITY")
        var quantity: Int = 0
) {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ORDER_ID")
    lateinit var order: Order
}
