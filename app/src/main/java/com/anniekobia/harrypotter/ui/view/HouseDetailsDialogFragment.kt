package com.anniekobia.harrypotter.ui.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.anniekobia.harrypotter.R
import com.anniekobia.harrypotter.databinding.FragmentHouseDetailsDialogBinding


class HouseDetailsDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentHouseDetailsDialogBinding
    private lateinit var houseLogoAnimation: Animation
    private val houseDetailsFragmentArgs : HouseDetailsDialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHouseDetailsDialogBinding.inflate(inflater, container, false)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val character = houseDetailsFragmentArgs.character
        character?.house?.let { setHouseDetails(it) }

        binding.pauseButton.setOnClickListener {
            val studentDetailsAction = HouseDetailsDialogFragmentDirections.actionHouseDetailsDialogFragmentToCharacterDetailsFragment(
                character
            )
            findNavController().navigate(studentDetailsAction)
        }

        return binding.root
    }


    private fun setHouseDetails(house: String){
        /**
         * Load character house logo animation
         */
        houseLogoAnimation = AnimationUtils.loadAnimation(context, R.anim.house_logo_animation)

        when (house) {
            getString(R.string.house_gryffindor) -> {
                binding.houseName.text = resources.getText(R.string.house_gryffindor)
                binding.houseLogo.setImageResource(R.drawable.logo_gryffindor)
                binding.houseLogo.startAnimation(houseLogoAnimation)
                binding.houseDetails.text = resources.getText(R.string.house_gryffindor_details)
            }
            getString(R.string.house_slytherin) -> {
                binding.houseName.text = resources.getText(R.string.house_slytherin)
                binding.houseLogo.setImageResource(R.drawable.logo_slytherin)
                binding.houseLogo.startAnimation(houseLogoAnimation)
                binding.houseDetails.text = resources.getText(R.string.house_slytherin_details)
            }
            getString(R.string.house_ravenclaw) -> {
                binding.houseName.text = resources.getText(R.string.house_ravenclaw)
                binding.houseLogo.setImageResource(R.drawable.logo_ravenclaw)
                binding.houseLogo.startAnimation(houseLogoAnimation)
                binding.houseDetails.text = resources.getText(R.string.house_ravenclaw_details)
            }
            getString(R.string.house_hufflepuff) -> {
                binding.houseName.text = resources.getText(R.string.house_ravenclaw)
                binding.houseLogo.setImageResource(R.drawable.logo_hufflepuff)
                binding.houseLogo.startAnimation(houseLogoAnimation)
                binding.houseDetails.text = resources.getText(R.string.house_hufflepuff_details)
            }
        }
    }
}