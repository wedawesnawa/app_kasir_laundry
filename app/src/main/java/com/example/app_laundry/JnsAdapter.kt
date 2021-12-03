package com.example.app_laundry

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView

class JnsAdapter(context : Context, resource: Int, var item: List<JenisCuci>) :
    ArrayAdapter<JenisCuci>(context, resource, item){

    private var Alljenis : List<JenisCuci> = item
    private val Ljenis: MutableList<JenisCuci> = ArrayList(item)
    val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return Ljenis.size
    }
    override fun getItem(position: Int): JenisCuci {
        return Ljenis.get(position)
    }
    override  fun getItemId(position:Int): Long{
        return Ljenis.get(position).id_cuci.toLong()
    }
    override fun getView(position: Int, convertView: View?, container: ViewGroup): View {
        var view: View? = convertView
        if (view == null) {
            view = inflater.inflate(R.layout.support_simple_spinner_dropdown_item,container,false)
        }
        (view?.findViewById(android.R.id.text1) as TextView).text = getItem(position)!!.jenis_cuci
        return view
    }
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View? = convertView
        if (view == null) {
            view = inflater.inflate(R.layout.support_simple_spinner_dropdown_item,parent,false)
        }
        (view?.findViewById(android.R.id.text1) as TextView).text = getItem(position)!!.jenis_cuci
        return view
    }
//    override fun getFilter(): Filter {
//        return object : Filter() {
//            override fun convertResultToString(resultValue: Any) :String {
//                return (resultValue as JenisCuci).jenis_cuci
//            }
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//                val filterResults = FilterResults()
//                if (constraint != null) {
//                    val jenisSuggestion: MutableList<JenisCuci> = ArrayList()
//                    for (ljenis in Alljenis) {
//                    if (ljenis.jenis_cuci.toLowerCase().startsWith(constraint.toString().toLowerCase())
//                        ) {
//                            jenisSuggestion.add(ljenis)
//                        }
//                    }
//                    filterResults.values = jenisSuggestion
//                    filterResults.count = jenisSuggestion.size
//                }
//                return filterResults
//            }
//            override fun publishResults(
//                constraint: CharSequence?,
//                results: FilterResults
//            ) {
//                if (results.count > 0) {
//                    for (result in results.values as List<*>) {
//                        if (result is JenisCuci) {
//                            Ljenis.add(result)
//                        }
//                    }
//                    notifyDataSetChanged()
//                } else if (constraint == null) {
//                    Ljenis.addAll(Alljenis)
//                    notifyDataSetInvalidated()
//                }
//            }
//        }
//    }
}
