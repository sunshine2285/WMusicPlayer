## 主要页面

#### 1. index Page

* 页面描述：乐库，展示音乐和歌单

* 组成部分：
  * 推荐歌单
  * 新歌首发
  * 热门歌曲

#### 2. user Page

* 页面描述：用户中心，显示用户的登录信息以及用户歌单，并提供歌单管理

* 组成部分：
  * 用户信息
  * 歌单
  * 收藏

#### 3. song Page

* 页面描述：音乐播放的详情页面，展示歌曲的详细信息，歌曲评论，以及发表评论

* 组成部分：
  * 歌曲封面图片
  * 歌词（备选）
  * 播放进度条（备选）
  * 发表评论
  * 歌曲的评论

#### 4. sheetlist Page

* 页面描述：歌单组成的列表
* 组成部分：
  * 歌单列表
  * 歌单列表内的每一项包含，歌单封面、歌单名称、歌单的前三首歌曲（歌曲名 - 歌手）

#### 5. sheet Page

* 页面描述：歌单的详情界面
* 组成部分：
  * 歌单封面
  * 最后更新日期
  * 播放所有，播放模式选择
  * 歌曲列表：每一项包含，歌曲名，歌手，播放按钮，更多选项（添加到我的歌单、移除歌曲（如果是我的歌单）、收藏）

## 模块划分

#### 1. 登录注册模块

* 自动登录

```flow
st=>start: 开始运行小程序
appjs=>operation: app.js中onLaunch()函数执行
wxLogin1=>condition: wx.login()
获取code
wxRequest=>condition: wx.request()
url:/login
isRegister=>condition: 返回值为userinfo
userid != -1 ？
Failed=>subroutine: wx.showModal:[failed]
register=>operation: 跳转到用户中心请求授权
getUserinfo=>operation: 将获得的用户信息
保存为全局变量GlobalData
end=>end: 登录流程结束

st->appjs->wxLogin1
wxLogin1(no)->Failed->end
wxRequest(no)->Failed->end
wxLogin1(yes)->wxRequest(yes)->isRegister(yes)->getUserinfo->end
isRegister(no)->register->end

```

> 以上流程即可实现自动登录，用户首次使用时授权一次即可

* 用户授权注册

```flow
st=>start: 用户中心
op=>operation: 用户点击登录，弹出授权窗口
isAuthorize=>condition: 授权成功
wxLogin=>condition: wx.login()
获取code
wxRequest=>condition: wx.request()
url:/register
register=>operation: 返回值为userid
保存为全局变量GlobalData
Failed=>subroutine: wx.showModal:[failed]
register=>operation: 用户注册成功
end=>end: 授权注册流程结束

st->op->isAuthorize(no)->Failed
isAuthorize(yes)->wxLogin
wxLogin(no)->Failed->end
wxRequest(no)->Failed->end
wxLogin(yes)->wxRequest(yes)->register->end
```

> **无法自动注册的原因**：微信由于修改 `wx.getUserInfo()` 接口的缘故，获取用户的授权等操作只能通过用户主动点击button来实现，[解决方法](<https://blog.csdn.net/sunshine2285/article/details/90900241>)

#### 2. 音乐播放模块

#### 3. 歌单管理模块