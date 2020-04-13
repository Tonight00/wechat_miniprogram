// pages/index/logs.js

Page({
    data:{
        username:'',
        password:'',
        password2:'',
        id:'',
        tel:'',
        lt:'《',
        gt:'》'
    },
    tap1: function(e){
        console.log(e)
       this.setData({
           username:e.detail.value
       })
    },
    tap2: function(e){
        this.setData({
            id:e.detail.value
        })
    },
    tap3: function(e){
         this.setData({
            tel:e.detail.value
        })
    }, 
    tap4: function(e){
        this.setData({
        password:e.detail.value
    })
    }, 
    tap5:function(e){
        this.setData({
            password2:e.detail.value
        })
    },
    sign_in : function(e){  
        console.log(this.data)
       
        var r1 = false
        var r2 = false
        if(this.data.password != this.data.password2){
            wx.showToast({
                title: '密码不一致',
                icon:'none',
                duration:1000
            })
            this.setData({
                password:'',
                password2:null
            })
            return   
        }

        if(this.data.id.length!=9 || this.data.tel.length!=11
        || this.data.password.length<8){
            wx.showToast({
                title: '信息不符合格式',
                icon:'none',
                duration:1000
            })
            this.setData({
                password:'',
                password2:null,
                id:null,
                tel:null,
                username:null
            })
            return
        }
    

        wx.request({
          url: 'http://localhost:8080/wechat_01/login',
          data: {
            name:this.data.username,
            id:this.data.id,
            tel:this.data.tel,
            password:this.data.password
          },
          header: {
            'content-type': 'application/json' // 默认值
          },
          success (res) {
            if(res.data == true) {
                wx.showToast({
                  title: '注册成功',
                  icon:'success',
                  duration:1500
                })
                wx.redirectTo({
                    url: 'index'
                })
            }
            else{
                wx.showToast({
                    title: '用户已经存在',
                    icon:'none',
                    duration:1500
                 })
            }
          },
          fail(res){
            console.log("注册链接连接失败！")
          }
        })
    },
    /*
    checkid: new function(self) {
        if(self.data.id == undefined) 
            return false
        if(self.data.id.length !=9)
            return false
        return true
    },
    checktel: new function(self){
        if(self.data.tel == undefined)
            return false
        if(self.data.tel.length!=11)
            return false
        return true
    }*/

})