package ru.juxlab.ifoodshoptest.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.juxlab.ifoodshoptest.R

class MainActivity: AppCompatActivity() {

      private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Layout
        setContentView(R.layout.activity_main)

        //Bottom Menu
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
       // val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
       // val toolbar = findViewById<Toolbar>(R.id.toolbar)

        //toolbar.hideOverflowMenu()

        //appBarConfiguration = AppBarConfiguration(
        //    setOf(
        //        R.id.nav_menu, R.id.nav_profile, R.id.nav_cart
        //    ), drawerLayout
        //)
        //setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

    }
}