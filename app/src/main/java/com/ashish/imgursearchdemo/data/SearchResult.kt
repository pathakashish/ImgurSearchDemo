package com.ashish.imgursearchdemo.data

/**
 * Some [Data] items have images array instead of direct image link. This [Image] class represents
 * image with link and title. Omitting the unused fields in this app.
 */
data class Image(val id: String, val title: String, val type: String, val animated: Boolean, val link: String, val comments: List<String>) {
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
data class Data(val id: String, val title: String, val link: String, val images: Array<Image>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Data

        if (id != other.id) return false
        if (title != other.title) return false
        if (link != other.link) return false
        if (!images.contentEquals(other.images)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + link.hashCode()
        result = 31 * result + images.contentHashCode()
        return result
    }
}

/**
 * Has search results which contains array of [Data].
 */
data class SearchResult(val data: Array<Data>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SearchResult

        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        return data.contentHashCode()
    }

}