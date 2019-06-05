//app.js
App({
  onLaunch: function() {

    // var that = this;
    // wx.showLoading({
    //   title: '载入中',
    // })
    // console.log("hello");
    // wx.login({
    //   success(res) {
    //     if (res.code) {
    //       wx.request({
    //         url: 'http://localhost:8080/musicServer/login',
    //         data: {
    //           code: res.code
    //         },
    //         success(res){
    //           wx.hideLoading();
    //           if(res.data.userid != -1){
    //             console.log("该用户尚已注册");
                

    //           }else {
    //             console.log("该用户尚未注册");
    //             that.globalData.userInfo = res.data;
    //             console.log(that.globalData.userInfo);
    //           }
    //         }
    //       })
    //     }
    //   },
    //   fail(res){
    //     console.log(res);
    //     wx.hideLoading();
    //     wx.showModal({
    //       title: '提示',
    //       content: '请检查您的网络连接',
    //       showCancel: false,
    //     })
    //   }
    // })
  },
  globalData: {
    userInfo: null,
    isPlay: false,
    IsStop: true,
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