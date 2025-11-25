package com.condominio.qorder.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Bem-vindo ao aplicativo do condomínio!\n\n" +
                "Aqui você encontrará todas as atualizações e comunicados importantes.\n\n" +
                "Últimas notícias:\n" +
                "• Manutenção programada no elevador 1 - 25/11\n" +
                "• Reunião de moradores - 30/11 às 19h\n" +
                "• Nova vaga de emprego na portaria"
    }
    val text: LiveData<String> = _text
}