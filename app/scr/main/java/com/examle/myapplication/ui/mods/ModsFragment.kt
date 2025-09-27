package com.example.myapplication.ui.mods

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R

class ModsFragment : Fragment() {

    private lateinit var modsViewModel: ModsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        modsViewModel = ViewModelProvider(this).get(ModsViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_mods, container, false)

        val statusText: TextView = root.findViewById(R.id.text_mods)
        val modsListView: ListView = root.findViewById(R.id.mods_list_view)
        val addModButton: Button = root.findViewById(R.id.add_mod_button)
        val refreshButton: Button = root.findViewById(R.id.refresh_button)

        // Наблюдаем за списком модов
        modsViewModel.modsList.observe(viewLifecycleOwner) { mods ->
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, mods)
            modsListView.adapter = adapter
        }

        // Наблюдаем за статусом
        modsViewModel.statusText.observe(viewLifecycleOwner) { status ->
            statusText.text = status
        }

        // Кнопка добавления мода
        addModButton.setOnClickListener {
            Toast.makeText(requireContext(), "Добавляем новый мод...", Toast.LENGTH_SHORT).show()
            modsViewModel.addMod("Новый мод ${System.currentTimeMillis()}")
        }

        // Кнопка обновления
        refreshButton.setOnClickListener {
            Toast.makeText(requireContext(), "Обновляем список модов...", Toast.LENGTH_SHORT).show()
        }

        // Клик по элементу списка
        modsListView.setOnItemClickListener { _, _, position, _ ->
            val modName = modsViewModel.modsList.value?.get(position) ?: ""
            Toast.makeText(requireContext(), "Выбран: $modName", Toast.LENGTH_SHORT).show()
        }

        return root
    }
}
