package com.condominio.qorder.ui.denuncia

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.condominio.qorder.databinding.FragmentDenunciaBinding
import com.condominio.qorder.model.Denuncia

class DenunciaFragment : Fragment() {

    private var _binding: FragmentDenunciaBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var denunciaAdapter: DenunciaAdapter
    private val denuncias = mutableListOf<Denuncia>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("DenunciaFragment", "onCreate called")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("DenunciaFragment", "onCreateView called")
        _binding = FragmentDenunciaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        try {
            // Inicializar lista de denúncias
            inicializarDenuncias()
            
            // Configurar RecyclerView
            denunciaAdapter = DenunciaAdapter(denuncias)
            binding.recyclerViewDenuncias.layoutManager = LinearLayoutManager(context)
            binding.recyclerViewDenuncias.adapter = denunciaAdapter
            
            // Set up submit button
            binding.buttonSubmit.setOnClickListener {
                submitDenuncia()
            }
        } catch (e: Exception) {
            Log.e("DenunciaFragment", "Error in onCreateView", e)
            e.printStackTrace()
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("DenunciaFragment", "onViewCreated called")
    }

    private fun inicializarDenuncias() {
        denuncias.add(Denuncia(
            1,
            "Problema no Elevador",
            "O elevador do bloco A está fazendo barulho estranho e parando entre andares.",
            "20/11/2023"
        ))
        
        denuncias.add(Denuncia(
            2,
            "Iluminação Pública",
            "As lâmpadas da entrada do condomínio estão queimadas há mais de uma semana.",
            "18/11/2023"
        ))
    }

    private fun submitDenuncia() {
        val titulo = binding.editTextTitulo.text?.toString() ?: ""
        val descricao = binding.editTextDescricao.text?.toString() ?: ""
        
        if (titulo.isNotEmpty() && descricao.isNotEmpty()) {
            val novaDenuncia = Denuncia(
                denuncias.size + 1,
                titulo,
                descricao,
                "Hoje"
            )
            
            denuncias.add(0, novaDenuncia) // Adicionar no início da lista
            denunciaAdapter.notifyItemInserted(0)
            binding.recyclerViewDenuncias.scrollToPosition(0)
            
            // Limpar os campos
            binding.editTextTitulo.setText("")
            binding.editTextDescricao.setText("")
            
            Toast.makeText(context, "Denúncia registrada com sucesso!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("DenunciaFragment", "onDestroyView called")
        _binding = null
    }
}