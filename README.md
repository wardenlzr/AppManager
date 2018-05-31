# AppManager
初始化项目，及TerrificLibrary

一个简约的Material Design 风格的APP卸载程序，
通过此APP，整理一些优秀的第三方库到TerrificLibrary，每一个都经过深思熟虑之后才会引入。

# 已引入优秀第三方
</br>[BaseRecyclerViewAdapterHelper](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)
</br>[IconFont](http://www.iconfont.cn/help/detail?spm=a313x.7781069.1998910419.15&helptype=code)
</br>[MaterialDialogs](https://github.com/afollestad/material-dialogs)
</br>[Glide](https://github.com/bumptech/glide)
</br>[Slidr](https://github.com/r0adkll/Slidr)
</br>[]()
</br>[]()
</br>[]()
</br>[]()
</br>[]()
...
</br>[AndroidUtilCode](https://github.com/Blankj/AndroidUtilCode)
* ## 基于[AndroidUtilCode](https://github.com/Blankj/AndroidUtilCode)新增以下工具
* ### Glide 相关 -> [GlideUtils.java][activity.java]
```
setPlaceholder        : 设置全局占位图
loadImg               : 加载图片，带默认占位图（也可单独设置占位图）
loadCircleImg         : 加载圆形图片，带默认占位图（也可单独设置占位图）
```
* ### Color 相关 -> [ColorUtils.java][activity.java]
```
getRandomColor        : 获取一个随机颜色
getResourcesColor     : 根据颜色资源Id，取得颜色的int色值
hex2IntColor          : 将十六进制 颜色代码 转换为 int
setColorAlpha         : 设置颜色透明度
```
* ### Number 相关 -> [NumberUtils.java][activity.java]
```
get2Decimal         : 获取2位小数
toInt               : 转换str为int,如果转换失败则返回0，（如果转换失败则返回defaultValue）
toLong              : 转换str为long，如果转换失败则返回0L，（如果转换失败则返回defaultValue）
toFloat             : 转换str为float，如果转换失败则返回0.0f，（如果转换失败则返回defaultValue）
toDouble            : 转换str为double，如果转换失败则返回0.0d，（如果转换失败则返回defaultValue）
toByte              : 转换str为byte，如果转换失败则返回0，（如果转换失败则返回defaultValue）
isNumber            : 校验String是否是一个有效的Java number
```


</br></br></br>