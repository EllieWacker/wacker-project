<div class="container py-4">
    <h2>Update Litter</h2>
    <c:choose>
        <c:when test="${empty litter}">
            <p class="lead">No litter found</p>
        </c:when>
        <c:otherwise>
            <c:if test="${not empty litterUpdated}">
                <div class="alert ${litterUpdated == true ? 'alert-success' : 'alert-danger'}" role="alert">
                        ${litterUpdatedMessage}
                </div>
            </c:if>

            <form class="row g-3" method="POST" action="${appURL}/update-litter">
                <div class="col-md-4">
                    <label for="litterID" class="form-label">Litter ID</label>
                    <input readonly type="text" class="form-control ${litterIDError != null ? 'is-invalid' : 'is-valid'}" id="litterID" value="${litter.litterID}">
                    <input type="hidden" name="litterID" value="${litter.litterID}">
                    <div class="${litterIDError != null ? 'invalid-feedback' : 'valid-feedback'}">
                            ${litterIDMessage}
                    </div>
                </div>


                <div class="col-md-9">
                    <label for="motherDogID" class="form-label">Mother Dog Name</label>
                    <select class="form-control ${motherDogIDError != null ? 'is-invalid' : ''}" id="motherDogID" name="motherDogID">
                        <c:forEach var="motherDog" items="${motherDogs}">
                            <option value="${motherDog.motherDogID}" ${motherDogID == motherDog.motherDogID ? 'selected' : ''}>${motherDog.motherDogID}</option>
                        </c:forEach>
                    </select>
                    <c:if test="${not empty motherDogIDError}"><div class="invalid-feedback">${motherDogIDError}</div></c:if>
                </div>


                <div class="col-md-8">
                    <label for="fatherDogID" class="form-label">Father Dog Name</label>
                    <select class="form-control ${fatherDogIDError != null ? 'is-invalid' : ''}" id="fatherDogID" name="fatherDogID">
                        <c:forEach var="fatherDog" items="${fatherDogs}">
                            <option value="${fatherDog.fatherDogID}" ${fatherDogID == fatherDog.fatherDogID ? 'selected' : ''}>${fatherDog.fatherDogID}</option>
                        </c:forEach>
                    </select>
                    <c:if test="${not empty fatherDogIDError}">
                        <div class="invalid-feedback">${fatherDogIDError}</div>
                    </c:if>
                </div>

                <div class="col-md-4">
                    <label for="image" class="form-label">Image</label>
                    <input type="text" class="form-control ${imageError != null ? 'is-invalid' : ''}" id="image" name="image" value="${litter.image}">
                    <c:if test="${not empty imageError}">
                        <div class="invalid-feedback">${imageError}</div>
                    </c:if>
                </div>

                <div class="col-md-4">
                    <label for="dateOfBirth" class="form-label">Date of Birth</label>
                    <input type="date" class="form-control ${dateOfBirthError != null ? 'is-invalid' : ''}" id="dateOfBirth" name="dateOfBirth" value="${litter.dateOfBirth}">
                    <c:if test="${not empty dateOfBirthError}">
                        <div class="invalid-feedback">${dateOfBirthError}</div>
                    </c:if>
                </div>

                <div class="col-md-4">
                    <label for="goHomeDate" class="form-label">Go Home Date</label>
                    <input type="date" class="form-control ${goHomeDateError != null ? 'is-invalid' : ''}" id="goHomeDate" name="goHomeDate" value="${litter.goHomeDate}">
                    <c:if test="${not empty goHomeDateError}">
                        <div class="invalid-feedback">${goHomeDateError}</div>
                    </c:if>
                </div>

                <div class="col-md-4">
                    <label for="numberPuppies" class="form-label">Number of Puppies</label>
                    <input type="number" class="form-control ${numberPuppiesError != null ? 'is-invalid' : ''}"
                           id="numberPuppies" name="numberPuppies"
                           value="${not empty litter.numberPuppies ? litter.numberPuppies : 0}"
                           min="0">
                    <c:if test="${not empty numberPuppiesError}">
                        <div class="invalid-feedback">${numberPuppiesError}</div>
                    </c:if>
                </div>

                <div class="col-12">
                    <button class="btn btn-dark" type="submit">Submit form</button>
                    <a href="${appURL}/litters" class="btn btn-dark">Return to Litters</a>
                </div>
            </form>
        </c:otherwise>
    </c:choose>
</div>
