<main>
    <jsp:include page="/WEB-INF/edit-profile-header.jspf"></jsp:include>

    <section class="pt-0">
        <div class="container">
            <div class="row">

                <jsp:include page="/WEB-INF/left-sidebar.jspf"></jsp:include>

                <!-- Main content START -->
                <div class="col-xl-9">
                    <div class="card border bg-transparent rounded-3 mb-0">
                        <div class="card-header bg-transparent border-bottom">
                            <h3 class="card-header-title mb-0">Delete Account</h3>
                        </div>

                        <div class="card-body">
                            <h6>If you delete your account, you will lose all your data.</h6>

                            <!-- Form -->
                            <form id="deleteAccountForm" method="POST" action="${appURL}/delete-account">
                                <!-- Email input -->
                                <div class="col-md-6 my-4">
                                    <label class="form-label" for="email">Enter your email to confirm account deletion</label>
                                    <input class="form-control <c:if test="${not empty results.emailError}">is-invalid</c:if>"
                                           type="text" id="email" name="email" value="${email}">
                                    <c:if test="${not empty results.emailError}">
                                        <div class="invalid-feedback">${results.emailError}</div>
                                    </c:if>
                                </div>

                                <!-- Trigger Modal -->
                                <button type="button" class="btn btn-danger mb-0" data-bs-toggle="modal" data-bs-target="#confirmDeleteModal">
                                    Delete my account
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div><!-- Row END -->
        </div>
    </section>

    <!-- Modal -->
    <div class="modal fade" id="confirmDeleteModal" tabindex="-1" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="confirmDeleteModalLabel">Confirm Account Deletion</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    Are you sure you want to delete your account? This action cannot be undone.
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <button type="submit" form="deleteAccountForm" class="btn btn-danger">Yes, Delete</button>
                </div>
            </div>
        </div>
    </div>
</main>
