// pages/index/index.js
const app = getApp();
const com = require(`../../utils/common.js`);

Page({

  /**
   * 页面的初始数据
   */
  data: {
    isPlay: false,
  },

  bindMoreSheet: function(e) {
    wx.navigateTo({
      url: '../sheetlist/sheetlist',
    });
  },
  bindMoreSong: function(e) {
    wx.navigateTo({
      url: '../songlist/songlist',
    });
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

  bindPlay(e) {
    var currentSong = this.data.hotSonglist[e.currentTarget.dataset.index];
    app.globalData.currentSong = currentSong;
    app.globalData.isPlay = true;
    wx.navigateTo({
      url: '../song/song',
    })
  },

  bindSheet(e) {
    var currentSheet = this.data.recommendSheetlist[e.currentTarget.dataset.index];
    var sheetData = {
      "id": currentSheet.id,
      "name": currentSheet.name,
      "userid": currentSheet.userid,
      "coverUrl": currentSheet.coverUrl,
      "date": currentSheet.date
    }
    app.globalData.sheetData = sheetData;
    wx.navigateTo({
      url: '../sheet/sheet',
    })
  },

  requestIndex(that) {
    wx.request({
      url: 'http://localhost:8080/musicServer/index',
      success(res) {
        if (res.statusCode == 200) {
          console.log(res.data);
          that.setData({
            recommendSheetlist: res.data.recommendSheetlist,
            hotSonglist: res.data.hotSonglist
          })
        } else {
          wx.showModal({
            title: '服务器异常',
            content: "status code:" + res.statusCode + "，请与管理员联系！",
            showCancel: false
          })
        }
      },
      fail(res) {
        wx.showModal({
          title: '网络异常',
          content: '无法连接服务器，请检查网络连接',
          showCancel: false
        })
      }
    })
  },

  login(that) {
    app.globalData.hasLogin = true;
    wx.showLoading({
      title: '正在加载',
      mask: true
    })
    wx.login({
      success(res) {
        if (res.code) {
          wx.request({
            url: 'http://localhost:8080/musicServer/login',
            data: {
              code: res.code
            },
            success(res) {
              wx.hideLoading();
              if (res.statusCode == 200) {
                if (res.data.userid != -1) {
                  app.globalData.isLogin = true;
                  app.globalData.userinfo = res.data;
                  that.requestIndex(that);
                } else {
                  wx.showModal({
                    title: '提示',
                    content: '您是第一次使用微音乐，请先登录',
                    showCancel: false,
                    mask: true,
                    success(res) {
                      if (res.confirm) {
                        wx.redirectTo({
                          url: '../user/user',
                        })
                      }
                    }
                  })
                }
              } else {
                wx.showModal({
                  title: '服务器异常',
                  content: "status code:" + res.statusCode + "，请与管理员联系！",
                  showCancel: false
                })
              }
            },
            fail(res) {
              wx.hideLoading();
              wx.showModal({
                title: '网络异常',
                content: '无法连接服务器，请检查网络连接',
                showCancel: false
              })
            }
          })
        }
      },
      fail(res) {
        wx.hideLoading();
        wx.showModal({
          title: '调用异常',
          content: 'wx.login() failed，请检查网络连接或升级微信到最新版本',
          showCancel: false
        })
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    // this.requestIndex(this);
    var self = this;
    console.log("index onload")
    if (!app.globalData.hasLogin) {
      this.login(self)
      console.log('login')
    } else if (app.globalData.indexData == undefined) {
      this.requestIndex(self);
    }
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {},

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    console.log("index Show");
    this.setData({
      isPlay: app.globalData.isPlay,
      coverUrl: ((app.globalData.coverUrl == undefined) ? '../../img/icon/music.png' : app.globalData.coverUrl)
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
    requestIndex();
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