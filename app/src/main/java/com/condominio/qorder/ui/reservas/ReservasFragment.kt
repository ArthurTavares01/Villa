package com.condominio.qorder.ui.reservas

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.condominio.qorder.databinding.FragmentReservasBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.*

class ReservasFragment : Fragment() {

    private var _binding: FragmentReservasBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ReservasFragment", "onCreate called")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("ReservasFragment", "onCreateView called")
        _binding = FragmentReservasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        try {
            // Set up button click listeners
            binding.buttonQuadra.setOnClickListener {
                mostrarDialogReserva("Quadra")
            }
            
            binding.buttonSalao.setOnClickListener {
                mostrarDialogReserva("Salão de Festas")
            }
            
            binding.buttonDeck.setOnClickListener {
                mostrarDialogReserva("Deck")
            }
        } catch (e: Exception) {
            Log.e("ReservasFragment", "Error in onCreateView", e)
            e.printStackTrace()
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("ReservasFragment", "onViewCreated called")
    }

    private fun mostrarDialogReserva(local: String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Reservar $local")
        builder.setMessage("Escolha a data e horário para sua reserva:")
        
        builder.setPositiveButton("Selecionar Data") { _, _ ->
            mostrarDatePicker(local)
        }
        
        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }

    private fun mostrarDatePicker(local: String) {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Selecione a data")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

        datePicker.addOnPositiveButtonClickListener { selectedDate ->
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val date = Date(selectedDate)
            val dataFormatada = formatter.format(date)
            mostrarTimePicker(local, dataFormatada)
        }

        datePicker.show(parentFragmentManager, "DATE_PICKER")
    }

    private fun mostrarTimePicker(local: String, data: String) {
        val timePicker = MaterialTimePicker.Builder()
            .setTitleText("Selecione o horário")
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(12)
            .setMinute(0)
            .build()

        timePicker.addOnPositiveButtonClickListener {
            val hora = String.format("%02d:%02d", timePicker.hour, timePicker.minute)
            confirmarReserva(local, data, hora)
        }

        timePicker.show(parentFragmentManager, "TIME_PICKER")
    }

    private fun confirmarReserva(local: String, data: String, hora: String) {
        val mensagem = "Reserva confirmada!\n\nLocal: $local\nData: $data\nHorário: $hora"
        
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Confirmação de Reserva")
        builder.setMessage(mensagem)
        
        builder.setPositiveButton("OK") { _, _ ->
            Toast.makeText(context, "Reserva para $local solicitada!", Toast.LENGTH_SHORT).show()
        }
        
        builder.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("ReservasFragment", "onDestroyView called")
        _binding = null
    }
}