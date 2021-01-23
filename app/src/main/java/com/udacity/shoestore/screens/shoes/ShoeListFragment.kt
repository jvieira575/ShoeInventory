package com.udacity.shoestore.screens.shoes

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeListItemLayoutBinding

/**
 * A [Fragment] that creates a Shoe List screen.
 */
class ShoeListFragment : Fragment() {

    private val shoeViewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentShoeListBinding>(inflater, R.layout.fragment_shoe_list, container, false)
        setHasOptionsMenu(true)
        binding.lifecycleOwner = this

        binding.shoeListFloatingActionButton.setOnClickListener { view ->
            view.findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

        shoeViewModel.shoesList.observe(viewLifecycleOwner, Observer { shoeList ->
            if (!shoeList.isNullOrEmpty()) {
                binding.shoeListLinearLayout.removeAllViews()
                shoeList.forEach { shoe ->
                    val shoeListItemBinding : ShoeListItemLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.shoe_list_item_layout, container, false)
                    shoeListItemBinding.shoe = shoe
                    binding.shoeListLinearLayout.addView(shoeListItemBinding.root)
                }
            }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.shoe_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Using this type of navigation to prevent using the default behaviour of using the naming convention in menu item
        // I found that if you used the menu, it would navigate to the login screen but but hitting the back button would
        // return the user to this screen
        this.findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
        return true
    }
}