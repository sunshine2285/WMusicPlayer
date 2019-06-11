// pages/user/user.js
const app = getApp();
const bgPlayer = wx.getBackgroundAudioManager();
Page({
  /**
   * 页面的初始数据
   */
  data: {
    userinfo: {
      "userid": "",
      "userName": "",
      "avatarUrl": ""
    },
    navbarActiveIndex: 0,
    navbarTitle: [
      "最近",
      "收藏"
    ]
  },

  //刷新页面所有数据,that为页面本身
  // var self = this;
  flush(self) {
    if (app.globalData.userinfo.userid == -1)
      return;
    wx.request({
      url: app.globalData.host + '/user',
      data: {
        userid: app.globalData.userinfo.userid
      },
      success(res) {
        if (res.statusCode == 200) {
          self.setData({
            recentSonglist: res.data.recentSonglist,
            collectedSonglist: res.data.collectedSonglist
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
          title: '连接异常',
          content: '无法连接服务器，网络连接或服务器故障',
          showCancel: false
        })
      }
    })
  },

  //导航切换
  onNavBarTap: function(event) {
    // 获取点击的navbar的index
    let navbarTapIndex = event.currentTarget.dataset.navbarIndex
    // 设置data属性中的navbarActiveIndex为当前点击的navbar
    this.setData({
      navbarActiveIndex: navbarTapIndex
    })
  },

  //导航切换动画结束，激活当前navbar
  onBindAnimationFinish: function({
    detail
  }) {
    // 设置data属性中的navbarActiveIndex为当前点击的navbar
    this.setData({
      navbarActiveIndex: detail.current
    })
  },

  /**
   * 授权注册登录
   * 如果之前有授权，直接会获得用户信息
   * 如果没有授权会弹出授权窗口，要求授权
   * 授权成功，获得用户信息。未获得用户授权则userinfo为undefined
   */
  bindLoginAndRegister: function(res) {
    var self = this;
    // 声明一个变量接收用户授权信息
    var userinfo = res.detail.userInfo;
    // 判断是否授权  true 替换微信用户头像
    if (userinfo != undefined) {
      //userid为-1代表用户还没有注册
      if (this.data.userinfo.userid == -1) {
        wx.showLoading({
          title: '登录中',
          mask: true
        })
        wx.login({
          success(res) {
            if (res.code) {
              wx.request({
                url: app.globalData.host + '/register',
                data: {
                  code: res.code,
                  userName: userinfo.nickName,
                  avatarUrl: userinfo.avatarUrl
                },
                success(res) {
                  //注册成功之后只需返回userid即可
                  self.setData({
                    'userinfo.userid': res.data.userid
                  })
                  app.globalData.userinfo.userid = res.userid;
                  wx.hideLoading();
                  wx.showToast({
                    title: '登录成功',
                  })
                  self.setData({
                    'userinfo.userName': userinfo.nickName,
                    'userinfo.avatarUrl': userinfo.avatarUrl,
                    isLogin: true
                  });
                  app.globalData.isLogin = true;
                  app.globalData.userinfo = self.data.userinfo;
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
      }

    } else {
      wx.showModal({
        title: '提示',
        content: '您必须授权才能使用所有功能',
        showCancel: false,
      })
    }

  },

  //跳转到主页
  bindToIndex(e) {
    wx.redirectTo({
      url: '../index/index',
    })
  },

  //跳转到歌曲播放页面
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

  bindPlayAndSkip(e) {
    var parrentindex = e.currentTarget.dataset.parrentindex;
    var index = e.currentTarget.dataset.index;

    app.globalData.currentSong = (parrentindex == 0) ? (this.data.recentSonglist[index]) : (this.data.collectedSonglist[index]);

    app.globalData.isPlay = true;
    wx.navigateTo({
      url: '../song/song'
    })
  },

  catchMenu(e) {
    var self = this;
    var parrentindex = e.currentTarget.dataset.parrentindex;
    var index = e.currentTarget.dataset.index;
    var currentSong = (parrentindex == 0) ? (this.data.recentSonglist[index]) : (this.data.collectedSonglist[index]);

    var songid = currentSong.id;
    var userid = app.globalData.userinfo.userid;
    wx.showActionSheet({
      itemList: ['收藏歌曲', '移除歌曲'],
      itemColor: "#2980b9",
      success(res) {
        if (res.tapIndex == 0) {
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
                  self.flush(self);
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
              }
            },
            fail(res) {
              wx.showModal({
                title: '连接异常',
                content: '无法连接服务器，网络连接或服务器故障',
                showCancel: false
              })
            }
          })
        } else if (res.tapIndex == 1) {
          wx.request({
            url: app.globalData.host + '/delete',
            data: {
              songid: songid,
              userid: userid,
              kind: self.data.navbarActiveIndex
            },
            success(res) {
              if (res.statusCode == 200) {
                if (res.data == 1) {
                  self.flush(self);
                  wx.showToast({
                    title: '移除成功',
                  })
                } else {
                  console.log("异常：" + res);
                  wx.showToast({
                    title: '移除歌曲异常：' + res.data,
                    icon: 'none'
                  })
                }
              } else {
                console.log("异常：" + res);
                wx.showModal({
                  title: '服务器异常',
                  content: "status code:" + res.statusCode + "，请与管理员联系！",
                  showCancel: false
                })
              }
            },
            fail(res) {
              wx.showModal({
                title: '连接异常',
                content: '无法连接服务器，网络连接或服务器故障',
                showCancel: false
              })
            }
          })
        }
      },
      fail(res) {
        console.log("异常：" + res);
      }
    })
  },

  catchPlay(e) {
    var parrentindex = e.currentTarget.dataset.parrentindex;
    var index = e.currentTarget.dataset.index;
    var currentSong = (parrentindex == 0) ? (this.data.recentSonglist[index]) : (this.data.collectedSonglist[index]);

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
    var self = this;
    self.setData({
      isPlay: app.globalData.isPlay,
      coverUrl: ((app.globalData.coverUrl == undefined) ? '../../img/icon/music.png' : app.globalData.coverUrl),
      userinfo: app.globalData.userinfo,
      isLogin: app.globalData.isLogin,
      currentSongid: (app.globalData.isPlay ? app.globalData.currentSongid : -1)
    })

    self.flush(self);
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