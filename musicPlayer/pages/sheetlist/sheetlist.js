// pages/sheets/sheets.js
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

  bindSheet(e) {
    var currentSheet = this.data.sheetlist[e.currentTarget.dataset.index];
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

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var self = this;
    wx.request({
      url: app.globalData.host + '/sheetlist',
      success(res){
        if(res.statusCode == 200){
          self.setData({
            sheetlist: res.data
          })
        }else{
          wx.showModal({
            title: '服务器异常',
            content: "status code:" + res.statusCode + "，请与管理员联系！",
            showCancel: false
          })
          console.log("异常：" + res);
        }
      },
      fail(res){
        wx.showModal({
          title: '连接异常',
          content: '无法连接服务器，网络连接或服务器故障',
          showCancel: false
        })
        console.log("异常：" + res);
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