/*  ---------------------------------------------------
    Template Name: Ogani
    Description:  Ogani eCommerce  HTML Template
    Author: Colorlib
    Author URI: https://colorlib.com
    Version: 1.0
    Created: Colorlib
---------------------------------------------------------  */

'use strict';

$(document).ready(function() {
	document.getElementById("udetail").addEventListener("click", function() {
		var html = '<hr>'
			+'<form class="form" action="##" method="post" id="registrationForm">'
			+'<div class="form-group">'

			+'	<div class="col-xs-6">'
			+'	<label for="name"><h4>Full name</h4></label> <input'
			+'	type="text" class="form-control" name="name"'
			+'	id="name" placeholder="name"'
			+'	title="enter your first name if any.">'
			+'</div>'
			+'	</div>'

				+'<div class="form-group">'

				+'	<div class="col-xs-6">'
				+'		<label for="phone"><h4>Phone</h4></label> <input type="text"'
				+'	class="form-control" name="phone" id="phone"'
						+'			placeholder="enter phone"'
						+'			title="enter your phone number if any.">'
						+'	</div>'
						+'</div>'
		
						+'	<div class="form-group">'

						+'<div class="col-xs-6">'
						+'	<label for="email"><h4>Email</h4></label> <input type="email"'
						+'	class="form-control" name="email" id="email"'
						+'	placeholder="you@email.com" title="enter your email.">'
						+'</div>'
						+'</div>'
						+'<div class="form-group">'

						+'	<div class="col-xs-6">'
						+'	<label for="address"><h4>Addess</h4></label> <input type="text"'
						+'	class="form-control" id="location" placeholder="somewhere"'
						+'	title="enter a location">'
						+'</div>'
						+'</div>'
						+'<div class="form-group">'

						+'	<div class="col-xs-6">'
						+'	<label for="password"><h4>Password</h4></label> <input'
				+'		type="password" class="form-control" name="password"'
				+'	id="password" placeholder="password"'
				+'	title="enter your password.">'
				+'</div>'
				+'</div>'
				+'<div class="form-group">'

				+'<div class="col-xs-6">'
				+'<label for="password2"><h4>Verify</h4></label> <input'
				+'	type="password" class="form-control" name="password2"'
				+'	id="password2" placeholder="password2"'
				+'	title="enter your password2.">'
				+'</div>'
				+'</div>'
				+'<div class="form-group">'
				+'<div class="col-xs-12">'
				+'	<br>'
				+'	<button class="btn btn-lg btn-success" type="submit">'
		+'		<i class="glyphicon glyphicon-ok-sign"></i> Save'
		+'	</button>'
		+'<button class="btn btn-lg" type="reset">'
		+'	<i class="glyphicon glyphicon-repeat"></i> Reset'
		+'	</button>'
		+'</div>'
		+'</div>'
		+'</form>'

		+'<hr>';
		$(".tab-pane").empty();
		$(".tab-pane").html(html);
	});
	document.getElementById("uorder").addEventListener("click", function() {
		$(".tab-pane").empty();
	});
    
    var readURL = function(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('.avatar').attr('src', e.target.result);
            }
    
            reader.readAsDataURL(input.files[0]);
        }
    }
    

    $(".file-upload").on('change', function(){
        readURL(this);
    });
});



function getTypeSearch(event){
	var text = $(event.target).text();
	if(text == "Price"){
		document.getElementById('typeToSearch').value = "price";
		document.getElementById('operator').value = "LESS_THAN_OR_EQUAL";
	}else if(text == "Price sale"){
		document.getElementById('typeToSearch').value = "price_sale";
		document.getElementById('operator').value = "LESS_THAN_OR_EQUAL";
	}else if(text == "All Products"){
		document.getElementById('typeToSearch').value = "name";
		document.getElementById('operator').value = "LIKE";
	}else{
		document.getElementById('typeToSearch').value = "category_id";
		document.getElementById('categoryIdToSearch').value = event.target.value;
		document.getElementById('operator').value = "LIKE";
	}
	document.getElementById('outPutTypeName').innerHTML = text + '<span onclick="showAllCategory();"class="arrow_carrot-down dropdowncate"></span>';
}

(function ($) {
		
    /*------------------
        Preloader
    --------------------*/
    $(window).on('load', function () {
        $(".loader").fadeOut();
        $("#preloder").delay(200).fadeOut("slow");

        /*------------------
            Gallery filter
        --------------------*/
        $('.featured__controls li').on('click', function () {
            $('.featured__controls li').removeClass('active');
            $(this).addClass('active');
        });
        if ($('.featured__filter').length > 0) {
            var containerEl = document.querySelector('.featured__filter');
            var mixer = mixitup(containerEl);
        }
    });

    
    /*------------------
    	Format timestamp for blog
	--------------------*/
//    var timestamp = document.getElementsByClassName("blogFormatDate");
//    
//    for(var i=0;i<timestamp.length;i++){
//    	var data = '<i class="fa fa-calendar-o"></i> ' + formatDate(new Date(parseInt(timestamp[i].getAttribute('data-value'))));
//    	timestamp[i].innerHTML = data;
//    }
    
    /*------------------
        Background Set
    --------------------*/
    $('.set-bg').each(function () {
        var bg = $(this).data('setbg');
        var formatBg = bg.replace(/\\/g,"/");
        $(this).css('background-image', 'url(' + formatBg + ')');
    });

    //Humberger Menu
    $(".humberger__open").on('click', function () {
        $(".humberger__menu__wrapper").addClass("show__humberger__menu__wrapper");
        $(".humberger__menu__overlay").addClass("active");
        $("body").addClass("over_hid");
    });

    $(".humberger__menu__overlay").on('click', function () {
        $(".humberger__menu__wrapper").removeClass("show__humberger__menu__wrapper");
        $(".humberger__menu__overlay").removeClass("active");
        $("body").removeClass("over_hid");
    });

    /*------------------
		Navigation
	--------------------*/
    $(".mobile-menu").slicknav({
        prependTo: '#mobile-menu-wrap',
        allowParentLinks: true
    });

    /*-----------------------
        Categories Slider
    ------------------------*/
    $(".categories__slider").owlCarousel({
        loop: true,
        margin: 0,
        items: 4,
        dots: false,
        nav: true,
        navText: ["<span class='fa fa-angle-left'><span/>", "<span class='fa fa-angle-right'><span/>"],
        animateOut: 'fadeOut',
        animateIn: 'fadeIn',
        smartSpeed: 1200,
        autoHeight: false,
        autoplay: true,
        responsive: {

            0: {
                items: 1,
            },

            480: {
                items: 2,
            },

            768: {
                items: 3,
            },

            992: {
                items: 4,
            }
        }
    });


    $('.hero__categories__all').on('click', function(){
        $('.hero__categories ul').slideToggle(400);
    });

    /*--------------------------
        Latest Product Slider
    ----------------------------*/
    $(".latest-product__slider").owlCarousel({
        loop: true,
        margin: 0,
        items: 1,
        dots: false,
        nav: true,
        navText: ["<span class='fa fa-angle-left'><span/>", "<span class='fa fa-angle-right'><span/>"],
        smartSpeed: 1200,
        autoHeight: false,
        autoplay: true
    });

    /*-----------------------------
        Product Discount Slider
    -------------------------------*/
    $(".product__discount__slider").owlCarousel({
        loop: true,
        margin: 0,
        items: 3,
        dots: true,
        smartSpeed: 1200,
        autoHeight: false,
        autoplay: true,
        responsive: {

            320: {
                items: 1,
            },

            480: {
                items: 2,
            },

            768: {
                items: 2,
            },

            992: {
                items: 3,
            }
        }
    });

    /*---------------------------------
        Product Details Pic Slider
    ----------------------------------*/
    $(".product__details__pic__slider").owlCarousel({
        loop: true,
        margin: 20,
        items: 4,
        dots: true,
        smartSpeed: 1200,
        autoHeight: false,
        autoplay: true
    });

    /*-----------------------
		Price Range Slider
	------------------------ */
    var rangeSlider = $(".price-range"),
        minamount = $("#minamount"),
        maxamount = $("#maxamount"),
        minPrice = rangeSlider.data('min'),
        maxPrice = rangeSlider.data('max');
    rangeSlider.slider({
        range: true,
        min: minPrice,
        max: maxPrice,
        values: [minPrice, maxPrice],
        slide: function (event, ui) {
            minamount.val('$' + ui.values[0]);
            maxamount.val('$' + ui.values[1]);
        }
    });
    minamount.val('$' + rangeSlider.slider("values", 0));
    maxamount.val('$' + rangeSlider.slider("values", 1));

    /*--------------------------
        Select
    ----------------------------*/
    $("select").niceSelect();

    /*------------------
		Single Product
	--------------------*/
    $('.product__details__pic__slider img').on('click', function () {

        var imgurl = $(this).data('imgbigurl');
        var bigImg = $('.product__details__pic__item--large').attr('src');
        if (imgurl != bigImg) {
            $('.product__details__pic__item--large').attr({
                src: imgurl
            });
        }
    });

    /*-------------------
		Quantity change
	--------------------- */
    var proQty = $('.pro-qty');
    proQty.prepend('<span class="dec qtybtn">-</span>');
    proQty.append('<span class="inc qtybtn">+</span>');
    proQty.on('click', '.qtybtn', function () {
        var $button = $(this);
        var oldValue = $button.parent().find('input').val();
        if ($button.hasClass('inc')) {
            var newVal = parseFloat(oldValue) + 1;
        } else {
            // Don't allow decrementing below zero
            if (oldValue > 0) {
                var newVal = parseFloat(oldValue) - 1;
            } else {
                newVal = 0;
            }
        }
        $button.parent().find('input').val(newVal);
    });

})(jQuery);

function showAllCategory(){
    document.getElementById("dropDownCategory").classList.toggle("show");
}

window.onclick = function(event) {
    if (!event.target.matches('.dropdowncate')) {
        var dropdowns = document.getElementsByClassName("dropdown-content");
        var i;
        for (i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
}


