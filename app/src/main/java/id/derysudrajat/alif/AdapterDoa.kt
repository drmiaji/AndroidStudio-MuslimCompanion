package id.derysudrajat.alif

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.derysudrajat.alif.AdapterDoa.ListViewHolder
import java.util.Locale

class AdapterDoa(private val modelBacaan: MutableList<ModelDoa>) :
    RecyclerView.Adapter<AdapterDoa.ListViewHolder>(), Filterable {

    var modelBacaanListFull: List<ModelDoa>

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val filteredList: MutableList<ModelDoa> = ArrayList()
                if (constraint == null || constraint.length == 0) {
                    filteredList.addAll(modelBacaanListFull)
                } else {
                    val filterPattern = constraint.toString().toLowerCase(Locale.ROOT)
                    for (modelDoaFilter in modelBacaanListFull) {
                        if (modelDoaFilter.strTitle!!.toLowerCase(Locale.ROOT).contains(filterPattern)) {
                            filteredList.add(modelDoaFilter)
                        }
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                modelBacaan.clear()
                modelBacaan.addAll(results.values as List<ModelDoa>)
                notifyDataSetChanged()

            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item_doa, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(listViewHolder: ListViewHolder, i: Int) {
        val dataModel = modelBacaan[i]
        listViewHolder.tvId.text = dataModel.strId
        listViewHolder.tvTitle.text = dataModel.strTitle
        listViewHolder.tvArabic.text = dataModel.strArabic
        listViewHolder.tvLatin.text = dataModel.strLatin
        listViewHolder.tvTerjemahan.text = dataModel.strTranslation
    }

    override fun getItemCount(): Int {
        return modelBacaan.size
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvId: TextView = itemView.findViewById(R.id.tvId)
        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        var tvArabic: TextView = itemView.findViewById(R.id.tvArabic)
        var tvLatin: TextView = itemView.findViewById(R.id.tvLatin)
        var tvTerjemahan: TextView = itemView.findViewById(R.id.tvTerjemahan)
    }

    init {
        modelBacaanListFull = ArrayList(modelBacaan)
    }
}
