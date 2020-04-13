//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    usernameValue:'',
    passwordValue:''
  },
  usernameInput: function(e) {
    
    this.setData({
      usernameValue: e.detail.value
    })
  },
  passwordInput: function(e) {
    this.setData({
      passwordValue: e.detail.value
    })
  },
  sign: function(e) {
    //检查账号合法性
    //账号长度为0
    if (this.data.usernameValue.length == 0
        || this.data.passwordValue.length <8){
      console.log("账号不能为零！")
      wx.showToast({
        title: '账号或密码错误',
        icon:'none',
        duration: 1000
      })
      this.setData({
        usernameValue:'',
        passwordValue:''
      })
      return
    }
    /*
    s = this.data.password;
    for (i = 'a';i<'z';i+=1)
      console
    */
   var self = this
    wx.request({
      url: 'http://localhost:8080/wechat_01/sign', //仅为示例，并非真实的接口地址
      data: {
        name: this.data.usernameValue,
        password: this.data.passwordValue
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success (res) {
        if(res.data == true) {
          wx.showToast({
            title: '登录成功',
            icon:'success',
            duration: 1000
          })
          getApp().globalData.username = self.data.usernameValue
          getApp().globalData.password= self.data.passwordValue
          wx.redirectTo({
            url: 'my'
          })
        }
        else {
          self.setData({
            usernameValue:'',
            passwordValue:''
          })
          wx.showToast({
            title: '登录失败了',
            icon:'none',
            duration: 1000
          })
          
        }
      },
      fail (res) {
        console.log("fail----------------")
      }
    })
  },
  login : function(e) {
    wx.redirectTo({
      url: 'user'
    })
  }
   /*
  data: {
   
    motto: 'Hello World',
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo')

  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse){
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }
  },
  /*
  getUserInfo: function(e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
    */
})
