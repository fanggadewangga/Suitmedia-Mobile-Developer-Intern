package com.fangga.suitmediamobiledeveloperintern2023.screen.third

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.fangga.suitmediamobiledeveloperintern2023.R
import com.fangga.suitmediamobiledeveloperintern2023.adapter.UserAdapter
import com.fangga.suitmediamobiledeveloperintern2023.databinding.ActivityThirdScreenBinding
import com.fangga.suitmediamobiledeveloperintern2023.util.RemoteState
import com.fangga.suitmediamobiledeveloperintern2023.util.ViewModelFactory

class ThirdScreenActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityThirdScreenBinding
    private lateinit var viewModel: ThirdScreenViewModel
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[ThirdScreenViewModel::class.java]
        userAdapter = UserAdapter()

        viewBinding.apply {

            thirdScreenAppBar.apply {
                setSupportActionBar(toolbar)
                supportActionBar?.hide()
                ivBack.setOnClickListener { finish() }
                tvTitle.text = getString(R.string.third_screen)
            }

            rvUser.apply {
                adapter = userAdapter
                layoutManager = LinearLayoutManager(this@ThirdScreenActivity, LinearLayoutManager.VERTICAL, false)
            }

            lifecycleScope.launchWhenStarted {
                viewModel.fetchUsers()
            }

            lifecycleScope.launchWhenStarted {
                viewModel.listUserState.collect {
                    if (it != null) {
                        userAdapter.submitData(it)
                    }
                }
            }

            lifecycleScope.launchWhenStarted {
                viewModel.remoteState.collect { state ->
                    when (state) {
                        RemoteState.LOADING -> {
                            includeLoadingState.root.visibility = VISIBLE
                            includeEmptyState.root.visibility = INVISIBLE
                            rvUser.visibility = INVISIBLE
                        }
                        RemoteState.SUCCESS -> {
                            includeLoadingState.root.visibility = INVISIBLE
                            includeEmptyState.root.visibility = INVISIBLE
                            rvUser.visibility = VISIBLE
                        }
                        RemoteState.ERROR -> {
                            includeLoadingState.root.visibility = INVISIBLE
                            includeEmptyState.root.visibility = VISIBLE
                            rvUser.visibility = INVISIBLE
                            Toast.makeText(this@ThirdScreenActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            refresh.setOnRefreshListener {
                lifecycleScope.launchWhenStarted {
                    viewModel.listUserState.collect {
                        if (it != null) {
                            userAdapter.submitData(it)
                        }
                    }
                }
                refresh.isRefreshing = false
            }
        }
    }
}