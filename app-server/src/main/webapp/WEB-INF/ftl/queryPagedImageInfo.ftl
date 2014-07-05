<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>查看图片</title>
    <meta http-equiv="X-UA-Compatible" cotent="IE=edge,chrome=1"/>
    <meta name="description" content="图片查看"/>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>

    <link href="${e.res('/static/css/global.css')}" rel="stylesheet" type="text/css"/>
</head>
<body>
<#if (pager.rows?size>0) >
<table>
    <#list pager.rows as imageInfo>
        <#if (imageInfo_index)%5==0>
        <tr>
        </#if>
        <td>
            <img src="${e.imageHostPath}${imageInfo.compressImagePath}" height="240" width="240"/>
        </td>
        <#if (imageInfo_index+1)%5==0>
        </tr>
        </#if>
    </#list>
</table>
    <#import "/layout/app/common/pager.ftl" as q>
    <@q.pager pageNo=pager.curPage pageSize=pager.pageSize recordCount=pager.total toURL="${rc.contextPath}/app/queryPagedImageInfo"/>
</#if>
</body>
</html>