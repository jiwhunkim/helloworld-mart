package com.helloworld.display.domain.content

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "display_content_metas")
class DisplayContentMeta(
    name: String,
    description: String,
    mallId: Long,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
        protected set

    var name: String = name
        protected set

    var description: String = description
        protected set

    var mallId: Long = mallId
        protected set
}
