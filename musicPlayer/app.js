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
    currentSong: undefined,
    indexData: undefined,
    sheetData: undefined,
    coverUrl: undefined,
    nextSongs: []
  }
})