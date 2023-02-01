<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: silverest
  Date: 6/3/22
  Time: 5:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    <title>Commandes</title>
</head>
<body>
<jsp:include page="topnav.jsp" />
<div class="container mx-auto px-4 sm:px-8">
    <div class="py-8">
        <div>
            <h3>Liste des commandes</h3>
        </div>
        <div class="-mx-4 sm:-mx-8 px-4 sm:px-8 py-4 overflow-x-auto">
            <div class="inline-block min-w-full shadow-md rounded-lg overflow-hidden">
                <s:if test="commandes.size() > 0">
                    <table class="min-w-full leading-normal">
                        <thead>
                        <tr>
                            <th class="px-5 py-3 border-b-2
                                       border-gray-200 bg-gray-100 text-left
                                       text-xs font-semibold text-gray-700
                                       uppercase tracking-wider">
                                Numéro</th>
                            <th class="px-5 py-3 border-b-2
                                       border-gray-200 bg-gray-100 text-left
                                       text-xs font-semibold text-gray-700
                                       uppercase tracking-wider">
                                Client</th>
                            <th class="px-5 py-3 border-b-2
                                       border-gray-200 bg-gray-100 text-left
                                       text-xs font-semibold text-gray-700
                                       uppercase tracking-wider">
                                Quantite</th>
                            <th class="px-5 py-3 border-b-2
                                       border-gray-200 bg-gray-100 text-left
                                       text-xs font-semibold text-gray-700
                                       uppercase tracking-wider">
                                Date</th>
                            <th class="px-5 py-3 border-b-2
                                       border-gray-200 bg-gray-100 text-left
                                       text-xs font-semibold text-gray-700
                                       uppercase tracking-wider"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <s:iterator value="commandes" status="commandeStatus">
                            <tr>
                                <s:url id="fileDownload" namespace="/commande" action="getPdf">
                                    <s:param name="id">
                                        <s:property value="codeCmd"/>
                                    </s:param>
                                </s:url>
                                <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm"><s:property value="codeCmd" /></td>
                                <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm"><s:property value="client" /></td>
                                <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm" style="text-align:center"><s:property value="qte" /></td>
                                <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm"><s:date name="date" format="dd/MM/yyyy"/></td>
                                <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm"><s:a class="text-indigo-600 hover:text-indigo-900 focus:outline-none focus:underline" href="%{fileDownload}">facture</s:a></td>
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
