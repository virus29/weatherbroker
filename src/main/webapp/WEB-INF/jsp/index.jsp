<%--
  Created by IntelliJ IDEA.
  User: IIakubov
  Date: 06.04.2018
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WeatherBrokerIndexPage</title>
</head>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
    var prefix = '/api/organization';

    var RestGet = function() {
        $.ajax({
            type: 'GET',
            url:  prefix + '/' + Date.now(),
            dataType: 'json',
            async: true,
            success: function(result) {
                alert('Время: ' + result.time
                    + ', сообщение: ' + result.message);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert(jqXHR.status + ' ' + jqXHR.responseText);
            }
        });
    }

    var RestPut = function() {
        var JSONObject= {
            'time': Date.now(),
            'message': 'Это пример вызова PUT метода'
        };

        $.ajax({
            type: 'PUT',
            url:  prefix,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(JSONObject),
            dataType: 'json',
            async: true,
            success: function(result) {
                alert('Время: ' + result.time
                    + ', сообщенеи: ' + result.message);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert(jqXHR.status + ' ' + jqXHR.responseText);
            }
        });
    }

    var RestPost = function() {
        $.ajax({
            type: 'POST',
            url:  prefix,
            dataType: 'json',
            async: true,
            success: function(result) {
                alert('Время: ' + result.time
                    + ', сообщение: ' + result.message);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert(jqXHR.status + ' ' + jqXHR.responseText);
            }
        });
    }

    var RestDelete = function() {
        $.ajax({
            type: 'DELETE',
            url:  prefix + '/' + Date.now(),
            dataType: 'json',
            async: true,
            success: function(result) {
                alert('Время: ' + result.time
                    + ', сообщение: ' + result.message);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert(jqXHR.status + ' ' + jqXHR.responseText);
            }
        });
    }
</script>

<body>

<h3>Это простой пример использования REST c помощью Ajax</h3>

<button type="button" onclick="RestGet()">Метод GET</button>
<button type="button" onclick="RestPost()">Метод POST</button>
<button type="button" onclick="RestDelete()">Метод DELETE</button>
<button type="button" onclick="RestPut()">Метод PUT</button>

</body>
</html>
