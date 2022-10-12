package com.example.pokemon.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.pokemon.MyApplication
import com.example.pokemon.R
import com.example.pokemon.databinding.FragmentPokeDetailBinding
import com.example.pokemon.model.Pokemon
import com.example.pokemon.viewmodel.PokeViewModel
//import com.example.pokemon.viewmodel.PokeViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@AndroidEntryPoint
class PokeDetailFragment : Fragment() {

    private var _binding:FragmentPokeDetailBinding ?= null
    private val binding get() = _binding!!

    private val viewModel: PokeViewModel by activityViewModels()

    private lateinit var disposable : Disposable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        binding.searchButton.setOnClickListener {
            hideKeyboard(it)
            val searchId = binding.searchPoke.text.toString()
            viewModel.getPoke(searchId)
        }
        viewModel.pokemon
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { pokemon ->
                binding.pokeNumber.text =
                    getString(R.string.poke_number, pokemon.id.toString(), pokemon.name)
                binding.pokeHeight.text = getString(R.string.poke_height, pokemon.getHeight())
                binding.pokeWeight.text = getString(R.string.poke_weight, pokemon.getWeight())
                val imageUrl = pokemon.getImage().toUri().buildUpon().scheme("https").build()
                binding.pokeImage.load(imageUrl)
                binding.pokeType.text = getString(R.string.poke_type, pokemon.getPokeType())

                if (pokemon.sprites.frontFemale != null) {
                    binding.genderButton.visibility = View.VISIBLE
                } else {
                    binding.genderButton.visibility = View.INVISIBLE
                }

                if (pokemon != null) {
                    binding.backButton.visibility = View.VISIBLE
                    binding.nextButton.visibility = View.VISIBLE
                }
            }
 */

        val changeIdStream = createPokeIdObservable()
        val backNextIdStream = createBackNextIdObservable()
        val pokeIdObservable = Observable.merge(changeIdStream, backNextIdStream)

        disposable = pokeIdObservable
            .flatMap { viewModel.getPoke(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                Log.d("doOnError", "getPoke error: $it")
            }
            .retry()
            .subscribeBy(
                onNext = {
                    viewModel.changePokemon(it)
                    bindPokemon(it)
                },
                onError= {
                    Log.d("pokemon", "getPoke error: $it")
                }
            )

        binding.genderButton.setOnClickListener {
            val action =
                PokeDetailFragmentDirections.actionPokeDetailFragmentToGenderImageFragment()
            findNavController().navigate(action)
        }
    }

    private fun createPokeIdObservable():Observable<String>{
        val observable = Observable.create<String> {emitter ->
            binding.searchButton.setOnClickListener {
                hideKeyboard(it)
                val searchId = binding.searchPoke.text.toString()
                emitter.onNext(searchId)
            }

            emitter.setCancellable {
                binding.searchButton.setOnClickListener(null)
            }
        }
        return observable
    }

    private fun createBackNextIdObservable():Observable<String>{
        val observable = Observable.create<String> {emitter ->
            binding.backButton.setOnClickListener {
                val searchId = viewModel.backPokeId()
                emitter.onNext(searchId)
            }

            binding.nextButton.setOnClickListener {
                val searchId = viewModel.nextPokeId()
                emitter.onNext(searchId)
            }

            emitter.setCancellable {
                binding.nextButton.setOnClickListener(null)
                binding.backButton.setOnClickListener(null)
            }
        }
        return observable
    }

    private fun bindPokemon(pokemon: Pokemon){
        binding.pokeNumber.text =
            getString(R.string.poke_number, pokemon.id.toString(), pokemon.name)
        binding.pokeHeight.text = getString(R.string.poke_height, pokemon.getHeight())
        binding.pokeWeight.text = getString(R.string.poke_weight, pokemon.getWeight())
        val imageUrl = pokemon.getImage().toUri().buildUpon().scheme("https").build()
        binding.pokeImage.load(imageUrl)
        binding.pokeType.text = getString(R.string.poke_type, pokemon.getPokeType())

        if (pokemon.sprites.frontFemale != null) {
            binding.genderButton.visibility = View.VISIBLE
        } else {
            binding.genderButton.visibility = View.INVISIBLE
        }

        binding.backButton.visibility = View.VISIBLE
        binding.nextButton.visibility = View.VISIBLE
    }

    private fun hideKeyboard(view: View){
        val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken,InputMethodManager.HIDE_NOT_ALWAYS)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        if(!disposable.isDisposed){
            disposable.dispose()
        }
    }
}