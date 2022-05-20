package com.example.emplist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.emplist.common.Constants

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        setContentView(R.layout.main_activity)
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
            val navController = navHostFragment.navController
            navController.navigate(R.id.userFragment)
    }

    override fun onBackPressed() {
        if (Constants.isloaded)
            finish()
        super.onBackPressed()
    }
}