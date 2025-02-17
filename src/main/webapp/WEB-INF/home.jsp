<main>
    <div class="container marketing">
        <div class="container text-center my-5 p-5 rounded" style="background-color: #707070;">
            <h1 class="display-4 fw-bold" style="color: white">Welcome to Doodle Dogs!</h1>
        </div>

        <div class="row">
            <div class="col-lg-4"><!--I found the fn online to make an extra / go away -->
                <img src="${pageContext.request.contextPath}${fn:replace(puppy1.image, '^/', '')}" alt="Dog Image" class="rounded-circle" width="140" height="140" style="object-fit: cover;">
                <h2 class="fw-normal">${puppy1.breedID} Puppies</h2>
                <p>${puppy1.breedDescription}</p>
                <p><a class="btn btn-secondary" href="#">View details &raquo;</a></p>
            </div><!-- /.col-lg-4 -->
            <div class="col-lg-4">
                <img src="${pageContext.request.contextPath}${fn:replace(puppy2.image, '^/', '')}" alt="Dog Image" class="rounded-circle" width="140" height="140" style="object-fit: cover;">
                <h2 class="fw-normal">${puppy2.breedID} Puppies</h2>
                <p>${puppy2.breedDescription}</p>
                <p><a class="btn btn-secondary" href="#">View details &raquo;</a></p>
            </div><!-- /.col-lg-4 -->
            <div class="col-lg-4">
                <img src="${pageContext.request.contextPath}${fn:replace(puppy3.image, '^/', '')}" alt="Dog Image" style="object-fit: cover;" class="rounded-circle" width="140" height="140">
                <h2 class="fw-normal">${puppy3.breedID} Puppies</h2>
                <p>${puppy3.breedDescription}</p>
                <p><a class="btn btn-secondary" href="#">View details &raquo;</a></p>
            </div><!-- /.col-lg-4 -->
        </div><!-- /.row -->


        <hr class="featurette-divider">

        <div class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading fw-normal lh-1">${meyerTestimonial.testimonialId} <span class="text-body-secondary"> </span></h2>
                <p class="lead">${meyerTestimonial.details}</p>
            </div>
            <div class="col-md-5 d-flex justify-content-center align-items-center">
                <img src="${pageContext.request.contextPath}${fn:replace(meyerTestimonial.image, '^/', '')}" alt="Your Image" class="testimonial-img img-fluid" width="300" height="300">
            </div>

        </div>

        <hr class="featurette-divider">

        <div class="row featurette">
            <div class="col-md-7 order-md-2">
                <h2 class="featurette-heading fw-normal lh-1">${sannerTestimonial.testimonialId} <span class="text-body-secondary"></span></h2>
                <p class="lead">${sannerTestimonial.details}</p>
            </div>
            <div class="col-md-5 order-md-1">
                <img src="${pageContext.request.contextPath}${fn:replace(sannerTestimonial.image, '^/', '')}" alt="Your Image" class="testimonial-img img-fluid" width="300" height="300">
            </div>
        </div>

        <hr class="featurette-divider">

        <div class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading fw-normal lh-1">${wackerTestimonial.testimonialId} <span class="text-body-secondary"></span></h2>
                <p class="lead">${wackerTestimonial.details}</p>
            </div>
            <div class="col-md-5">
                <img src="${pageContext.request.contextPath}${fn:replace(wackerTestimonial.image, '^/', '')}" alt="Your Image" class="testimonial-img img-fluid" width="300" height="300">
            </div>
        </div>
        <hr class="featurette-divider">
    </div><!-- /.container -->
</main>