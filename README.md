# Starrim-Lifecycle-KTX
I'm starrim, a 15-year-old student in Chinese. My English isn't very well so the README.md may not be very good.<br>
This extension is my first public project. Because I will take the high school entrance exam, so I may not have time to fix bugs and improve code.
<br> So **everyone** can fork this extension! 

## 1 Introduction
This is an extension of Android Lifecycle, which can help developers use Lifecycle easily. 
<br>It will reduce most of Boilerplate Code in your Kotlin Project.
<br>For example, This is the code to use ViewBinding in your Activity:
```kotlin
    private lateinit var binding: ResultProfileBinding

    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        binding = ResultProfileBinding.inflate(layoutInflater)
        val view = binding.root
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
## 1 介绍
这是一个针对 Android Lifecycle 的扩展，可以帮助开发者更轻松、简洁地使用 Lifecycle组件。
<br>它能够减少绝大多数样板代码的使用
<br>例如, 这是一段来自官方的 ViewBinding 使用实例代码:
```kotlin
    private lateinit var binding: ResultProfileBinding
    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        binding = ResultProfileBinding.inflate(layoutInflater)
        val view = binding.root
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

## 2 Usage
**Pay Attention! Only Kotlin project can use the extensions!** (Sorry Java developer!!)

