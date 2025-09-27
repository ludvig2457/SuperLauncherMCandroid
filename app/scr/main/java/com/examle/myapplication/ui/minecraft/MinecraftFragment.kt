package com.example.myapplication.ui.minecraft

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R

class MinecraftFragment : Fragment() {

    private lateinit var viewModel: MinecraftViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(MinecraftViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_minecraft, container, false)

        val launchButton: Button = root.findViewById(R.id.launch_button)
        val statusText: TextView = root.findViewById(R.id.status_text)
        val launchStatusText: TextView = root.findViewById(R.id.launch_status_text)

        // Наблюдаем за статусом
        viewModel.statusText.observe(viewLifecycleOwner) { status ->
            statusText.text = status
        }

        viewModel.launchStatus.observe(viewLifecycleOwner) { status ->
            launchStatusText.text = status
        }

        // Кнопка запуска
        launchButton.setOnClickListener {
            viewModel.launchMinecraft()
            Toast.makeText(requireContext(), "Запускаем Minecraft...", Toast.LENGTH_SHORT).show()
        }

        return root
    }
}
