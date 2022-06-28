package br.com.zup.exercicio55.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.zup.exercicio55.databinding.ActivityMainBinding
import br.com.zup.exercicio55.main.model.MainModel
import br.com.zup.exercicio55.main.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observers()
        viewModel.getSavedData()

        binding.bTeste.setOnClickListener {
            authenticate()
            Toast.makeText(this, "salvando estado", Toast.LENGTH_SHORT).show()
        }
    }
    fun authenticate(){
        val teste = binding.teste.text.toString()
        val testeDois =  binding.testeDois.text.toString().toInt()
        val login = MainModel(teste,testeDois)
        viewModel.authentication(login, binding.switch1.isChecked)
    }

    fun observers(){
        viewModel.savedData.observe(this){
            binding.teste.setText(it.teste)
            binding.testeDois.setText(it.testeDois.toString())
        }
        viewModel.saveDataFlag.observe(this){
            binding.switch1.isChecked = it
        }
    }
}