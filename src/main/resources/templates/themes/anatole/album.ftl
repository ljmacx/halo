<#include "module/macro.ftl">
<@head title="${title}" keywords="${title}" description="${options.seo_description!'Anatole'}"></@head>
<#--<#include "module/sidebar.ftl">-->
<div class="main">
    <#include "module/page-top-new.ftl">
    <div class="autopagerize_page_element">
        <div class="content">
            <div class="archive animated fadeInDown">
                <ul class="list-with-title">
                    <div class="listing-title">${title}</div>

                    <@postTag method="archiveMonth">
                        <#list archives as archive>
                            <div class="listing-title">${archive.year?c + " - " + archive.month?c}</div>
                            <ul class="listing">
                                <#list archive.posts?sort_by("createTime")?reverse as post>
                                    <div class="listing-item">
                                        <div class="listing-post">
                                            <a href="javascript:openNewWin('${context!}/archives/${post.url!}','${post.title!}')">${post.title!}</a>
                                            <div class="post-time">
                                                <span class="date">${post.createTime?string("yyyy-MM-dd")}</span>
                                            </div>
                                        </div>
                                    </div>
                                </#list>
                            </ul>
                        </#list>
                    </@postTag>
                </ul>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function openNewWin(url,title)
    {
        window.open(url,title);
        // console.log('测试啊 9.39');
    }
</script>
<@footer></@footer>
