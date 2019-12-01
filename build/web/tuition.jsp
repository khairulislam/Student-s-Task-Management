<%-- 
    Document   : tuition
    Created on : Dec 16, 2016, 4:40:31 PM
    Author     : khair
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tuition Page</title>
    </head>
    <body>
    <jsp:include page="navigation.jsp"/>
        <h1>Your tuitions!</h1>
        <h2>Add a row </h2>
        
        <form method="post" action="TuitionProcess">
        <pre>
        Tuition id : <input type="text" name="tuition_id" />(Must not be blank )</br>
        Time      : <input type="text" name="time" /></br>
        
        </br><h2>This part is optional :</h2></br>
        Place      : <input type="text" name="place" /></br>
        Salary      : <input type="text" name="salary" /></br>
        <input type="submit" value="Sumbit" /></br>
            </pre>
        </form>
        <h2> List of your tuitions</h2>
        </br>
        <h2>Time schedule for your tuitions</h2>
    </body>
</html>
