package mx.openpay.challenge.ui

import android.Manifest
import android.os.Build
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import mx.openpay.challenge.R
import mx.openpay.challenge.databinding.ActivityHomeBinding
import mx.openpay.challenge.tools.ManagePermissions

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        verifyStoragePermissions()
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_home)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_account,
                R.id.navigation_movie,
                R.id.navigation_map,
                R.id.navigation_upload,
                R.id.navigation_about_me,
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private val permissionsRequestCode = 123
    private lateinit var managePermissions: ManagePermissions

    private fun verifyStoragePermissions() {
        val list = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            listOf(
                Manifest.permission.POST_NOTIFICATIONS,
                Manifest.permission.ACCESS_FINE_LOCATION,
            )
        } else {
            listOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
            )
        }
        managePermissions =
            ManagePermissions(this@HomeActivity, list, permissionsRequestCode)
        managePermissions.checkPermissions()
    }

}
