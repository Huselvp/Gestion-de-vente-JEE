<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    <title>Articles</title>
</head>
<body>
<jsp:include page="topnav.jsp" />
<div class="container mx-auto px-4 sm:px-8">
    <div class="py-8">
        <div>
            <h2>Liste des articles</h2>
        </div>
        <div class="-mx-4 sm:-mx-8 px-4 sm:px-8 py-4 overflow-x-auto">
            <div class="inline-block min-w-full shadow-md rounded-lg overflow-hidden">
                <s:if test="articleDAOs.size() > 0">
                    <table class="min-w-full leading-normal">
                        <thead>
                        <tr>
                            <th class="px-5 py-3 border-b-2
                                border-gray-200 bg-gray-100 text-left
                                text-xs font-semibold text-gray-700
                                uppercase tracking-wider">
                                Code
                            </th>
                            <th class="px-5 py-3 border-b-2
                                border-gray-200 bg-gray-100
                                text-left text-xs font-semibold
                                text-gray-700 uppercase tracking-wider">
                                Nom
                            </th>
                            <th class="px-5 py-3 border-b-2
                                border-gray-200 bg-gray-100
                                text-left text-xs font-semibold
                                text-gray-700 uppercase tracking-wider">
                                Prix
                            </th>
                            <th class="px-5 py-3 border-b-2
                                border-gray-200 bg-gray-100
                                text-left text-xs font-semibold
                                text-gray-700 uppercase tracking-wider">
                                Quantite
                            </th>
                            <th class="px-5 py-3 border-b-2
                                border-gray-200 bg-gray-100
                                text-left text-xs font-semibold
                                text-gray-700 uppercase tracking-wider">
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <s:iterator value="articleDAOs" status="userStatus">
                            <tr>

                                <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                    <s:property value="code"/>
                                </td>
                                <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                    <s:property value="nom"/>
                                </td>
                                <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                    <s:property value="prix"/>
                                </td>
                                <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                    <s:property value="qte"/>
                                </td>
                                <td>
                                    <s:form theme="simple" cssClass="form-inline" action="addCommande">
                                        <s:hidden key="code"/>
                                        <div class="flex flex-wrap -mx-3 mb-2">
                                            <div class="w-full md:w-1/3 px-3 mb-6 md:mb-0">
                                                <s:textfield
                                                        cssClass="appearance-none block w-full px-3 py-2
                                                            border border-gray-300 rounded-md placeholder-gray-400
                                                            focus:outline-none focus:shadow-outline-blue
                                                            focus:border-blue-300 transition duration-150
                                                            focus:bg-white bg-gray-200
                                                            ease-in-out sm:text-sm sm:leading-5" placeholder="Quantite"
                                                        cssStyle="margin-top: 1em;"
                                                        type="number"
                                                        value="0"
                                                        name="qte"/>
                                            </div>
                                            <div class="w-full md:w-1/3 px-3 mb-6 md:mb-0">
                                                <s:textfield
                                                        cssClass="appearance-none block w-full px-3 py-2
                                                            border border-gray-300 rounded-md placeholder-gray-400
                                                            focus:outline-none focus:shadow-outline-blue
                                                            focus:border-blue-300 transition duration-150
                                                            focus:bg-white bg-gray-200
                                                            ease-in-out sm:text-sm sm:leading-5"
                                                        cssStyle="margin-top: 1em;"
                                                        placeholder="Client"
                                                        name="client"/>
                                            </div>
                                            <span class="block rounded-md shadow-sm">
                                                <s:submit
                                                        cssClass="w-full flex justify-center py-2 px-4 border
                                                            border-transparent text-sm font-medium rounded-md
                                                            text-white bg-indigo-600 hover:bg-indigo-500
                                                            focus:outline-none focus:border-indigo-700
                                                            focus:shadow-outline-indigo active:bg-indigo-700
                                                            transition duration-150 ease-in-out"
                                                        cssStyle="margin-top: 1em;"
                                                        value="Ajouter commande"/>
                                            </span>
                                            </div>
                                        </div>
                                        </s:form>
                                </td>
                            </tr>
                        </s:iterator>
                        </tbody>
                    </table>
                </s:if>
            </div>
        </div>
    </div>
</div>

</body>
</html>
