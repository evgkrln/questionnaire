$(document).ready(function(){
    var limitPerPage = parseInt($('#maxRows').val());
    var numberOfItems = $('#mytable tbody tr').length;
    $("#mytable tr").hide();
    for (var i = 0; i <= limitPerPage; i++) {
        $("#mytable tr:eq(" + i + ")").show();
    }
    var totalPages = Math.ceil(numberOfItems / limitPerPage);
    $('.pages').empty();
    $(".pages").append("<li class='previous-page page-item'><a class='page-link' href='javascript:void(0)' aria-label=Previous><span aria-hidden=true>&laquo;</span></a></li>");
    $(".pages").append("<li class='current-page page-item active'><a class='page-link' href='javascript:void(0)'>" + 1 + "</a></li>");
    for (var i = 2; i <= totalPages; i++) {
        $(".pages").append("<li class='current-page page-item'><a class='page-link' href='javascript:void(0)'>" + i + "</a></li>");
    }
    $(".pages").append("<li class='next-page page-item'><a class='page-link' href='javascript:void(0)' aria-label=Next><span aria-hidden=true>&raquo;</span></a></li>");
    $('.str').empty();
    var first = 1;
    if(totalPages==1){
        var second = numberOfItems;
    } else {
        var second = limitPerPage;
    }
    $(".str").append("<h6>"+first+"-"+second+" of "+numberOfItems+"</h6>");

    $(".pagination li.current-page").click(function() {
        if ($(this).hasClass('active')) {
            return false;
        } else {
            var currentPage = $(this).index();
            $(".pagination li").removeClass('active');
            $(this).addClass('active');
            $("#mytable tr").hide();
            var grandTotal = limitPerPage * currentPage;
            $("#mytable tr:eq(0)").show();
            for (var i = grandTotal - limitPerPage + 1; i <= grandTotal; i++) {
                $("#mytable tr:eq(" + i + ")").show();
            }
            $('.str').empty();
            var first = grandTotal - limitPerPage + 1;
            if (currentPage==totalPages) {
                var second = numberOfItems;
            } else {
                var second = grandTotal;
            }
            $(".str").append("<h6>"+first+"-"+second+" of "+numberOfItems+"</h6>");
        }
    });

    $(".next-page").click(function() {
        var currentPage = $(".pagination li.active").index();
        if (currentPage === totalPages) {
            return false;
        } else {
            currentPage++;
            $(".pagination li").removeClass('active');
            $("#mytable tr").hide();
            var grandTotal = limitPerPage * currentPage;
            $("#mytable tr:eq(0)").show();
            for (var i = grandTotal - limitPerPage + 1; i <= grandTotal; i++) {
                $("#mytable tr:eq(" + i + ")").show();
            }
            $('.str').empty();
            var first = grandTotal - limitPerPage + 1;
            if (currentPage==totalPages) {
                var second = numberOfItems;
            } else {
                var second = grandTotal;
            }
            $(".str").append("<h6>"+first+"-"+second+" of "+numberOfItems+"</h6>");
            $(".pagination li.current-page:eq(" + (currentPage - 1) + ")").addClass('active');
        }
    });

    $(".previous-page").click(function() {
        var currentPage = $(".pagination li.active").index();
        if (currentPage === 1) {
            return false;
        } else {
            currentPage--;
            $(".pagination li").removeClass('active');
            $("#mytable tr").hide();
            var grandTotal = limitPerPage * currentPage;
            $("#mytable tr:eq(0)").show();
            for (var i = grandTotal - limitPerPage + 1; i <= grandTotal; i++) {
                $("#mytable tr:eq(" + i + ")").show();
            }
            $('.str').empty();
            var first = grandTotal - limitPerPage + 1;
            if (currentPage==totalPages) {
                var second = numberOfItems;
            } else {
                var second = grandTotal;
            }
            $(".str").append("<h6>"+first+"-"+second+" of "+numberOfItems+"</h6>");
            $(".pagination li.current-page:eq(" + (currentPage - 1) + ")").addClass('active');
        }
    });



    $('#maxRows').change(function() {
        var limitPerPage = parseInt($(this).val());
        var numberOfItems = $('#mytable tbody tr').length;
        $("#mytable tr").hide();
        for (var i = 0; i <= limitPerPage; i++) {
            $("#mytable tr:eq(" + i + ")").show();
        }
        var totalPages = Math.ceil(numberOfItems / limitPerPage);
        $('.pagination').empty();
        $(".pagination").append("<li class='previous-page page-item'><a class='page-link' href='javascript:void(0)' aria-label=Previous><span aria-hidden=true>&laquo;</span></a></li>");
        $(".pagination").append("<li class='current-page page-item active'><a class='page-link' href='javascript:void(0)'>" + 1 + "</a></li>");
        for (var i = 2; i <= totalPages; i++) {
            $(".pagination").append("<li class='current-page page-item'><a class='page-link' href='javascript:void(0)'>" + i + "</a></li>");
        }
        $(".pagination").append("<li class='next-page page-item'><a class='page-link' href='javascript:void(0)' aria-label=Next><span aria-hidden=true>&raquo;</span></a></li>");
        $('.str').empty();
        var first = 1;
        if(totalPages==1){
            var second = numberOfItems;
        } else {
            var second = limitPerPage;
        }
        $(".str").append("<h6>"+first+"-"+second+" of "+numberOfItems+"</h6>");

        $(".pagination li.current-page").click(function() {
            if ($(this).hasClass('active')) {
                return false;
            } else {
                var currentPage = $(this).index();
                $(".pagination li").removeClass('active');
                $(this).addClass('active');
                $("#mytable tr").hide();
                var grandTotal = limitPerPage * currentPage;
                $("#mytable tr:eq(0)").show();
                for (var i = grandTotal - limitPerPage + 1; i <= grandTotal; i++) {
                    $("#mytable tr:eq(" + i + ")").show();
                }
                $('.str').empty();
                var first = grandTotal - limitPerPage + 1;
                if (currentPage==totalPages) {
                    var second = numberOfItems;
                } else {
                    var second = grandTotal;
                }
                $(".str").append("<h6>"+first+"-"+second+" of "+numberOfItems+"</h6>");
            }
        });

        $(".next-page").click(function() {
            var currentPage = $(".pagination li.active").index();
            if (currentPage === totalPages) {
                return false;
            } else {
                currentPage++;
                $(".pagination li").removeClass('active');
                $("#mytable tr").hide();
                var grandTotal = limitPerPage * currentPage;
                $("#mytable tr:eq(0)").show();
                for (var i = grandTotal - limitPerPage + 1; i <= grandTotal; i++) {
                    $("#mytable tr:eq(" + i + ")").show();
                }
                $('.str').empty();
                var first = grandTotal - limitPerPage + 1;
                if (currentPage==totalPages) {
                    var second = numberOfItems;
                } else {
                    var second = grandTotal;
                }
                $(".str").append("<h6>"+first+"-"+second+" of "+numberOfItems+"</h6>");
                $(".pagination li.current-page:eq(" + (currentPage - 1) + ")").addClass('active');
            }
        });

        $(".previous-page").click(function() {
            var currentPage = $(".pagination li.active").index();
            if (currentPage === 1) {
                return false;
            } else {
                currentPage--;
                $(".pagination li").removeClass('active');
                $("#mytable tr").hide();
                var grandTotal = limitPerPage * currentPage;
                $("#mytable tr:eq(0)").show();
                for (var i = grandTotal - limitPerPage + 1; i <= grandTotal; i++) {
                    $("#mytable tr:eq(" + i + ")").show();
                }
                $('.str').empty();
                var first = grandTotal - limitPerPage + 1;
                if (currentPage==totalPages) {
                    var second = numberOfItems;
                } else {
                    var second = grandTotal;
                }
                $(".str").append("<h6>"+first+"-"+second+" of "+numberOfItems+"</h6>");
                $(".pagination li.current-page:eq(" + (currentPage - 1) + ")").addClass('active');
            }
        });
    });
});