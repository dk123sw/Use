>一个较完整的apk,比起Transition代码量翻了好几倍，而且用了好多开源库
吐槽一下不知道多久才能看明白这个程序 :sleepy: 不定期更新....

#### 3种android架构的解释
[http://http://www.tianmaying.com/tutorial/AndroidMVC](http://http://www.tianmaying.com/tutorial/AndroidMVC)
##### 图片加载：Picasso
使用github上的开源库 https://github.com/square/picasso

用到.css类型的文件  CSS就是一种叫做样式表（stylesheet）的技术。做网页使用...

>       <application>中使用了<android:largeHeap="true">
一个应用如果使用了largeHeap，会请求系统为Dalvik虚拟机分配更大的内存空间。
详解http://droidyue.com/blog/2015/08/01/dive-into-android-large-heap/index.html

##### 网络请求 Rxjava
https://gank.io/post/560e15be2dca930e00da1083 这作者的解析十分详细！！
RxAndroid是RxJava的一个针对Android平台的扩展。它包含了一些能够简化Android开发的工具。

##### otto ( http://blog.csdn.net/lzyzsd/article/details/42016681)
##### retrofit	
https://realm.io/cn/news/droidcon-jake-wharton-simple-http-retrofit-2/
##### 关于Material design新控件的描述
https://realm.io/cn/news/kau-michael-wolfson-material-design-everywhere/
##### okhttp
http://blog.csdn.net/lmj623565791/article/details/47911083
##### RxAndroid的使用方法  RecycleView,retrofit,rx的混合使用
http://blog.csdn.net/caroline_wendy/article/details/50444461
##### 	picasso
   picasso是Square公司开源的一个Android图形缓存库，地址http://square.github.io/picasso/，可以实现图片下载和缓存功能。
仅仅只需要一行代码就能完全实现图片的异步加载：
Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(imageView);
http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2014/0731/1639.html
http://www.jianshu.com/p/6b746c904a49singToolbarLayout 可折叠的ToolBar
http://www.open-
##### 采用nineoldandroids，可以在3.0以前的系统上使用属性动画
##### photoview实现图片的双指缩放和双击放大缩小
http://www.cnblogs.com/tianzhijiexian/p/3888664.html
##### NumberProgressBar
http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2014/0813/1645.html
otto
Otto 是square公司出的一个事件库（pub/sub模式），用来简化应用程序组件之间的通讯
就这三个方法@Subscribe @Produce bus.register(this);
##### Butter Knife，专门为Android View设计的绑定注解
http://www.jianshu.com/p/9ad21e548b69
##### RecycleView
http://www.jianshu.com/p/16712681731e
##### ViewPager与TabLayout
http://www.bubuko.com/infodetail-977938.html
##### LiteOrm：Android高性能数据库框架
开源代码 -> https://github.com/litesuits/android-lite-orm
详细解析 http://blog.csdn.net/u014099894/article/details/51586500
android序列化 什么是序列化
http://blog.csdn.net/qq_23547831/article/details/51779528
