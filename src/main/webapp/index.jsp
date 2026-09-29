<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Student Management System</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; }
        fieldset { margin-bottom: 15px; }
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #999; padding: 6px 10px; text-align: left; }
        .ok { color: green; } .err { color: red; }
    </style>
</head>
<body>
<h2>Student Management System</h2>

<c:if test="${not empty message}"><p class="ok"><c:out value="${message}"/></p></c:if>
<c:if test="${not empty error}"><p class="err"><c:out value="${error}"/></p></c:if>

<fieldset><legend>1. Add Student</legend>
<form method="post" action="students">
    <input type="hidden" name="action" value="add">
    ID: <input type="number" name="studentId" required>
    Name: <input type="text" name="name" required>
    Age: <input type="number" name="age" required>
    Course: <input type="text" name="course" required>
    Marks: <input type="number" step="0.01" name="marks" required>
    <button type="submit">Add</button>
</form></fieldset>

<fieldset><legend>2. View All Students</legend>
<a href="students">View All</a></fieldset>

<fieldset><legend>3. Search Student by ID</legend>
<form method="get" action="students">
    <input type="hidden" name="action" value="search">
    ID: <input type="number" name="studentId" required>
    <button type="submit">Search</button>
</form></fieldset>

<fieldset><legend>4. Update Student Marks</legend>
<form method="post" action="students">
    <input type="hidden" name="action" value="updateMarks">
    ID: <input type="number" name="studentId" required>
    New Marks: <input type="number" step="0.01" name="marks" required>
    <button type="submit">Update</button>
</form></fieldset>

<fieldset><legend>5. Delete Student</legend>
<form method="post" action="students">
    <input type="hidden" name="action" value="delete">
    ID: <input type="number" name="studentId" required>
    <button type="submit">Delete</button>
</form></fieldset>

<fieldset><legend>6. Exit</legend>
<a href="students?action=exit">Exit</a></fieldset>

<h3>Students</h3>
<table>
    <tr><th>ID</th><th>Name</th><th>Age</th><th>Course</th><th>Marks</th></tr>
    <c:forEach var="s" items="${students}">
        <tr>
            <td><c:out value="${s.studentId}"/></td>
            <td><c:out value="${s.name}"/></td>
            <td><c:out value="${s.age}"/></td>
            <td><c:out value="${s.course}"/></td>
            <td><c:out value="${s.marks}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>