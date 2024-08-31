package com.example.turnthepage.model

data class menuItem(
    val bookTitle: String? = null,
    val bookPrice: Int? = null,
    val bookDescription: String? = null,
    val bookImage: String? = null
) {
    // Optional: Secondary constructor for Firebase deserialization
    @Suppress("UNCHECKED_CAST")
    constructor(map: Map<String, Any?>) : this(
        map["bookTitle"] as? String,
        map["bookPrice"] as? Int ,
        map["bookDescription"] as? String,
        map["bookImage"] as? String
    )
}
