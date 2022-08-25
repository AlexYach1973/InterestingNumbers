package com.alexyach.kotlin.numbers.ui.numberdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.alexyach.kotlin.numbers.databinding.FragmentNumberDetailsBinding
import com.alexyach.kotlin.numbers.model.NumberModel
import com.alexyach.kotlin.numbers.ui.base.BaseFragment
import com.alexyach.kotlin.numbers.ui.numberslist.GetMethod

private const val PARAM_GET_METHOD = "getMethod"

class NumberDetailsFragment : BaseFragment<FragmentNumberDetailsBinding,
        NumberDetailsViewModel>() {

    override val viewModel: NumberDetailsViewModel by lazy {
        ViewModelProvider(this)[NumberDetailsViewModel::class.java]
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentNumberDetailsBinding.inflate(inflater, container, false)

    private var getMethod: GetMethod? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            getMethod = it.getSerializable(PARAM_GET_METHOD) as GetMethod?
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getDetailsNumber(getMethod!!)

        viewModel.getValueInfoLiveData().observe(viewLifecycleOwner) {
            getResponseAppState(it)
        }
    }

    private fun getResponseAppState(appState: NumberDetailsAppState) {

        when (appState) {
            is NumberDetailsAppState.Loading -> {
                loadResult()
            }

            is NumberDetailsAppState.SuccessGetDetails -> {
                renderResult(appState.numberInfo)
                showResult()
            }

            is NumberDetailsAppState.ErrorGetDetails -> {
                toast(appState.errorString)
                showResult()
            }
        }
    }

    private fun renderResult(model: NumberModel) {
        binding.textViewNumberInfo.text = model.fact
    }

    private fun showResult() {
        binding.apply {
            textViewNumberInfo.visibility = View.VISIBLE
            frameLayoutLoading.visibility = View.GONE
        }
    }

    private fun loadResult() {
        binding.apply {
            textViewNumberInfo.visibility = View.GONE
            frameLayoutLoading.visibility = View.VISIBLE
        }
    }

    companion object {

        fun newInstance(getMethod: GetMethod) =
            NumberDetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(PARAM_GET_METHOD, getMethod)
                }
            }
    }
}