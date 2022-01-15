package com.codingblocksmodules.agrome.data.model

data class TransportItem(
	val transport: List<TransportItem>? = null,
	val address: String? = null,
	val contactNo: String? = null,
	val companyName: String? = null,
	val profileImage: String? = null,
	val id: Int? = null,
	val availability: String? = null,
	val category: String? = null
)

