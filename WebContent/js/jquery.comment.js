(function($){
	function crateCommentInfo(obj){
		/*
		 * <div class="comment-info">
			<header><img src="./images/img.jpg"></header>
			<div class="comment-right">
				<h3>鍖垮悕</h3>
				<div class="comment-content-header"><span><i class="glyphicon glyphicon-time"></i> 2017-10-17 11:42:53</span><span><i class="glyphicon glyphicon-map-marker"></i>娣卞湷</span></div>
				<p class="content">mongodb 鍓湰闆嗛厤缃壇鏈泦姒傚康锛氬氨鎴戠殑鐞嗚В灏辨槸鍜屼富浠庡鍒� 宸笉澶氾紝灏辨槸鍦ㄤ富浠庡鍒剁殑鍩虹涓婂鍔犱簡涓€涓€変妇鐨勬満鍒躲€�
				澶嶅埗闆� 鐗圭偣锛氭暟鎹竴鑷存€� 涓绘槸鍞竴鐨勶紝娌℃湁Mysql 閭ｆ牱鐨勫弻涓荤粨鏋勫ぇ澶氭暟鍘熷垯锛岄泦缇ゅ瓨娲昏妭鐐瑰皬浜庝簩鍒嗕箣涓€鏄泦缇や笉鍙啓锛�
				鍙彲璇讳粠搴撴棤娉曞啓鍏ユ暟鎹嚜鍔ㄥ鐏鹃€氳繃涓嬮潰鐨勪竴涓浘鏉ョ畝鍗曠殑浜嗚В涓�
				 閰嶇疆杩囩▼锛氫竴銆佸畨瑁卪ongodb瀹夎杩囩▼鐣ワ紝涓嶆噦寰楀彲浠ョ湅鍓嶉潰鐨勬暀绋嬩簩銆佸垱寤哄瓨鍌ㄧ洰褰曚笌閰嶇疆鏂囦欢鍒�...</p>
				<div class="comment-content-footer">
					<div class="row">
						<div class="col-md-10">
							<span><i class="glyphicon glyphicon-pushpin"></i> 鏉ヨ嚜:win10 </span><span><i class="glyphicon glyphicon-globe"></i> chrome 55.0.2883.87</span>
						</div>
						<div class="col-md-2"><span class="reply-btn">鍥炲</span></div>
					</div>
				</div>
				<div class="reply-list">
					<div class="reply">
						<div><a href="javascript:void(0)">鍖垮悕</a>:<a href="javascript:void(0)">@鍖垮悕</a><span>杩欏啓鐨勬槸浠€涔堥涓滆タ銆傘€傘€傘€�</span></div>
						<p><span>2017-10-17 11:42:53</span> <span class="reply-list-btn">鍥炲</span></p>
					</div>
				</div>
			</div>
		</div>
		 * */
		
		if(typeof(obj.time) == "undefined" || obj.time == ""){
			obj.time = getNowDateFormat();
		}
		
		var el = "<div class='comment-info'><header><img src='"+obj.img+"'></header><div class='comment-right'><h3>"+obj.replyName+"</h3>"
				+"<div class='comment-content-header'><span><i class='glyphicon glyphicon-time'></i>"+obj.time+"</span>";
		
		if(typeof(obj.address) != "undefined" && obj.browse != ""){
			el =el+"<span><i class='glyphicon glyphicon-map-marker'></i>"+obj.address+"</span>";
		}
		el = el+"</div><p class='content'>"+obj.content+"</p><div class='comment-content-footer'><div class='row'><div class='col-md-10'>";
		
		if(typeof(obj.osname) != "undefined" && obj.osname != ""){
			el =el+"<span><i class='glyphicon glyphicon-pushpin'></i> 鏉ヨ嚜:"+obj.osname+"</span>";
		}
		
		if(typeof(obj.browse) != "undefined" && obj.browse != ""){
			el = el + "<span><i class='glyphicon glyphicon-globe'></i> "+obj.browse+"</span>";
		}
		
		el = el + "</div><div class='col-md-2'><span class='reply-btn'>鍥炲</span></div></div></div><div class='reply-list'>";
		if(obj.replyBody != "" && obj.replyBody.length > 0){
			var arr = obj.replyBody;
			for(var j=0;j<arr.length;j++){
				var replyObj = arr[j];
				el = el+createReplyComment(replyObj);
			}
		}
		el = el+"</div></div></div>";
		return el;
	}
	
	//杩斿洖姣忎釜鍥炲浣撳唴瀹�
	function createReplyComment(reply){
		var replyEl = "<div class='reply'><div><a href='javascript:void(0)' class='replyname'>"+reply.replyName+"</a>:<a href='javascript:void(0)'>@"+reply.beReplyName+"</a><span>"+reply.content+"</span></div>"
						+ "<p><span>"+reply.time+"</span> <span class='reply-list-btn'>鍥炲</span></p></div>";
		return replyEl;
	}
	function getNowDateFormat(){
		var nowDate = new Date();
		var year = nowDate.getFullYear();
		var month = filterNum(nowDate.getMonth()+1);
		var day = filterNum(nowDate.getDate());
		var hours = filterNum(nowDate.getHours());
		var min = filterNum(nowDate.getMinutes());
		var seconds = filterNum(nowDate.getSeconds());
		return year+"-"+month+"-"+day+" "+hours+":"+min+":"+seconds;
	}
	function filterNum(num){
		if(num < 10){
			return "0"+num;
		}else{
			return num;
		}
	}
	function replyClick(el){
		el.parent().parent().append("<div class='replybox'><textarea cols='80' rows='50' placeholder='鏉ヨ鍑犲彞鍚�......' class='mytextarea' ></textarea><span class='send'>鍙戦€�</span></div>")
		.find(".send").click(function(){
			var content = $(this).prev().val();
			if(content != ""){
				var parentEl = $(this).parent().parent().parent().parent();
				var obj = new Object();
				obj.replyName="鍖垮悕";
				if(el.parent().parent().hasClass("reply")){
					console.log("1111");
					obj.beReplyName = el.parent().parent().find("a:first").text();
				}else{
					console.log("2222");
					obj.beReplyName=parentEl.find("h3").text();
				}
				obj.content=content;
				obj.time = getNowDateFormat();
				var replyString = createReplyComment(obj);
				$(".replybox").remove();
				parentEl.find(".reply-list").append(replyString).find(".reply-list-btn:last").click(function(){alert("涓嶈兘鍥炲鑷繁");});
			}else{
				alert("绌哄唴瀹�");
			}
		});
	}
	
	
	$.fn.addCommentList=function(options){
		var defaults = {
			data:[],
			add:""
		}
		var option = $.extend(defaults, options);
		//鍔犺浇鏁版嵁
		if(option.data.length > 0){
			var dataList = option.data;
			var totalString = "";
			for(var i=0;i<dataList.length;i++){
				var obj = dataList[i];
				var objString = crateCommentInfo(obj);
				totalString = totalString+objString;
			}
			$(this).append(totalString).find(".reply-btn").click(function(){
				if($(this).parent().parent().find(".replybox").length > 0){
					$(".replybox").remove();
				}else{
					$(".replybox").remove();
					replyClick($(this));
				}
			});
			$(".reply-list-btn").click(function(){
				if($(this).parent().parent().find(".replybox").length > 0){
					$(".replybox").remove();
				}else{
					$(".replybox").remove();
					replyClick($(this));
				}
			})
		}
		
		//娣诲姞鏂版暟鎹�
		if(option.add != ""){
			obj = option.add;
			var str = crateCommentInfo(obj);
			$(this).prepend(str).find(".reply-btn").click(function(){
				replyClick($(this));
			});
		}
	}
	
	
})(jQuery);