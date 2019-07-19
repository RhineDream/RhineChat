// var gloalUrl = "http://localhost:9999/chat/";
var gloalUrl = "http://www.jingyingjidi.com/chat/";
// 转为json数据格式
function transformToJson(formData){
    var obj={}
    for (var i in formData) {
        /*[{"name":"user","value":"hpc"},{"name":"pwd","value":"123"},{"name":"sex","value":"M"},{"name":"age","value":"100"},{"name":"phone","value":"13011112222"},{"name":"email","value":"xxx@xxx.com"}]
        */
        //下标为的i的name做为json对象的key，下标为的i的value做为json对象的value
        obj[formData[i].name]=formData[i]['value'];
    }
    return obj;
}
function setHeader(xhr) {
    xhr.setRequestHeader('Authorization', "Basic OTgxNjhhMGRiMDEzM2I4ZWUwYzU6ZGMyZjk0Mzk4NTEwZTc0YTZkZmM2MjRlZDA4NTY0OTFiNzJkZWI5Ng==");
}

function setTokenHeader(xhr) {
    xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem("token"));
}
function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r != null) return decodeURI(r[2]);
    return null;
}
function logout(){
    localStorage.clear();
    location.reload();
}

function genLoginInfo(){
    var username = localStorage.getItem("userName");
    console.log(username);//输出
    if (username != null && username != '') {
        $("#islogin").show();
        $("#notlogin").hide();

        var userinfo = JSON.parse(localStorage.getItem("userInfo"));
        $("#myname").html(userinfo.name);
    } else {
        $("#islogin").hide();
        $("#notlogin").show();
    }
}