# 0.0 写在最前
我在初中时代玩到Minecraft这款游戏，其可玩性非常之高，我同接触到它的大多数人一样， 也深深爱上了这款游戏。
最开始只知道它是使用Java语言编写的，只知道想运行它必须要配置Java运行时环境(Java Runtime Environment)或者Java开发工具包（Java Developement Kit），后来在学习了Java语言之后就有了阅读它源代码的想法，一直延续到昨天才开始动工。
# 0.1 环境
- 操作系统: Windows 10 1903
- 集成开发环境: IntelliJ IDEA 2019.2 （之后简称idea）
- Java开发工具包: 8u221 （之后简称jdk）
- 反编译软件: Minecraft Coder Pack 9.40 （之后简称mcp）
- Minecraft 启动器: Hello Minecraft! Launcher 3.2.130 （之后简称hmcl）
# 0.2 获取源代码
## 0.2.1 在hmc获取反编译所需原料:
- （打开HMCL，左侧侧边栏）游戏->游戏列表->安装新版本->安装新游戏版本-选择游戏版本->稳定版->1.12 稳定版->安装
![](https://upload-images.jianshu.io/upload_images/19785935-5dbb7bf9ecb245b9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
- （安装完成后）->游戏列表->1.12 右边竖排的三个点->游戏文件夹
![](https://upload-images.jianshu.io/upload_images/19785935-7058da8e5a60e65c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
- 游戏文件夹下就是游戏编译归档后的Jar包（在.minecraft/version/1.12），本地库（在version/1.12/1.12-natives，扩展名在Windows下应为.dll，在linux应为.so，在OSX应为.dylib），游戏资源（在.minecraft/assets），库文件（在.minecraft/libraries）
![](https://upload-images.jianshu.io/upload_images/19785935-6729f3606df5fdd5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
- 下载服务器端的Jar包：[minecraft_server.1.12.jar](https://s3.amazonaws.com/Minecraft.Download/versions/1.12/minecraft_server.1.12.jar)
## 0.2.2 在mcp进行反编译
- 准备文件：  
将0.2.1下载的minecraft_server.1.12.jar文件复制到mcp/jars。  
将0.2.1获取的.minecraft/assets，.minecraft/libraries和.minecraft/versions/1.12复制到mcp/jars。
![](https://upload-images.jianshu.io/upload_images/19785935-771552b7f31cc642.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)  
- 反编译
启动mcp/decompile.bat脚本。  
![](https://upload-images.jianshu.io/upload_images/19785935-72c1238a5a4341fa.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)  
大概等待几分钟之后，反编译完成。在mcp/src/minecraft是客户端的源代码，在mcp/src/minecraft_server下是服务器端的源代码。  
![](https://upload-images.jianshu.io/upload_images/19785935-4320ec1324bdce90.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)  
# 0.3 导入源代码
## 0.3.1 创建工程
- （打开idea）->Create New Project->Java->（创建工程，这里的命名为minecraft-client）
## 0.3.2 导入包及Java文件
- 复制mcp/src/minecraft下的所有文件到minecraft-client/src，这里建议用系统的自带资源管理器复制，如果在idea里粘贴，要花更长的时间。
# 0.3.4 导入库，本地库
将.minecraft\libraries下的所有jar包（建议使用"*.jar"搜索目录下的所有Jar包）复制到minecraft-client/jar（需要手动创建）。
- （在idea）->File->Project Structure->
->把Project language level设置为8- lambda, type annotations etc.
![](https://upload-images.jianshu.io/upload_images/19785935-dc73cf57ff01a3e4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
->（在Project Structure->Project Settings->Modules->minecraft-client）
向Module添加JARs：
![](https://upload-images.jianshu.io/upload_images/19785935-c8ae7d53c13af293.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)  
选中JARs：  
![](https://upload-images.jianshu.io/upload_images/19785935-3d4a42a19f68e12b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)  
添加完成：  
![](https://upload-images.jianshu.io/upload_images/19785935-63805410afc83aed.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)  
修改minecraft-client/src/Start.java：把"1.8"修改为"1.12"  
![](https://upload-images.jianshu.io/upload_images/19785935-3e826bc6bf66a9c6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)  
## 0.3.5 编译与试运行
单击Start公共类左边的那个绿色箭头，编译，运行它的main()方法：  
![](https://upload-images.jianshu.io/upload_images/19785935-fec8763122c58c42.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)  
### 0.3.5.1 编译会出现的错误
毫无悬念，会提示: 100 Errors, 100 Warings:
Java: 找不到符号
符号: 类Nullabl
位置: javax.annotation
......
类似这样找不到符号或者程序包的错误有100条
![](https://upload-images.jianshu.io/upload_images/19785935-a383720aeabcde31.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
这是因为有一个用于注解的库没有导进来，为什么没有呢，因为这个注解库在Minecraft里只是用于编译过程，在编译之后就不再需要这个库了。这个库就是[FindBugs JSR305](https://mvnrepository.com/artifact/com.google.code.findbugs/jsr305)，请自行点进去下载，然后参考0.3.4导入，导入之后就可以正常编译了。
### 0.3.5.2 运行会出现的错误
![](https://upload-images.jianshu.io/upload_images/19785935-fc376b24d97370fb.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
由图中的控制台输出可见，它有一个错误和一个异常，这里先解决错误。根据提示，这个错误是因为没有log4j2的配置文件，所以就要配置一个：
新建文件：
log4j2.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
        </Console>
    </Appenders>
    <Loggers>
        <!-- 这是设置某个包的级别 -->
        <Logger name="net.minecraft" level="trace"/>
        <!-- 默认的日志级别 :是有依赖关系的，比如说指定warn,那么它输出warn,error 和fatal级别的日志
             trace,
             debug,
             info,
             warn,
             error,
             fatal
        -->
        <!-- 这是设置默认的日志级别 -->
        <Root level="warn">
            <AppenderRef ref="Console"/>
        </Root>
    </Loggers>
</Configuration>
```
放到minecraft-client/src下：  
![注意：必须命名为log4j2.xml](https://upload-images.jianshu.io/upload_images/19785935-5c717aa3914002a3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)  
再次运行：  
![](https://upload-images.jianshu.io/upload_images/19785935-9f8f430f75fd828c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)  
可以看到已经没有这个错误了。  
### 0.3.5.3 运行会出现的异常
这个异常是UnsatisfiedLinkError，输出的信息是no lwjgl64 in java.library.path，这是因为没有设置lwjgl到Java本地库。参考LWJGL的官方文档：[Setting Up LWJGL with IntelliJ IDEA](http://wiki.lwjgl.org/wiki/Setting_Up_LWJGL_with_IntelliJ_IDEA.html)，
在这里我把.minecraft\versions\1.12\1.12-natives拷贝到D盘根目录，再在Run/Debug Configuration的VM options里添加：-Djava.library.path="D:\1.12-natives"  
![](https://upload-images.jianshu.io/upload_images/19785935-eeaf883f370596a4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)  
再次运行，可以看到已经正常运行了：  
![](https://upload-images.jianshu.io/upload_images/19785935-d1e7fd5fec2ee393.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)  
当然这里也还是有错误，在之后再着手解决，至此，已经完全完成Minecraft1.12客户端和服务器端的源码反编译与导入至IDEA与解决错误异常。  