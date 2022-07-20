package com.obvious.nasapicturesapp.ui

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.obvious.nasapicturesapp.R
import com.obvious.nasapicturesapp.databinding.FragmentNasaListBinding
import com.obvious.nasapicturesapp.ui.NasaDetailFragment.Companion.KEY_SELECTED_ITEM_POS
import com.obvious.nasapicturesapp.utils.Result
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class NasaListFragment : Fragment() {

    private var _binding: FragmentNasaListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val nasaListViewModel:NasaListViewModel by viewModels()
    private val sharedViewModel:MainActivitySharedViewModel by activityViewModels()

    lateinit var adapter: NasaImagesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNasaListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            adapter = NasaImagesAdapter(){ pos->
                val bundle = Bundle().apply {
                    putInt(KEY_SELECTED_ITEM_POS,pos)
                }
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)
            }
            pictureListRecyclerView.adapter = adapter
        }
        registerObservers()
    }

    private fun registerObservers() {
        nasaListViewModel.nasaItemsLiveData.observe(viewLifecycleOwner, Observer {
            when(it){
                is Result.Success -> {
                    binding.apply {
                        progressCircular.visibility = View.GONE
                        sharedViewModel.nasaImagesList = it.data
                        adapter.submitList(it.data)
                    }
                }
                is Result.Error -> {
                    binding.progressCircular.visibility = View.GONE
                    Toast.makeText(requireContext(),it.toString(),Toast.LENGTH_SHORT).show()
                }

            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}