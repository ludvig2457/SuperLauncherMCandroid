package com.example.myapplication.ui.minecraft

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MinecraftViewModel : ViewModel() {
    private val _statusText = MutableLiveData<String>().apply {
        value = "Готов к запуску Minecraft"
    }
    val statusText: LiveData<String> = _statusText

    private val _launchStatus = MutableLiveData<String>()
    val launchStatus: LiveData<String> = _launchStatus

    fun launchMinecraft() {
        _launchStatus.value = "⏳ Запускаем Minecraft..."
        _statusText.value = "Minecraft запускается!"

        // Симуляция запуска
        Thread {
            Thread.sleep(2000)
            _launchStatus.postValue("✅ Minecraft запущен!")
        }.start()
    }
}