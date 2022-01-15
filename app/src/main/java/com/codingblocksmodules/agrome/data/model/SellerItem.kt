package com.codingblocksmodules.agrome.data.model

data class SellerItem(
	val seller: List<SellerItem>? = null,
	val country: String? = null,
	val profileImage: String? = null,
	val priceWithTransportation: String? = null,
	val quantity: Int? = null,
	val sellerName: String? = null,
	val price: String? = null,
	val district: String? = null,
	val contact: String? = null,
	val itemName: String? = null,
	val state: String? = null
)

