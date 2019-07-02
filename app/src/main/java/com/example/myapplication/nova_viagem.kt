package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.myapplication.dao.Database
import com.example.myapplication.domain.Destino
import com.example.myapplication.domain.Viagem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class nova_viagem : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val v = inflater.inflate(R.layout.fragment_nova_viagem, container, false)
        var cmbDestino = v.findViewById<Spinner>(R.id.cmbDestino)
        var rdnTipoViagem = v.findViewById<RadioGroup>(R.id.rdnTipoViagem)

        var txtDataChegada = v.findViewById<EditText>(R.id.txtDataChegada)
        var txtDataPartida = v.findViewById<EditText>(R.id.txtDataPartida)
        var txtOrcamento = v.findViewById<EditText>(R.id.txtOrcamento)
        var txtQuantidadePessoas = v.findViewById<EditText>(R.id.txtQuantidadePessoas)

        var rdnTipoViagemSelecionado = v.findViewById<RadioButton>(rdnTipoViagem.checkedRadioButtonId)


        // Create an ArrayAdapter using a simple spinner layout and languages array
        GlobalScope.launch(Dispatchers.Main) { // launch a new coroutine in background and continue
            val destinos = withContext(Dispatchers.IO) {
                val destinoDao = Database.getInstance(requireContext()).destinoDao()
                destinoDao.findAll()
            }

            val aa = ArrayAdapter<Destino>(requireContext(), android.R.layout.simple_spinner_item, destinos)
            // Set layout to use when the list of choices appear
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Set Adapter to Spinner
            cmbDestino!!.setAdapter(aa)
        }

        val dt_chegada = txtDataChegada.text.trim().toString()
        val dt_partida = txtDataPartida.text.trim().toString()

        var sn_lazer = 0
        // Get the checked radio button id from radio group
        var id: Int = rdnTipoViagem.checkedRadioButtonId
        if (id!=-1){ // If any radio button checked from radio group
             if (id == R.id.rdbLazer){
                 sn_lazer = 1
             }
        }


        val btnSalvar = v.findViewById<Button>(R.id.btnSalvar)
        btnSalvar.setOnClickListener {
            if(validaForm(
                    cmbDestino.selectedItemId,
                    sn_lazer,
                    dt_chegada,
                    dt_partida,
                    if(txtOrcamento.text.trim().toString() != "") txtOrcamento.text.trim().toString().toDouble() else -1.0,
                    if(txtQuantidadePessoas.text.trim().toString() != "") txtQuantidadePessoas.text.trim().toString().toInt() else -1
                )){
                var viagem = Viagem(
                    cmbDestino.selectedItemId,
                    sn_lazer,
                    dt_chegada,
                    dt_partida,
                    txtOrcamento.text.trim().toString().toDouble(),
                    txtQuantidadePessoas.text.trim().toString().toInt()
                )

                GlobalScope.launch(Dispatchers.Main) { // launch a new coroutine in background and continue
                    val viagemDao = Database.getInstance(requireContext()).viagemDao()
                    viagemDao.inserir(viagem)
                }
            }
        }
        // Inflate the layout for this fragment
        return v
    }

    fun validaForm(id_destino: Long,
                   sn_lazer: Int,
                   dt_chegada: String,
                   dt_partida: String,
                   qt_orcamento: Double,
                   qt_pessoas: Int):Boolean{
        var erro = false
        var mensagem = "Corriga os seguintes campos antes de prosseguir: \n"
        if(-1 > id_destino){
            erro = true
            mensagem += "Destino \n"
        }

        if(dt_chegada == ""){
            erro = true
            mensagem += "Data de chegada \n"
        }

        if(dt_partida == ""){
            erro = true
            mensagem += "Data de partida \n"
        }

        if(qt_orcamento < 0){
            erro = true
            mensagem += "Valor de orçamento \n"
        }

        if(qt_pessoas < 0){
            erro = true
            mensagem += "Quantidade de pessoas \n"
        }

        if(erro){
            Toast.makeText(requireContext(), mensagem, Toast.LENGTH_SHORT).show()
            return false
        } else {
            return true
        }
    }

}
