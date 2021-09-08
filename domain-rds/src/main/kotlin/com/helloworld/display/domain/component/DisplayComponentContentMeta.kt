package com.helloworld.display.domain.component

import com.helloworld.display.domain.content.DisplayContentMeta
import javax.persistence.*

@Entity(name = "display_component_content_metas")
class DisplayComponentContentMeta(
    component: DisplayComponent,
    contentMeta: DisplayContentMeta
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
        protected set

    @ManyToOne
    @JoinColumn(
        name = "component_id",
        nullable = false,
        insertable = false,
        updatable = false,
        foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT)
    )
    var component: DisplayComponent = component
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
}
