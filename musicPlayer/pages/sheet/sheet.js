// pages/sheet/sheet.js
const app = getApp();
const bgPlayer = wx.getBackgroundAudioManager();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    songs: []
  },

  /**
   * 生命周期函数--监听页面加载
   */

  bindPlayAndSkip(e) {
    app.globalData.currentSong = this.data.songs[e.currentTarget.dataset.index];
    app.globalData.isPlay = true;
    wx.navigateTo({
      url: '../song/song'
    })
  },
  catchMenu(e) {
    var songid = this.data.songs[e.currentTarget.dataset.index].id;
    var userid = app.globalData.userinfo.userid;
    wx.showActionSheet({
      itemList: ['收藏歌曲'],
      itemColor: "#2980b9",
      success(res) {
        wx.request({
          url: app.globalData.host + '/collect',
          data: {
            songid: songid,
            userid: userid,
            kind: 1
          },
          success(res) {
            if (res.statusCode == 200) {
              if (res.data == 1) {
                wx.showToast({
                  title: '收藏成功',
                })
              } else if (res.data == 0) {
                wx.showToast({
                  title: '歌曲已在收藏列表中',
                  icon: 'none'
                })
              } else {
                wx.showToast({
                  title: '收藏歌曲异常：' + res.data,
                  icon: 'none'
                })
              }
            } else {
              wx.showModal({
                title: '服务器异常',
                content: "status code:" + res.statusCode + "，请与管理员联系！",
                showCancel: false
              })
              console.log("异常：" + res);
            }
          },
          fail(res) {
            wx.showModal({
              title: '连接异常',
              content: '无法连接服务器，网络连接或服务器故障',
              showCancel: false
            })
            console.log("异常：" + res);
          }
        })
      },
      fail(res) {
        console.log("异常：" + res);
      }
    })
  },

  catchPlay(e) {
    var currentSong = this.data.songs[e.currentTarget.dataset.index];

    if (currentSong.id == this.data.currentSongid) {
      this.setData({
        currentSongid: -1
      })
      app.globalData.currentSongid = -1;
      app.globalData.isPlay = false;
      bgPlayer.pause();
    } else {
      app.globalData.currentSong = currentSong;
      app.globalData.currentSongid = currentSong.id;
      app.globalData.isPlay = true;
      app.globalData.coverUrl = currentSong.coverUrl;

      bgPlayer.title = currentSong.name + " - " + currentSong.singer;
      bgPlayer.coverImgUrl = currentSong.coverUrl;
      bgPlayer.src = currentSong.audioUrl;

      this.setData({
        isPlay: true,
        coverUrl: currentSong.coverUrl,
        currentSongid: currentSong.id
      })
    }
  },

  bindToIndex(e) {
    wx.navigateBack({
      delta: 2
    })
  },
  bindToSong(e) {
    if (app.globalData.currentSong == undefined) {
      wx.showToast({
        icon: 'none',
        title: '没有歌曲在播放',
        mask: true,
        duration: 1000
      })
      return;
    }
    app.globalData.isPlay = true;
    wx.navigateTo({
      url: '../song/song',
    })
  },
  bindToUser(e) {
    wx.reLaunch({
      url: '../user/user',
    })
  },

  onLoad: function(options) {
    this.setData({
      sheetData: app.globalData.sheetData
    })

    var self = this;
    wx.request({
      url: app.globalData.host + '/sheet',
      data: {
        id: this.data.sheetData.id,
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success(res) {
        if (res.statusCode == 200) {
          self.setData({
            songs: res.data
          })
        } else {
          wx.showModal({
            title: '服务器异常',
            content: "status code:" + res.statusCode + "，请与管理员联系！",
            showCancel: false
          })
          console.log("异常：" + res);
        }
      },
      fail(res) {
        wx.showModal({
          title: '网络异常',
          content: '无法连接服务器，请检查网络连接',
          showCancel: false
        })
        console.log("异常：" + res);
      }
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
    this.setData({
      isPlay: app.globalData.isPlay,
      coverUrl: ((app.globalData.coverUrl == undefined) ? '../../img/icon/music.png' : app.globalData.coverUrl),
      currentSongid: (app.globalData.isPlay ? app.globalData.currentSongid : -1)
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