<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<div class="am-cf">
    <div class="am-fr">
        <ul class="am-pagination tpl-pagination">
            <c:choose>
                <c:when test="${page.pages == 1}">
                    <li class="am-disabled"><a href="javascript:firstPage(${page.pageNum});">«</a></li>
                    <li class="am-active"><a href="javascript:void(0);">1</a></li>
                    <li class="am-disabled"><a href="javascript:endPage(${page.pageNum});">»</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="javascript:firstPage(${page.pageNum});">«</a></li>
                    <c:forEach begin="1" end="${page.pages}" var="p">
                        <c:choose>

                            <c:when test="${p == page.pageNum}">
                                <li class="am-active"><a href="javascript:void(0);">${p}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="javascript:showPage(${p});">${p}</a></li>
                            </c:otherwise>

                        </c:choose>
                    </c:forEach>
                    <li><a href="javascript:endPage(${page.pageNum});">»</a></li>
                </c:otherwise>
            </c:choose>

        </ul>
    </div>
</div>

<script type="text/javascript">
    // 首页
    function firstPage(curtPage) {
        if(curtPage == 1) {
            return;
        }
        reQueryForm(1);
    }

    // 尾页
    function endPage(curtPage) {
        if(curtPage == ${page.pages}) {
            return;
        }
        reQueryForm(${page.pages});
    }

    // 第几页
    function showPage(pageNum) {
        reQueryForm(pageNum);

    }

    function reQueryForm(pageNum) {
        $("form>input:eq(0)").val(pageNum);
        $("form>input:eq(1)").val("9");
        $("form:eq(0)").submit();
    }
</script>