// pages/test/test.js
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  bindPlay: function(e) {

    wx.playBackgroundAudio({
      dataUrl: 'http://localhost:8080/musicServer/audio',
    })

    // var self = this;
    /*
    wx.request({
        url: 'http://localhost:8080/musicServer/text',
        data: {
          // id: '2966721720',
          // format: '1'
        },
        header: {
          'content-type': 'application/json' // 默认值
        },
        success(res) {
          console.log(res.data);
          // self.setData({
          //   songs: res.data.data
          // })
        }
      }) 
      */
    console.log(e);
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {
    wx.login({
      success(res) {
        console.log(res);
      }
    })
    wx.checkSession({
      success(res) {
        console.log(res);
      }
    })
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

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