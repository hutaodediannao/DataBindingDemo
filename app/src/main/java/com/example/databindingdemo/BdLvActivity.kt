package com.example.databindingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.BaseObservable
import androidx.databinding.BindingConversion
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.databindingdemo.databinding.ActivityBdLvBinding

class BdLvActivity : AppCompatActivity() {

    private val person = Person("胡涛", 33)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bind = DataBindingUtil.setContentView<ActivityBdLvBinding>(this, R.layout.activity_bd_lv)
        val personViewModel = PersonViewModel(person)
        bind.pv = personViewModel
        bind.lifecycleOwner = this


    }
}

data class Person(var name: String, var age: Int) {
}

class PersonViewModel(var person: Person) : ViewModel() {
    val dataLv by lazy { MutableLiveData(person) }
    fun plusAge() {
        person.age = person.age.plus(1)
        dataLv.value = person
    }

    fun changeName() {
        person.name = "这是修改好的Name:${System.nanoTime()}"
        dataLv.value = person
    }
}

open class MyObservableViewModel : ViewModel(), Observable {

    private val callbacks: PropertyChangeRegistry = PropertyChangeRegistry()
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }

    fun notifyChange() {
        callbacks.notifyCallbacks(this, 0, null)
    }

    fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }
}



