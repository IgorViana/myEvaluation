package com.tribo_mkt.evaluation.utils

import android.view.View
import android.widget.ProgressBar
import android.widget.Toast

class CarregamentoStatusUtils(
    private val progressBar: ProgressBar,
    private val status: NetworkState
) {

    fun controlarVisibilidade() {
        when (status) {
            NetworkState.LOADING -> {
                progressBar.visibility = View.VISIBLE
            }
            NetworkState.LOADED -> {
                progressBar.visibility = View.GONE
            }
            NetworkState.ERROR -> {
                Toast.makeText(
                    progressBar.context,
                    "Algo errado aconteceu. Tente novamente mais tarde.",
                    Toast.LENGTH_LONG
                ).show()
            }
            else -> {
                progressBar.visibility = View.GONE
            }
        }
    }
}