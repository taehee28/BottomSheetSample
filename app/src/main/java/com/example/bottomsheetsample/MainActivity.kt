package com.example.bottomsheetsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bottomsheetsample.bottomsheet.base.BaseSingleBottomSheetDialog
import com.example.bottomsheetsample.bottomsheet.count.CountBottomSheet
import com.example.bottomsheetsample.bottomsheet.list.ListBottomSheetDialog
import com.example.bottomsheetsample.bottomsheet.show
import com.example.bottomsheetsample.bottomsheet.single.GreetingBottomSheetDialog
import com.example.bottomsheetsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShowBottomSheet.setOnClickListener {
//            CountBottomSheet().show(supportFragmentManager, "")
//            GreetingBottomSheetDialog().show(supportFragmentManager, "")
            ListBottomSheetDialog().show(supportFragmentManager)
        }
    }
}