## AIDL Demo

###### 服务端

 * 1 定义一个AIDL接口
  * 1.1 如果是一个bean对象，需要一个parcelable类型的aidl
 * 2 为AIDL（Service）实现对应Stub（主要是实现aidl接口）
 * 3 将服务“暴露”给客户程序使用
  * 3.1 注册文件注册service
  * 3.2 intent启动service

###### 客户端

 * 1.定义AIDL接口（路径和服务端要一致，如果有Bean，路径也要一致）
 * 2.通过Intent启动对应的Service
  * 2.1 ServiceConnection中onServiceConnected方法得到Aidl接口对象
 * 3.接下来就可以使用了