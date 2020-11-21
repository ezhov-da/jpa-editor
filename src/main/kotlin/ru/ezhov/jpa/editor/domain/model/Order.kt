package ru.ezhov.jpa.editor.domain.model

import java.math.BigDecimal
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "\"ORDER\"")
data class Order(
        @Id
        var id: Int,

        @ManyToOne
        @JoinColumn(name = "CUSTOMER_ID")
        var customer: Customer? = null,

        @Column(name = "ORDER_NUMBER")
        var number: String? = null,

        @Column(name = "ORDER_DATE")
        var date: Date? = null,

        @Column(name = "TOTAL_AMOUNT")
        var totalAmount: BigDecimal? = null
) {
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    lateinit var orderItems: List<OrderItem>
}