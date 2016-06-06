# DynamicFixDemo

动态修复的Demo

AndFix热补丁原理就是在native动态替换方法java层的代码，通过native层hook java层的代码。

优点

1、因为是动态的，所以不需要重启应用就可以生效
2、支持ART与Dalvik
3、与multidex方案相比，性能会有所提升(Multi Dex需要修改所有class的class_ispreverified标志位，导致运行时性能有所损失)
支持新增加方法
4、支持在新增方法中新增局部变量
5、足够轻量，生成补丁文件简单
6、安全性够高，验证签名

缺点

1、因为是动态的，跳过了类的初始化，设置为初始化完毕，所以对于静态方法、静态成员变量、构造方法或者class.forname()的处理可能会有问题
2、不支持新增成员变量和修改成员变量
3、官方apkPatch工具不支持multidex,但是可以通过修改工具来达到支持multidex的目的
4、由于是在native层替换方法，某些缺心眼厂商可能会修改源生关键部分的native层实现，导致可能在某些特定ROM支持不够好


流程：
1、引入android fix的gradle
dependencies {
    compile 'com.alipay.euler:andfix:0.4.0@aar'
}

2、在BaseApplication初始化修复工具
patchManager = new PatchManager(context);
patchManager.init(“1.0”);//current version

3、加载补丁。一般尽量在Application的onCreate方法中使用
  patchManager.loadPatch();

4、应用补丁
        patchManager.addPatch(path);//path of the patch file that was downloaded

        一般在应用补丁的时候需要加入try－catch，所以这样可以避免一些异常情况
        try {
            String patchFileString = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + APATCH_PATH;
            mPatchManager.addPatch(patchFileString);
        } catch (IOException e) {
            Log.e(TAG, "io:"+e);
        }


附：如何生成.apatch文件
进入到apkpatch目录后，键入以下命令

apkpatch -o ／Users/Chaersi/AndFix/output -k sign.jks -p abc123456 -a alias -e abc123456 -f fix.apk -t bug.apk

字段说明：
-o <output> ： 输出目录
-k <keystore>： 打包所用的keystore
-p <password>： keystore的密码
-a <alias>： keystore 用户别名
-e <alias password>： keystore 用户别名密码
-f <new.apk> ：新版本
-t <old.apk> : 旧版本


说明：
对于andfix修复，主要就是将两次不同的apk进行比对，然后将这两次的apk的差别生成apatch文件，然后通过下载，放入对应目录。
打开APP后进行修复。