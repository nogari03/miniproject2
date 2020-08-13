<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
<script>
	function fn_process() {
		var _id = $("#id").val();
		$.ajax({
					type : "post",
					dataType : "text",
					url : "/jsonPractice",
					data : {
						id : _id,
						action: "process.do"
					},
					success : function(data, textStatus) {
						console.log(data);
						var jsonInfo = JSON.parse(data);
						var memberInfo = "<tr>"
						for ( var i in jsonInfo.members) { 
							memberInfo += "<td>고객ID</td>"+"<td><input type='text' id='id2' value='"+ jsonInfo.members[i].id +"'></td></tr>";	
							memberInfo += "<tr><td>고객PWD</td>"+"<td><input type='text' id='pwd' value='"+jsonInfo.members[i].pwd +"'></td></tr>";	
							memberInfo += "<tr><td>고객NAME</td>"+"<td><input type='text' id='name' value='"+jsonInfo.members[i].name +"'></td></tr>";	
							console.log(jsonInfo.members[i].id);
						}
						console.log(memberInfo);
						$("#output").html(memberInfo);
					},
					error : function(data, textStatus) {
						console.log(data.readyState);
						console.log(data.status);
						console.log(data.responseText);
					},
					complete : function(data, textStatus) {
					}
				});
	}
	</script>
	
	<script>
	function fn_add() {
			var memberInfo = "<tr>"
				memberInfo += "<td>고객ID</td>"+"<td><input type='text' id='id2'></td></tr>";	
				memberInfo += "<tr><td>고객PWD</td>"+"<td><input type='text' id='pwd'></td></tr>";;	
				memberInfo += "<tr><td>고객NAME</td>"+"<td><input type='text' id='name'></td></tr>";	
			console.log(memberInfo);
			$("#output").html(memberInfo);

	}
	</script>
	<script>
	function fn_save() {
		if($("#id").val()!=""){
			var _id = $("#id").val();
			var _id2 = $("#id2").val();
			var _pwd = $("#pwd").val();	
			var _name = $("#name").val();	
	$.ajax({
		type : "post",
		dataType : "text",
		url : "/jsonPractice",
		data : {
			id: _id,
			id2 : _id2,
			pwd : _pwd,
			name : _name,
			action : "save.do",
			fun : "edit"
		},success : function(data, textStatus) {
			console.log("여기임??"+data);
			if(data=="yes"){
				var memberInfo ="<tr><th>수정되었습니다.</th></tr>"
					$("#output").html(memberInfo);
			}else if(data=="ok"){
				var memberInfo ="<tr><th>추가되었습니다.</th></tr>"
					$("#output").html(memberInfo); }
		},
		error : function(data, textStatus) {
			console.log(data.readyState);
			console.log(data.status);
			console.log(data.responseText);
		},
		complete : function(data, textStatus) {
		}
	}); 
	}
		else if($("#id").val()==""){
			var _id = $("#id").val();
			var _id2 = $("#id2").val();
			var _pwd = $("#pwd").val();	
			var _name = $("#name").val();	
			$.ajax({
				type : "post",
				dataType : "text",
				url : "/jsonPractice",
				data : {
					id: "empty",
					id2 : _id2,
					pwd : _pwd,
					name : _name,
					action : "save.do",
					fun : "add"
				}, success : function(data, textStatus) {
					console.log(data);
					if(data=="yes"){
						var memberInfo ="<tr><th>수정되었습니다.</th></tr>"
							$("#output").html(memberInfo);
					}else if(data=="ok"){
						var memberInfo ="<tr><th>추가되었습니다.</th></tr>"
							$("#output").html(memberInfo); }
				},
				error : function(data, textStatus) {
					console.log(data.readyState);
					console.log(data.status);
					console.log(data.responseText);
				},
				complete : function(data, textStatus) {
				}
			}); 
		}	
}
	</script>
	<script>
function fn_prev() {
		var _id2 = $("#id2").val();
$.ajax({
			type : "post",
			dataType : "text",
			url : "/jsonPractice",
			data : {
				id : _id2,
				action : "prev.do"
			},
			success : function(data, textStatus) {
				console.log(data);
				var jsonInfo = JSON.parse(data);
				var memberInfo = "<tr>"
				for ( var i in jsonInfo.members) { 
					if(_id2==jsonInfo.members[i].id){
					memberInfo += "<td>고객ID</td>"+"<td><input type='text' id='id2' value='"+ jsonInfo.members[i-1].id +"'></td></tr>";	
					memberInfo += "<tr><td>고객PWD</td>"+"<td><input type='text' id='pwd' value='"+ jsonInfo.members[i-1].pwd +"'></td></tr>";	
					memberInfo += "<tr><td>고객NAME</td>"+"<td><input type='text' id='name' value='"+ jsonInfo.members[i-1].name +"'></td></tr>";	
					console.log(jsonInfo.members[i-1].id);
					}
				}
				console.log(memberInfo);
				$("#output").html(memberInfo);
			},
			error : function(data, textStatus) {
				console.log(data.readyState);
				console.log(data.status);
				console.log(data.responseText);
			},
			complete : function(data, textStatus) {
			}
		});
	}
	</script>
	<script>
	function fn_next() {
		var _id2 = $("#id2").val();
$.ajax({
			type : "post",
			dataType : "text",
			url : "/jsonPractice",
			data : {
				id : _id2,
				action : "next.do"
			},
			success : function(data, textStatus) {
				console.log(data);
				var jsonInfo = JSON.parse(data);
				var memberInfo = "<tr>"
				for ( var i in jsonInfo.members) { 
					if(_id2==jsonInfo.members[i].id){
					memberInfo += "<td>고객ID</td>"+"<td><input type='text' id='id2' value='"+ jsonInfo.members[(i*1)+1].id +"'></td></tr>";	
					memberInfo += "<tr><td>고객PWD</td>"+"<td><input type='text' id='pwd' value='"+ jsonInfo.members[(i*1)+1].pwd +"'></td></tr>";	
					memberInfo += "<tr><td>고객NAME</td>"+"<td><input type='text' id='name' value='"+ jsonInfo.members[(i*1)+1].name +"'></td></tr>";	
					console.log(jsonInfo.members[(i*1)+1].id);
					}
				}
				console.log(memberInfo);
				$("#output").html(memberInfo);
			},
			error : function(data, textStatus) {
				console.log(data.readyState);
				console.log(data.status);
				console.log(data.responseText);
			},
			complete : function(data, textStatus) {
			}
		});
	}
</script>
	

</head>
<body>
	<input type="text" id="id">
	<button onclick="fn_process()">조회</button>
	<button onclick="fn_add()">추가</button>
	<button onclick="fn_save()">저장</button>
	<button onclick="fn_prev()">이전</button>
	<button onclick="fn_next()">이후</button>

	<table id="output"></table>

</body>
</html>