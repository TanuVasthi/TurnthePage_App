/*package com.example.turnthepage.model

data class cartItems(
    var bookTitle: String? = null,
    var bookPrice: String? = null,
    var bookDescription: String? = null,
    var bookImage: String? = null,
    var bookQuantity: Int? = null
)*/
package com.example.turnthepage.model

data class cartItems(
    var bookTitle: String? = null,
    var bookPrice: Int? = null, // Change bookPrice to Int
    var bookDescription: String? = null,
    var bookImage: String? = null,
    var bookQuantity: Int? = null
)
