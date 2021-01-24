package com.udacity.shoestore.screens.shoes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe

const val KEY_SHOE_NAME = "key_shoe_name"
const val KEY_SHOE_COMPANY = "key_shoe_company"
const val KEY_SHOE_SIZE = "key_shoe_size"
const val KEY_SHOE_DESCRIPTION = "key_shoe_description"

/**
 * A [Fragment] that creates a Shoe Detail screen.
 */
class ShoeDetailFragment : Fragment() {

    private val shoeViewModel: ShoeViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate<FragmentShoeDetailBinding>(inflater, R.layout.fragment_shoe_detail, container, false)

        if (savedInstanceState != null) {
            val shoeName = savedInstanceState.getString(KEY_SHOE_NAME, "")
            val shoeCompany = savedInstanceState.getString(KEY_SHOE_COMPANY, "")
            val shoeSize = savedInstanceState.getDouble(KEY_SHOE_SIZE, 0.0)
            val shoeDescription = savedInstanceState.getString(KEY_SHOE_DESCRIPTION, "")
            binding.shoe = Shoe(shoeName, shoeSize, shoeCompany, shoeDescription)
        } else {
            binding.shoe = Shoe("", 0.0, "", "")
        }
        binding.lifecycleOwner = this

        binding.cancelShoeButton.setOnClickListener {  view ->

            //Instead of navigating to new instance of ShoeListFragment(with pop actions to exclude from back stack),
            // you can just use the findNavController().navigateUp() method, which would similarly navigate back
            // by popping the next fragment from back-stack as we want to go back once user saves/cancels adding the new shoe

            //view.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
            view.findNavController().navigateUp()
        }

        binding.saveShoeButton.setOnClickListener { view ->
            val shoeToAdd : Shoe? = binding.shoe
            if (shoeToAdd != null && shoeToAdd.company.isNotBlank() && shoeToAdd.name.isNotBlank() && shoeToAdd.description.isNotBlank()) {
                shoeViewModel.addShoe(shoeToAdd)

                //Instead of navigating to new instance of ShoeListFragment(with pop actions to exclude from back stack),
                // you can just use the findNavController().navigateUp() method, which would similarly navigate back
                // by popping the next fragment from back-stack as we want to go back once user saves/cancels adding the new shoe

                //view.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
                view.findNavController().navigateUp()

            } else {
                Toast.makeText(requireContext(), "Please enter all details to save a shoe!", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val shoeToSave = binding.shoe
        outState.putString(KEY_SHOE_NAME, shoeToSave?.name)
        outState.putString(KEY_SHOE_COMPANY, shoeToSave?.company)
        shoeToSave?.size?.let { outState.putDouble(KEY_SHOE_SIZE, it) }
        outState.putString(KEY_SHOE_DESCRIPTION, shoeToSave?.description)
    }
}