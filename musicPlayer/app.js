//app.js
App({
  onLaunch: function() {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo

              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  },
  globalData: {
    userInfo: null,
    playing: false,
    stopping: false,
    currentSong:{
      "singer": "许嵩",
      "name": "断桥残雪",
      "id": "004ENQPZ0dHaqy",
      "time": 227,
      "pic": "https://y.gtimg.cn/music/photo_new/T002R300x300M000001jmC6x1RMfh0.jpg?max_age=2592000",
      "lrc": "http://v1.itooi.cn/tencent/lrc?id=004ENQPZ0dHaqy",
      "url": "http://v1.itooi.cn/tencent/url?id=004ENQPZ0dHaqy"
    },
    nextSongs: []
  }
})