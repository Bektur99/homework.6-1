package com.example.homework6_1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.homework6_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLauncher()
        setData()
        initClickers()
    }

    private fun initClickers() {
        with(binding) {
            btnPass.setOnClickListener {
                if (etText.text.isNotEmpty()) {
                    passData()
                } else {
                    Toast.makeText(this@MainActivity, "editText is empty", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun passData() {
        val data = Intent(this@MainActivity, SecondActivity::class.java)
        data.putExtra(DATA_KEY, binding.etText.toString())
        resultLauncher.launch(data)
    }

    private fun setData() {
        binding.etText.setText(intent.getStringExtra(DATA_KEY))
    }

    private fun initLauncher() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    binding.etText.setText(result.data?.getStringExtra(DATA_KEY))
                }
            }
    }

    companion object {
        const val DATA_KEY = "data"
    }
}