package com.codingblocksmodules.agrome.data.model

data class ShopItem(
	val shop: List<ShopItem>? = null,
	val quantity: String? = null,
	val price: String? = null,
	val itemName: String? = null,
	val itemImage: String? = null,
	val id: Int? = null
)

