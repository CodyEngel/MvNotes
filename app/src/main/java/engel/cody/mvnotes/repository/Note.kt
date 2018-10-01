package engel.cody.mvnotes.repository

/**
 * @author cody
 */
data class Note(
    val title: String = "",
    val body: String = "",
    val modifiedAt: Long = System.currentTimeMillis(),
    val tags: List<Tag> = emptyList(),
    val noteHash: Int? = null
)