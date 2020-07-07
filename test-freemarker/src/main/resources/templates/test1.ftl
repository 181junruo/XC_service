<!DOCTYPE html>
<html>
<head>
    <meta charset="utf‐8">
    <title>Hello World!</title>
</head>
<body>
Hello ${name}!
<br>
遍历数据模型中list中的学生信息
<table>
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>金额</td>
        <td>出生日期</td>

    </tr>
    <#if stus??>
    <#list stus as stu>
        <tr>
            <td>${stu_index+1}</td>
            <td <#if stu.name == '小明'>style="background: aqua" </#if>>${stu.name}</td>
            <td>${stu.age}</td>
            <td <#if stu.money gt 300>style="background: aqua" </#if>>${stu.money}</td>
            <td>${stu.birthday?string("yyyy年MM月dd日")}</td>
        </tr>
    </#list>
    <br>
    学生个数:${stus?size}
    </#if>
</table>
<br>
遍历数据模型中的stuMap
<br>
姓名：${(stuMap['stu1'].name)!''}<br>
年龄：${(stuMap['stu1'].age)!''}<br>

姓名：${(stuMap.stu1.name)!''}<br>
年龄：${(stuMap.stu1.age)!''}<br>

<br>
遍历map中的key.stuMap?keys就是key列表（list）
<br>
 <#list stuMap?keys as k>
    姓名：${stuMap[k].name}<br>
    年龄：${stuMap[k].age}<br>
 </#list>

<br>
${point?c}
<br>
<#assign text="{'bank':'工商银行','account':'10101920201920212'}" />
<#assign data=text?eval />
开户行：${data.bank} 账号：${data.account}
</body>
</html>



