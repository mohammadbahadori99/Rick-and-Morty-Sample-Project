package com.example.rickandmorty.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.rickandmorty.databinding.ActivityCharactersBinding
import com.example.rickandmorty.model.CharacterView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_characters.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CharactersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCharactersBinding
    private lateinit var charactersListAdapter: CharactersListAdapter
    private val vm: CharactersViewModel by viewModels()

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        showList()
    }

    private fun setupRecyclerView() {
        charactersListAdapter = CharactersListAdapter { adapterOnClick(it) }
        rv_characters_activityCharacter.adapter = charactersListAdapter
        rv_characters_activityCharacter.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun adapterOnClick(characterView: CharacterView) {
    }


    private fun showList() {
        lifecycleScope.launch(Dispatchers.Main) {
            vm.myList.observe(this@CharactersActivity) {
                charactersListAdapter.submitList(it)
            }

            vm.showLoading.observe(this@CharactersActivity) {
                pb_waiting_activityCharacters.visibility = if (it) {
                    View.VISIBLE
                } else {
                    View.INVISIBLE
                }
            }
            vm.error.observe(this@CharactersActivity) {
                it?.let {
                    showSnackbar(it.error) { vm.getCharacters() }
                }
            }
        }
    }

    private fun showSnackbar(exception: Exception, block: () -> Unit) {
        var message = exception.message
        if (message.isNullOrEmpty()) {
            message = "Something went wrong!"
        }
        Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE)
            .setAction("Retry") {
                block()
            }.show()
    }
}