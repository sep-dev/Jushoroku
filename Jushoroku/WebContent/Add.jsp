<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="jushorokuPackage.*" import="java.sql.ResultSet" import="java.util.*" %>
<!-- 文字コード -->
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>住所録登録</title>
<style>
<%@ include file="/css/add.css" %>
</style>
</head>
<body>
<!-- 変数の宣言 -->
<%
String name = "";
String address = "";
String tel = "";
String categoryid = "";
String errmsg = "";
ResultSet rs = null;
%>

<!-- common.getCategoryAll()で取得した値を格納 -->
<%
Common common = new Common();
ArrayList<CategoryBean> beancategory = new ArrayList<CategoryBean>();
beancategory = Common.getCategoryAll();
%>
<!-- リクエスト(name)がnullの場合に行う処理 -->
<%
if (request.getParameter("name") == null) {
	name = "";
	address = "";
	tel = "";
	categoryid = "";
	errmsg = "";
} else {
	name = (String) request.getAttribute("name");
	address = (String) request.getAttribute("address");
	tel = (String ) request.getAttribute("tel");
	categoryid = (String) request.getAttribute("categoryid");
	errmsg = (String) request.getAttribute("errmsg");
}
%>

<h1>住所録管理システム：住所録登録</h1>
<form method="post" action="AddBL">
	<dl>
		<dt>名前＊</dt><dd><span>:&nbsp;</span><input type="text" id="name" name="name" value="<%=name %>" ></dd>
		<dt>住所＊</dt><dd><span>:&nbsp;</span><input type="text" class="addresswidth" id="address" name="address" value="<%=address %>" ></dd>
		<dt>電話番号</dt><dd><span>:&nbsp;</span><input type="text" id="tel" name="tel" value="<%=tel %>"></dd>
		<dt>カテゴリ</dt><dd><span>:</span>
		<select  name="categoryid" >
			<%for(int i = 0; i < beancategory.size(); i++) { %>
				<option value="<%=beancategory.get(i).getCategoryId() %>"><%=beancategory.get(i).getCategoryName() %></option>
			<%} %>
		</select></dd>
	</dl>
	<%=errmsg %>
	<input type="submit" class="button" value="確認">
	<!-- ボタンごとに指定可能formmethod -->
	<input type="submit" class="button" value="戻る" formmethod="get" formaction="ListBL">
</form>

</body>
</html>