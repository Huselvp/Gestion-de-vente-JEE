<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: EasyGaming
  Date: 03/06/2022
  Time: 01:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    <title>Profile</title>
</head>
<body>
<jsp:include page="topnav.jsp" />
<div class="min-h-screen bg-white flex">
    <div class="flex-1 flex flex-col justify-center py-12 px-4 sm:px-6 lg:flex-none lg:px-20 xl:px-24">
        <div class="mx-auto w-full max-w-sm">
            <div>
                <h2 class="mt-6 text-3xl leading-9 font-extrabold text-gray-900">
                    Bienvenu <s:property value="user.getLogin()"/>
                </h2>
            </div>
            <div>
                <h4 class="mt-6 text-3xl leading-9 font-extrabold text-gray-900">
                    Modifier: mot de passe
                </h4>
            </div>
            <div class="mt-8">
                <div class="mt-6">
                    <s:form action="profile">
                        <div>
                            <div class="mt-1 rounded-md shadow-sm">
                                <s:textfield
                                        cssClass="appearance-none block px-3 py-2
                                                border border-gray-300 rounded-md placeholder-gray-400
                                                focus:outline-none focus:shadow-outline-blue
                                                focus:border-blue-300 transition duration-150
                                                ease-in-out sm:text-sm sm:leading-5"
                                        cssStyle="width: 300px; margin-bottom: 1em;"
                                        labelposition="top"
                                        labelSeparator=""
                                        name="password" label="Mot de passe" type="password" value=""/>
                            </div>
                            <div class="mt-1 rounded-md shadow-sm">
                                <s:textfield
                                        cssClass="appearance-none block w-full px-3 py-2
                                            border border-gray-300 rounded-md placeholder-gray-400
                                            focus:outline-none focus:shadow-outline-blue
                                            focus:border-blue-300 transition duration-150
                                            ease-in-out sm:text-sm sm:leading-5"
                                        cssStyle="width: 300px; margin-bottom: 1em;"
                                        labelposition="top"
                                        labelSeparator=""
                                        name="passwordConfirm" label="Confirmer mot de passe" type="password" value=""/>
                            </div>
                            <div class="mt-6">
                                <span class="block w-full rounded-md shadow-sm">
                                    <s:submit
                                            cssClass="w-full flex justify-center py-2 px-4 border
                                                border-transparent text-sm font-medium rounded-md
                                                text-white bg-indigo-600 hover:bg-indigo-500
                                                focus:outline-none focus:border-indigo-700
                                                focus:shadow-outline-indigo active:bg-indigo-700
                                                transition duration-150 ease-in-out"
                                            value="Modifier"/>
                                </span>
                            </div>
                    </s:form>
                    <s:if test="password!=passwordConfirm">
                        <p>Les mots de passe ne correspondent pas</p>
                    </s:if>
                        </div>
                </div>
            </div>
        </div>
        <div class="md:w-1/2">
            <img class="w-full"
                 src="styling/img/pass_reset.png"
                 alt="">
        </div>
    </div>
</div>
</body>
</html>
