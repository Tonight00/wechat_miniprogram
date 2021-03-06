const app = getApp()
Page({
  data: {
    m:null
  },

  tap4: function(e) {
    this.setData({
      m: e.detail.value
    })
  },
  onLoad: function () {
    
  },

  cash_in: function(e) {
    var self = this
    console.log(this.data.m)
    wx.showModal({
      title:"",
      content:"确定要充值"+this.data.m+"元吗？",
      confirmText:"确定",
      cancelText:'取消',
      success (res){
        if(res.confirm){
          wx.request({
            url: 'http://localhost:8080/wechat_01/store', //仅为示例，并非真实的接口地址
            data: {
              name: app.globalData.username,
              password: app.globalData.password,
              money: self.data.m
            },
            header: {
              'content-type': 'application/json' // 默认值
            },
            success(res) {
              console.log(res.data)
              self.setData({
                assets:  res.data["assets"],
                rates: res.data["rates"],
                profit: res.data["interest"]
              })
              wx.showToast({
                title: '充值成功',
                icon: 'success',
                duration:1500
              })
              wx.reLaunch({
                url: 'my',
              })
            },
            fail (res) {
              console.log("充值失败 ")
            }
          })
        }
        else {
          wx.reLaunch({
            url: 'my',
          })
          return
        }
      }
    })
   


  }


  
})