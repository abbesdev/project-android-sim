package com.abbes.schoolspace.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.abbes.schoolspace.ParentScreens.GradeListFragment
import com.abbes.schoolspace.ParentScreens.HomeworkListFragment


class ViewPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        var fragment: Fragment = GradeListFragment()
        if (position == 0) {
            fragment = GradeListFragment()
        } else if (position == 1) {
            fragment = HomeworkListFragment()
        }
        return fragment
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        if (position == 0) {
            title = "Grades"
        } else if (position == 1) {
            title = "Homework"
        }
        return title
    }
}