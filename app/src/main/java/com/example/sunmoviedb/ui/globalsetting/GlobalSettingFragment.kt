package com.example.sunmoviedb.ui.globalsetting

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.sunmoviedb.R
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * Created on 4/5/19 by Sang
 * Description:
 **/
class GlobalSettingFragment : PreferenceFragmentCompat() {

    private val globalSettingViewModel: GlobalSettingViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().addOnBackPressedCallback {
            requireActivity().finish()
            true
        }
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.global_setting, rootKey)
        val captionSettingPreference = findPreference("pref_caption_setting_key") as Preference
        captionSettingPreference.setOnPreferenceClickListener {
            globalSettingViewModel.loadCaption()
            true
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
    }

    private fun setupViewModel() {
        globalSettingViewModel.apply {
            captionEvent.observe(viewLifecycleOwner, Observer {
                val action = GlobalSettingFragmentDirections.actionGlobalToCaption()
                findNavController().navigate(action)
            })
        }
    }
}
