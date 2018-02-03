
</div>
</div>
<footer class="sticky-footer">
    <div class="container">
        <div class="text-center">
            <small>Copyright &copy; Evo Cinema 2017</small>
        </div>
    </div>
</footer>
<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fa fa-angle-up"></i>
</a>


<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Sicuro di voler eseguire il Logout?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Seleziona "Logout" se vuoi abbandonare la sessione corrente.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Annulla</button>
                <a class="btn btn-primary" href="../Logout">Logout</a>
            </div>
        </div>
    </div>

    
    <script src="../Template%20Sb%20Admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="../Template%20Sb%20Admin/vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="../Template%20Sb%20Admin/js/sb-admin.min.js"></script>
    <script src="../javascript/login.js" type="text/javascript"></script>
    <script src="../javascript/registrazione.js" type="text/javascript"></script>
    <script src="../javascript/EliminaAccount.js" type="text/javascript"></script>
   <script src="../javascript/registraGestore.js" type="text/javascript"></script>
   <script src="../javascript/dettagliUtente.js" type="text/javascript"></script>
   
    <script>
        var swiper = new Swiper('.swiper-container', {
            effect: 'coverflow',
            grabCursor: true,
            centeredSlides: true,
            slidesPerView: 'auto',
            coverflowEffect: {
                rotate: 50,
                stretch: 0,
                depth: 100,
                modifier: 1,
                slideShadows: true,
            },
            pagination: {
                el: '.swiper-pagination',
            },
        });
    </script>
    <script>
        $(function () {
            var dateFormat = "mm/dd/yy",
                    from = $("#from")
                    .datepicker({
                        defaultDate: "+1w",
                        changeMonth: true,
                        numberOfMonths: 1
                    })
                    .on("change", function () {
                        to.datepicker("option", "minDate", getDate(this));
                    }),
                    to = $("#to").datepicker({
                defaultDate: "+1w",
                changeMonth: true,
                numberOfMonths: 1
            })
                    .on("change", function () {
                        from.datepicker("option", "maxDate", getDate(this));
                    });

            function getDate(element) {
                var date;
                try {
                    date = $.datepicker.parseDate(dateFormat, element.value);
                } catch (error) {
                    date = null;
                }

                return date;
            }
        });
    </script>
    <script src="../Template%20Sb%20Admin/vendor/datatables/jquery.dataTables.js"></script>
    <script src="../Template%20Sb%20Admin/vendor/datatables/dataTables.bootstrap4.js"></script>
    <script src="../Template%20Sb%20Admin/js/sb-admin-datatables.min.js"></script>
</div>


</body>

</html>
