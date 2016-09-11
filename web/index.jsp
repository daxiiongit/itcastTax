<%@ page import="javafx.application.Application" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.XmlWebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="com.sunyanxiong.entity.Person" %><%--
  Created by IntelliJ IDEA.
  User: Daxiong
  Date: 2016/9/9 0009
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <%-- 测试web服务器启动时是否成功加载 spring IoC 容器 --%>
  <%--<%
    /* 1.从 application 域对象中获取spring IoC 容器 */
    ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
    /* 2.从spring IoC 容器中获取 bean */
    Person person = ctx.getBean(Person.class);
    /* 3.使用 bean */
    person.hello();
  %>--%>

  <%-- 请求地址 --%>
  <a href="person-save.action">person save</a>

  </body>
</html>
