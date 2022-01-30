package com.codingblocksmodules.agrome.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.codingblocksmodules.agrome.ui.view.activities.ReUpyogItemActivity
import com.codingblocksmodules.agrome.ui.view.fragments.ArticlesFragment
import com.codingblocksmodules.agrome.ui.view.fragments.VideosFragment

class ScreenSliderAdapter(fa : ReUpyogItemActivity): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when(position){
        0 -> ArticlesFragment()
        else -> VideosFragment()
    }

}