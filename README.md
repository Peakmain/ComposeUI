# composeProject

> **常用Compose UI封装——提高Android Compose UI开发**

**使用文档链接:** https://www.yuque.com/peakmain/alaof0/fis43sflpl602m18

**How To**

- Step 1. Add the JitPack repository to your build file
- gradle

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```
- gradle.kts
```kotlin
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
  }
}
```
- Step 2. Add the dependency
```groovy
dependencies {
    implementation("com.github.Peakmain:ComposeUI:+")
}
```
#### 关于我

- 简书： https://www.jianshu.com/u/3ff32f5aea98
- 我的GitHub地址：https://github.com/Peakmain

#### Donations

如果您觉得我的开源库帮您节省了大量的开发时间，请扫描下方的二维码随意打赏，您的支持将激励我不断前进
![微信](https://user-images.githubusercontent.com/26482737/184805287-0561a7e2-da13-4ef4-b367-c5e8672c121d.jpg)
![支付宝](https://user-images.githubusercontent.com/26482737/184805306-f44511a7-7660-4fe1-9f07-305005576c2c.jpg)
