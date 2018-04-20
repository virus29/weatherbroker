<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: IIakubov
  Date: 06.04.2018
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>WeatherBroker</title>
    <%--<link rel="stylesheet" href="css/bootstrap.min.css">--%>
    <%--<script src="js/bootstrap.min.js"></script>--%>
    <%--<link rel="stylesheet" href="css/bootstrap.min.css">--%>
        <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <!--<script src="js/jquery.min.js"></script>-->
    <!--<script src="js/form.js"> </script>-->
</head>

<!--<script type="text/javascript">-->


    <!--// $("button").click(function(){-->
    <!--//     $.get("cityname", function(data, status){-->
    <!--//         alert("Data: " + data + "\nStatus: " + status);-->
    <!--//     });-->
    <!--// });-->

    <!--$(document).ready(function() {-->

        <!--$("#cityNameSubmit").on("click", function(){-->
            <!--// Only change code below this line.-->

            <!--$.getJSON("/moskow", function(json) {-->
                <!--$(".message").html(JSON.stringify(json));-->
            <!--});-->

            <!--// Only change code above this line.-->
        <!--});-->

    <!--});-->
    <!--// var prefix = '/';-->
    <!--//-->
    <!--// var RestGet = function() {-->
    <!--//     $.ajax({-->
    <!--//         type: 'GET',-->
    <!--//         url:  prefix + cityName,-->
    <!--//         dataType: 'json',-->
    <!--//         async: true,-->
    <!--//         success: function(result) {-->
    <!--//             alert(result.message)-->
    <!--//         },-->
    <!--//         error: function(jqXHR, textStatus, errorThrown) {-->
    <!--//             alert(jqXHR.status + ' ' + jqXHR.responseText);-->
    <!--//         }-->
    <!--//     });-->
    <!--// }-->
<!--</script>-->
<%--${document.body.getElementById("cityName").value}--%>

<body>

<form:form method="GET" action="/weatherbroker-1.0-SNAPSHOT/cityName">
    <div class="form-group">
        <label for="cityName">Название Города: </label>
        <input type="text" class="form-control" id="cityName" name="cityName" placeholder="cityName">
        <small id="cityNameHelp" class="form-text text-muted">Введите грод для получения погоды</small>
    </div>
    <button type="submit" class="btn btn-primary" id="cityNameSubmit">Получить прогноз погоды</button>
</form:form>

</body>
</html>
