package com.shoppi.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.shoppi.app.*
import com.shoppi.app.model.ViewModelFactory

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels { ViewModelFactory(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbarTitle = view.findViewById<TextView>(R.id.toolbar_home_title)
        val toolbarIcon = view.findViewById<ImageView>(R.id.toolbar_home_icon)
        val viewPager = view.findViewById<ViewPager2>(R.id.viewpager_home_banner)
        val viewPagerIndicator = view.findViewById<TabLayout>(R.id.viewpager_home_banner_indicator)

//        val button = view.findViewById<Button>(R.id.btn_enter_product_detail)
//        button.setOnClickListener {
//            findNavController().navigate(R.id.action_home_to_product_detail)
//        }

        viewModel.title.observe(viewLifecycleOwner) { title ->
            toolbarTitle.text = title.text

            GlideApp.with(this)
                .load(title.iconUrl)
                .into(toolbarIcon)
        }

        viewPager.adapter = HomeBannerAdapter().apply {
            viewModel.topBanners.observe(viewLifecycleOwner) { banners ->
                submitList(banners)
            }
        }


        val screenWidth = resources.displayMetrics.widthPixels
        val pagerWidth = resources.getDimension(R.dimen.viewPager_item_width)
        val pagerMargin = resources.getDimension(R.dimen.viewPager_item_margin)
        val offset = screenWidth - pagerWidth - pagerMargin

        viewPager.offscreenPageLimit = 3
        viewPager.setPageTransformer { page, position ->
            page.translationX = position * -offset
        }

        TabLayoutMediator(viewPagerIndicator, viewPager
        ) { tab, position ->

        }.attach()

    }
}