package com.udacity.shoestore.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

/**
 * A [Fragment] that creates the "Login" screen of the app. This screen doesn't actually do any authentication.
 */
class LoginFragment : Fragment() {

    private lateinit var emailEditText : EditText
    private lateinit var passwordEditText : EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login, container, false)
        emailEditText = binding.emailEditText
        passwordEditText = binding.passwordEditText

        binding.existingLoginButton.setOnClickListener {  view ->
            if (validateEntry()) {
                view.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
            }
        }

        binding.newLoginButton.setOnClickListener { view ->
            if (validateEntry()) {
                view.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
            }
        }

        return binding.root
    }

    private fun validateEntry() : Boolean {
        var valid = false

        if (emailEditText.text.isNotBlank() && passwordEditText.text.isNotBlank()) {
            valid = true
        } else {
            Toast.makeText(requireContext(), "Please enter an email address and password!", Toast.LENGTH_SHORT).show()
        }

        return valid
    }
}