package com.fangga.suitmediamobiledeveloperintern2023.screen.first

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.fangga.suitmediamobiledeveloperintern2023.R
import com.fangga.suitmediamobiledeveloperintern2023.databinding.ActivityFirstScreenBinding
import com.fangga.suitmediamobiledeveloperintern2023.screen.second.SecondScreenActivity
import com.fangga.suitmediamobiledeveloperintern2023.util.Constant
import com.fangga.suitmediamobiledeveloperintern2023.util.ViewModelFactory
import com.thecode.aestheticdialogs.AestheticDialog
import com.thecode.aestheticdialogs.DialogAnimation
import com.thecode.aestheticdialogs.DialogStyle
import com.thecode.aestheticdialogs.DialogType
import com.thecode.aestheticdialogs.OnDialogClickListener

class FirstScreenActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityFirstScreenBinding
    private lateinit var viewModel: FirstScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[FirstScreenViewModel::class.java]

        viewBinding.apply {
            btnCheck.setOnClickListener {
                val inputText = edtPalindrome.text.toString()
                if (viewModel.isPalindrome(inputText))
                    showDialog(
                        type = DialogType.SUCCESS,
                        getString(R.string.palindrome_check),
                        getString(R.string.palindrome_true)
                    )
                else
                    showDialog(
                        type = DialogType.ERROR,
                        getString(R.string.palindrome_check),
                        getString(R.string.palindrome_false)
                    )
            }

            btnNext.setOnClickListener {
                val username = edtName.text.toString()
                val intentToSecond = Intent(this@FirstScreenActivity, SecondScreenActivity::class.java)
                intentToSecond.putExtra(Constant.EXTRA_NAME, username)
                startActivity(intentToSecond)
            }
        }
    }

    private fun showDialog(
        type: DialogType,
        title: String,
        message: String,
        onAction: (AestheticDialog.Builder) -> Unit = { it.dismiss() },
    ) {
        AestheticDialog.Builder(this, DialogStyle.FLASH, type)
            .setTitle(title)
            .setMessage(message)
            .setAnimation(DialogAnimation.SLIDE_UP)
            .setGravity(Gravity.CENTER)
            .setOnClickListener(object : OnDialogClickListener {
                override fun onClick(dialog: AestheticDialog.Builder) {
                    onAction(dialog)
                }
            })
            .show()
    }
}