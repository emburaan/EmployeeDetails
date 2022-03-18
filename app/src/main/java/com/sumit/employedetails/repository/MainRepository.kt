package com.sumit.employedetails.repository

import com.sumit.employedetails.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getEmployee() = apiHelper.getEmployees()
}