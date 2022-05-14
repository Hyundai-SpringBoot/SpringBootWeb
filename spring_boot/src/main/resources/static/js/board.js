var boardService = (function() {

	//댓글 조회
	function get(bno,callback,error) {
		$.get("/board/" + bno ,function(result){
			if(callback){
				callback(result);				
			}//end if			
		}).fail( function(xhr,status,err) {
			if(error){
				error();
			}
		  }				
		); 	
	}

	
	//댓글 삭제
	function remove(bno, callback, error) {
		$.ajax({
			type: "delete",
			url : "/board/" + bno,
			success: function(deleteResult,status,xhr) {
				if(callback){
					callback(deleteResult);
				}; 			
			}
		,
		error: function(xhr,status,er) {
			if(error){ error(er)};	
			}
		})			
	}
	
	
	
	//댓글 날짜(rdate) 수정하기
	function displayTime(timevalue) {
		var today = new Date();
		var gap = today.getTime() - timevalue;
		var dateObj = new Date(timevalue);
		var str ="";
		if (gap < (1000 * 60 * 60 * 24)) { //댓글이 하루안이면
			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();
			return [ (hh > 9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0') + mi,
					':', (ss > 9 ? '' : '0') + ss ].join('');

		} else { //댓글이 하루 지났으면
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth() + 1; // getMonth() is zero-based
			var dd = dateObj.getDate();
			return [ yy, '/', (mm > 9 ? '' : '0') + mm, '/',
					(dd > 9 ? '' : '0') + dd ].join('');
		}
		
	}


	return {
		get : get,
		remove : remove,
	    displayTime : displayTime
	}; 
})();
