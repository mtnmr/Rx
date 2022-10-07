package com.example.pokemon.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.pokemon.MyApplication
import com.example.pokemon.R
import com.example.pokemon.databinding.FragmentGenderImageBinding
import com.example.pokemon.databinding.FragmentPokeDetailBinding
import com.example.pokemon.viewmodel.PokeViewModel
//import com.example.pokemon.viewmodel.PokeViewModelFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenderImageFragment : Fragment() {

    private var _binding: FragmentGenderImageBinding?= null
    private val binding get() = _binding!!

    private val viewModel: PokeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGenderImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val maleFrontUrl = viewModel.pokemon.value!!.sprites.frontDefault.toUri().buildUpon().scheme("https").build()
//        val maleBackUrl = viewModel.pokemon.value!!.sprites.backDefault.toUri().buildUpon().scheme("https").build()
//        val femaleFrontUrl = viewModel.pokemon.value!!.sprites.frontFemale!!.toUri().buildUpon().scheme("https").build()
//        val femaleBackUrl = viewModel.pokemon.value!!.sprites.backFemale!!.toUri().buildUpon().scheme("https").build()
//
//        binding.apply {
//            maleFront.load(maleFrontUrl)
//            maleBack.load(maleBackUrl)
//            femaleFront.load(femaleFrontUrl)
//            femaleBack.load(femaleBackUrl)
//        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}