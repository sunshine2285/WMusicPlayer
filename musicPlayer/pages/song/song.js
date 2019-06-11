// pages/play/play.js
const app = getApp();
const bgPlayer = wx.getBackgroundAudioManager();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    cover_btn_img: "",
    song: [],
    isPlay: true,
  },

  flushComment(self) {
    wx.request({
      url: app.globalData.host + '/comment',
      data: {
        songid: self.data.song.id
      },
      success(res) {
        self.setData({
          commentlist: res.data
        })
      }
    })
  },

  bindCoverBtn: function(e) {
    var t_cover_btn_img;
    var isplay = this.data.isPlay;
    if (isplay) {
      t_cover_btn_img = "../../img/icon/play-cover.png";
      bgPlayer.pause();
    } else {
      t_cover_btn_img = "../../img/icon/stop-cover.png";
      bgPlayer.play();
    }

    this.setData({
      isPlay: !isplay,
      cover_btn_img: t_cover_btn_img
    })
    app.globalData.isPlay = !isplay;
  },

  bindInput(e) {
    this.setData({
      commentContent: e.detail.value
    })
  },

  bindSubmit(e) {
    var self = this;
    if (self.data.commentContent == undefined){
      wx.showToast({
        title: '请先填写评论',
        icon: 'none'
      })
      return;
    } else if (self.data.commentContent.length == 0){
      wx.showToast({
        title: '请先填写评论',
        icon: 'none'
      })
      return;
    } else if (self.data.commentContent.length > 30) {
      wx.showToast({
        title: '写太多啦，30个字符就够啦',
        icon: 'none'
      })
      return;
    }
    
    wx.request({
      url: app.globalData.host + '/insertComment',
      data: {
        songid: self.data.song.id,
        userid: self.data.userinfo.userid,
        content: self.data.commentContent
      },
      success(res) {
        if (res.statusCode == 200) {
          if (res.data == 1) {
            self.setData({
              commentContent: null
            })
            self.flushComment(self);
            wx.showToast({
              title: '评论成功'
            })
          }
          else{
            wx.showToast({
              title: '评论失败',
              icon: 'none'
            })
          }
        }
      }
    })
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var t_cover_btn_img = "../../img/icon/stop-cover.png";
    if (!app.globalData.isPlay) {
      t_cover_btn_img = "../../img/icon/play-cover.png"
    }

    this.setData({
      song: app.globalData.currentSong,
      isPlay: app.globalData.isPlay,
      cover_btn_img: t_cover_btn_img,
      userinfo: app.globalData.userinfo
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {
    var self = this;
    self.flushComment(self);
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    bgPlayer.title = this.data.song.name + " - " + this.data.song.singer;
    bgPlayer.coverImgUrl = this.data.song.coverUrl;
    bgPlayer.src = this.data.song.audioUrl;
    app.globalData.coverUrl = this.data.song.coverUrl;
    app.globalData.currentSongid = this.data.song.id;
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

  },
})

bgPlayer.onEnded(function() {
  app.globalData.isPlay = false;
})

bgPlayer.onPlay(function() {})

bgPlayer.onCanplay(function() {
  wx.request({
    url: 'http://localhost:8080/musicServer/collect',
    data: {
      songid: app.globalData.currentSong.id,
      userid: app.globalData.userinfo.userid,
      kind: 0
    },
    success(res) {
      if (res.statusCode == 200) {
        if (res.data != 1 && res.data != -1) {
          console.log("异常：" + res);
        }
      } else {
        console.log("异常：" + res);
      }
    }
  })
})