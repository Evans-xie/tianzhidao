var danmu = {
    //封装弹幕相关ajax的url
    URl: {
        danmu: function (viewId) {
            return "/danmu/view/" + viewId;
        }
    },
    danmu: {
        id:null,
        userId: null,
        contents: null,
        viewId: null,
        color: null,
        time: new Date(),
        x: 0.0,
        y: 0.0
    }
}

//模拟Get请求
function ajaxRequestGet(uri) {
    /* var server = serverPath;
    var url = server + uri; */
    var url = uri;
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        url: url,
        success: function (response) {
            if(response["code"]==400){

            }
            console.log(response);
        },
        error: function () {
            console.log('Ajax请求失败！');
        }
    });
}

//模拟Post请求
function ajaxRequestPost(uri, method, data) {
    $.ajax({
        type: method,
        contentType: 'application/json;charset=UTF-8',//此句话需要配合 json序列化方法使用
        dataType: 'json',
        data: JSON.stringify(data),
        url: uri,
        success: function (response) {
            console.log(response);
        },
        error: function (response) {
            if(response["desc"]){
                alert(response["desc"]);
            }
            console.log('Ajax error');
            console.log(response);
        }
    });
}

//非json格式的请求
function ajaxWithoutJson(uri, method, header) {
    $.ajax({
        type: method,
        contentType: 'application/json;charset=UTF-8',//此句话需要配合 json序列化方法使用
        dataType: 'json',
        data: JSON.stringify(header),
        url: uri,
        beforeSend: function (request) {
            request.setRequestHeader("studentId", header.studentId);
            request.setRequestHeader("password", header.password)
        },
        success: function (response) {
            var result = response['data'];
            if (result && result['user']) {
                var obj = result['user'];
                user.name = obj['name'];
                user.userId = obj['id'];
                user.studentId = obj['studentId'];
                console.log(user);
            }
            console.log(response);
        },
        error: function (response) {
            console.log('Ajax error');
            console.log(response);
        }
    });
}

function messaction(ppp) {
    ppp.addClass("changmessage");
}

function messstop(ppp) {
    ppp.hide();
}

function pushDanmu() {
    var message = $("#message").val();
    if (message.length > 0) {
        danmu.danmu.contents=message;
        danmu.danmu.time=new Date();
        danmu.danmu.color="#000000";
        danmu.danmu.viewId="123";
        ajaxRequestPost(danmu.URl.danmu(123),"POST",danmu.danmu);
        $("#fontdiv").append("<pre class='oldp' style='width: " + message.length + "em'>" + message + "</pre>");
        $("#message").val("");
        var ppp = $("pre").last();
        //移动弹幕
        setTimeout(function () {
            messaction(ppp);
        }, 100);
        //隐藏弹幕
        setTimeout(function () {
            messstop(ppp);
        }, 8500);
    }
}

function getDanmu() {
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        url: danmu.URl.danmu(123),
        success: function (response) {
            if(response["code"]=="200"){
                console.log("code:"+response["code"]);
                var data=response["data"];
                console.log("data:"+data);
                if(data["danmu"]){
                    var danmus=data["danmu"];
                    console.log("danmu:"+JSON.stringify(danmus));
                    for(j = 0,len=danmus.length; j < len; j++) {
                        if (danmus[j]) {
                            console.log("danmu:" + danmus[j]);
                            var content = danmus[j]["contents"];
                            pushtext(content);
                        }

                    }
                }
            }
            console.log(response);
        },
        error: function () {
            console.log('Ajax请求失败！');
        }
    });
}

function pushtext(message) {
    if (message.length > 0) {
        $("#fontdiv").append("<pre class='oldp' style='width: " + message.length + "em'>" + message + "</pre>");
        $("#message").val("");
        var ppp = $("pre").last();
        //移动弹幕
        setTimeout(function () {
            messaction(ppp);
        }, 100);
        //隐藏弹幕
        setTimeout(function () {
            messstop(ppp);
        }, 8500);
    }
};

document.onkeydown = function (event) {
    var e = event || window.event || arguments.callee.caller.arguments[0];
    if (e && e.keyCode == 13) {
        //要做的事情
        e.preventDefault();
        pushDanmu();
    }
};

