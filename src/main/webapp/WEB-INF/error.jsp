<main>
    <div class="container pt-5">
        <div class="row">
            <div class="col-12 text-center">
                <c:choose>
                    <c:when test="${initParam['debugging'] eq 'true'}">
                        <p>${errorMsg}</p>
                    </c:when>
                    <c:otherwise>
                        <h2>Oh no!</h2>
                        <img src="${pageContext.request.contextPath}/images/sadStitch.gif" alt="A photo of sad stitch">
                        <p class="mb-4">Something went wrong. We are sorry for the inconvenience.</p>
                        <a href="${appURL}" class="btn btn-primary">Take me to the Homepage</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</main>