// pages/sheet/sheet.js
var app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    songs:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */

  bindPlayAndSkip: function(e){
    app.globalData.currentSong = e.currentTarget.dataset.item;
    app.globalData.isPlay = true;
    wx.navigateTo({
      url: '../song/song',
      success(res){
      },
      fail(res){
        console.log(res);
      }
    })
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

  onLoad: function (options) {
    this.setData({
      sheetData: app.globalData.sheetData
    })
    
    var self = this;
    wx.request({
      url: 'http://localhost:8080/musicServer/sheet',
      data:{
        id: this.data.sheetData.id,
      },
      header:{
        'content-type': 'application/json' // 默认值
      },
      success(res) {
        if(res.statusCode == 200){
          self.setData({
            songs: res.data.data
          })
        }else{
          wx.showModal({
            title: '服务器异常',
            content: "错误代码 statuscode: " + res.statusCode,
            showCancel: false,
          })
        }
      },
      fail(res){
        wx.showModal({
          title: '错误',
          content: "无法连接服务器，请检查网络连接",
          showCancel: false,
        })
      }
    })
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
    console.log("sheet Show");
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