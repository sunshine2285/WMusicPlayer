// pages/index/index.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    isPlay: false,
  },
  
  bindMoreSheet: function (e) {
    wx.navigateTo({
      url: '../sheetlist/sheetlist',
    });
  },
  bindMoreSong: function (e) {
    wx.navigateTo({
      url: '../sheet/sheet',
    });
  },
  bindToIndex(e){
    wx.redirectTo({
      url: '../index/index',
    })
  },
  bindToSong(e){
    app.globalData.isPlay = true;
    wx.navigateTo({
      url: '../song/song',
    })
  },
  bindToUser(e){
    wx.redirectTo({
      url: '../user/user',
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    console.log("index Show");
    this.setData({
      isPlay: app.globalData.isPlay
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})