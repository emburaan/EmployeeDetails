package com.sumit.employedetails.api

import com.sumit.employedetails.models.Employee
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {

    @GET("users")
    suspend fun getEmployees(): Response<List<Employee>>
}