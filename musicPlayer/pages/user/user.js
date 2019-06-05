// pages/user/user.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    userinfo: {
      "userid": "",
      "userName": "",
      "avatarUrl": ""
    }
  },
  /**
   * 如果之前有授权，直接会获得用户信息
   * 如果没有授权会弹出授权窗口，要求授权
   * 授权成功，获得用户信息。未获得用户授权则userinfo为undefined
   */
  bindLogin: function(res) {
    // 声明一个变量接收用户授权信息
    var userinfo = res.detail.userInfo;
    // 判断是否授权  true 替换微信用户头像
    console.log((userinfo != undefined));
    if (userinfo != undefined) {
      this.setData({
        'userinfo.userName': userinfo.nickName,
        'userinfo.avatarUrl': userinfo.avatarUrl,
      })
    } else {
      wx.showModal({
        title: '提示',
        content: '您必须授权才能使用所有功能',
        showCancel: false,
      })
    }
    console.log(this.data.userinfo);
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