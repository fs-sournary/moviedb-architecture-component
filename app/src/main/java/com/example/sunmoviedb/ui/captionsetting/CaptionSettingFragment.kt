package com.example.sunmoviedb.ui.captionsetting

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.example.sunmoviedb.R

/**
 * Created in 4/16/19 by Sang
 * Description:
 */
class CaptionSettingFragment : PreferenceFragmentCompat() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().addOnBackPressedCallback {
            findNavController().popBackStack()
            true
        }
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.caption_setting, rootKey)
        val useCaptionPreference = findPreference("pref_use_caption_key") as SwitchPreference
        val languageListPreference = findPreference("pref_caption_language_key") as ListPreference
        val textSizeListPreference = findPreference("pref_caption_text_size_key") as ListPreference
        if (useCaptionPreference.isChecked) {
            languageListPreference.isEnabled = true
            textSizeListPreference.isEnabled = true
        } else {
            languageListPreference.isEnabled = false
            textSizeListPreference.isEnabled = false
        }
        useCaptionPreference.setOnPreferenceChangeListener { _, newValue ->
            if (newValue is Boolean) {
                if (newValue) {
                    languageListPreference.isEnabled = true
                    textSizeListPreference.isEnabled = true
                } else {
                    languageListPreference.isEnabled = false
                    textSizeListPreference.isEnabled = false
                }
                true
            } else {
                false
            }
        }
    }

}
