package ru.juxlab.ifoodshoptest.ui.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import ru.juxlab.ifoodshoptest.R
import ru.juxlab.ifoodshoptest.data.model.ProductCategory

class CategoryListAdapter(private val myDataset: List<ProductCategory>) :
    RecyclerView.Adapter<CategoryListAdapter.ObjectsListViewHolder>() {

    class ObjectsListViewHolder(private val cardView: CardView) : RecyclerView.ViewHolder(cardView){
        private val categoryNameView = cardView.findViewById<TextView>(R.id.textView_category_name)

        fun bind(productCategory: ProductCategory, checked: Boolean) {
            categoryNameView.setText(productCategory.nameId)

            //Только для визуального демо
            if (checked) {
                categoryNameView.setBackgroundColor(categoryNameView.resources.getColor(R.color.category_checked_background))
                //cardView.setBackgroundColor(categoryNameView.resources.getColor(R.color.category_checked_background))
                categoryNameView.setTextColor(categoryNameView.resources.getColor(R.color.price))
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ObjectsListViewHolder {
        // create a new view
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_menu_category_list_row, parent, false) as CardView

        return ObjectsListViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: ObjectsListViewHolder, position: Int) {
        val productCategory = myDataset[position]
        holder.bind(productCategory, position == 0) //Для визуального демо


    }
    override fun getItemCount() = myDataset.size
}