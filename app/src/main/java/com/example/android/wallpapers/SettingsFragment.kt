package com.example.android.wallpapers


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.wallpapers.utilities.AuthUtil
import kotlinx.android.synthetic.main.fragment_settings.*


/**
 * A simple [Fragment] subclass.
 *
 */
class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {




        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onStart() {
        super.onStart()

        logout_button.setOnClickListener {
            AuthUtil.logout()
        }
    }


}
