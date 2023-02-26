package com.shoppi.app.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.shoppi.app.*
import com.shoppi.app.common.KEY_PRODUCT_ID
import com.shoppi.app.databinding.FragmentHomeBinding
import com.shoppi.app.ui.categorydetail.ProductPromotionAdapter
import com.shoppi.app.ui.categorydetail.SectionTitleAdapter
import com.shoppi.app.ui.common.EventObserver
import com.shoppi.app.ui.common.ViewModelFactory
import com.shoppi.app.ui.common.OnClick

class HomeFragment : Fragment(), OnClick {

    private val viewModel: HomeViewModel by viewModels { ViewModelFactory(requireContext()) }
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val button = view.findViewById<Button>(R.id.btn_enter_product_detail)
//        button.setOnClickListener {
//            findNavController().navigate(R.id.action_home_to_product_detail)
//        }

        binding.lifecycleOwner = viewLifecycleOwner
        setToolbar()
        setNavigation()
        setTopBanners()
        setSpecialPriceProduct()

    }

    // onClick
    override fun onClickProduct(productId: String) {
        findNavController().navigate(R.id.action_home_to_product_detail, bundleOf(
            KEY_PRODUCT_ID to "desk-1"
        ))
    }

    private fun setToolbar() {
        viewModel.title.observe(viewLifecycleOwner) { title ->
            Log.d("Home Fragment", "$title")
            binding.title = title
        }
    }

    private fun setNavigation() {
        viewModel.openProductDetailEvent.observe(viewLifecycleOwner, EventObserver { productId ->
            findNavController().navigate(R.id.action_home_to_product_detail, bundleOf(
                KEY_PRODUCT_ID to productId
            ))
        })
    }

    private fun setTopBanners() {
        with(binding.viewpagerHomeBanner) {
            adapter = HomeBannerAdapter(viewModel).apply {
                viewModel.topBanners.observe(viewLifecycleOwner) { banners ->
                    Log.d("Home Fragment", "$banners")
                    submitList(banners)
                }
            }

            val screenWidth = resources.displayMetrics.widthPixels
            val pagerWidth = resources.getDimension(R.dimen.viewPager_item_width)
            val pagerMargin = resources.getDimension(R.dimen.viewPager_item_margin)
            val offset = screenWidth - pagerWidth - pagerMargin

            offscreenPageLimit = 3
            setPageTransformer { page, position ->
                page.translationX = position * -offset
            }

            TabLayoutMediator(binding.viewpagerHomeBannerIndicator, this
            ) { tab, position ->

            }.attach()
        }
    }

    private fun setSpecialPriceProduct() {
        val titleAdapter = SectionTitleAdapter()
        val promotionAdapter = ProductPromotionAdapter(this)
        binding.rvProductSpecialPrice.adapter = ConcatAdapter(titleAdapter, promotionAdapter)
        viewModel.promotion.observe(viewLifecycleOwner) { promotion ->
//            Log.d("Home Fragment", "${promotion}")
//            Log.d("Home Fragment", "${promotion.items}")
            titleAdapter.submitList(listOf(promotion.title))
            promotionAdapter.submitList(promotion.items)
        }
    }




}
