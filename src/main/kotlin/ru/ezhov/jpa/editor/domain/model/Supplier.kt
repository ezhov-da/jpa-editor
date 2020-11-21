package ru.ezhov.jpa.editor.domain.model

import javax.persistence.*


@Entity
@Table(name = "SUPPLIER")
data class Supplier(
        @Id
        var id: Int,

        @Column(name = "COMPANY_NAME")
        var companyName: String? = null,

        @Column(name = "CONTACT_NAME")
        var contactName: String? = null,

        @Column(name = "CITY")
        var city: String? = null,

        @Column(name = "COUNTRY")
        var country: String? = null,

        @Column(name = "PHONE")
        var phone: String? = null,

        @Column(name = "FAX")
        var fax: String? = null,
) {
    @OneToMany(mappedBy = "supplier", fetch = FetchType.EAGER)
    lateinit var productList: List<Product>
}