package com.sumit.employedetails.api

import com.sumit.employedetails.models.Employee
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiServices: ApiServices
) : ApiHelper {
    override suspend fun getEmployees(): Response<List<Employee>> = apiServices.getEmployees()
}