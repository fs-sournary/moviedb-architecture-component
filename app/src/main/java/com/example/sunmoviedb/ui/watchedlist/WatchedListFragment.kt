package com.example.sunmoviedb.ui.watchedlist

import com.example.sunmoviedb.R
import com.example.sunmoviedb.databinding.FragmentWatchedListBinding
import com.example.sunmoviedb.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created in 4/8/19 by Sang
 * Description:
 */
class WatchedListFragment : BaseFragment<FragmentWatchedListBinding, WatchedListViewModel>() {

    override val viewModel: WatchedListViewModel by viewModel()

    override val layoutId: Int = R.layout.fragment_watched_list
}
