package com.example.marketim.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import androidx.core.content.ContextCompat
import com.example.marketim.Classes.Helper
import com.example.marketim.Classes.Product
import com.example.marketim.R
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.elv_orders_group.view.*

class ELVOrdersAdapter(val _context: Context, var _products: ArrayList<Product>) :
    BaseExpandableListAdapter() {
    var _productsFiltered: ArrayList<Product>
    var _productsOriginal = ArrayList<Product>()

    init {
        _productsFiltered = _products
        _productsOriginal = _products
    }

    override fun getGroup(p0: Int): Any {
        return this._productsFiltered[p0].orderName
    }

    override fun getGroupView(p0: Int, p1: Boolean, p2: View?, p3: ViewGroup?): View {
        var convertView = p2
        val product = _productsFiltered[p0]

        if (convertView == null) {
            val inflater = this._context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.elv_orders_group, null)
        }

        when (product.productState) {
            _context.getString(R.string.os_getting_ready) -> {
                convertView!!.ivItemState.setImageResource(R.color.colorOrange)
                convertView.tvItemState.setTextColor(ContextCompat.getColor(_context, R.color.colorOrange))
            }
            _context.getString(R.string.os_waiting_verification) -> {
                convertView!!.ivItemState.setImageResource(R.color.colorRed)
                convertView.tvItemState.setTextColor(ContextCompat.getColor(_context, R.color.colorRed))
            }
            else -> {
                convertView!!.ivItemState.setImageResource(R.color.colorGreen)
                convertView.tvItemState.setTextColor(ContextCompat.getColor(_context, R.color.colorGreen))
            }
        }

        convertView.tvItemDay.text = product.date
        convertView.tvItemMonth.text = Helper.getMonthString(_context, product.month)
        convertView.tvItemTitle.text = product.marketName
        convertView.tvItemName.text = product.orderName
        convertView.tvItemState.text = product.productState
        convertView.tvItemPrice.text = product.productPrice.toString()

        return convertView
    }

    override fun getChildrenCount(p0: Int): Int {
        return 1
    }

    override fun getChild(p0: Int, p1: Int): Any {
        return _productsFiltered[p0]
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getChildId(p0: Int, p1: Int): Long {
        return p1.toLong()
    }

    override fun getChildView(p0: Int, p1: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View {
        var convertView = p3
        val details = _productsFiltered[p0].productDetail

        if (convertView == null) {
            val inflater = this._context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.elv_orders_item, null)
        }

        convertView!!.tvItemName.text = details.orderDetail
        convertView.tvItemPrice.text = details.summaryPrice.toString()

        return convertView
    }

    override fun getGroupCount(): Int {
        return this._productsFiltered.size
    }
}