
<div class="col-xl-9">
    <div class="card border bg-transparent rounded-3 mb-0">
        <div class="card-header bg-transparent border-bottom">
            <h3 class="card-header-title mb-0">Delete Puppy</h3>
        </div>

        <div class="card-body">
            <h6>If you delete this puppy, all it's data will be lost.</h6>

            <!-- Form -->
            <form id="deletePuppyForm" method="POST" action="${appURL}/delete-puppy">
                <!-- Email input -->
                <div class="col-md-6 my-4">
                    <label class="form-label" for="puppyID">Enter the puppy id to confirm deletion</label>
                    <input class="form-control <c:if test="${not empty results.puppyIDError}">is-invalid</c:if>"
                           type="text" id="puppyID" name="puppyID" value="${puppyID}">
                    <c:if test="${not empty results.puppyIDError}">
                        <div class="invalid-feedback">${results.puppyIDError}</div>
                    </c:if>
                </div>

                <!-- Trigger Modal -->
                <button type="button" class="btn btn-danger mb-0" data-bs-toggle="modal" data-bs-target="#confirmDeleteModal">
                    Delete Puppy
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
                    Are you sure you want to delete this puppy? This action cannot be undone.
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <button type="submit" form="deletePuppyForm" class="btn btn-danger">Yes, Delete</button>
                </div>
            </div>
        </div>
    </div>
</main>
