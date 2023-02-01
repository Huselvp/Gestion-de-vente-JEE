<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    <style><%@include file="styling/default.css"%></style>
    <title>Login</title>
</head>
<body>
<s:url id= "signup" action="signup" escapeAmp="false"/>
<div class="min-h-screen bg-white flex">
    <div class="flex-1 flex flex-col justify-center py-12 px-4 sm:px-6 lg:flex-none lg:px-20 xl:px-24">
        <div class="mx-auto w-full max-w-sm">
            <div>
                <div>
                    <img class="inset-0 w-3/4 object-cover"
                         src="styling/img/logo.png"
                         alt="" />
                </div>
                <h2 class="mt-6 text-3xl leading-9 font-extrabold text-gray-900">
                    Connection
                </h2>
            </div>
            <div class="mt-8">
                <div class="mt-6">
                    <s:form action="login">
                        <div>
                            <div class="mt-1 rounded-md shadow-sm">
                                <s:textfield
                                        cssClass="appearance-none block w-full px-3 py-2
                                            border border-gray-300 rounded-md placeholder-gray-400
                                            focus:outline-none focus:shadow-outline-blue
                                            focus:border-blue-300 transition duration-150
                                            ease-in-out sm:text-sm sm:leading-5"
                                        cssStyle="width: 300px; margin-bottom: 1em;"
                                        label="Compte"
                                        labelSeparator=""
                                        labelposition="top"
                                        name="login"/>
                            </div>
                            <div class="mt-1 rounded-md shadow-sm">
                                <s:textfield
                                        cssClass="appearance-none block w-full px-3 py-2
                                            border border-gray-300 rounded-md placeholder-gray-400
                                            focus:outline-none focus:shadow-outline-blue
                                            focus:border-blue-300 transition duration-150
                                            ease-in-out sm:text-sm sm:leading-5"
                                        cssStyle="width: 300px; margin-bottom: 1em;"
                                        label="Mot de passe"
                                        labelSeparator=""
                                        labelposition="top"
                                        name="password"
                                        type="password"/>
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
                                            value="Se connecter"/>
                                </span>
                            </div>
                            </s:form>
                            <div class="text-xl py-4 leading-5">
                                <p>vous avez pas de compte?</p>
                                <s:a cssClass="font-medium text-indigo-600 hover:text-indigo-500
                                        focus:outline-none focus:underline transition ease-in-out
                                        duration-150"
                                     href="%{signup}">
                                    s'enregister
                                </s:a>
                            </div>
                        </div>
                </div>
            </div>
        </div>
        <div class="max-w-full relative lg:block  w-0 flex-1">
            <img class="absolute inset-0 h-full w-full object-cover"
                 src="styling/img/login.png"
                 alt="" />
        </div>
    </div>
</div>
</body>
</html>