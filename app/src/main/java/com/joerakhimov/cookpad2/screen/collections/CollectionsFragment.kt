package com.joerakhimov.cookpad2.screen.collections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.joerakhimov.cookpad2.R
import com.joerakhimov.cookpad2.data.model.collection.CollectionItem
import com.joerakhimov.cookpad2.data.model.util.Status
import com.joerakhimov.cookpad2.databinding.FragmentCollectionsBinding
import com.joerakhimov.cookpad2.extensions.setGone
import com.joerakhimov.cookpad2.extensions.setVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CollectionsFragment: Fragment(R.layout.fragment_collections) {

    companion object {
        fun newInstance() = CollectionsFragment()
    }

    private val viewModel: CollectionsViewModel by viewModels()
    private var _binding: FragmentCollectionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        initRefreshListener()
    }

    private fun setupObservers() {
        viewModel.collections.observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.swipeCollections.isRefreshing = false
                        binding.recyclerCollections.setVisible()
                        resource.data?.let { collections -> showCollections(collections) }
                    }
                    Status.ERROR -> {
                        binding.swipeCollections.isRefreshing = false
                        binding.recyclerCollections.setGone()
                        resource.message?.let { showSnackbar(it) }
                    }
                    Status.LOADING -> {
                        binding.swipeCollections.isRefreshing = true
                        binding.recyclerCollections.setGone()
                    }
                }
            }
        })

    }

    private fun showCollections(collections: List<CollectionItem>) {
        binding.recyclerCollections.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CollectionsAdapter(collections)
        }
    }

    private fun initRefreshListener() {
        binding.swipeCollections.setOnRefreshListener {
            viewModel.onRefresh()
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar
            .make(
                requireContext(),
                binding.root,
                message,
                Snackbar.LENGTH_LONG
            )
            .show()
    }

}