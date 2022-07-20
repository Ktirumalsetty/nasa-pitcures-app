package com.obvious.nasapicturesapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.obvious.nasapicturesapp.databinding.FragmentNasaImageDetailsBinding
import com.obvious.nasapicturesapp.model.Nasa

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NasaDetailFragment : Fragment() {

    private var _binding: FragmentNasaImageDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val sharedViewModel:MainActivitySharedViewModel by activityViewModels()
    private val detailsAdapter:NasaImageDetailPagerAdapter = NasaImageDetailPagerAdapter()
    val images:List<Nasa>?=null
    var position:Int = 0

    companion object{
        const val KEY_SELECTED_ITEM_POS = "key_pos"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNasaImageDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position = arguments?.getInt(KEY_SELECTED_ITEM_POS,0)?:1
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailsAdapter.submitList(sharedViewModel.nasaImagesList)
            binding.photoViewPager.apply {
                binding.photoViewPager.apply {
                    adapter = detailsAdapter
                    setCurrentItem(position, false)
//                    setPageTransformer(ZoomOutPageTransformer())
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}