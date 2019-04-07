package com.example.sunmoviedb.ui.account

import com.example.sunmoviedb.R
import com.example.sunmoviedb.databinding.FragmentAccountBinding
import com.example.sunmoviedb.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created in 4/16/19 by Sang
 * Description:
 */
class AccountFragment : BaseFragment<FragmentAccountBinding, AccountViewModel>() {

    override val viewModel: AccountViewModel by viewModel()

    override val layoutId: Int = R.layout.fragment_account
}
