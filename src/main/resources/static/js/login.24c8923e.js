(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["login"],{"0290":function(e,t,s){"use strict";s.r(t);var r=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"login-wrap"},[s("div",{staticClass:"ms-login"},[s("div",{staticClass:"ms-title"},[e._v("主机系统性能监控系统")]),s("el-tabs",{attrs:{type:"border-card"},model:{value:e.activeName,callback:function(t){e.activeName=t},expression:"activeName"}},[s("el-tab-pane",{attrs:{label:"登录",name:"first"}},[s("el-form",{ref:"login",staticClass:"ms-content",attrs:{model:e.login,rules:e.rules,"label-width":"0px"}},[s("el-form-item",{attrs:{prop:"username"}},[s("el-input",{attrs:{placeholder:"username",clearable:""},model:{value:e.login.username,callback:function(t){e.$set(e.login,"username",t)},expression:"login.username"}},[s("el-button",{attrs:{slot:"prepend",icon:"el-icon-lx-people"},slot:"prepend"})],1)],1),s("el-form-item",{attrs:{prop:"password"}},[s("el-input",{attrs:{type:"password",placeholder:"password"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.submitForm()}},model:{value:e.login.password,callback:function(t){e.$set(e.login,"password",t)},expression:"login.password"}},[s("el-button",{attrs:{slot:"prepend",icon:"el-icon-lx-lock"},slot:"prepend"})],1)],1),s("div",{staticClass:"login-btn"},[s("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitForm()}}},[e._v("登录")])],1)],1)],1),s("el-tab-pane",{attrs:{label:"注册",name:"second"}},[s("el-form",{ref:"register",staticClass:"ms-content",attrs:{model:e.register,rules:e.rules,"label-width":"0px"}},[s("el-form-item",{attrs:{prop:"username"}},[s("el-input",{attrs:{placeholder:"username",clearable:""},model:{value:e.register.username,callback:function(t){e.$set(e.register,"username",t)},expression:"register.username"}},[s("el-button",{attrs:{slot:"prepend",icon:"el-icon-lx-people"},slot:"prepend"})],1)],1),s("el-form-item",{attrs:{prop:"password"}},[s("el-input",{attrs:{type:"password",placeholder:"password"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.register()}},model:{value:e.register.password,callback:function(t){e.$set(e.register,"password",t)},expression:"register.password"}},[s("el-button",{attrs:{slot:"prepend",icon:"el-icon-lx-lock"},slot:"prepend"})],1)],1),s("div",{staticClass:"login-btn"},[s("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.registerUser()}}},[e._v("注册")])],1)],1)],1)],1)],1)])},o=[],n={data:function(){return{activeName:"second",login:{username:"",password:"",status:"1"},register:{username:"",password:""},rules:{username:[{required:!0,message:"请输入用户名",trigger:"blur"}],password:[{required:!0,message:"请输入密码",trigger:"blur"}]}}},methods:{submitForm:function(){var e=this;this.$refs.login.validate((function(t){if(!t)return e.$message.error("请输入账号和密码"),console.log("error submit!!"),!1;e.loginUser(),localStorage.setItem("ms_username",e.login.username)}))},resetForm:function(e){this.$refs[e].resetFields()},registerUser:function(){this.$axios.post("Boot/registerUser",this.register).then(function(e){200==e.data.code?(this.$message.success("注册成功"),this.resetForm("register")):400==e.data.code?this.$message.error(e.data.message):401==e.data.code&&(this.$message.error(e.data.message),this.resetForm("register"))}.bind(this)).catch((function(e){console.log(e)}))},loginUser:function(){this.$axios.post("Boot/login",this.login).then(function(e){200==e.data.code?(this.$message.success("登录成功"),this.record(),this.$router.push("/dashboard")):400==e.data.code&&(this.$message.error("输入用户名密码错误,请重新输入！"),this.resetForm("login"))}.bind(this)).catch((function(e){console.log(e)}))},record:function(){this.$axios.post("Boot/insertJournal",this.login).then(function(e){200==e.data.code?console.log("success"):400==e.data.code&&console.log("error")}.bind(this)).catch((function(e){console.log(e)}))}}},a=n,i=(s("2b2a"),s("5d22")),l=Object(i["a"])(a,r,o,!1,null,"40a48a1e",null);t["default"]=l.exports},"2b2a":function(e,t,s){"use strict";s("569b")},"569b":function(e,t,s){}}]);