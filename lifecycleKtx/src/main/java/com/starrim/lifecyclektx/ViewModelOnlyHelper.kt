package com.starrim.lifecyclektx

import androidx.lifecycle.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ViewModelOnlyHelper<VM:ViewModel,OWNER>(vm:Class<VM>,owner: OWNER): ReadOnlyProperty<Any?,VM>
        where OWNER:LifecycleOwner,OWNER:ViewModelStoreOwner {

    private val model by lazy { ViewModelProvider(owner).get(vm) }
    init {
        owner.lifecycle.addObserver(object :DefaultLifecycleObserver {
            override fun onCreate(_o: LifecycleOwner) {
                super.onCreate(owner)
                ::model.invoke()
                _o.lifecycle.removeObserver(this)
            }
        })
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): VM = model

}