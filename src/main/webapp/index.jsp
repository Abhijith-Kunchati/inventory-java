<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" xmlns="http://java.sun.com/jsf/html">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/JS/index.js"></script>
    <title> Inventory WebApp </title>
</head>
<body>
<h1 class="center-align">Inventory Grocery</h1>
<button onclick="init()" class="blue-grey darken-4 white-text btn waves-effect waves-light">Check</button>
<table class="striped centered responsive-table">
    <thead class="blue-grey darken-4 white-text">
    <tr>
        <th scope="col">#Item Id</th>
        <th scope="col">Item Name</th>
        <th scope="col">Sub-category</th>
        <th scope="col">Brand</th>
        <th scope="col">Quantity</th>
        <th scope="col">Price</th>
    </tr>
    </thead>
    <tbody id="item-list"></tbody>
</table>
</body>
</html>