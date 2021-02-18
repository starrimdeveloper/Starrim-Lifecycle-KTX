package com.starrim.lifecyclektx

import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
// Created by Starrim on 2021-2-17

/** 2 functions for View Binding Delegates: (ONLY in AppCompatActivity)
 *
 * BE SURE that the bindings are used after ON_CREATE and before ON_DESTROY.
 * @see ViewBindingHelper
 * @param clazz The type of <b>REAL</b> ViewBinding.
 *
 */
fun <VB:ViewBinding> AppCompatActivity.viewBinding(clazz: Class<VB>)
        = ViewBindingHelper(this,clazz)
inline fun <reified VB:ViewBinding> AppCompatActivity.viewBinding()
        = viewBinding(VB::class.java)

/** 4 functions for ViewModel with LifecycleObserver binding:
 *
 * When you need both ViewModel and LifecycleObserver in the Activity or Fragment,
 * You can make your ViewModel implement LifecycleObserver and use these functions
 *
 * @see ViewModelWithObserverHelper
 * @see LifecycleObserver
 * @see ViewModel
 *
 */
fun <VM> AppCompatActivity.viewModelWithObserver(vm:Class<VM>)
        where VM: ViewModel, VM:LifecycleObserver = ViewModelWithObserverHelper(this,vm)
inline fun <reified VM> AppCompatActivity.viewModelWithObserver()
        where VM: ViewModel, VM:LifecycleObserver = viewModelWithObserver(VM::class.java)

fun <VM> Fragment.viewModelWithObserver(vm:Class<VM>)
        where VM: ViewModel, VM:LifecycleObserver = ViewModelWithObserverHelper(this,vm)
inline fun <reified VM> Fragment.viewModelWithObserver()
        where VM: ViewModel, VM:LifecycleObserver = viewModelWithObserver(VM::class.java)

/**
 * Function for ViewModel ONLY.
 *
 * If you don't want to use ViewModel with a LifecycleObserver,
 * you should use these functions.
 * They can provide a delegate for your ViewModel to initialize on it's own without your code.
 *
 * @see ViewModelProvider
 * @see ViewModelOnlyHelper
 *
 */
fun <VM:ViewModel> AppCompatActivity.viewModel(vm: Class<VM>) = ViewModelOnlyHelper(vm,this)
inline fun <reified VM:ViewModel> AppCompatActivity.viewModel() = viewModel(VM::class.java)

fun <VM:ViewModel> Fragment.viewModel(vm: Class<VM>) = ViewModelOnlyHelper(vm,this)
inline fun <reified VM:ViewModel> Fragment.viewModel() = viewModel(VM::class.java)

/**
 * Function for liveData
 *
 * The function observer(owner,block:(T)->Unit) is deprecate in livedata-ktx
 * but I think it's very necessary for us to code.
 * So I provide this extension function.
 *
 * @see LiveData.observe
 *
 */
@MainThread
inline fun <T> LiveData<T>.onChange(owner: LifecycleOwner, crossinline block:(T)->Unit)
        = observe(owner, Observer { block(it) })