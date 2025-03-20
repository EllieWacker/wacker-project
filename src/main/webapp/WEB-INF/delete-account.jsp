<main>
    <%@include file="/WEB-INF/edit-profile-header.jspf"%>
    <section class="pt-0">
        <div class="container">
            <div class="row">
                <%@include file="/WEB-INF/left-sidebar.jspf"%>
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
                                    <input class="form-control <c:if test="${not empty errorMessage}">is-invalid</c:if>"
                                           type="text" id="email" name="email" value="${email}">
                                    <c:if test="${not empty errorMessage}">
                                        <div class="invalid-feedback">${errorMessage}</div>
                                    </c:if>
                                </div>

                                <button type="submit" class="btn btn-danger mb-0">Delete my account</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div><!-- Row END -->
        </div>
    </section>

    <c:if test="${showConfirmation}">
        <div class="modal fade show" id="confirmDeleteModal" tabindex="-1" aria-labelledby="confirmDeleteModalLabel" aria-modal="true" style="display: block;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="confirmDeleteModalLabel">Confirm Account Deletion</h1>
                        <a href="${appURL}/delete-account" class="btn-close" aria-label="Close"></a>
                    </div>
                    <div class="modal-body">
                        Are you sure you want to delete your account? This action cannot be undone.
                    </div>
                    <div class="modal-footer">
                        <a href="${appURL}/delete-account" class="btn btn-secondary">Cancel</a>

                        <form method="POST" action="${appURL}/delete-account" style="display: inline;">
                            <input type="hidden" name="email" value="${email}">
                            <input type="hidden" name="confirmed" value="true">
                            <button type="submit" class="btn btn-danger">Yes, Delete</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-backdrop fade show"></div>
    </c:if>
</main>
