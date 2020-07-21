package com.ashish.imgursearchdemo.model

/**
 * Some [Data] items have images array instead of direct image link. This [Image] class represents
 * image with link and title. Omitting the unused fields in this app.
 */
data class Image(
    val id: String,
    val title: String?,
    val type: String?,
    val animated: Boolean,
    val link: String
) {
    /**
     * Currently this app only supports static images(.jpeg) or animated GIFs(.gif).
     * .mp4 and other formats are currently not supported.
     *
     * @return **true** if we support showing this image in our app. **false** otherwise.
     */
    fun isSupported() = !animated || type == "image/gif"
}

/**
 * Represents item in [SearchResult.data] array. Omitting fields which we are not using in this app.
 */
data class Data(
    val id: String,
    val title: String?,
    val type: String?,
    val animated: Boolean,
    val link: String?,
    private val images: List<Image>?
) {
    val imageList: List<Image>
        get() {
            return images?.map { it.copy(title = title) } ?: listOf(toImage())
        }

    /**
     * Currently this app only supports static images(.jpeg) or animated GIFs(.gif).
     * .mp4 and other formats are currently not supported.
     *
     * @return **true** if we support showing this image in our app. **false** otherwise.
     */
    fun isSupported() = !animated || type == "image/gif"

    private fun toImage() = Image(id, title, type, animated, link!!)

    fun supportedImagesWithData(): List<Image> {
        return (images?.map { it.copy(title = title) } ?: listOf(toImage()))
            .filter { it.isSupported() }
    }

}

/**
 * Has search results which contains array of [Data].
 */
data class SearchResult(
    val data: List<Data>,
    val success: Boolean,
    val status: Int
)