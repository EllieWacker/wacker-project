
<div class="col-xl-9">
    <div class="card border bg-transparent rounded-3 mb-0">
        <div class="card-header bg-transparent border-bottom">
            <h3 class="card-header-title mb-0">Delete Litter</h3>
        </div>

        <div class="card-body">
            <h6>If you delete this litter, all it's data will be lost.</h6>

            <!-- Form -->
            <form id="deleteLitterForm" method="POST" action="${appURL}/delete-litter">
                <!-- Email input -->
                <div class="col-md-6 my-4">
                    <label class="form-label" for="litterID">Enter the litter id to confirm deletion</label>
                    <input class="form-control <c:if test="${not empty results.litterIDError}">is-invalid</c:if>"
                           type="text" id="litterID" name="litterID" value="${litterID}">
                    <c:if test="${not empty results.litterIDError}">
                        <div class="invalid-feedback">${results.litterIDError}</div>
                    </c:if>
                </div>

                <!-- Trigger Modal -->
                <button type="button" class="btn btn-danger mb-0" data-bs-toggle="modal" data-bs-target="#confirmDeleteModal">
                    Delete Litter
                </button>
            </form>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="confirmDeleteModal" tabindex="-1" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="confirmDeleteModalLabel">Confirm Account Deletion</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Are you sure you want to delete this litter? This action cannot be undone.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                <button type="submit" form="deleteLitterForm" class="btn btn-danger">Yes, Delete</button>
            </div>
        </div>
    </div>
</div>