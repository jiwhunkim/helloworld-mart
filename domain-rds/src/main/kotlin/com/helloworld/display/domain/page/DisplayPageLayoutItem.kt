package com.helloworld.display.domain.page

import com.helloworld.display.domain.component.DisplayComponentItem
import javax.persistence.*

@Entity(name = "display_page_layout_items")
class DisplayPageLayoutItem(
    layout: DisplayPageLayout,
    componentItem: DisplayComponentItem,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
        protected set

    @ManyToOne
    @JoinColumn(
        name = "layout_id",
        nullable = false,
        insertable = false,
        updatable = false,
        foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT)
    )
    var layout: DisplayPageLayout = layout
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
}
