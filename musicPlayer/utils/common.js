function login(that) {
  return new Promise(function(resolve, reject) {
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
              console.log('123');
              if (res.data.userid != -1) {
                that.globalData.isLogin = true;
                that.globalData.userinfo = res.data;
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
  })
}

module.exports.login = login