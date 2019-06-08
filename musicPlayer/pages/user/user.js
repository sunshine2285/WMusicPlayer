// pages/user/user.js
const app = getApp();
Page({
  /**
   * 页面的初始数据
   */
  data: {
    userinfo: {
      "userid": "",
      "userName": "",
      "avatarUrl": ""
    },
    navbarActiveIndex: 0,
    navbarTitle: [
      "最近播放",
      "我的歌单"
    ]
  },

  onNavBarTap: function(event) {
    // 获取点击的navbar的index
    let navbarTapIndex = event.currentTarget.dataset.navbarIndex
    // 设置data属性中的navbarActiveIndex为当前点击的navbar
    this.setData({
      navbarActiveIndex: navbarTapIndex
    })
  },
  onBindAnimationFinish: function({
    detail
  }) {
    // 设置data属性中的navbarActiveIndex为当前点击的navbar
    this.setData({
      navbarActiveIndex: detail.current
    })
  },

  /**
   * 如果之前有授权，直接会获得用户信息
   * 如果没有授权会弹出授权窗口，要求授权
   * 授权成功，获得用户信息。未获得用户授权则userinfo为undefined
   */
  bindLoginAndRegister: function(res) {
    var that = this;
    // 声明一个变量接收用户授权信息
    var userinfo = res.detail.userInfo;
    // 判断是否授权  true 替换微信用户头像
    if (userinfo != undefined) {
      //userid为-1代表用户还没有注册
      if (this.data.userinfo.userid == -1) {
        wx.showLoading({
          title: '登录中',
          mask: true
        })
        wx.login({
          success(res) {
            if (res.code) {
              wx.request({
                url: 'http://localhost:8080/musicServer/register',
                data: {
                  code: res.code,
                  userName: userinfo.nickName,
                  avatarUrl: userinfo.avatarUrl
                },
                success(res) {
                  //注册成功之后只需返回userid即可
                  console.log(res);
                  that.setData({
                    'userinfo.userid': res.data.userid
                  })
                  app.globalData.userinfo.userid = res.userid;
                  wx.hideLoading();
                  wx.showToast({
                    title: '登录成功',
                  })
                  that.setData({
                    'userinfo.userName': userinfo.nickName,
                    'userinfo.avatarUrl': userinfo.avatarUrl,
                    isLogin: true
                  });
                  app.globalData.isLogin = true;
                  app.globalData.userinfo = that.data.userinfo;
                },
                fail(res) {
                  wx.hideLoading();
                  wx.showModal({
                    title: '服务器连接失败',
                    content: '无法连接服务器，请与客服联系',
                    showCancel: false
                  })
                }
              })
            }
          },
          fail(res) {
            wx.hideLoading();
            wx.showModal({
              title: '调用失败',
              content: 'wx.login()失败，请升级微信或者检查网络连接',
              showCancel: false
            })
          }
        })
      }

    } else {
      wx.showModal({
        title: '提示',
        content: '您必须授权才能使用所有功能',
        showCancel: false,
      })
    }

  },

  bindToIndex(e) {
    wx.redirectTo({
      url: '../index/index',
    })
  },
  bindToSong(e) {
    app.globalData.isPlay = true;
    wx.navigateTo({
      url: '../song/song',
    })
  },
  bindToUser(e) {
    wx.redirectTo({
      url: '../user/user',
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.setData({
      isLogin: app.globalData.isLogin
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    console.log("user Show");
    this.setData({
      isPlay: app.globalData.isPlay,
      coverUrl: ((app.globalData.coverUrl == undefined) ? '../../img/icon/music.png' : app.globalData.coverUrl),
      userinfo: app.globalData.userinfo
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})