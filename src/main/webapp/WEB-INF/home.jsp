<main>

<%--    <div id="myCarousel" class="carousel slide mb-6" data-bs-ride="carousel">--%>
<%--        <div class="carousel-indicators">--%>
<%--            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>--%>
<%--            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>--%>
<%--            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2" aria-label="Slide 3"></button>--%>
<%--        </div>--%>
<%--        <div class="carousel-inner">--%>
<%--            <div class="carousel-item active">--%>
<%--                <svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg" aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false"><rect width="100%" height="100%" fill="var(--bs-secondary-color)"/></svg>--%>
<%--                <div class="container">--%>
<%--                    <div class="carousel-caption text-start">--%>
<%--                        <h1>Example headline.</h1>--%>
<%--                        <p class="opacity-75">Some representative placeholder content for the first slide of the carousel.</p>--%>
<%--                        <p><a class="btn btn-lg btn-primary" href="#">Sign up today</a></p>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="carousel-item">--%>
<%--                <svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg" aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false"><rect width="100%" height="100%" fill="var(--bs-secondary-color)"/></svg>--%>
<%--                <div class="container">--%>
<%--                    <div class="carousel-caption">--%>
<%--                        <h1>Another example headline.</h1>--%>
<%--                        <p>Some representative placeholder content for the second slide of the carousel.</p>--%>
<%--                        <p><a class="btn btn-lg btn-primary" href="#">Learn more</a></p>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="carousel-item">--%>
<%--                <svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg" aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false"><rect width="100%" height="100%" fill="var(--bs-secondary-color)"/></svg>--%>
<%--                <div class="container">--%>
<%--                    <div class="carousel-caption text-end">--%>
<%--                        <h1>One more for good measure.</h1>--%>
<%--                        <p>Some representative placeholder content for the third slide of this carousel.</p>--%>
<%--                        <p><a class="btn btn-lg btn-primary" href="#">Browse gallery</a></p>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">--%>
<%--            <span class="carousel-control-prev-icon" aria-hidden="true"></span>--%>
<%--            <span class="visually-hidden">Previous</span>--%>
<%--        </button>--%>
<%--        <button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">--%>
<%--            <span class="carousel-control-next-icon" aria-hidden="true"></span>--%>
<%--            <span class="visually-hidden">Next</span>--%>
<%--        </button>--%>
<%--    </div>--%>


    <!-- Marketing messaging and featurettes
    ================================================== -->
    <!-- Wrap the rest of the page in another container to center all the content. -->


    <div class="container marketing">
        <div class="container text-center my-5 p-5 rounded" style="background-color: #707070;">
            <h1 class="display-4 fw-bold" style="color: white">Welcome to Doodle Dogs!</h1>
        </div>

        <!-- Three columns of text below the carousel -->
        <div class="row">
            <div class="col-lg-4">
                <img src="${pageContext.request.contextPath}/${puppy1.image}" alt="Dog Image" class="rounded-circle" width="140" height="140" style="object-fit: cover;">
                <h2 class="fw-normal">Aussiedoodle Puppies</h2>
                <p>We are known for our adorable Mini Aussies! Click the button below to learn more.</p>
                <p><a class="btn btn-secondary" href="#">View details &raquo;</a></p>
            </div><!-- /.col-lg-4 -->
            <div class="col-lg-4">
                <img src="${pageContext.request.contextPath}/${puppy2.image}" alt="Dog Image" class="rounded-circle" width="140" height="140" style="object-fit: cover;">
                <h2 class="fw-normal">Cockapoo Puppiess</h2>
                <p>Our Cockapoo puppies are absolutely precious! Click the button below to learn more.</p>
                <p><a class="btn btn-secondary" href="#">View details &raquo;</a></p>
            </div><!-- /.col-lg-4 -->
            <div class="col-lg-4">
                <img src="${pageContext.request.contextPath}/${puppy3.image}" alt="Dog Image" style="object-fit: cover;" class="rounded-circle" width="140" height="140">
                <h2 class="fw-normal">Goldendoodle Puppies</h2>
                <p>These Mini Goldendoodles are as sweet as they come! Click the button below to learn more.</p>
                <p><a class="btn btn-secondary" href="#">View details &raquo;</a></p>
            </div><!-- /.col-lg-4 -->
        </div><!-- /.row -->


        <!-- START THE FEATURETTES -->

        <hr class="featurette-divider">

        <div class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading fw-normal lh-1">${meyerTestimonial.testimonialId} <span class="text-body-secondary"> </span></h2>
                <p class="lead">${meyerTestimonial.details}</p>
            </div>
            <div class="col-md-5 d-flex justify-content-center align-items-center">
                <img src="${pageContext.request.contextPath}/${meyerTestimonial.image}" alt="Your Image" class="testimonial-img img-fluid" width="300" height="300">
            </div>

        </div>

        <hr class="featurette-divider">

        <div class="row featurette">
            <div class="col-md-7 order-md-2">
                <h2 class="featurette-heading fw-normal lh-1">${sannerTestimonial.testimonialId} <span class="text-body-secondary"></span></h2>
                <p class="lead">${sannerTestimonial.details}</p>
            </div>
            <div class="col-md-5 order-md-1">
                <img src="${pageContext.request.contextPath}/${sannerTestimonial.image}" alt="Your Image" class="testimonial-img img-fluid" width="300" height="300">
            </div>
        </div>

        <hr class="featurette-divider">

        <div class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading fw-normal lh-1">${wackerTestimonial.testimonialId} <span class="text-body-secondary"></span></h2>
                <p class="lead">${wackerTestimonial.details}</p>
            </div>
            <div class="col-md-5">
                <img src="${pageContext.request.contextPath}/${wackerTestimonial.image}" alt="Your Image" class="testimonial-img img-fluid" width="300" height="300">
            </div>
        </div>
        <hr class="featurette-divider">

        <!-- /END THE FEATURETTES -->

    </div><!-- /.container -->
</main>