package com.alexyach.kotlin.numbers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alexyach.kotlin.numbers.R
import com.alexyach.kotlin.numbers.databinding.ActivityMainBinding
import com.alexyach.kotlin.numbers.ui.numberslist.NumbersListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, NumbersListFragment.newInstance())
                .commit()
        }
    }
}