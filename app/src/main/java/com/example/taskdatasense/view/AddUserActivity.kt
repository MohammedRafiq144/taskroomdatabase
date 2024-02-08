package com.example.taskdatasense.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskdatasense.R
import com.example.taskdatasense.adapter.UserListAdapter
import com.example.taskdatasense.databinding.ActivityAddUserBinding
import com.example.taskdatasense.viewmodel.MainModel
import com.example.taskdatasense.viewmodel.factory.MainModelFactory

class AddUserActivity : AppCompatActivity() {
    lateinit var binding:ActivityAddUserBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var listAdapter: UserListAdapter
    private lateinit var viewmodel: MainModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.viewUserDetailsRclyr

        binding.navigateHomeBtn.setOnClickListener {
            val intent:Intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        viewmodel = ViewModelProvider(
            this@AddUserActivity,
            MainModelFactory(this)
        ).get(MainModel::class.java)

        if (getAllListValues()) {
            recyclerView.visibility = View.VISIBLE
            binding.textView10.visibility = View.GONE
        } else {
            recyclerView.visibility = View.GONE
            binding.textView10.visibility = View.VISIBLE
        }
    }
    private fun getAllListValues(): Boolean {
        listAdapter = UserListAdapter(
            this
        )
        recyclerView.let {
            recyclerView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = listAdapter
        }
        listAdapter.resetView(viewmodel.getAllUserData())
        return true
    }


}