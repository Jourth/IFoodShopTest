package ru.juxlab.ifoodshoptest.ui.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.juxlab.ifoodshoptest.R
import ru.juxlab.ifoodshoptest.data.model.Product
import java.text.NumberFormat


class ProductListAdapter (val viewModel: MenuViewModel, val fragment: MenuFragment): RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {

    private var productList = emptyList<Product>()

    class ProductViewHolder(val cardView: CardView, val viewModel: MenuViewModel, val fragment: MenuFragment): RecyclerView.ViewHolder(cardView){
        private val nameView       = cardView.findViewById<TextView>(R.id.textView_list_product_name)
        private val descriptonView = cardView.findViewById<TextView>(R.id.textView_list_product_description)
        private val priceView = cardView.findViewById<TextView>(R.id.textView_list_product_price)
        private val productImageView = cardView.findViewById<ImageView>(R.id.imageView_list_product_image)
        private val rowView        = cardView



        fun bind(product: Product){
            nameView.text       = product.name
            descriptonView.text = product.description

            val formatter: NumberFormat = NumberFormat.getNumberInstance()
            formatter.isGroupingUsed = true
            formatter.maximumFractionDigits = 0
            priceView.text = "от " + formatter.format(product.price) + " р" //Упрощённо для визуального демо


            fragment.launch (Dispatchers.IO) {
                val bitmap = viewModel.getSavedImage(product.imageId).bitmap
                withContext(Dispatchers.Main){
                    productImageView.setImageBitmap(bitmap)
                }
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_menu_product_list_row, parent, false) as CardView, viewModel, fragment)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    fun setData(newProductList: List<Product>){
        productList = newProductList
        notifyDataSetChanged()
    }

}