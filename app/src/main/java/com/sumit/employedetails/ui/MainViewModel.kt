package com.sumit.employedetails.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumit.employedetails.models.Employee
import com.sumit.employedetails.other.Resource
import com.sumit.employedetails.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {
    private val _res = MutableLiveData<Resource<List<Employee>>>()

    val res: LiveData<Resource<List<Employee>>>
        get() = _res

    init {
        getEmployees()
    }

    private fun getEmployees() = viewModelScope.launch {
        _res.postValue(Resource.loading(null))
        mainRepository.getEmployee().let {
            if (it.isSuccessful) {
                _res.postValue(Resource.success(it.body()))
            } else {
                _res.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }
}