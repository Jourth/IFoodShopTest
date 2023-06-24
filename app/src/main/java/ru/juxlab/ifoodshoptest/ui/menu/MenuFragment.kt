package ru.juxlab.ifoodshoptest.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import ru.juxlab.ifoodshoptest.IShopApplication
import ru.juxlab.ifoodshoptest.R
import ru.juxlab.ifoodshoptest.ui.ScopedFragment

class MenuFragment: ScopedFragment(), KodeinAware {


    override lateinit var kodein: Kodein

    private val viewModelFactory: MenuViewModelFactory by instance()

    private lateinit var viewModel: MenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        kodein = (requireActivity().application as IShopApplication).kodein

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MenuViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_menu, container, false)

        //Product recycler view
        val productListRecyclerView = root.findViewById<RecyclerView>(R.id.recyclerView_product_list)
        val productsAdapter = ProductListAdapter(viewModel, this)
        productListRecyclerView.adapter = productsAdapter
        productListRecyclerView.layoutManager = LinearLayoutManager(requireContext())


        launch {
            val productListLiveData = viewModel.productList.await()
            productListLiveData.observe(viewLifecycleOwner, Observer { productsAdapter.setData(it) })
        }

        //Category recycler view
        val categoriesRecyclerView = root.findViewById<RecyclerView>(R.id.recyclerView_categories)
        val categoriesAdapter = CategoryListAdapter(viewModel.categoryList)
        categoriesRecyclerView.adapter = categoriesAdapter
        categoriesRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)

        //Promo recycler view
        val promoRecyclerView = root.findViewById<RecyclerView>(R.id.recyclerView_promos)
        val promosAdapter = PromoListAdapter(viewModel.promoList)
        promoRecyclerView.adapter = promosAdapter
        promoRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)


        return root

    }

}