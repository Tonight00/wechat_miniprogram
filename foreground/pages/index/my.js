const app = getApp()
Page({
  data: {
    username:null,
    rates:null,
    assets:null,
    profit:null
  },

  onLoad: function () {
    var self = this
    wx.showLoading({
      title:"请求数据中"
    })
    wx.request({
      url: 'http://localhost:8080/wechat_01/query', //仅为示例，并非真实的接口地址
      data: {
        name: app.globalData.username,
        password: app.globalData.password
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success (res) {
        setTimeout(function(){wx.hideLoading()},1000) 
        self.setData({
          assets:  res.data["assets"],
          rates: res.data["rates"],
          profit: res.data["interest"],
          username: app.globalData.username+",欢迎你！"
        })
        app.globalData.money = res.data["assets"]
      },
      fail (res) {
        console.log("获取个人信息失败")
      }

    })
  },

  cash_in : function(e) {
    wx.navigateTo({
      url: 'cash_in',
    })
  },

  cash_out : function(e) {
    wx.navigateTo({
      url: 'cash_out',
    })
  }
})