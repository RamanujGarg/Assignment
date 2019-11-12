package com.techflitter.assignments

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
class Notes {

    @Id
    internal var id: Long = 0

    internal var name: String? = null

    internal var upVote: Int = 0

    internal var downVote: Int = 0
}
