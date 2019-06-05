//app.js
App({
  onLaunch: function() {
    wx.showLoading({
      title: '载入中',
    })
    console.log("hello");
    wx.login({
      success(res) {
        if (res.code) {
          wx.request({
            url: 'http://localhost:8080/musicServer/login',
            data: {
              code: res.code
            }
          })
        } else {
        }
      }
    })
  },
  globalData: {
    userInfo: null,
    IsPlay: true,
    IsStopp: true,
    IsPause: false,
    currentSong:{
      "singer": "许嵩",
      "name": "断桥残雪",
      "id": "004ENQPZ0dHaqy",
      "time": 227,
      "pic": "https://y.gtimg.cn/music/photo_new/T002R300x300M000001jmC6x1RMfh0.jpg",
      "lrc": "http://v1.itooi.cn/tencent/lrc?id=004ENQPZ0dHaqy",
      "url": "http://v1.itooi.cn/tencent/url?id=004ENQPZ0dHaqy"
    },
    nextSongs: []
  }
})