<div class="container py-4">
    <h2>Update Puppy</h2>
    <c:choose>
        <c:when test="${empty puppy}">
            <p class="lead">No puppy found</p>
        </c:when>
        <c:otherwise>
            <c:if test="${not empty puppyUpdated}">
                <div class="alert ${puppyUpdated == true ? 'alert-success' : 'alert-danger'}" role="alert">
                        ${puppyUpdatedMessage}
                </div>
            </c:if>

            <form class="row g-3" method="POST" action="${appURL}/update-puppy">
                <div class="col-md-4">
                    <label for="puppyID" class="form-label">Puppy ID</label>
                    <input readonly type="text" class="form-control ${puppyIDError != null ? 'is-invalid' : 'is-valid'}" id="puppyID" value="${puppy.puppyID}">
                    <input type="hidden" name="puppyID" value="${puppy.puppyID}">
                    <div class="${puppyIDError != null ? 'invalid-feedback' : 'valid-feedback'}">
                            ${puppyIDMessage}
                    </div>
                </div>


                <div class="col-md-9">
                    <label for="breedID" class="form-label">Breed</label>
                    <select class="form-control ${breedIDError != null ? 'is-invalid' : ''}" id="breedID" name="breedID">
                        <option value="aussiedoodle" ${puppy.breedID == 'aussiedoodle' ? 'selected' : ''}>Aussiedoodle</option>
                        <option value="goldendoodle" ${puppy.breedID == 'goldendoodle' ? 'selected' : ''}>Goldendoodle</option>
                        <option value="cockapoo" ${puppy.breedID == 'cockapoo' ? 'selected' : ''}>Cockapoo</option>
                    </select>
                    <c:if test="${not empty breedIDError}">
                        <div class="invalid-feedback">${breedIDError}</div>
                    </c:if>
                </div>


                <div class="col-md-4">
                    <label for="litterID" class="form-label">Litter</label>
                    <input type="text" class="form-control ${litterIDError != null ? 'is-invalid' : ''}" id="litterID" name="litterID" value="${puppy.litterID}">
                    <c:if test="${not empty litterIDError}">
                        <div class="invalid-feedback">${litterIDError}</div>
                    </c:if>
                </div>

                <div class="col-md-8">
                    <label for="medicalRecordID" class="form-label">Medical Record ID</label>
                    <input type="text" class="form-control ${medicalRecordIDError != null ? 'is-invalid' : ''}" id="medicalRecordID" name="medicalRecordID" value="${puppy.medicalRecordID}">
                    <c:if test="${not empty medicalRecordIDError}">
                        <div class="invalid-feedback">${medicalRecordIDError}</div>
                    </c:if>
                </div>

                <div class="col-md-4">
                    <label for="image" class="form-label">Image</label>
                    <input type="text" class="form-control ${imageError != null ? 'is-invalid' : ''}" id="image" name="image" value="${puppy.image}">
                    <c:if test="${not empty imageError}">
                        <div class="invalid-feedback">${imageError}</div>
                    </c:if>
                </div>

                <div class="col-md-4">
                    <label for="gender" class="form-label">Gender</label>
                    <select class="form-control ${genderError != null ? 'is-invalid' : ''}" id="gender" name="gender">
                        <option value="male" ${puppy != null && 'male'.equalsIgnoreCase(puppy.gender) ? 'selected' : ''}>Male</option>
                        <option value="female" ${puppy != null && 'female'.equalsIgnoreCase(puppy.gender) ? 'selected' : ''}>Female</option>
                    </select>
                    <c:if test="${not empty genderError}">
                        <div class="invalid-feedback">${genderError}</div>
                    </c:if>
                </div>


                <div class="col-md-4">
                    <label for="adopted" class="form-label">Adopted</label>
                    <select class="form-control ${adoptedError != null ? 'is-invalid' : ''}" id="adopted" name="adopted">
                        <option value="true" ${puppy != null && puppy.adopted == true ? 'selected' : ''}>Yes</option>
                        <option value="false" ${puppy != null && puppy.adopted == false ? 'selected' : ''}>No</option>
                    </select>
                    <c:if test="${not empty adoptedError}">
                        <div class="invalid-feedback">${adoptedError}</div>
                    </c:if>
                </div>


                <div class="col-md-4">
                    <label for="microchip" class="form-label">Microchip</label>
                    <select class="form-control ${microchipError != null ? 'is-invalid' : ''}" id="microchip" name="microchip">
                        <option value="true" ${puppy != null && puppy.microchip == true ? 'selected' : ''}>Yes</option>
                        <option value="false" ${puppy != null && puppy.microchip == false ? 'selected' : ''}>No</option>
                    </select>
                    <c:if test="${not empty microchipError}">
                        <div class="invalid-feedback">${microchipError}</div>
                    </c:if>
                </div>



                <div class="col-md-4">
                    <label for="price" class="form-label">Price</label>
                    <input type="number" step="0.01" value="${puppy.price != null ? puppy.price : 100}" class="form-control ${priceError != null ? 'is-invalid' : ''}" id="price" name="price">
                    <c:if test="${not empty priceError}">
                        <div class="invalid-feedback">${priceError}</div>
                    </c:if>
                </div>

                <div class="col-md-4">
                    <label for="breedDescription" class="form-label">Breed Description</label>
                    <input type="text" class="form-control ${breedDescriptionError != null ? 'is-invalid' : ''}" id="breedDescription" name="breedDescription" value="${puppy.breedDescription}">
                    <c:if test="${not empty breedDescriptionError}">
                        <div class="invalid-feedback">${breedDescriptionError}</div>
                    </c:if>
                </div>

                <div class="col-12">
                    <button class="btn btn-dark" type="submit">Submit form</button>
                </div>
            </form>
        </c:otherwise>
    </c:choose>
</div>
