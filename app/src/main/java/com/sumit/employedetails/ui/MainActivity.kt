package com.sumit.employedetails.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sumit.employedetails.databinding.ActivityMainBinding
import com.sumit.employedetails.other.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: EmployeeAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUi()
        setupObserver()
    }

    private fun setupObserver() {
        mainViewModel.res.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    binding.rvEmployees.visibility = View.VISIBLE
                    it.data.let { res ->
                        res?.let { it1 -> adapter.submitList(it1) }
                    }
                }
                Status.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.rvEmployees.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progress.visibility = View.GONE
                    binding.rvEmployees.visibility = View.VISIBLE
                    Snackbar.make(binding.rootView, "Something went wrong", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    private fun setupUi() {
        adapter = EmployeeAdapter(this)
        binding.rvEmployees.layoutManager = LinearLayoutManager(this)
        binding.rvEmployees.adapter = adapter
    }
}