<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: EasyGaming
  Date: 07/06/2022
  Time: 04:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">

<s:url id= "articles" action="articles" escapeAmp="false"/>
<s:url id= "commandes" action="commandes" escapeAmp="false"/>
<s:url id= "clients" action="clients" escapeAmp="false"/>
<s:url id= "profile" action="profile" escapeAmp="false"/>
<s:url id= "logout" action="logout" escapeAmp="false"/>
<nav class="fixed w-screen bg-gray-900 lg:px-40 sm:px-10 px-2 sm:flex justify-between items-center border-b-1 border-gray-600">
    <div class="flex px-4 justify-between sm:py-0 pb-1">
        <a href="">
            <img src="styling/img/logo.png" class="h-12" style="filter: brightness(0) invert(1);" alt="">
        </a>

        <button class='text-blue-300 text-3xl sm:hidden block focus:outline-none' id='navIcon'>
            &#9776;
        </button>
    </div>
    <div class="flex px-4 item-center justify-between sm:py-0 pb-1">
        <ul class='hidden sm:flex cursor-pointer' id='navContent'>
            <li class='py-4 px-6 sm:border-b-2 border-blue-300 hover:text-blue-300 transition duration-200 text-blue-300 hover:bg-gray-800 sm:hover:bg-transparent text-white '>
                <s:a href="%{articles}">Articles</s:a>
            </li>
            <li class='py-4 px-6 sm:border-b-2 border-blue-300 hover:text-blue-300 transition duration-200 text-blue-300 hover:bg-gray-800 sm:hover:bg-transparent text-white '>
                <s:a href="%{commandes}">Commandes</s:a>
            </li>
            <li class='py-4 px-6 sm:border-b-2 border-blue-300 hover:text-blue-300 transition duration-200 text-blue-300 hover:bg-gray-800 sm:hover:bg-transparent text-white '>
                <s:a href="%{clients}">Clients</s:a>
            </li>
            <li class='py-4 px-6 sm:border-b-2 border-blue-300 hover:text-blue-300 transition duration-200 text-blue-300 hover:bg-gray-800 sm:hover:bg-transparent text-white '>
                <s:a href="%{profile}">Profile</s:a>
            </li>
            <li class='py-4 px-6 sm:border-b-2 border-blue-300 hover:text-blue-300 transition duration-200 text-blue-300 hover:bg-gray-800 sm:hover:bg-transparent text-white '>
                <s:a href="%{logout}">Se deconnecter</s:a>
            </li>
        </ul>
    </div>
</nav>
