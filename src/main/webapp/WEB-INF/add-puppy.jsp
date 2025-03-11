
<div class="container py-4">
    <a href="puppies" class="btn btn-primary" role="button">View All Puppies</a>
    <h2>Add New Puppy</h2>
    <c:if test="${not empty puppyAdded}">
        <div class="alert <c:choose><c:when test="${puppyAdded}">alert-success</c:when><c:otherwise>alert-danger</c:otherwise></c:choose>" role="alert">
                ${puppyAddedMessage}
        </div>
    </c:if>
    <form class="row g-3" method="POST" action="${appURL}/add-puppy">
        <div class="col-md-3">
            <label for="puppyID" class="form-label">Puppy Id</label>
            <input type="text" class="form-control <c:if test="${not empty puppyIDError}">is-invalid</c:if>" id="puppyID" name="puppyID" value="${puppyID}">
            <c:if test="${not empty puppyIDError}"><div class="invalid-feedback">${puppyIDError}</div></c:if>

        </div>

        <div class="col-md-9">
            <label for="breedID" class="form-label">Breed</label>
            <select class="form-control <c:if test="${not empty breedIDError}">is-invalid</c:if>" id="breedID" name="breedID">
            <option value="aussiedoodle" <c:if test="${empty breedID or breedID == 'aussiedoodle'}">selected</c:if>>Aussiedoodle</option>
            <option value="goldendoodle" <c:if test="${breedID == 'goldendoodle'}">selected</c:if>>Goldendoodle</option>
            <option value="cockapoo" <c:if test="${breedID == 'cockapoo'}">selected</c:if>>Cockapoo</option>
            </select>
            <c:if test="${not empty breedIDError}">
                <div class="invalid-feedback">${breedIDError}</div>
            </c:if>
        </div>

        <div class="col-md-4">
            <label for="litterID" class="form-label">Litter</label>
            <input type="text" class="form-control <c:if test="${not empty litterIDError}">is-invalid</c:if>" id="litterID" name="litterID" value="${litterID}">
            <c:if test="${not empty litterIDError}"><div class="invalid-feedback">${litterIDError}</div></c:if>
        </div>

        <div class="col-md-8">
            <label for="medicalRecordID" class="form-label">Medical Record ID</label>
            <input type="text" class="form-control <c:if test="${not empty medicalRecordIDError}">is-invalid</c:if>" id="medicalRecordID" name="medicalRecordID" value="${medicalRecordID}">
            <c:if test="${not empty medicalRecordIDError}"><div class="invalid-feedback">${medicalRecordIDError}</div></c:if>

        </div>
        <div class="col-md-4">
            <label for="image" class="form-label">Image</label>
            <input type="text" class="form-control <c:if test="${not empty imageError}">is-invalid</c:if>" id="image" name="image" value="${image}">
            <c:if test="${not empty imageError}"><div class="invalid-feedback">${imageError}</div></c:if>
        </div>

        <div class="col-md-4">
            <label for="gender" class="form-label">Gender</label>
            <select class="form-control <c:if test="${not empty genderError}">is-invalid</c:if>" id="gender" name="gender">
            <option value="male" <c:if test="${empty gender or gender == 'male'}">selected</c:if>>Male</option>
            <option value="female" <c:if test="${gender == 'female'}">selected</c:if>>Female</option>
            </select>
            <c:if test="${not empty genderError}">
                <div class="invalid-feedback">${genderError}</div>
            </c:if>
        </div>


        <div class="col-md-4">
            <label for="adopted" class="form-label">Adopted</label>
            <select class="form-control <c:if test="${not empty adoptedError}">is-invalid</c:if>" id="adopted" name="adopted">
            <option value="yes" <c:if test="${empty adopted or adopted == 'yes'}">selected</c:if>>Yes</option>
            <option value="no" <c:if test="${adopted == 'no'}">selected</c:if>>No</option>
            </select>
            <c:if test="${not empty adoptedError}">
                <div class="invalid-feedback">${adoptedError}</div>
            </c:if>
        </div>


        <div class="col-md-4">
            <label for="microchip" class="form-label">Microchip</label>
            <select class="form-control <c:if test="${not empty microchipError}">is-invalid</c:if>" id="microchip" name="microchip">
            <option value="yes" <c:if test="${empty microchip or microchip == 'yes'}">selected</c:if>>Yes</option>
            <option value="no" <c:if test="${microchip == 'no'}">selected</c:if>>No</option>
            </select>
            <c:if test="${not empty microchipError}">
                <div class="invalid-feedback">${microchipError}</div>
            </c:if>
        </div>



        <div class="col-md-4">
            <label for="price" class="form-label">Price</label>
            <input type="number" step="0.01" content="2.32" value="${not empty price ? price : '100'}" class="form-control <c:if test="${not empty priceError}">is-invalid</c:if>" id="price" name="price" value="${price}">
            <c:if test="${not empty priceError}"><div class="invalid-feedback">${priceError}</div></c:if>
        </div>

        <div class="col-md-4">
            <label for="breedDescription" class="form-label">Breed Description</label>
            <input type="text" class="form-control <c:if test="${not empty breedDescriptionError}">is-invalid</c:if>" id="breedDescription" name="breedDescription" value="${breedDescription}">
            <c:if test="${not empty breedDescriptionError}"><div class="invalid-feedback">${breedDescriptionError}</div></c:if>
        </div>
        <div class="col-12">
            <button class="btn btn-dark" type="submit">Submit form</button>
        </div>
    </form>
</div>
