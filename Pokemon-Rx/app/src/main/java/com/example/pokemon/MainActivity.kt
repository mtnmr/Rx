package com.example.pokemon

import android.content.Context
import android.hardware.input.InputManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_container)
        return  navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    //背景タップでキーボード非表示
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val layout = findViewById<ConstraintLayout>(R.id.constraint_layout)
        inputManager.hideSoftInputFromWindow(
            layout.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )

        return false
    }
}