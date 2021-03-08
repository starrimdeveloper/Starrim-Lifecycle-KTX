package com.starrim.lifecyclektx

import android.view.LayoutInflater
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

@Suppress("UNCHECKED_CAST")
@MainThread
abstract class ViewBindingHelper<VB: ViewBinding> : ReadOnlyProperty<Any?, VB?> {

    protected abstract val binding: VB

    @MainThread
    override operator fun getValue(thisRef: Any?, property: KProperty<*>): VB = binding

}