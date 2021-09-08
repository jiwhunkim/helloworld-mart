package com.helloworld.display.domain.component

import javax.persistence.*

@Entity(name = "display_component_items")
class DisplayComponentItem(
    component: DisplayComponent,
    name: String,
    description: String
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

    var name: String = name
        protected set

    var description: String = description
        protected set
}
