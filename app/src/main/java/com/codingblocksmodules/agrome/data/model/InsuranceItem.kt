package com.codingblocksmodules.agrome.data.model

data class InsuranceItem(
	val insurance: List<InsuranceItem>? = null,
	val address: String? = null,
	val contactNo: String? = null,
	val companyName: String? = null,
	val companyCode: String? = null,
	val id: Int? = null,
	val email: String? = null
)

