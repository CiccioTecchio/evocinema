<jsp:include page="Header.jsp"/>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="#">Evo Cinema</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Login
                        <span class="sr-only">(current)</span>
                    </a>
                </li>


            </ul>
        </div>
    </div>
</nav>

<header>

    <div class="swiper-container">
        <div class="swiper-wrapper">
            <div class="swiper-slide" style="background-image:url(images/image1.jpg)"></div>
            <div class="swiper-slide" style="background-image:url(images/image1.jpg)"></div>
            <div class="swiper-slide" style="background-image:url(images/image1.jpg)"></div>
            <div class="swiper-slide" style="background-image:url(images/image1.jpg)"></div>
            <div class="swiper-slide" style="background-image:url(images/image1.jpg)"></div>
        </div>       
    </div>
</header>
<div id="date">
    <div align="center">
        <strong><label for="from" >Dal</label></strong>
        <input type="text" id="from" name="from" style="width:100px">
        <strong><label for="to">al</label>
            <input type="text" id="to" name="to"style="width:100px">

            </div>
            </div>
           <jsp:include page="Footer.jsp"/>


