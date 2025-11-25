package com.condominio.qorder.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.condominio.qorder.R
import com.condominio.qorder.databinding.FragmentHomeBinding
import com.condominio.qorder.model.Noticia
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var noticiaAdapter: NoticiaAdapter
    private val noticias = mutableListOf<Noticia>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("HomeFragment", "onCreate called")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("HomeFragment", "onCreateView called")
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        try {
            // Inicializar lista de notícias
            inicializarNoticias()
            
            // Configurar RecyclerView
            noticiaAdapter = NoticiaAdapter(noticias)
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            binding.recyclerView.adapter = noticiaAdapter
            
            // Configurar SwipeRefreshLayout
            binding.swipeRefresh.setOnRefreshListener {
                // Simular atualização
                binding.swipeRefresh.isRefreshing = false
            }
            
            // Configurar FloatingActionButton
            binding.fabAdd.setOnClickListener {
                mostrarDialogAdicionarNoticia()
            }
        } catch (e: Exception) {
            Log.e("HomeFragment", "Error in onCreateView", e)
            e.printStackTrace()
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("HomeFragment", "onViewCreated called")
    }

    private fun inicializarNoticias() {
        noticias.add(Noticia(
            1,
            "Manutenção Programada",
            "25/11/2025",
            "Informamos que haverá manutenção programada no elevador 1 no dia 25/11 das 8h às 12h. Pedimos desculpas pelo transtorno."
        ))
        
        noticias.add(Noticia(
            2,
            "Reunião de Moradores",
            "23/11/2025",
            "A assembleia de moradores acontecerá no dia 30/11 às 19h no salão de festas. Contamos com a presença de todos."
        ))
        
        noticias.add(Noticia(
            3,
            "Novas Vagas na Portaria",
            "20/11/2025",
            "Estamos contratando para a portaria do condomínio. Interessados devem procurar o síndico para mais informações."
        ))
        
        noticias.add(Noticia(
            4,
            "Festa de Final de Ano",
            "15/11/2025",
            "A festa de final de ano será realizada no dia 15/12 às 20h. Não se esqueça de confirmar sua presença com a administração."
        ))
    }

    private fun mostrarDialogAdicionarNoticia() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Adicionar Notícia")
        
        val view = layoutInflater.inflate(R.layout.dialog_adicionar_noticia, null)
        val tituloEditText = view.findViewById<EditText>(R.id.edit_text_titulo)
        val conteudoEditText = view.findViewById<EditText>(R.id.edit_text_conteudo)
        
        builder.setView(view)
        
        builder.setPositiveButton("Adicionar") { _, _ ->
            val titulo = tituloEditText.text?.toString() ?: ""
            val conteudo = conteudoEditText.text?.toString() ?: ""
            
            if (titulo.isNotEmpty() && conteudo.isNotEmpty()) {
                val novaNoticia = Noticia(
                    noticias.size + 1,
                    titulo,
                    "Hoje",
                    conteudo
                )
                
                noticias.add(0, novaNoticia) // Adicionar no início da lista
                noticiaAdapter.notifyItemInserted(0)
                binding.recyclerView.scrollToPosition(0)
            }
        }
        
        builder.setNegativeButton("Cancelar", null)
        
        builder.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("HomeFragment", "onDestroyView called")
        _binding = null
    }
}