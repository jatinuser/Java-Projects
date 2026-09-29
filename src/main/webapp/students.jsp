<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://sun.com" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Details Dashboard</title>
    <!-- Tailwind CSS CDN for modern, clean styling -->
    <script src="https://tailwindcss.com"></script>
</head>
<body class="bg-gray-50 font-sans antialiased text-gray-900">

<div class="max-w-6xl mx-auto px-4 py-8">
    <!-- Header Section -->
    <div class="mb-8 border-b border-gray-200 pb-4">
        <h1 class="text-3xl font-bold tracking-tight text-gray-900">Student Directory</h1>
        <p class="text-sm text-gray-500 mt-1">A comprehensive list of all enrolled student records.</p>
    </div>

    <!-- Table Container -->
    <div class="overflow-hidden bg-white shadow-sm ring-1 ring-gray-200 rounded-lg">
        <table class="min-w-full divide-y divide-gray-200">
            <thead class="bg-gray-100">
            <tr>
                <th scope="col" class="py-3.5 pl-4 pr-3 text-left text-xs font-semibold uppercase tracking-wider text-gray-600 sm:pl-6">ID</th>
                <th scope="col" class="px-3 py-3.5 text-left text-xs font-semibold uppercase tracking-wider text-gray-600">Full Name</th>
                <th scope="col" class="px-3 py-3.5 text-left text-xs font-semibold uppercase tracking-wider text-gray-600">Age</th>
                <th scope="col" class="px-3 py-3.5 text-left text-xs font-semibold uppercase tracking-wider text-gray-600">Course</th>
                <th scope="col" class="px-3 py-3.5 text-left text-xs font-semibold uppercase tracking-wider text-gray-600">Marks</th>
            </tr>
            </thead>
            <tbody class="divide-y divide-gray-200 bg-white">
            <!-- Checks if the student list is empty -->
            <c:choose>
                <c:when class="empty-list-check" test="${not empty studentList}">
                    <!-- Iterates over the 'studentList' collection passed from your Servlet -->
                    <c:forEach var="student" items="${studentList}">
                        <tr class="hover:bg-gray-50 transition-colors">
                            <td class="whitespace-nowrap py-4 pl-4 pr-3 text-sm font-medium text-gray-900 sm:pl-6">
                                <c:out value="${student.id}" />
                            </td>
                            <td class="whitespace-nowrap px-3 py-4 text-sm text-gray-700 font-medium">
                                <c:out value="${student.name}" />
                            </td>
                            <td class="whitespace-nowrap px-3 py-4 text-sm text-gray-500">
                                <c:out value="${student.age}" />
                            </td>
                            <td class="whitespace-nowrap px-3 py-4 text-sm text-gray-500">
                                        <span class="inline-flex items-center rounded-md bg-blue-50 px-2 py-1 text-xs font-medium text-blue-700 ring-1 ring-inset ring-blue-700/10">
                                            <c:out value="${student.course}" />
                                        </span>
                            </td>
                            <td class="whitespace-nowrap px-3 py-4 text-sm font-semibold text-gray-700">
                                <c:out value="${student.marks}" />
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <!-- Placeholder row if no student records are found -->
                    <tr>
                        <td colspan="5" class="px-6 py-10 text-center text-sm text-gray-500">
                            No student records found.
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
