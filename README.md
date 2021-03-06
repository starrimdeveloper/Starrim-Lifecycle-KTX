# Starrim-Lifecycle-KTX
I'm Starrim, a 16-year-old(in 2021) student in Chinese. My English isn't very well so the document may not be very good. Don't care too much!(smile)<br>
This extension is my first public project. I will take the high school entrance exam in June, 2021 so I may not have enough time to fix bugs and improve code.
<br> **Everyone** can fork this extension! 
<br> When you find any bugs or want to give me any suggestions, send me **E-malls** or **discuss** here!
<br>

我是Starrim，一个16岁（2021年）的中国学生！我的英语太菜了所以有的文档可能写的不是很好。但是不要太注意啦！（被打）
<br>这个扩展是我的第一次公开项目尝试。因为6月份要中考了所以可能不会有足够的时间去维护这个项目。但是大家都可以 Fork 这个项目来用，也可以帮助我改进这个项目！
<br>如果在用的过程中遇到了啥问题可以给我 **发邮件** 或者 **在这里的 Issues 或者 Discussions 讨论**，只要看到我都会处理
> E-mall address: **starrim0725@163.com**

Developer **Starrim**, 2021-2-17

### Pay attention:
Only **Kotlin** project can use it because this project provides lots of Delegate(in Kotlin). Sorry Java developers!!
### 使用前请注意:
只有 **Kotlin** 项目才能使用这个扩展，因为在很多地方用到了 Kotlin 的委托，对不起啦 Java 开发者们（Java 没有委托属性）

## 1.Introduction
This is an extension of Android Lifecycle, which can help developers use Lifecycle easily. 
<br>It will reduce most of Boilerplate Code in your Kotlin Project.
<br>For example, This is the code to use ViewBinding in your Activity:
```kotlin
    // Boilerplate Code 1
    private lateinit var binding: ResultProfileBinding

    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        // Boilerplate Code 2
        binding = ResultProfileBinding.inflate(layoutInflater)
        // Boilerplate Code 3
        val view = binding.root
        // Boilerplate Code 4
        setContentView(view)
    }
```

But after using this extension, you can only use **ONE** property to use ViewBinding:
```kotlin
    val binding by viewBinding<ResultProfileBinding>()
```
Also you needn't set ContentView in your Activity. All views can be use now!
```kotlin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.helloText.text = "Success"
    }
```
Most of functions in this extension are **only use ONE property**！
## 1.介绍
这是一个针对 Android Lifecycle 的扩展，可以帮助开发者更轻松、简洁地使用 Lifecycle组件。
<br>它能够减少绝大多数样板代码的使用
<br>例如, 这是一段来自官方的 ViewBinding 使用实例代码:
```kotlin
    // 样板代码1
    private lateinit var binding: ResultProfileBinding
    
    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        // 样板代码2
        binding = ResultProfileBinding.inflate(layoutInflater)
        // 样板代码3
        val view = binding.root
        // 样板代码4
        setContentView(view)
    }
```
但使用了这里的扩展方法之后，只需要一行参数委托声明就可以使用 ViewBinding了:
```kotlin
    val binding by viewBinding<ResultProfileBinding>()
```
而且你可以省去 setContentView 方法的调用，一切视图都能够直接调用了。一切都交给一个属性委托来搞定！
```kotlin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.helloText.text = "Success"
    }
```
#### 本扩展的**绝大多数方法**都像这样，**只需要一行代码**解决！

## 2.How to get it
Firstly, download its source and **import** the folder name *lifecycleKtx* as a **model** in your project.<br>
Then, implement the dependency in your **model** build.gradle (such as app/ where you **need to use the extension** **as following:**
```groovy
dependencies {
    //......
    implementation project(':lifecycleKtx')
}
```
OK! Now you can use this extension in your project!

## 2.怎么引用
首先把本项目的源代码下载下来（推荐下载release里面的zip），并且 **作为一个模块导入** 到你自己的项目里面。<br>
然后**像下面这样**在你需要用到扩展的模块里面**引用依赖**
```groovy
dependencies {
    //......
    implementation project(':lifecycleKtx')
}
```
好了！现在你就能够享受扩展为你带来的便利了！

## 3.Usage
**Pay Attention! Only Kotlin project can use the extensions!** (Sorry Java developer!!)<br>
If you want to use Lifecycle Starrim-Extensions, be sure that you're using **Kotlin, AndroidX and AppCompat**(Lifecycle require these support) and **configure Lifecycle features** according to Google Documents.<br>
Although these samples are in Activity, **functions of Fragment are similar to them!**
### View Binding
```kotlin
// Top Functions, they're similar.
fun <VB:ViewBinding> AppCompatActivity.viewBinding(clazz: Class<VB>)
inline fun <reified VB:ViewBinding> AppCompatActivity.viewBinding()
```
Usage:
```kotlin
val binding by viewBinding<ActivityMainBinding>()
```
Sample:
```kotlin
class MainActivity : AppCompatActivity() {

    // There's a class named ActivityMainBinding automatically generated by Androud ViewBinding.
    // Set Binding property such as here:
    private val binding by viewBinding<ActivityMainBinding>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Use all Views here.
    }

}
```
Pay attention! **Don't use this binding in the function** *AppCompatActivity.OnDestroy()*

### ViewModel with LifecycleObserver
>Your ViewModel **must extend ViewModel and implement LifecycleObserver or DefaultLifecycleBoserver**
```kotlin
// For AppCompatActivity
fun <VM> AppCompatActivity.viewModelWithObserver(vm:Class<VM>) where VM: ViewModel, VM:LifecycleObserver
inline fun <reified VM> AppCompatActivity.viewModelWithObserver() where VM: ViewModel, VM:LifecycleObserver

//For androidx Fragment
fun <VM> Fragment.viewModelWithObserver(vm:Class<VM>) where VM: ViewModel, VM:LifecycleObserver
inline fun <reified VM> Fragment.viewModelWithObserver() where VM: ViewModel, VM:LifecycleObserver
```
Usage:
```kotlin
val model by viewModelWithObserver<XXXViewModel>()
```
Sample:
```kotlin
// Your ViewModel shoud be similar to that:
class MainViewModel: ViewModel(), LifecycleObserver {
    //......
}

class MainActivity : AppCompatActivity() {
    // Set ViewModel with LivecycleObserver here as following:
    private val model by viewModelWithObserver<MainViewModel>()
    // Then you can use model everywhere in this Activity.
}
```

### ViewModel Only
> which usage is similar to that of ViewModel with Observer
```kotlin
// For AppCompatActivity
fun <VM:ViewModel> AppCompatActivity.viewModel(vm: Class<VM>)
inline fun <reified VM:ViewModel> AppCompatActivity.viewModel()

// For androidx Fragment
fun <VM:ViewModel> Fragment.viewModel(vm: Class<VM>)
inline fun <reified VM:ViewModel> Fragment.viewModel()
```
Usage:
```kotlin
val model by viewModel<XXXViewModel>()
```
Sample:
```kotlin
// Your ViewModel shoud be similar to that:
class MainViewModel: ViewModel() {
    //......
}

class MainActivity : AppCompatActivity() {
    // Set ViewModel with LivecycleObserver here as following:
    private val model by viewModel<MainViewModel>()
    // Then you can use model everywhere in this Activity.
}
```

## 3.使用
**注意，只有 Kotlin 才能使用这些扩展（因为要用到属性委托）** (对不起了Java人)<br>
如果你要用这些扩展, 请保证你的项目正在使用用 **Kotlin, AndroidX 和 AppCompat**(Lifecycle 需要这些组件) 并且按照官方的教程 **配置好Gradle** <br>
尽管下面的示例都是关于 Activity 的, **但Fragment的方法基本上也是一样的**

### View Binding（绑定视图）
```kotlin
// Top Functions, they're similar.
fun <VB:ViewBinding> AppCompatActivity.viewBinding(clazz: Class<VB>)
inline fun <reified VB:ViewBinding> AppCompatActivity.viewBinding()
```
使用代码:
```kotlin
val binding by viewBinding<ActivityMainBinding>()
```
示范:
```kotlin
class MainActivity : AppCompatActivity() {

    // 这里 ViewBinding 系统已经根据我们的布局文件自动生成了 ActivityMainBinding 类
    // 像下面这样在这个地方设置好 Activity 的绑定视图:
    private val binding by viewBinding<ActivityMainBinding>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //在这里 binding 就可以直接用了
    }

}
```
注意! 不要在 *AppCompatActivity.OnDestroy()* 方法中使用 ViewBinding！可能会导致程序崩溃

### ViewModel with LifecycleObserver（实现了 LifecycleObserver 的 ViewModel）
> 如果要使用这些方法，**你的 ViewModel 必须实现 LifecycleObserver接口 或者 DefaultLifecycleBoserver接口**
```kotlin
// For AppCompatActivity
fun <VM> AppCompatActivity.viewModelWithObserver(vm:Class<VM>) where VM: ViewModel, VM:LifecycleObserver
inline fun <reified VM> AppCompatActivity.viewModelWithObserver() where VM: ViewModel, VM:LifecycleObserver

//For androidx Fragment
fun <VM> Fragment.viewModelWithObserver(vm:Class<VM>) where VM: ViewModel, VM:LifecycleObserver
inline fun <reified VM> Fragment.viewModelWithObserver() where VM: ViewModel, VM:LifecycleObserver
```
使用代码:
```kotlin
val model by viewModelWithObserver<XXXViewModel>()
```
示范:
```kotlin
// 你的 ViewModel 应该像这样:
class MainViewModel: ViewModel(), LifecycleObserver {
    //你自己定义的其他逻辑 
}

class MainActivity : AppCompatActivity() {
    // 像这样设置好你的 ViewModel:
    private val model by viewModelWithObserver<MainViewModel>()
    // 然后你就能在这个Activity里面使用你的 ViewModel了
    // 这个 ViewModel 是一个 Lifecycle Observer
    // 能够根据你的编程在 Activity 状态变化的时候执行你定义的逻辑
}
```

### ViewModel Only
> 它的用法和 实现了LifecycleObserver的ViewModel 很相似
```kotlin
// For AppCompatActivity
fun <VM:ViewModel> AppCompatActivity.viewModel(vm: Class<VM>)
inline fun <reified VM:ViewModel> AppCompatActivity.viewModel()

// For androidx Fragment
fun <VM:ViewModel> Fragment.viewModel(vm: Class<VM>)
inline fun <reified VM:ViewModel> Fragment.viewModel()
```
使用代码:
```kotlin
val model by viewModel<XXXViewModel>()
```
示范:
```kotlin
// 你的 ViewModel 应该像这样:
class MainViewModel: ViewModel() {
    //......
}

class MainActivity : AppCompatActivity() {
   
    private val model by viewModel<MainViewModel>()
    
}
```
