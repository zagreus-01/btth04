<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manager - Category Management</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        h2 { margin-bottom: 10px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        table, th, td { border: 1px solid #ddd; }
        th, td { padding: 10px; text-align: left; }
        th { background: #f4f4f4; }
        button { margin: 2px; padding: 6px 12px; cursor: pointer; }

        /* Cột Actions nhỏ gọn */
        th.actions, td.actions {
            width: 160px; /* chiều rộng cố định */
            text-align: center;
            white-space: nowrap; /* tránh xuống dòng */
        }

        td.actions button, td.actions a button {
            padding: 4px 8px; /* thu nhỏ nút */
            font-size: 0.9em;
        }

        .popup {
            display: none; position: fixed; top: 50%; left: 50%;
            transform: translate(-50%, -50%);
            background: #fff; padding: 20px; border: 1px solid #ddd;
            box-shadow: 0 2px 8px rgba(0,0,0,0.3);
        }
        .popup.active { display: block; }
        .popup h3 { margin-top: 0; }
    </style>
</head>
<body>
    <div style="text-align:right;">
        <form action="${pageContext.request.contextPath}/logout" method="post">
            <button type="submit">Logout</button>
        </form>
    </div>

    <h2>Category Management (Manager)</h2>

    <!-- Add form -->
    <form action="${pageContext.request.contextPath}/manager/category/add" method="post">
        <input type="text" name="name" placeholder="Category Name" required>
        <button type="submit">Add</button>
    </form>

    <!-- Category List -->
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th class="actions">Actions</th>
        </tr>
        <c:forEach var="c" items="${categories}">
            <tr>
                <td>${c.id}</td>
                <td>${c.name}</td>
                <td class="actions">
                    <button onclick="openViewPopup('${c.id}', '${c.name}')">View</button>
                    <button onclick="openEditPopup('${c.id}', '${c.name}')">Edit</button>
                    <a href="${pageContext.request.contextPath}/manager/category/delete?id=${c.id}">
                        <button type="button">Delete</button>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <!-- View Popup -->
    <div id="viewPopup" class="popup">
        <h3>Category Details</h3>
        <p><b>ID:</b> <span id="viewId"></span></p>
        <p><b>Name:</b> <span id="viewName"></span></p>
        <button onclick="closePopup('viewPopup')">Close</button>
    </div>

    <!-- Edit Popup -->
    <div id="editPopup" class="popup">
        <h3>Edit Category</h3>
        <form action="${pageContext.request.contextPath}/manager/category/edit" method="post">
            <input type="hidden" name="id" id="editId">
            <input type="text" name="name" id="editName" required>
            <button type="submit">Save</button>
            <button type="button" onclick="closePopup('editPopup')">Cancel</button>
        </form>
    </div>

    <script>
        function openViewPopup(id, name) {
            document.getElementById("viewId").innerText = id;
            document.getElementById("viewName").innerText = name;
            document.getElementById("viewPopup").classList.add("active");
        }

        function openEditPopup(id, name) {
            document.getElementById("editId").value = id;
            document.getElementById("editName").value = name;
            document.getElementById("editPopup").classList.add("active");
        }

        function closePopup(popupId) {
            document.getElementById(popupId).classList.remove("active");
        }
    </script>
</body>
</html>
