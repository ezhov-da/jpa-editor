package ru.ezhov.jpa.editor.domain.model

import javax.persistence.*

@Entity
@Table(name = "CUSTOMER")
data class Customer(
        @Id
        var id: Int,

        @Column(name = "FIRST_NAME")
        var firstName: String?,

        @Column(name = "LAST_NAME")
        var lastName: String?,

        @Column(name = "CITY")
        var city: String?,

        @Column(name = "COUNTRY")
        var country: String?,

        @Column(name = "PHONE")
        var phone: String?
) {
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMER_ID")
    lateinit var orderList: List<Order>
}