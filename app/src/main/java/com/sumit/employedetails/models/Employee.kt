package com.sumit.employedetails.models

import com.google.gson.annotations.SerializedName

data class Employee(
    @SerializedName("name")
    val employee_name: String? = "",
    @SerializedName("email")
    val employee_email: String? = "",
    @SerializedName("id")
    val id: String? = ""
)