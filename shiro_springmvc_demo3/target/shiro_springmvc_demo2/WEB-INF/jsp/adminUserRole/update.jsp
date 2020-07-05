<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@include file="/WEB-INF/jsp/header.jsp" %>

<title>分配角色</title>
</head>
<body>
<div class="pd-20">
<%--    当前提交的服务器的地址--%>
    <form action="dosubmitUpdateAdminUserRoles.do" method="post">
<%--        隐藏表单域   --%>
    <input type="hidden" name="adminUserId" value="${adminUserId }" />
<%--    <div class="row cl">--%>
<%--        <label class="form-label col-2">用户账号:</label>--%>
<%--        <div class="formControls col-5">--%>
<%--            <input id="password" type="text" readonly class="input-text" name="account" value="${adminUserId }"  />--%>
<%--        </div>--%>
<%--        <div class="col-5"></div>--%>
<%--    </div>--%>
        <table class="table table-border table-bordered table-bg table-hover" >

		<!-- 此处要修改的其它用户信息-->
            <thead>
                <tr>
                    <th>选中</th>
                    <th>角色名称</th>
                    <th>角色描述</th>
                </tr>
            </thead>
            <tbody>
                    
            <!-- 此处写角色选中。-->
            <c:forEach var="role" items="${roleList}">
                <tr>
                    <td>
<%--                        如果当前用户存在角色选中，如果不存在的角色不选中。--%>
                           <input type="checkbox" name="roleId" value="${role.id}"
                           <c:forEach items="${adminUserRoleList}" var="adminUserRole">
                               <c:if test="${role.id eq adminUserRole.roleId}">checked</c:if>
                           </c:forEach>
                           />
                    </td>
                    <td>
                           ${role.name}
                    </td>
                    <td>
                            ${role.description}
                    </td>
                </tr>
            </c:forEach>

                    
            </tbody>
        </table>
        <br>
        <input class="btn btn-primary radius" type="submit" value="分配角色" />
        <input class="btn btn-default radius" type="button" value="关闭" onclick="location.href='list.do'" style="margin-left: 30px;" />
    </form>
</div>
</body>
</html>