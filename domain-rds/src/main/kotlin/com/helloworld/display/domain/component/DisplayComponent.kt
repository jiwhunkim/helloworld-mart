package com.helloworld.display.domain.component

import com.helloworld.display.domain.page.DisplayPage
import javax.persistence.*

@Entity(name = "display_components")
class DisplayComponent(
    page: DisplayPage,
    name: String,
    description: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
        protected set

    @ManyToOne
    @JoinColumn(
        name = "page_id",
        nullable = false,
        insertable = true,
        updatable = true,
        foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT)
    )
    var page: DisplayPage = page
        protected set

    var name: String = name
        protected set

    var description: String = description
        protected set
}
