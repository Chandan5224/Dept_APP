package com.example.deptapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import com.example.deptapp.fragments.*
import com.example.deptapp.R
import com.example.deptapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        openDashboard()

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this@MainActivity,
            binding.drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )


        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        binding.navigationView.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.admin -> {
                    binding.appBarLayout.visibility=View.GONE
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, LogInFragment())
                        .commit()
                    binding.drawerLayout.closeDrawers()
                }
                R.id.faculty -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, FacultyFragment())
                        .commit()
                    binding.drawerLayout.closeDrawers()
                }
                R.id.students -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, StudentsFragment())
                        .commit()
                    binding.drawerLayout.closeDrawers()
                }
                R.id.academics -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, AcademicsFragment())
                        .commit()
                    binding.drawerLayout.closeDrawers()
                }
                R.id.library -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, LibraryFragment())
                        .commit()
                    binding.drawerLayout.closeDrawers()
                }
                R.id.alumni -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, AlumniFragment())
                        .commit()
                    binding.drawerLayout.closeDrawers()
                }
                R.id.society -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, SocietyFragment())
                        .commit()
                    binding.drawerLayout.closeDrawers()
                }
                R.id.about -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, AboutFragment())
                        .commit()
                    binding.drawerLayout.closeDrawers()
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }

    override fun onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawers()
        }else{
            when (supportFragmentManager.findFragmentById(R.id.frame)) {
                !is HomeFragment -> {
                    openDashboard()
                    binding.navigationView.checkedItem?.isChecked = false
                }
                else -> super.onBackPressed()
            }
        }
    }

    private fun openDashboard()
    {
        val fragment= HomeFragment()
        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame,fragment)
        transaction.commit()
        if(!binding.appBarLayout.isVisible)
            binding.appBarLayout.visibility=View.VISIBLE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        if (id == android.R.id.home) {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.title = ""
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}