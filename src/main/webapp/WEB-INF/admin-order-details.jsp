<div class="container py-4">
    <a href="${appURL}/orders" class="btn btn-primary mb-4">View all orders</a>
    <div class="row">
        <div class="col-sm-12 col-md-10 col-lg-8">
            <div class="card">
                <div class="card-body mx-4">
                    <div class="container">
                        <div class="row">
                            <ul class="list-unstyled">
                                <li class="text-black">${fn:escapeXml(order.shipFirstName)}&nbsp;${fn:escapeXml(order.shipLastName)} </li>
                                <li class="text-muted mt-1"><span class="text-black">Order</span> #${order.orderID}</li>
                                <li class="text-black mt-1"><fmt:formatDate value="${order.orderDateDate}" type="date" dateStyle="long" /></li>
                            </ul>
                        </div>
                        <div class="row">
                            <div class="col-1"></div>
                            <div class="col-5"><p>Product Name</p></div>
                            <div class="col-2"><p class="float-end">Price</p></div>
                            <div class="col-2"><p class="float-end">Total</p></div>
                            <hr>
                        </div>
                        <c:forEach items="${order.items}" var="item" varStatus="status">
                            <div class="row">
                                <div class="col-1">${status.count}</div>
                                <div class="col-5">
                                    <p>${item.productName}</p>
                                </div>
                                <div class="col-2">
                                    <p class="float-end"><fmt:formatNumber value="${item.price}" type="currency" />
                                    </p>
                                </div>
                                <div class="col-2">
                                    <p class="float-end"><fmt:formatNumber value="${item.totalPrice}" type="currency" />
                                    </p>
                                </div>
                                <hr <c:if test="${status.last}">style="border: 2px solid black;"</c:if>>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="row text-black">

                        <div class="col-12">
                            <p class="float-end fw-bold">Total: <fmt:formatNumber value="${order.totalPrice}" type="currency" />
                            </p>
                        </div>
                        <hr style="border: 2px solid black;">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>