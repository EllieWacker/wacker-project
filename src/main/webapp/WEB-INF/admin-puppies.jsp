<div class="container py-4">
  <div class="row">
    <!-- Main content START -->
    <div class="col-xl-12">
      <!-- Title -->
      <h1>All Puppies</h1>
      <p class="lead">
        <c:choose>
          <c:when test="${puppies.size() == 1}">There is 1 puppy</c:when>
          <c:otherwise>There are ${puppies.size()} puppies</c:otherwise>
        </c:choose>
      </p>
      <c:if test="${puppies.size() > 0}">
        <div class="table-responsive">
          <table class="table table-bordered">
            <thead>
            <tr>
              <th scope="col"></th>
              <th scope="col">Puppy ID</th>
              <th scope="col">Breed ID</th>
              <th scope="col">Litter ID</th>
              <th scope="col">Medical Record ID</th>
              <th scope="col">Image</th>
              <th scope="col">Gender</th>
              <th scope="col">Adopted</th>
              <th scope="col">Microchip</th>
              <th scope="col">Price</th>
              <th scope="col">Breed Description</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${puppies}" var="puppy">
              <tr>
                <td>
                  <a href="update-puppy?puppyID=${puppy.puppyID}" class="btn btn-sm btn-outline-primary">Edit</a>
                  <a href="delete-puppy?puppy_id=${puppy.puppyID}" class="btn btn-sm btn-outline-danger">Delete</a>
                </td>
                <td>${puppy.puppyID}</td>
                <td>${puppy.breedID}</td>
                <td>${puppy.litterID}</td>
                <td>${puppy.medicalRecordID}</td>
                <td>${puppy.image}</td>
                <td>${puppy.gender}</td>
                <td>${puppy.adopted ? 'Yes' : 'No'}</td>
                <td>${puppy.microchip ? 'Yes' : 'No'}</td>
                <td>${puppy.price}</td>
                <td>${puppy.breedDescription}</td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </c:if>
    </div> <!-- Col END -->
  </div> <!-- Row END -->
</div> <!-- Container END -->
