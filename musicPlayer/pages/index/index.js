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
      url: '../sheet/sheet',
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

  bindSheet(e) {
    console.log(e.currentTarget.dataset.index);
    var currentSheet = this.data.recommendSheetlist[e.currentTarget.dataset.index];
    console.log(currentSheet);
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
          console.log(res)
          console.log(res.data)
          that.setData({
            data: res.data,
            recommendSheetlist: res.data.recommendSheetlist
          })
          console.log(that.data.recommendSheetlist);
          console.log(that.data.recommendSheetlist[0]);
          console.log(that.data.recommendSheetlist[0].name);
        } else {
          wx.showModal({
            title: '服务器故障',
            content: "status code:" + res.statusCode + "，请与管理员联系！",
            showCancel: false
          })
        }
      },
      fail(res) {
        console.log("request index failed")
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
                    } else if (res.cancel) {
                      console.log('用户点击取消')
                    }
                  }
                })
              }
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
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    // this.requestIndex(this);
    var self = this;
    console.log("index onload start")
    if (!app.globalData.hasLogin) {
      this.login(self)
      console.log('log')
    } else if (app.globalData.indexData == undefined) {
      this.requestIndex(self);
    }

    console.log("index onload end")

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