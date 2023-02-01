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
    <title>Commandes</title>
</head>
<body>
<jsp:include page="topnav.jsp" />
<div class="container mx-auto px-4 sm:px-8">
    <div class="py-8">
        <h3>Liste des clients</h3>
    </div>
    <div class="-mx-6 sm:-mx-8 px-3 sm:px-8 py-4 overflow-x-auto">
        <div class="inline-block min-w-full shadow-md rounded-lg overflow-hidden">
            <s:if test="clients.size() > 0">
                <table class="min-w-full leading-normal">
                    <thead>
                    <tr>
                        <th class="px-5 py-3 border-b-2
                                       border-gray-200 bg-gray-100 text-left
                                       text-xs font-semibold text-gray-700
                                       uppercase tracking-wider">
                            Client</th>
                        <th class="px-5 py-3 border-b-2
                                       border-gray-200 bg-gray-100 text-left
                                       text-xs font-semibold text-gray-700
                                       uppercase tracking-wider"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="clients" status="clientStatus">
                        <tr>
                            <s:url id="fileDownload" namespace="/client" action="gemerateClientPdf">
                                <s:param name="id">
                                    <s:property value="client"/>
                                </s:param>
                            </s:url>
                            <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm"><s:property value="client" /></td>
                            <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm"><s:a class="px-5 py-5 border-b border-gray-200 bg-white text-sm" href="%{fileDownload}">facture</s:a></td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </s:if>
        </div>
    </div>
</div>
</body>
</html>
