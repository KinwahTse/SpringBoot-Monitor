(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["form"],{"09ab":function(t,e,a){},"5a70":function(t,e,a){"use strict";a("09ab")},ec6b:function(t,e,a){"use strict";a.r(e);var o=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("div",{staticClass:"crumbs"},[a("el-breadcrumb",{attrs:{separator:"/"}},[a("el-breadcrumb-item",[a("i",{staticClass:"el-icon-coin"}),t._v(" 告警策略 ")])],1)],1),a("div",{staticClass:"container"},[a("div",{staticClass:"handle-box"},[a("el-button",{attrs:{type:"primary"},on:{click:function(e){t.dialogFormVisible=!0}}},[t._v("添加告警策略")]),a("el-dialog",{staticStyle:{width:"75%"},attrs:{title:"告警策略",visible:t.dialogFormVisible,center:""},on:{"update:visible":function(e){t.dialogFormVisible=e},close:function(e){return t.resetForm("form")}}},[a("el-form",{ref:"form",attrs:{model:t.form,rules:t.rules,"label-width":"80px"}},[a("el-form-item",{staticStyle:{width:"400px"},attrs:{label:"策略名称",prop:"name"}},[a("el-input",{model:{value:t.form.name,callback:function(e){t.$set(t.form,"name",e)},expression:"form.name"}})],1),a("el-form-item",{attrs:{label:"告警类型",prop:"type"}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:t.form.type,callback:function(e){t.$set(t.form,"type",e)},expression:"form.type"}},[a("el-option",{key:"cpuUsage",attrs:{label:"CPU使用率告警",value:"CPU使用率"}}),a("el-option",{key:"memUsage",attrs:{label:"内存使用率告警",value:"内存使用率"}}),a("el-option",{key:"diskReadAndWrite",attrs:{label:"磁盘使用率告警",value:"磁盘使用率"}})],1)],1),a("el-form-item",{attrs:{label:"应用主机",prop:"host"}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:t.form.host,callback:function(e){t.$set(t.form,"host",e)},expression:"form.host"}},[a("el-option",{key:"hostname",attrs:{label:t.hostNameData,value:t.hostNameData}})],1)],1),a("el-form-item",{staticStyle:{width:"200px"},attrs:{label:"一般告警",prop:"commonly"}},[a("el-input",{model:{value:t.form.commonly,callback:function(e){t.$set(t.form,"commonly",e)},expression:"form.commonly"}},[a("i",{staticStyle:{"font-style":"normal","margin-right":"10px"},attrs:{slot:"suffix"},slot:"suffix"},[t._v("%")])])],1),a("el-form-item",{staticStyle:{width:"200px"},attrs:{label:"严重告警",prop:"serious"}},[a("el-input",{model:{value:t.form.serious,callback:function(e){t.$set(t.form,"serious",e)},expression:"form.serious"}},[a("i",{staticStyle:{"font-style":"normal","margin-right":"10px"},attrs:{slot:"suffix"},slot:"suffix"},[t._v("%")])])],1),a("el-form-item",{staticStyle:{width:"400px"},attrs:{label:"邮箱",prop:"mail"}},[a("el-input",{model:{value:t.form.mail,callback:function(e){t.$set(t.form,"mail",e)},expression:"form.mail"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:function(e){return t.submitForm("form")}}},[t._v("添 加")]),a("el-button",{on:{click:function(e){t.dialogFormVisible=!1}}},[t._v("取 消")])],1)],1)],1)],1),a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.tableData,border:""}},[a("el-table-column",{attrs:{prop:"name",label:"告警策略名称",width:"180"}}),a("el-table-column",{attrs:{prop:"content",label:"策略内容",width:"680"}}),a("el-table-column",{attrs:{prop:"host",label:"应用主机",width:"280"}}),a("el-table-column",{attrs:{prop:"time",label:"设置时间",width:"230"}}),a("el-table-column",{attrs:{label:"操作"},scopedSlots:t._u([{key:"default",fn:function(e){return["停止"==e.row.status?a("el-button",{attrs:{type:"text",icon:"el-icon-video-play"},on:{click:function(a){return t.handleStart(e.$index,e.row)}}},[t._v("开始")]):t._e(),"开始"==e.row.status?a("el-button",{attrs:{type:"text",icon:"el-icon-video-pause"},on:{click:function(a){return t.handleStop(e.$index,e.row)}}},[t._v("停止")]):t._e(),a("el-button",{staticClass:"red",attrs:{type:"text",icon:"el-icon-delete"},on:{click:function(a){return t.handleDelete(e.$index,e.row)}}},[t._v("删除")])]}}])})],1)],1)])},r=[],n=(a("a450"),{name:"baseform",data:function(){return{dialogFormVisible:!1,form:{name:"",type:"",host:"",commonly:"",serious:"",mail:""},recordData:{username:"",status:"",name:""},compare:[],hostNameData:"",tableData:[],DeleteId:[],rules:{name:[{required:!0,message:"请输入策略名称",trigger:"blur"},{min:4,max:8,message:"长度在4到8个字符",trigger:"blur"}],type:[{required:!0,message:"请选择告警类型",trigger:"blur"}],host:[{required:!0,message:"请选择应用主机",trigger:"blur"}],commonly:[{required:!0,message:"一般告警值不能为空",trigger:"blur"},{pattern:"^(?:0|[1-9][0-9]?|100)$",message:"请输入0-100的正整数",trigger:"blur"}],serious:[{required:!0,message:"严重告警值不能为空",trigger:"blur"},{pattern:"^(?:0|[1-9][0-9]?|100)$",message:"请输入0-100的正整数",trigger:"blur"}],mail:[{required:!0,message:"请输入邮箱地址",trigger:"blur"},{type:"email",message:"请输入正确的邮箱地址",trigger:["blur","change"]}]}}},methods:{submitForm:function(t){var e=this;this.$refs[t].validate((function(t){if(!t)return console.log("error submit!!"),!1;e.onSubmit()}))},onSubmit:function(){var t=this;t.recordData.name=this.form.name,this.$axios.post("Boot/StrategyPost",this.form).then(function(e){200==e.data.code?(this.dialogFormVisible=!1,this.resetForm("form"),this.$message.success("添加策略成功"),t.recordData.status="2",console.log(t.recordData.name),t.record(),this.getAllStrategy()):400==e.data.code&&this.$message.error("error")}.bind(this)).catch((function(t){console.log(t)}))},resetForm:function(t){this.$refs[t].resetFields()},Destroytimer:function(t){var e=this;e.tableData[t].Infotimer&&clearInterval(e.tableData[t].Infotimer),e.tableData[t].Strategytimer&&clearInterval(e.tableData[t].Strategytimer)},handleStart:function(t,e){var a=this,o=this;o.$confirm("确定开始策略吗？","提示",{type:"warning"}).then((function(){a.$message.success(o.tableData[t].name+"开启成功"),o.recordData.status="3",o.recordData.name=o.tableData[t].name,o.record(),o.tableData[t].status="开始",o.tableData[t].compareData=0,"CPU使用率"===o.tableData[t].type?o.Infotimer=setInterval((function(){setTimeout((function(){o.getcpuInfo(o.tableData[t])}),0)}),4e3):"内存使用率"===o.tableData[t].type?o.Infotimer=setInterval((function(){setTimeout((function(){o.getmemInfo(o.tableData[t])}),0)}),4e3):"磁盘使用率"===o.tableData[t].type&&(o.Infotimer=setInterval((function(){setTimeout((function(){o.getdiskReads(o.tableData[t])}),0)}),4e3)),o.tableData[t].Infotimer=o.Infotimer,o.Strategytimer=setInterval((function(){setTimeout((function(){o.startStrategy(o.tableData[t]),o.tableData[t].compareData=0}),0)}),121e3),o.tableData[t].Strategytimer=o.Strategytimer})).catch((function(t){console.log(t)}))},handleStop:function(t,e){var a=this,o=this;o.$confirm("确定停止策略吗？","提示",{type:"warning"}).then((function(){o.Destroytimer(t),a.$message.success(o.tableData[t].name+"停止成功"),o.recordData.status="4",o.recordData.name=o.tableData[t].name,o.record(),o.tableData[t].status="停止",o.compareData=0})).catch((function(t){console.log(t)}))},startStrategy:function(t){t.data=t.compareData/30,console.log(t.data),this.$axios.post("Boot/startStrategy",t).then((function(t){t.data.code})).catch((function(t){console.log(t)}))},handleDelete:function(t,e){var a=this;this.$confirm("确定要删除吗？","提示",{type:"warning"}).then((function(){a.deleteById(a.tableData[t]),a.tableData.splice(t,1)})).catch((function(t){console.log(t)}))},deleteById:function(t){var e=this,a=this;a.recordData.name=t.name,this.$axios.post("Boot/deleteById",t).then((function(t){200==t.data.code&&(e.$message.success("删除策略成功"),a.recordData.status="5",a.record())})).catch((function(t){console.log(t)}))},getAllStrategy:function(){var t=this,e=this;e.$axios.get("Boot/findAllStrategy").then((function(e){var a=e.data;t.tableData=a})).catch((function(t){console.log("error!")}))},getBasicInfo:function(){var t=this;t.$axios.get("sigar/basicInfo").then((function(e){if("0"===e.data.code){var a=e.data.data;a&&a.name&&(t.hostNameData=a.hostName)}else 401===e.code&&console.log("error!")})).catch((function(t){console.log("error!")}))},getcpuInfo:function(t){var e=this;e.$axios.get("sigar/cpuInfo").then((function(a){if("0"===a.data.code){var o=a.data.data;o&&o.name&&(e.compare=o.usedPercent,t.compareData=parseInt(e.compare)+parseInt(t.compareData),console.log(t.compareData))}})).catch((function(t){console.log("error!")}))},getmemInfo:function(t){var e=this;e.$axios.get("sigar/memInfo").then((function(a){if("0"===a.data.code){var o=a.data.data;o&&o.name&&(e.compare=o.usedPercent,t.compareData=parseInt(e.compare)+parseInt(t.compareData),console.log(t.compareData))}})).catch((function(t){console.log("error!")}))},getdiskReads:function(t){var e=this;e.$axios.get("sigar/diskUsage").then((function(a){if("0"===a.data.code){var o=a.data.data;o&&o.name&&(e.compare=o.diskReads,t.compareData=parseInt(e.compare)+parseInt(t.compareData),console.log(t.compareData))}})).catch((function(t){console.log("error!")}))},record:function(){this.recordData.username=localStorage.getItem("ms_username"),this.$axios.post("Boot/insertJournal",this.recordData).then(function(t){200==t.data.code?console.log("success"):400==t.data.code&&console.log("error")}.bind(this)).catch((function(t){console.log(t)}))}},mounted:function(){var t=this;t.getBasicInfo(),t.getAllStrategy()}}),s=n,i=(a("5a70"),a("5d22")),l=Object(i["a"])(s,o,r,!1,null,"62f728f0",null);e["default"]=l.exports}}]);