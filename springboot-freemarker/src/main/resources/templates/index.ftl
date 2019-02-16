<!DOCTYPE html>

<html lang="en">

<body>
    hello, ${name}, let's go!
    <@myTag tagId = 1 tagName = "mytag">
        <#if myTag??>
            <p>${myTag.tagId},${myTag.tagName}</p>
        </#if>
    </@myTag>
</body>

</html>