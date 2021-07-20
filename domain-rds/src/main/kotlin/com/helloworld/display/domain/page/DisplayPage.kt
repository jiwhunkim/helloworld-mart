package com.helloworld.display.domain.page

import com.helloworld.common.domain.BaseEntity
import javax.persistence.*

@Entity(name = "display_pages")
class DisplayPage(
    mallId: Long,
    name: String,
    code: String,
    layoutType: PageLayoutType,
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
        protected set

    var mallId: Long = mallId
        protected set

    var name: String = name
        protected set

    var code: String = code
        protected set

    @Enumerated(EnumType.STRING)
    var layoutType = layoutType
        protected set
}
