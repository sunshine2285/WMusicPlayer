//app.js
App({
  onLaunch: function() {
  },
  globalData: {
    host:"http://localhost:8080/musicServer",
    isPlay: false,
    isLogin: false,
    hasLogin: false,
    userinfo: {
      "userid": "-1",
      "userName": "",
      "avatarUrl": ""
    },
    currentSong: undefined,
    currentSongid: -1,
    indexData: undefined,
    sheetData: undefined,
    coverUrl: undefined,
  }
})