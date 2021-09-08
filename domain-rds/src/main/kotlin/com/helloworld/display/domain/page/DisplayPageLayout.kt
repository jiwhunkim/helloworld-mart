package com.helloworld.display.domain.page

import java.time.ZonedDateTime
import javax.persistence.*

@Entity(name = "display_page_layouts")
class DisplayPageLayout(
    page: DisplayPage,
    name: String,
    description: String,
    enabled: Boolean = false,
    startAt: ZonedDateTime = ZonedDateTime.now().minusDays(1),
    endAt: ZonedDateTime = ZonedDateTime.now().plusYears(1)
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

    var enabled: Boolean = enabled
        protected set

    var startAt: ZonedDateTime = startAt
        protected set

    var endAt: ZonedDateTime = endAt
        protected set
}
