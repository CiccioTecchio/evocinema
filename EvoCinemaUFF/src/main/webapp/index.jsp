<% request.setAttribute("title", "Home"); %>
<jsp:include page="Header.jsp" />



        <header>
            
            <div class="swiper-container">
                <div class="swiper-wrapper">
                    <div class="swiper-slide" ><img src="images/locandine/benedettafollia.jpg" alt=""/></div>
                    <div class="swiper-slide" ><img src="images/locandine/benedettafollia.jpg" alt=""/></div>
                    <div class="swiper-slide" ><img src="images/locandine/benedettafollia.jpg" alt=""/></div>
                    <div class="swiper-slide" ><img src="images/locandine/benedettafollia.jpg" alt=""/></div>
                    <div class="swiper-slide" ><img src="images/locandine/benedettafollia.jpg" alt=""/></div>
                    <div class="swiper-slide" ><img src="images/locandine/benedettafollia.jpg" alt=""/></div>
                </div>       
            </div>
        </header>
        <div id="date">
            <div align="center">
                <strong><label for="from" >Dal</label></strong>
                <input type="text" id="from" name="from" style="width:100px">
                <img class="calendar-icon" src="images/calendar.png" height="26" width="26">
                <strong><label for="to">al</label>
                <input type="text" id="to" name="to"style="width:100px">
                <img class="calendar-icon" src="images/calendar.png" height="26" width="26">
               
            </div>
        </div>
        
        
<jsp:include page="Footer.jsp" />
       

