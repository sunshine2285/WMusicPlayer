// pages/songlist/songlist.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  bindToIndex(e) {
    wx.navigateBack({
      delta: 2
    })
  },
  bindToSong(e) {
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

  bindPlay(e) {
    var currentSong = this.data.songlist[e.currentTarget.dataset.index];
    console.log(currentSong);

    app.globalData.currentSong = currentSong;
    app.globalData.isPlay = true;
    wx.navigateTo({
      url: '../song/song',
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var self = this;
    wx.request({
      url: 'http://localhost:8080/musicServer/songlist',
      success(res) {
        if (res.statusCode == 200) {
          console.log(res);
          self.setData({
            songlist: res.data
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