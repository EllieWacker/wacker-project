<div class="container py-4">
    <div class="row">
        <div class="col-xl-12">
            <h1>All Litters</h1>
            <p class="lead">
                <c:choose>
                    <c:when test="${litters.size() == 1}">There is 1 litter</c:when>
                    <c:otherwise>There are ${litters.size()} litters</c:otherwise>
                </c:choose>
            </p>
            <c:if test="${litters.size() > 0}">
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th scope="col"></th>
                            <th scope="col">Litter ID</th>
                            <th scope="col">Father Dog ID</th>
                            <th scope="col">Mother Dog ID</th>
                            <th scope="col">Image</th>
                            <th scope="col">Date Of Birth</th>
                            <th scope="col">Go Home Date</th>
                            <th scope="col">Number Of Puppies</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${litters}" var="litter">
                            <tr>
                                <td>
                                    <a href="update-litter?litterID=${litter.litterID}" class="btn btn-sm btn-outline-primary">Edit</a>
                                    <a href="delete-litter?litterID=${litter.litterID}" class="btn btn-sm btn-outline-danger">Delete</a>
                                </td>
                                <td>${litter.litterID}</td>
                                <td>${litter.fatherDogID}</td>
                                <td>${litter.motherDogID}</td>
                                <td>${litter.image}</td>
                                <td>${litter.dateOfBirth}</td>
                                <td>${litter.goHomeDate}</td>
                                <td>${litter.numberPuppies}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>
    </div>
</div>
