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

  bindPlayAndSkip(e){
    app.globalData.currentSong = e.currentTarget.dataset.item;
    app.globalData.isPlay = true;
    wx.navigateTo({
      url: '../song/song'
    })
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
            songs: res.data
          })
        }else{
          wx.showModal({
            title: '服务器异常',
            content: "status code:" + res.statusCode + "，请与管理员联系！",
            showCancel: false
          })
        }
      },
      fail(res){
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
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    console.log("sheet Show");
    this.setData({
      isPlay: app.globalData.isPlay,
      coverUrl: ((app.globalData.coverUrl == undefined) ? '../../img/icon/music.png' : app.globalData.coverUrl)
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