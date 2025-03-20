<div class="container py-4">
    <h2>Puppies</h2>
    <div class="row g-4">
        <c:forEach items="${puppies}" var="puppy">
            <div class="col-sm-12 col-md-6 col-lg-4 col-xl-3">
                <sl-card class="card-overview">
                    <img
                            slot="image"
                            src="${pageContext.request.contextPath}${fn:replace(puppy.image, '^/', '')}"
                            alt="Puppy Image."
                            style="width: 13em; height: 200px; object-fit: cover;"
                    />

                    <strong>${puppy.puppyID}</strong><br />
                    $${puppy.price}<br />
                    <small>${puppy.gender}</small><br />
                    <small>${puppy.adopted ? 'Adopted' : 'Available'}</small>

                    <c:if test="${!puppy.adopted}">
                        <div slot="footer">
                            <sl-button variant="primary" pill>Adopt</sl-button>
                        </div>
                    </c:if>
                </sl-card>
            </div>
        </c:forEach>
    </div>
</div>
