# qiniu-tools

> 喜欢的麻烦给个Star，你的Star就是对我开源的最大支持和动力。谢谢！！

## 下载地址

> 链接：https://pan.baidu.com/s/1DutgVkJFVd6X5hdBwHM7Zg 
> 提取码：06gy 

## 起因

由于本人平时记笔记写博客时需要将一些例图之类的上传到七牛云图床，之前在Windows平台使用很还用的工具可以一键上传到七牛云，最近将工作环境迁移到Mac环境了，并没有找到合适的方便的工具，于是就好了一点时间自己写了一个。先看效果图吧。

![](http://qiniu.mrain22.cn/1573359587234.gif)

Gif图有点快。大体说一下：

> - 截图后打开`qiniu-tools`界面按`Shift` + `Ctrl` + `V`即可将图片上传到七牛云图床。
> - 也可以复制网页中的图片上传。
> - 当然本地图片也是可以的。
> - 当你觉得刚刚上传的图片不合适时，可以点击删除按钮，删除刚刚上传的图片。

## 使用

### 推荐

如果你是一名程序猿，推荐在安装了java环境下直接运行jar包，将`conf.properties`配置文件放在与jar同级目录下。

```properties
## conf.properties  配置自己的七牛云信息。
access-key=****
secret-key=****
# 七牛配置的域名地址
qiniu-path=***
# 上传到那个存储空间
bucket-name=***
```

### Windows

1. 将`qiniu-tools\app`目录下的`conf.properties`修改为自己的配置。
2. 运行`qiniu-tools\`目录下的`qiniu-tools.exe`即可。

### MAC

1. 修改`qiniu-tools.app/Contents/Java`下的`conf.properties`修改为自己的配置。
2. 运行`qiniu-tools.app`即可。