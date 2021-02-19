package com.starrim.lifecyclektx

import androidx.lifecycle.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ViewModelWithObserverHelper<VM,OWNER> internal constructor(owner:OWNER, vm:Class<VM>) :ReadOnlyProperty<Any?,VM>
        where VM:ViewModel,VM:LifecycleObserver, OWNER:LifecycleOwner,OWNER:ViewModelStoreOwner {

    private val model by lazy { ViewModelProvider(owner).get(vm) }
    init {
        owner.lifecycle.addObserver(object: DefaultLifecycleObserver {
            override fun onCreate(_ow: LifecycleOwner) {
                super.onCreate(owner)
                owner.lifecycle.addObserver(model)
                _ow.lifecycle.removeObserver(this)
            }
        })
    }

    override operator fun getValue(thisRef: Any?, property: KProperty<*>): VM = model

}