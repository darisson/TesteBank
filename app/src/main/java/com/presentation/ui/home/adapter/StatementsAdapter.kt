package com.presentation.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.br.myapplication.R
import com.domain.model.Statement
import com.presentation.ui.home.utils.dateFormat
import com.presentation.ui.home.utils.formatToMonetary

class StatementsAdapter(private val context: Context,
                        private val listStatements: List<Statement>)
    : RecyclerView.Adapter<StatementsAdapter.StatementsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatementsViewHolder =
        StatementsViewHolder(
            LayoutInflater.from(context)
            .inflate(R.layout.item_view_statements, parent, false))

    override fun getItemCount(): Int = listStatements.size

    override fun onBindViewHolder(holder: StatementsViewHolder, position: Int) {
        holder.bindView(listStatements[position])
    }

    class StatementsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val txtPayment: TextView by lazy { itemView.findViewById<TextView>(R.id.txtPayment) }
        private val txtAccount: TextView by lazy { itemView.findViewById<TextView>(R.id.txtAccount) }
        private val txtDesc: TextView by lazy { itemView.findViewById<TextView>(R.id.txtDescricao) }
        private val txtTitle: TextView by lazy { itemView.findViewById<TextView>(R.id.txtTitle) }

        fun bindView(statement: Statement) {
            txtAccount.text = statement.value.formatToMonetary()
            txtPayment.text = statement.date.dateFormat()
            txtDesc.text = statement.desc
            txtTitle.text = statement.title
        }
    }
}