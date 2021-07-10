package com.helloworld.display.domain.component

import com.helloworld.display.domain.content.DisplayContentGroup
import javax.persistence.*

@Entity(name = "display_component_item_content_groups")
class DisplayComponentItemContentGroup(
    componentItem: DisplayComponentItem,
    contentGroup: DisplayContentGroup? = null,
    order: Int = 0
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
        protected set

    @ManyToOne
    @JoinColumn(
        name = "component_item_id",
        nullable = false,
        insertable = false,
        updatable = false,
        foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT)
    )
    var componentItem: DisplayComponentItem = componentItem
        protected set

    @ManyToOne
    @JoinColumn(
        name = "content_group_id",
        nullable = false,
        insertable = false,
        updatable = false,
        foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT)
    )
    var contentGroup: DisplayContentGroup? = contentGroup

    var order: Int = order
        protected set
}
