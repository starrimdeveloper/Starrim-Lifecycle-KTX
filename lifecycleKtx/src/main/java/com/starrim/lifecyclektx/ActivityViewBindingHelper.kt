package com.starrim.lifecyclektx

import android.view.LayoutInflater
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

@Suppress("UNCHECKED_CAST")
class ActivityViewBindingHelper<VB:ViewBinding>(activity:AppCompatActivity, clazz: Class<VB>): ViewBindingHelper<VB>() {

    override val binding by lazy { clazz.getMethod("inflate",LayoutInflater::class.java).invoke(null,activity.layoutInflater) as VB }

    init {
        activity.lifecycle.addObserver(object :DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                super.onCreate(owner)
                activity.setContentView(binding.root)
                owner.lifecycle.removeObserver(this)
            }
        })
    }

}