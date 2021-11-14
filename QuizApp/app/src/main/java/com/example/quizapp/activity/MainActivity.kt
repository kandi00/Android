package com.example.quizapp.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.quizapp.R
import com.example.quizapp.data.MainViewModel
import com.example.quizapp.data.MainViewModelFactory
import com.example.quizapp.data.SharedViewModel
import com.example.quizapp.databinding.ActivityMainBinding
import com.example.quizapp.repository.Repository

class MainActivity : AppCompatActivity() {

    private val tag = "MainActivity"
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: SharedViewModel
    private lateinit var viewModel_: MainViewModel
    private lateinit var navController : NavController
    private lateinit var drawerLayout : DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = SharedViewModel(application)

        //Drawer navigation
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        navController = navHostFragment.navController
        //navController = findNavController(R.id.fragmentContainerView2)
        drawerLayout = binding.maDrawerLayout
        binding.navigationView.setupWithNavController(navController)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)

        //Retrofit, coroutines
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel_ = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel_.getPost()
        viewModel_.myResponse.observe(this, Observer { response ->
            response.results.forEach { Log.d("Response Code", it.toString()) }
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onStart(){
        super.onStart()
        Log.i(tag, "onStart")
        Toast.makeText(applicationContext, "onStart", Toast.LENGTH_SHORT).show()
    }

    override fun onResume(){
        super.onResume()
        Log.i(tag, "onResume")
        Toast.makeText(applicationContext, "onResume", Toast.LENGTH_SHORT).show()
    }

    override fun onPause(){
        super.onPause()
        Log.i(tag, "onPause")
        Toast.makeText(applicationContext, "onPause", Toast.LENGTH_SHORT).show()
    }

    override fun onStop(){
        super.onStop()
        Log.i(tag, "onStop")
        Toast.makeText(applicationContext, "onStop", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.i(tag, "onDestroy")
        val toast = Toast.makeText(applicationContext, "onDestroy", Toast.LENGTH_SHORT)
        toast.show()
    }
}