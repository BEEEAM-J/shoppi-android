package com.shoppi.app.ui.categorydetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.shoppi.app.common.KEY_CATEGORY_LABEL
import com.shoppi.app.databinding.FragmentCategoryDetailBinding
import com.shoppi.app.ui.common.OnClick
import com.shoppi.app.ui.common.ViewModelFactory

class CategoryDetailFragment: Fragment(), OnClick {

    private val viewModel: CategoryDetailViewModel by viewModels { ViewModelFactory(requireContext()) }
    private lateinit var binding: FragmentCategoryDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryDetailBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

//        val categoryId = requireArguments().getString(KEY_CATEGORY_ID)
        setToolBar()
        setListAdapter()

    }

    private fun setToolBar() {
        val categoryLabel = requireArguments().getString(KEY_CATEGORY_LABEL)
        binding.toolbarCategoryDetail.title = categoryLabel
    }

    private fun setListAdapter(){
        // ConcatAdapter : 여러 개의 어댑터를 연결
        // 여러 가지의 뷰 타입을 하나의 어댑터에 배치 가능
        val topSellingSectionAdapter = CategoryTopSellingSectionAdapter()
        val titleAdapter = SectionTitleAdapter()
        val promotionAdapter = ProductPromotionAdapter(this)
        binding.rvCategoryDetail.adapter = ConcatAdapter(topSellingSectionAdapter, titleAdapter, promotionAdapter)
        viewModel.topSelling.observe(viewLifecycleOwner) { TopSelling ->
            topSellingSectionAdapter.submitList(listOf(TopSelling))
        }
        viewModel.promotions.observe(viewLifecycleOwner) { Promotion ->
//            Log.d("Home Fragment", "${Promotion.title}")
//            Log.d("Home Fragment", "${Promotion.items}")
            titleAdapter.submitList(listOf(Promotion.title))
            promotionAdapter.submitList(Promotion.items)
        }
    }

    override fun onClickProduct(productId: String) {
//        findNavController().navigate(R.id.action_home_to_product_detail, bundleOf(
//            KEY_PRODUCT_ID to "desk-1"
//        ))
    }
}