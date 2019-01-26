var seckill = {

    path : "",

    url : {
        randomURL : function (){
            return seckill.path + "/seckill/goods/random";
        },
        seckillURl : function () {
            return seckill.path + "/seckill/goods/";
        },
        queryURL : function () {
            return seckill.path + "/seckill/result";
        }
    },

    func : {
        initDetial : function(id,nowTime,startTime,endTime){
            if(nowTime < startTime){
                if (nowTime < startTime) {
                    //秒杀未开始，显示秒杀倒计时

                    var killTime = new Date(startTime + 1000);//防止时间偏移

                    $("#seckillTip").countdown(killTime, function (event) {
                        //时间格式
                        var format = event.strftime('距秒杀开始还有: %D天 %H时 %M分 %S秒');
                        $("#seckillTip").html("<span style='color:red;'>"+format+"</span>");

                    }).on('finish.countdown', function () {
                        //倒计时完成后回调事件，可以执行秒杀
                        seckillObj.func.startSeckill(id);
                    });

                } else if (nowTime > endTime) {
                    //秒杀已结束，直接显示秒杀已经结束
                    $("#seckillTip").html("<span style='color:red;'>来晚了，秒杀已结束~</span>");

                } else {
                    //秒杀已开始，可以开始秒杀，显示立即秒杀按钮
                    seckillObj.func.startSeckill(id);
                }
                // $("#seckillTip").html("秒杀还没有开始，请耐心等候");
            }else if(nowTime > startTime && nowTime < endTime) {
                seckill.func.startSeckill(id);
            }else {
                $("#seckillTip").html("<span style='color:red;'>来晚了，秒杀已结束~</span>");
            }
        },
        //秒杀开始处理函数
        startSeckill : function (id) {
            //时间已经到了，可以秒杀了，这个时候，我们通过获取一个商品的唯一标识，
            //如果能获取到则可以秒杀，不能获取到，则不能秒杀
            $.ajax({
                url : seckill.url.randomURL() + "/" + id,
                dataType : "json",
                type : "post",
                success : function(json){
                    if(json.errorCode == "0"){
                        var random = json.data;
                        if(random){
                            $("#seckillTip").html("<button id='seckillBtn' value='id'>立即秒杀</button>");
                            $("#seckillBtn").click(function(){
                               $("#seckillBtn").attr("disabled",true);
                                seckill.func.execSeckill(id,random);
                            });
                        }
                    }else {
                        $("#seckillTip").html("<span style='color:red;'>"+json.errorMessage+"</span>");
                    }
                }
            })
        },
        //开始秒杀操作
        execSeckill : function (id,random) {
            //秒杀真正开始，才会暴露秒杀地址
            $.ajax({
                url : seckill.url.seckillURl() + id + "/" + random,
                type : "post",
                dataType : "json",
                success : function (json) {
                    if(json.errorCode == "0"){
                        //秒杀成功，现在页面给用户做提示
                        $("#seckillTip").html("<span style='color:red;'>"+json.errorMessage+"</span>");

                        window.setInterval(function () {
                            seckill.func.queryResult(id);
                        },3000);
                    }else {
                        $("#seckillTip").html("<span style='color:red;'>系统繁忙，请稍后在试.....</span>");
                    }
                }
            })
        },
        //查询最终秒杀结果
        queryResult : function (id) {
            $.ajax({
                url : seckill.url.queryURL() + "/" + id,
                type : "post",
                dataType : "json",
                success : function (json) {
                    if(json.errorCode == "0"){
                        $("#seckillTip").html("<a style='color:red;'>秒杀成功，请立即支付。<a href='http://www.alipay.com' target='_blank'>去支付</a></span>");
                        //查到结果了，就应该把页面的3秒轮询去掉，不要反复去查
                        window.clearInterval();
                    }else if(json.errorCode == "1"){
                        $("#seckillTip").html("<span style='color:red;'>"+json.errorMessage+"</span>");
                        //查到结果了，就应该把页面的3秒轮询去掉，不要反复去查
                        window.clearInterval();
                    }
                }
            })
        }
    }
}