package com.fangga.suitmediamobiledeveloperintern2023.screen.second

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.fangga.suitmediamobiledeveloperintern2023.databinding.ActivitySecondScreenBinding
import com.fangga.suitmediamobiledeveloperintern2023.screen.third.ThirdScreenActivity
import com.fangga.suitmediamobiledeveloperintern2023.util.Constant
import com.fangga.suitmediamobiledeveloperintern2023.util.ViewModelFactory

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySecondScreenBinding
    private lateinit var viewModel: SecondScreenViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[SecondScreenViewModel::class.java]
        val selectedName = intent.getStringExtra(Constant.EXTRA_NAME) ?: ""

        viewModel.setName(selectedName)

        lifecycleScope.launchWhenStarted {
            viewModel.username.collect { username ->
                viewBinding.tvName.text = username
            }
        }

        viewBinding.apply {
            secondScreenAppBar.apply {
                setSupportActionBar(toolbar)
                supportActionBar?.hide()
                ivBack.setOnClickListener { finish() }
                tvTitle.text = "Second Screen"
            }
            tvSelectedUser.text = Constant.SELECTED_USERNAME ?: ""
            btnChooseUser.setOnClickListener {
                val intent = Intent(this@SecondScreenActivity, ThirdScreenActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewBinding.tvSelectedUser.text = Constant.SELECTED_USERNAME  ?: ""
    }
}