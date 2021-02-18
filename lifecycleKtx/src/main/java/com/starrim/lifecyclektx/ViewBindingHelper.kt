package com.starrim.lifecyclektx

import android.view.LayoutInflater
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

@Suppress("UNCHECKED_CAST")
@MainThread
class ViewBindingHelper<VB: ViewBinding>(activity: AppCompatActivity, clazz: Class<VB>): ReadOnlyProperty<Any?, VB?> {

    private val binding by lazy {
        // Get the static function : <ViewBinding>.inflate(LayoutInflater) -> <ViewBinding>
        clazz.getMethod("inflate",LayoutInflater::class.java).invoke(null, activity.layoutInflater) as VB
    }
    init {
        activity.lifecycle.addObserver(object: DefaultLifecycleObserver {
            @MainThread
            override fun onCreate(owner: LifecycleOwner) {
                super.onCreate(owner)
                binding.run { activity.setContentView(root) }
                owner.lifecycle.removeObserver(this)
            }
        })
    }

    @MainThread
    override operator fun getValue(thisRef: Any?, property: KProperty<*>): VB = binding

}