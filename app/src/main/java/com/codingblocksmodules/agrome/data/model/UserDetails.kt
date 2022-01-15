package com.codingblocksmodules.agrome.data.model

data class UserDetails (
    val name : String = "",
    val phNo : Long =-1,
    val password: String = "",
    val email: String = "",
    val type : String = "",
    val address : String = "",
    val country : String = "",
    val profilePic : String = ""
){
    constructor(name: String, phNo: Long, password: String, email: String, type: String):this(
        name,
        phNo,
        password,
        email,
        type,
        "",
        "",
        ""
    )
}