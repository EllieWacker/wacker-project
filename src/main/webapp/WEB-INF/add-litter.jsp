
<div class="container py-4">
    <a href="litters" class="btn btn-primary" role="button">View All Puppies</a>
    <h2>Add New Litter</h2>
    <c:if test="${not empty litterAdded}">
        <div class="alert <c:choose><c:when test="${litterAdded}">alert-success</c:when><c:otherwise>alert-danger</c:otherwise></c:choose>" role="alert">
                ${litterAddedMessage}
        </div>
    </c:if>
    <form class="row g-3" method="POST" action="${appURL}/add-litter">
        <div class="col-md-3">
            <label for="litterID" class="form-label">Litter ID</label>
            <input type="text" class="form-control <c:if test="${not empty litterIDError}">is-invalid</c:if>" id="litterID" name="litterID" value="${litterID}">
            <c:if test="${not empty litterIDError}"><div class="invalid-feedback">${litterIDError}</div></c:if>
        </div>

        <!-- Mother Dog Dropdown -->
        <div class="col-md-9">
            <label for="motherDogID" class="form-label">Mother Dog Name</label>
            <select class="form-control ${motherDogIDError != null ? 'is-invalid' : ''}" id="motherDogID" name="motherDogID">
                <c:forEach var="motherDog" items="${motherDogs}">
                    <option value="${motherDog.motherDogID}" ${motherDogID == motherDog.motherDogID ? 'selected' : ''}>${motherDog.motherDogID}</option>
                </c:forEach>
            </select>
            <c:if test="${not empty motherDogIDError}"><div class="invalid-feedback">${motherDogIDError}</div></c:if>
        </div>

        <!-- Father Dog Dropdown -->
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
            <input type="text" class="form-control <c:if test="${not empty imageError}">is-invalid</c:if>" id="image" name="image" value="${image}">
            <c:if test="${not empty imageError}"><div class="invalid-feedback">${imageError}</div></c:if>
        </div>

        <div class="col-md-4">
            <label for="dateOfBirth" class="form-label">Date Of Birth</label>
            <input type="date" class="form-control <c:if test="${not empty dateOfBirthError}">is-invalid</c:if>" id="dateOfBirth" name="dateOfBirth" value="${dateOfBirth}">
            <c:if test="${not empty dateOfBirthError}"><div class="invalid-feedback">${dateOfBirthError}</div></c:if>
        </div>


        <div class="col-md-4">
            <label for="goHomeDate" class="form-label">Go Home Date</label>
            <input type="date" class="form-control <c:if test="${not empty goHomeDateError}">is-invalid</c:if>" id="goHomeDate" name="goHomeDate" value="${goHomeDate}">
            <c:if test="${not empty goHomeDateError}"><div class="invalid-feedback">${goHomeDateError}</div></c:if>
        </div>


        <div class="col-md-4">
            <label for="numberPuppies" class="form-label">Number of Puppies</label>
            <input type="number" class="form-control <c:if test="${not empty numberPuppiesError}">is-invalid</c:if>" id="numberPuppies" name="numberPuppies" value="${not empty numberPuppies ? numberPuppies : 0}">
            <c:if test="${not empty numberPuppiesError}"><div class="invalid-feedback">${numberPuppiesError}</div></c:if>
        </div>
        
        <div class="col-12">
            <button class="btn btn-dark" type="submit">Submit form</button>
        </div>
    </form>
</div>
