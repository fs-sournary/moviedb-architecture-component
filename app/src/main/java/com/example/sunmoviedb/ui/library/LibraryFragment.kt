package com.example.sunmoviedb.ui.library

import com.example.sunmoviedb.R
import com.example.sunmoviedb.databinding.FragmentLibraryBinding
import com.example.sunmoviedb.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created in 4/8/19 by Sang
 * Description:
 */
class LibraryFragment: BaseFragment<FragmentLibraryBinding, LibraryViewModel>(){

    override val viewModel: LibraryViewModel by viewModel()

    override val layoutId: Int = R.layout.fragment_library
}
