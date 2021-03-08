package com.starrim.lifecyclektx

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

@Suppress("UNCHECKED_CAST")
abstract class FragmentWithBinding<VB:ViewBinding>: Fragment {

    open fun onViewCreating(
        viewBinding: VB,
        savedInstanceState: Bundle?
    ) { }

    private val clazz: Class<VB>
    private var _binding: VB? = null
    protected val binding get() = _binding!!

    constructor(clazz:Class<VB>): super() { this.clazz = clazz }

    constructor(clazz:Class<VB>, @LayoutRes id:Int): super(id) { this.clazz = clazz }

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val method = clazz.getMethod("inflate",LayoutInflater::class.java,ViewGroup::class.java,Boolean::class.java)
        _binding = method.invoke(null,inflater,container,false) as VB
        onViewCreating(binding,savedInstanceState)
        return binding.root
    }

}