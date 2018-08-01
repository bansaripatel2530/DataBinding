package com.shopomy.base

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils

/**
 * Created by mavya.soni on 30/03/17.
 */

class MBaseFragmentManager(private val compatActivity: AppCompatActivity) {

    /**
     * Replace fragment into desire fragment container layout
     *
     * @param container    fragment container resource  id
     * @param nextFragment the fragment which now we want to replace fragment in same container
     * @throws IllegalStateException throws in case of transaction after activity saved its state
     */
    @Throws(IllegalStateException::class)
    fun replaceFragment(container: Int, nextFragment: Fragment?) {
        if (nextFragment == null) {
            return
        }
        compatActivity.supportFragmentManager
                .beginTransaction()
                .replace(container, nextFragment, nextFragment.javaClass.getSimpleName())
                .commit()
    }

    /**
     * Replace fragment into desire fragment container layout
     *
     * @param container    fragment container resource  id
     * @param nextFragment the fragment which now we want to add above current fragment in same container
     * @throws IllegalStateException throws in case of transaction after activity saved its state
     */
    @Throws(IllegalStateException::class)
    fun addFragment(container: Int, nextFragment: Fragment?) {

        if (nextFragment == null) {
            return
        }
        val hideFragment = compatActivity.supportFragmentManager.findFragmentById(container)
        compatActivity.supportFragmentManager
                .beginTransaction()
                .add(container, nextFragment, nextFragment.javaClass.getSimpleName())
                .hide(hideFragment)
                .addToBackStack(hideFragment.javaClass.getSimpleName())
                .commit()

    }

    fun showFragment(fragmentName: String) {
        val supportFragmentManager = compatActivity.supportFragmentManager
        for (entry in 0..supportFragmentManager.backStackEntryCount - 1) {
            val tag = supportFragmentManager.getBackStackEntryAt(entry).name
            if (!TextUtils.isEmpty(tag) && !tag.equals(fragmentName, ignoreCase = true)) {
                val showFragment = compatActivity.supportFragmentManager.findFragmentByTag(tag)
                if (showFragment != null) {
                    showFragment.userVisibleHint = false
                }
            }
        }
        compatActivity.supportFragmentManager.popBackStack(fragmentName, FragmentManager.POP_BACK_STACK_INCLUSIVE)

    }

    fun clearAllFragment() {
        val supportFragmentManager = compatActivity.supportFragmentManager
        for (entry in 0..supportFragmentManager.backStackEntryCount - 1) {
            val tag = supportFragmentManager.getBackStackEntryAt(entry).name
            val showFragment = compatActivity.supportFragmentManager.findFragmentByTag(tag)
            if (showFragment != null && entry != 0) {
                showFragment.userVisibleHint = false
            }
        }
        compatActivity.supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

}
