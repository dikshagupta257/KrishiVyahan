package com.codingblocksmodules.agrome.data.model

data class LabItem(
	val lab: List<LabItem>? = null,
	val charges: Int? = null,
	val labName: String? = null,
	val location: String? = null,
	val id: Int? = null,
	val contactInfo: String? = null,
	val testName: String? = null,
	val testingAt: String? = null
)

