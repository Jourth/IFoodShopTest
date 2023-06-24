package ru.juxlab.ifoodshoptest.ui.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import ru.juxlab.ifoodshoptest.R
import ru.juxlab.ifoodshoptest.data.model.Promo

class PromoListAdapter (private val myDataset: List<Promo>) :
    RecyclerView.Adapter<PromoListAdapter.ObjectsListViewHolder>() {

    class ObjectsListViewHolder(private val cardView: CardView) : RecyclerView.ViewHolder(cardView){
        private val promoImageView = cardView.findViewById<ImageView>(R.id.imageView_promo_banner)

        fun bind(promo: Promo) {
            promoImageView.setImageResource(promo.imageId)

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ObjectsListViewHolder {
        // create a new view
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_menu_promo_list_row, parent, false) as CardView

        return ObjectsListViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: ObjectsListViewHolder, position: Int) {
        val promo = myDataset[position]
        holder.bind(promo)


    }
    override fun getItemCount() = myDataset.size
}