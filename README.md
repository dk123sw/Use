#源代码在 GPL v3 协议下发布, 使用前, 请确保你了解这个协议!

[LICENSE](/LICENSE)

如有特殊协议方面的请求, 可以与我沟通: drakeet.me@gmail.com

------

<img src="/app/src/main/res/mipmap-hdpi/ic_meizhi_150602.png" width="128" height="128" /> <img src="/app/src/main/res/mipmap-hdpi/ic_meizhi_150619.png" width="128" height="128" />

端午节回家的时候，无聊之中，做了一个专门欣赏妹纸的小应用，数据来自[代码家](https://github.com/daimajia)的干货网站：http://gank.io 我只抓取了妹纸图做了个新的开源小应用：

Google Play: https://play.google.com/store/apps/details?id=me.drakeet.meizhi

直接下载：https://fir.im/mengmeizhi (请尽量 Google Play 下载，如果喜欢，去给个五星好评，非常感谢！)

v2.1(1.82MB)

* 修复 视频播放切换问题；
* 优化 视频播放体验；
* 新增 可爱的初次使用指引；
* 新增 自动收缩浮动刷新按钮；
* 新增 点击主页卡片的文字打开干货页面；
* 新增 干货视频自动横屏播放；
* 新增 打开 Android 等干货条目页面；
* 新增 web 页面标题跑马灯、渐变切换动画效果；
* 新增 登录和缓存 GitHub 帐号功能；
* 特别 照顾魅族用户没有返回键退出视频播放的尴尬状况；

* 大幅优化 主页流畅度和稳定性；
* 大量重构 开源代码，更加优雅和规整；
* 未来：增加 收藏、搜索功能；

<img src="/screenshots/s0.png" alt="screenshot" title="screenshot" width="270" height="486" />   <img src="/screenshots/s6.png" alt="screenshot" title="screenshot" width="270" height="486" />

<img src="/screenshots/s7.jpg" alt="screenshot" title="screenshot" width="270" height="486" />   <img src="/screenshots/s5.png" alt="screenshot" title="screenshot" width="270" height="486" />

<img src="/screenshots/s8.png" alt="screenshot" title="screenshot" width="270" height="486" />   <img src="/screenshots/s9.png" alt="screenshot" title="screenshot" width="270" height="486" />

Contributors

[代码家](https://github.com/daimajia)：增加了首页妹纸卡片点击非图片区域（日期）打开干货页面（http://gank.io）；

[iamwent](https://github.com/iamwent)：Adding feature: saving picture to phone (by long press meizhi image in meizhi detail image page/activity);

###2.0

9号开始重构，开发2.0，至今可谓完成了个完整的干货客户端，不仅仅是看妹纸了，开源又好用，绝对是程序猿必备良心项目… 做得匆忙，但代码自认为还是写得很不错的，用了很多注解和 lambda 表达式，用了 RxJava & Retrofit。UI 交互上，细节和动画效果也不少，为了许多小效果有时连发好几个测试版…