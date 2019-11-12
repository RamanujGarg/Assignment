package com.techflitter.assignments

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class VideoModel @JvmOverloads constructor(
    @Id var id: Long,
    var itemName: String = "",
    var itemPostion: Int = 0,
    var upVoteCount: Int = 0,
    var downVoteCount: Int = 0
)
