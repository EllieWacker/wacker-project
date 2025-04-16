<section class="h-100 h-custom" style="background-color: gray;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12">
                <div class="card card-registration card-registration-2" style="border-radius: 15px;">
                    <div class="card-body p-0">
                        <div class="row g-0">
                            <div class="col-lg-8">
                                <div class="p-5">
                                    <div class="d-flex justify-content-between align-items-center mb-5">
                                        <h1 class="fw-bold mb-0">Shopping Cart</h1>
                                        <h6 class="mb-0 text-muted">
                                            <c:choose>
                                                <c:when test="${not empty cart}">
                                                    ${cart.totalPuppyCount} &nbsp ${cart.totalPuppyCount eq 1 ? 'item' : 'items'}
                                                </c:when>
                                                <c:otherwise>

                                                </c:otherwise>
                                            </c:choose>
                                        </h6>
                                    </div>
                                    <hr class="my-4">


                                    <!-- puppy -->
                                    <c:forEach items="${cart.contents}" var="entry">
                                        <div class="row mb-4 d-flex justify-content-between align-items-center">
                                            <div class="col-md-3 col-lg-2 col-xl-2">
                                                <img
                                                        src="${pageContext.request.contextPath}${fn:replace(entry.key.image, '^/', '')}"
                                                        class="img-fluid rounded-3" alt="Puppy img">
                                            </div>
                                            <div class="col-md-3 col-lg-3 col-xl-3">
                                                <h6 class="text-muted">Puppy</h6>
                                                <h6 class="mb-0">${entry.key.puppyID}</h6>
                                            </div>
                                            <div class="col-md-3">
                                                <h6 class="text-muted">Breed</h6>
                                                <h6 class="mb-0">${entry.key.breedID}</h6>
                                            </div>
                                            <div class="col-md-2">
                                                <h6 class="mb-0"><fmt:formatNumber value="${entry.key.price}" type="currency"></fmt:formatNumber></h6>
                                            </div>
                                            <div class="col-md-1 text-end">
                                                <form method="POST" action="${appURL}/cart" class="d-flex justify-content-center align-items-end">
                                                    <input type="hidden" name="puppyID" value="${entry.key.puppyID}">
                                                    <input type="hidden" name="action" value="delete">
                                                    <button type="submit" class="text-muted"><i class="bi bi-trash"></i></button>
                                                </form>
                                            </div>
                                        </div>

                                        <hr class="my-4">
                                    </c:forEach>


                                    <div class="pt-5">
                                        <h6 class="mb-0"><a href="${appURL}/view-puppies" class="text-body">
                                            <i class="bi bi-arrow-left"></i>Back to puppies</a></h6>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 bg-body-tertiary">
                                <div class="p-5">
                                    <h3 class="fw-bold mb-5 mt-2 pt-1">Summary</h3>
                                    <hr class="my-4">

                                    <div class="d-flex justify-content-between mb-4">
                                        <h5 class="text-uppercase">Subtotal</h5>
                                        <h5><fmt:formatNumber value="${cart.totalPrice}" type="currency"></fmt:formatNumber></h5>
                                    </div>

                                    <div class="d-flex justify-content-between mb-4">
                                        <h5 class="text-uppercase mb-3">Free Shipping</h5>
                                        <h5><fmt:formatNumber value="0" type="currency"></fmt:formatNumber></h5>
                                        <%--                                        <div class="mb-4 pb-2">--%>
                                        <%--                                            <select data-mdb-select-init>--%>
                                        <%--                                                <option value="1">Standard-Delivery- €5.00</option>--%>
                                        <%--                                                <option value="2">Two</option>--%>
                                        <%--                                                <option value="3">Three</option>--%>
                                        <%--                                                <option value="4">Four</option>--%>
                                        <%--                                            </select>--%>
                                        <%--                                        </div>--%>
                                    </div>

                                    <hr class="my-4">

                                    <div class="d-flex justify-content-between mb-5">
                                        <h5 class="text-uppercase">Total price</h5>
                                        <h5><fmt:formatNumber value="${cart.totalPrice}" type="currency"></fmt:formatNumber></h5>
                                    </div>

                                    <form action="${appURL}/checkout" method="GET">
                                        <button type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-dark btn-block btn-lg"
                                                data-mdb-ripple-color="dark">Adopt</button>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>