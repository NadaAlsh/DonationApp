package com.nadaalshaibani.donationapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.GravityCompat
import com.nadaalshaibani.donationapp.ui.DeveloperActivity
import com.nadaalshaibani.donationapp.ui.theme.DonationAppTheme

class MainActivity : ComponentActivity(), AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var binding: ActivityMainBinding

    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            DonationAppTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
//        }
//    }
//}
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle!!.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.navigationView.setNavigationItemSelectedListener(this)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
        val navController = navHostFragment!!.findNavController()

        val popupMenu = PopupMenu(this, null)
        popupMenu.inflate(R.menu.bottom_menu)
        binding.bottomBar.setupWithNavController(popupMenu.menu, navController)


        navController.addOnDestinationChangedListener(object :
            NavController.OnDestinationChangedListener {
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?,
            ) {
                title = when (destination.id) {
                    R.id.homeFragment -> "Home"
                    R.id.profileFragment -> "Profile"
                    else -> "Blood"
                }
            }

        })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> {
                val appLink = """
                    https://play.google.com/store/apps/details?id=${this.getPackageName()}
                        """.trimIndent()
                val sendInt = Intent(Intent.ACTION_SEND)
                sendInt.putExtra(Intent.EXTRA_SUBJECT, this.getString(R.string.app_name))
                sendInt.putExtra(
                    Intent.EXTRA_TEXT,
                    this.getString(R.string.app_name).toString() + appLink
                )
                sendInt.type = "text/plain"
                this.startActivity(Intent.createChooser(sendInt, "Share"))
            }

            R.id.rate -> {
                val appName = this.packageName
                try {
                    this.startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=$appName")
                        )
                    )
                } catch (anfe: ActivityNotFoundException) {
                    this.startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=$appName")
                        )
                    )
                }
            }

            R.id.developer -> {
                startActivity(Intent(this, DeveloperActivity::class.java))
            }

            R.id.location -> {
                startActivity(Intent(this, LocationActivity::class.java))
            }

//            R.id.location -> {
//                val intent = Intent(Intent.ACTION_VIEW)
//                intent.data = Uri.parse("geo:0,0?q=Dhaka")
//                val chooser = Intent.createChooser(intent, "Lauch Maps")
//                startActivity(chooser)
//            }

            R.id.logout -> {
                auth.signOut()
                startActivity(Intent(this, LoginActivity::class.java))
                Toast.makeText(this, "Log Out", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        }
        return super.onOptionsItemSelected(item)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.close()
        } else super.onBackPressed()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DonationAppTheme {
        Greeting("Android")
    }
}