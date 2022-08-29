package com.ftw.hometerview.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ftw.hometerview.databinding.DialogLogoutBinding

class LogoutDialog() : DialogFragment() {
    private var _binding: DialogLogoutBinding? = null
    private val binding get() = _binding!!

    interface Listener {
        fun onClickLogoutFromLogoutDialog()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogLogoutBinding.inflate(inflater, container, false)
        val view = binding.root
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.logoutSelect.setOnClickListener {
            (parentFragment as? Listener)?.onClickLogoutFromLogoutDialog()
        }
        binding.logoutCancle.setOnClickListener {
            dismiss()
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
