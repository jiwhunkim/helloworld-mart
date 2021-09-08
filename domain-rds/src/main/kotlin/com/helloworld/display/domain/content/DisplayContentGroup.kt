package com.helloworld.display.domain.content

import javax.persistence.*

@Entity(name = "display_content_groups")
class DisplayContentGroup(
    contentMeta: DisplayContentMeta,
    name: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
        protected set

    @ManyToOne
    @JoinColumn(
        name = "content_meta_id",
        nullable = false,
        insertable = false,
        updatable = false,
        foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT)
    )
    var contentMeta: DisplayContentMeta = contentMeta
        protected set

    var name: String = name
        protected set
}
