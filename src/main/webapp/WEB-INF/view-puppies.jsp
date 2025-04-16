<div class="container py-4">
    <div class="col d-flex justify-content-between align-items-center">
        <h2 class="mb-4">Check out our adorable puppies!</h2>
        <!-- Responsive toggler START -->
        <button class="btn btn-primary d-lg-none" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasSidebar" aria-controls="offcanvasSidebar">
            <i class="bi bi-list fs-4"></i>
        </button>
        <!-- Responsive toggler END -->
    </div>

    <div class="row">
        <div class="col-lg-9">
            <div class="col d-flex justify-content-between align-items-center">
                <p class="lead">Showings puppies ${firstPuppyShown}-${lastPuppyShown} of ${totalPuppies}</p>
                <%@include file="/WEB-INF/pagination.jspf"%>
            </div>
            <div class="row g-4">
                <c:forEach items="${puppies}" var="puppy">
                    <div class="col-sm-12 col-md-6 col-lg-4">
                        <sl-card class="card-overview">
                            <img
                                    slot="image"
                                    src="${pageContext.request.contextPath}${fn:replace(puppy.image, '^/', '')}"
                                    alt="Puppy Image."
                                    style="width: 13em; height: 200px; object-fit: cover;"
                            />
                            <strong>${puppy.puppyID}</strong><br />
                            <fmt:formatNumber value="${puppy.price}" type="currency"/><br />
                            <small>${puppy.gender}</small><br />
                            <small>${puppy.adopted ? 'Adopted' : 'Available'}</small><br />
                            <p class="badge rounded-pill text-bg-secondary"><a class="text-white" href="${appURL}/view-puppies?breedFilter=${puppy.breedID}">${puppy.breedID}</a></p><br />


                            <c:if test="${!puppy.adopted}">
                                <div slot="footer">
                                    <form method="POST" action="${appURL}/adopt" class="w-75">
                                        <input type="hidden" name="puppyID" value="${puppy.puppyID}">
                                        <div class="input-group">
                                            <button type="submit" class="btn btn-outline-primary">Adopt</button>
                                        </div>
                                    </form>
                                </div>
                            </c:if>
                        </sl-card>
                    </div>
                </c:forEach>
            </div> <!-- end puppy row-->
            <div class="col d-flex justify-content-end align-items-center mt-4">
                <%@include file="/WEB-INF/pagination.jspf"%>
            </div>
        </div> <!-- end 3/4-->
        <%@include file="puppy-sidebar.jspf"%>
    </div> <!-- end row -->
</div>
