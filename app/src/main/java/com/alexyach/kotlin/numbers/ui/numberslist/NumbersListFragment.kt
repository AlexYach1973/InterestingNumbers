package com.alexyach.kotlin.numbers.ui.numberslist

import android.os.Bundle
import android.view.*
import androidx.lifecycle.ViewModelProvider
import com.alexyach.kotlin.numbers.R
import com.alexyach.kotlin.numbers.databinding.FragmentNumbersListBinding
import com.alexyach.kotlin.numbers.model.NumberModel
import com.alexyach.kotlin.numbers.ui.base.BaseFragment
import com.alexyach.kotlin.numbers.ui.numberdetails.NumberDetailsFragment

class NumbersListFragment : BaseFragment<FragmentNumbersListBinding,
        NumbersListViewModel>() {

    override val viewModel: NumbersListViewModel by lazy {
        ViewModelProvider(this)[NumbersListViewModel::class.java]
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentNumbersListBinding.inflate(inflater, container, false)

    private  lateinit var adapter: NumbersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Menu
        setHasOptionsMenu(true)

        viewModel.getNumbersList()
        viewModel.getNumberListLiveData().observe(viewLifecycleOwner){dataList ->
            setAdapter(dataList)
        }

       setButtonClick()
    }

    private fun setAdapter(dataList: List<NumberModel>) {
        adapter = NumbersAdapter(dataList
        ) { number ->
            GetMethod.GET_FROM_ROOM.getMethod = number.number
            toNumberDetailsFragment(GetMethod.GET_FROM_ROOM)
        }
        binding.recyclerFragmentList.adapter = adapter
    }

    private fun setButtonClick() {
        binding.btnRandom.setOnClickListener {

            toNumberDetailsFragment(GetMethod.GET_RANDOM)
        }

        binding.btnByNumber.setOnClickListener {
            binding.etInputNumber.text.toString().let {
                GetMethod.GET_BY_NUMBER.getMethod = it
                toNumberDetailsFragment(GetMethod.GET_BY_NUMBER)
            }
        }
    }

    private fun toNumberDetailsFragment(getMethod: GetMethod) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, NumberDetailsFragment.newInstance(getMethod))
            .addToBackStack("NumberListFragment")
            .commit()
    }

    /** Menu */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_number_list_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.delete_all -> {
                viewModel.deleteAllRoom()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        fun newInstance() = NumbersListFragment()
    }
}