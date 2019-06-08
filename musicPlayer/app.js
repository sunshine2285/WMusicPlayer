//app.js
App({
  onLaunch: function() {
  },
  globalData: {
    isPlay: false,
    isLogin: false,
    hasLogin: false,
    userinfo: {
      "userid": "-1",
      "userName": "",
      "avatarUrl": ""
    },
    currentSong:{
      "singer": "许嵩",
      "name": "断桥残雪",
      "id": "004ENQPZ0dHaqy",
      "time": 227,
      "pic": "https://y.gtimg.cn/music/photo_new/T002R300x300M000001jmC6x1RMfh0.jpg",
      "lrc": "http://v1.itooi.cn/tencent/lrc?id=004ENQPZ0dHaqy",
      "url": "http://v1.itooi.cn/tencent/url?id=004ENQPZ0dHaqy"
    },
    indexData: undefined,
    sheetData: undefined,
    nextSongs: []
  }
})