package com.sumit.employedetails.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sumit.employedetails.R
import com.sumit.employedetails.databinding.ItemEmployeeBinding
import com.sumit.employedetails.models.Employee

class EmployeeAdapter(private val context: Context) :
    RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_employee, parent, false)
        )
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemEmployeeBinding.bind(view)

        fun bind(employee: Employee) {
            with(binding) {
                textViewUserName.text = employee.employee_name
                textViewUserEmail.text = employee.employee_email
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Employee>() {
        override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<Employee>) = differ.submitList(list)


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }
}