
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
                <option value="Mini Aussiedoodle" <c:if test="${empty breedID or breedID == 'Mini Aussiedoodle'}">selected</c:if>>Mini Aussiedoodle</option>
                <option value="Mini Goldendoodle" <c:if test="${breedID == 'Mini Goldendoodle'}">selected</c:if>>Mini Goldendoodle</option>
                <option value="Cockapoo" <c:if test="${breedID == 'Cockapoo'}">selected</c:if>>Cockapoo</option>
            </select>
            <c:if test="${not empty breedIDError}">
                <div class="invalid-feedback">${breedIDError}</div>
            </c:if>
        </div>


        <div class="col-md-4">
            <label for="litterID" class="form-label">Litter</label>
            <select class="form-control ${litterIDError != null ? 'is-invalid' : ''}" id="litterID" name="litterID">
                <c:forEach var="litter" items="${litters}">
                    <option value="${litter.litterID}" ${litterID == litter.litterID ? 'selected' : ''}>${litter.litterID}</option>
                </c:forEach>
            </select>
            <c:if test="${not empty litterIDError}">
                <div class="invalid-feedback">${litterIDError}</div>
            </c:if>
        </div>

        <div class="col-md-4">
            <label for="medicalRecordID" class="form-label">Medical Record ID</label>
            <select class="form-control <c:if test="${not empty medicalRecordIDError}">is-invalid</c:if>" id="medicalRecordID" name="medicalRecordID">
                <option value="AmyMeyer1" <c:if test="${empty medicalRecordID or medicalRecordID == 'AmyMeyer1'}">selected</c:if>>Deworming</option>
                <option value="CarterSmith1" <c:if test="${medicalRecordID == 'CarterSmith1'}">selected</c:if>>Essential Vaccinations and Deworming</option>
                <option value="LukeWacker1" <c:if test="${medicalRecordID == 'LukeWacker1'}">selected</c:if>>All Vaccinations and Deworming</option>
            </select>
            <c:if test="${not empty medicalRecordIDError}">
                <div class="invalid-feedback">${medicalRecordIDError}</div>
            </c:if>
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
            <input type="number" step="0.01"
                   class="form-control ${not empty priceError ? 'is-invalid' : ''}"
                   id="price" name="price" value="${price}">
            <c:if test="${not empty priceError}">
                <div class="invalid-feedback">${priceError}</div>
            </c:if>
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
