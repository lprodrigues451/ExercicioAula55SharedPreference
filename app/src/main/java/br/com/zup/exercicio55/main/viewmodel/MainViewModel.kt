package br.com.zup.exercicio55.main.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.zup.exercicio55.main.model.MainModel

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _savedData: MutableLiveData<MainModel> = MutableLiveData()
    val savedData: LiveData<MainModel> = _savedData

    private val _saveDataFlag: MutableLiveData<Boolean> = MutableLiveData()
    val saveDataFlag: LiveData<Boolean> = _saveDataFlag

    val pref = application.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
    val prefEditor = pref.edit()

    fun getSavedData() {
        try {
            val teste = pref.getString("teste", "").toString()
            val testeDois = pref.getInt("testeDois", 0)
            val savedUser = MainModel(teste, testeDois)
            _savedData.value = savedUser
            _saveDataFlag.value = pref.getBoolean("testeFlag", false)
        } catch (e: Exception) {
            Log.i("Error", "------> ${e.message}")
        }
    }

    fun authentication(main: MainModel, flagSaveData: Boolean) {
        try {
            prefEditor.putBoolean("testeFlag", flagSaveData)
            if (flagSaveData) {
                prefEditor.putString("teste", main.teste)
                prefEditor.putInt("testeDois", main.testeDois)
                prefEditor.apply()
            } else {
                prefEditor.remove("teste")
                prefEditor.remove("testeDois")
                prefEditor.apply()
            }
        } catch (ex: Exception) {
            Log.i("Error", "------> ${ex.message}")
        }
    }
}