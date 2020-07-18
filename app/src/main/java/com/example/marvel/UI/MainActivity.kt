package com.example.marvel.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.marvel.R

class MainActivity : AppCompatActivity() {

//    private lateinit var viewPager: ViewPager
//    private lateinit var tabs: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        tabs = findViewById(R.id.tab_layout)
//        viewPager = findViewById(R.id.viewPager)
//
//        val adapter = MyFragmentPagerAdapter(supportFragmentManager)
//        adapter.addFragment(OnboardingFragmentOne())
//        adapter.addFragment(OnboardingFragmentTwo())
//        adapter.addFragment(OnboardingFragmentThree())
//
//        viewPager.adapter = adapter
//        tabs.setupWithViewPager(viewPager)
    }

//    fun selectIndex(newIndex: Int) {
//        viewPager.currentItem = newIndex
//    }
//
//    fun goToNextScreen(view: View) {
//        when(view.id) {
//            R.id.screen_one_cardview -> selectIndex(1)
//            R.id.screen_two_cardview -> selectIndex(2)
//        }
//    }
//
//    fun openGetStartedPage(view: View) {
//        when(view.id) {
//            R.id.txtSkipTwo -> selectIndex(2)
//            R.id.txtSkipOne -> selectIndex(2)
//        }
//    }
//
//
//    private inner class MyFragmentPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
//        private val mFragmentList: ArrayList<Fragment> = ArrayList()
//
//        override fun getItem(position: Int): Fragment {
//            return mFragmentList.get(position)
//        }
//
//        override fun getCount(): Int {
//            return mFragmentList.size
//        }
//
//        fun addFragment(fragment: Fragment) {
//            mFragmentList.add(fragment)
//        }
//    }

}
