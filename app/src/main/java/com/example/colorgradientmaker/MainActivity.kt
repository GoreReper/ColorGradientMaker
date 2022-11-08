package com.example.colorgradientmaker

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.colorgradientmaker.contracts.HasCustomTitle
import com.example.colorgradientmaker.contracts.Navigator
import com.example.colorgradientmaker.screen.colorlist.ColorListFragment
import com.example.colorgradientmaker.screen.currentcolor.CurrentColorFragment
import com.example.colorgradientmaker.screen.sizelist.SizeListFragment


class MainActivity : AppCompatActivity(), Navigator{

    private val currentFragment: Fragment
        get() = supportFragmentManager.findFragmentById(R.id.fragmentContainer)!!

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            updateUi()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, CurrentColorFragment())
                .commit()
        }

        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun showColorListScreen() {
        launchFragment(ColorListFragment())
    }

    override fun showSizeListScreen() {
        launchFragment(SizeListFragment())
    }

    override fun goBack() {
        onBackPressed()
    }

    override fun goToMainScreen() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    private fun launchFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    private fun updateUi() {
        val fragment = currentFragment
        Log.i("TEST", "SPAM")
        if (fragment is HasCustomTitle) {
            supportActionBar?.setTitle(getString(fragment.getTitleRes()))
        } else {
            supportActionBar?.setTitle(getString(R.string.app_name))
        }

        if (supportFragmentManager.backStackEntryCount > 0) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setDisplayShowHomeEnabled(false)
        }
    }
}