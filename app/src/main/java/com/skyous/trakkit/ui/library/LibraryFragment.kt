package com.skyous.trakkit.ui.library

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skyous.trakkit.R

/**
 * Class comment here
 *
 * @author Peter Abraham
 * @since 10/18/2018
 *
 */

class LibraryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = LibraryFragment()
    }
}