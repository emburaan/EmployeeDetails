package com.sumit.employedetails.api

import com.sumit.employedetails.models.Employee
import retrofit2.Response

interface ApiHelper {
    suspend fun getEmployees(): Response<List<Employee>>
}
