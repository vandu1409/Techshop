document.addEventListener("DOMContentLoaded", function() {
	const arr = document.querySelectorAll('.lazyload')
	arr.forEach((v) => {
		io.observe(v);
	})
	const arrBg = document.querySelectorAll('.lazyload_bg')
	arrBg.forEach((v) => {
		bo.observe(v);
	})
})
var is_renderd = 0;
$(document).ready(function ($) {
	$(window).one('mousemove touchstart load',renderLayout )
});
var wDWs = $(window).width();
function renderLayout(){
	if(is_renderd) return 
	is_renderd = 1
	//<![CDATA[ 
	function loadCSS(e, t, n) { "use strict"; var i = window.document.createElement("link"); var o = t || window.document.getElementsByTagName("footer")[0]; i.rel = "stylesheet"; i.href = e; i.media = "only x"; o.parentNode.insertBefore(i, o); setTimeout(function () { i.media = n || "all" }) }
	loadCSS("https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css");
	//]]> 
	if(localStorage.last_viewed_products != undefined) {
		var last_viewd_pro_array = JSON.parse(localStorage.last_viewed_products);
		if(document.querySelector('.countviewed') != null) {
			document.querySelector('.countviewed').innerHTML = last_viewd_pro_array.length;
		}
	}
	var placeholderText = ['Bạn muốn tìm gì?','Tivi', 'Tủ lạnh', 'Điều hòa', 'Loa','Tủ Đông','Lọc nước','Máy in','...'];
	$('.search-auto').placeholderTypewriter({text: placeholderText});
	awe_backtotop();
	awe_category();
	// search_smart();
	if (document.querySelector('.qtyplus') != null) {
		document.querySelector('.qtyplus').addEventListener('click', function(e){
			e.preventDefault();   
			fieldName = this.dataset.field; 
			var numberA = document.querySelector('input[data-field='+fieldName+']').value();
			var currentVal = parseInt(numberA);
			if (!isNaN(currentVal)) { 
				document.querySelector('input[data-field='+fieldName+']').value = currentVal + 1;
			} else {
				document.querySelector('input[data-field='+fieldName+']').value = 0;
			}
		});
	}
	if (document.querySelector('.qtyminus') != null) {
		document.querySelector('.qtyminus').addEventListener('click', function(e){
			e.preventDefault(); 
			fieldName = this.dataset.field; 
			var numberA = document.querySelector('input[data-field='+fieldName+']').value();
			var currentVal = parseInt(numberA);
			if (!isNaN(currentVal) && currentVal > 1) {          
				document.querySelector('input[data-field='+fieldName+']').value = currentVal - 1;
			} else {
				document.querySelector('input[data-field='+fieldName+']').value = 1;
			}
		});
	}
	if (wDWs < 1199 && wDWs > 992) {
		$('.item_big li:has(ul)' ).one("click", function(e)     {
			e.preventDefault();
			return false;    
		});
		$('.ul_menu li:has(ul)' ).one("click", function(e)     {
			e.preventDefault();
			return false;    
		});
	}
	if (wDWs < 1199 && wDWs > 992) {
		$('.ul_menu li:has(ul)' ).one("click", function(e) {
			e.preventDefault();
			return false;    
		});
	}
	$('.ul_menu li .fa').click(function(e){
		if($(this).hasClass('current')) {
			$(this).closest('.ul_menu').find('li, .fa').removeClass('current');
		} else {
			$(this).closest('.ul_menu').find('li, .fa').removeClass('current');
			$(this).closest('li').addClass('current');
			$(this).addClass('current');
		}
	});
	$('.item_big li .fa').click(function(e){
		if($(this).hasClass('current')) {
			$(this).closest('ul').find('li, .fa').removeClass('current');
		} else {
			$(this).closest('ul').find('li, .fa').removeClass('current');
			$(this).closest('li').addClass('current');
			$(this).addClass('current');
		}
	});
	if(window.matchMedia('(min-width: 992px)').matches){
		horizontalNav().init()
		$(window).on('resize',()=>horizontalNav().init())
	}
	var opacityEvent = document.querySelectorAll('.opacity_menu'),
		closeMenu = document.querySelectorAll('.menu-main .menu-title'),
		categoryNav = document.querySelectorAll('.menu_bar');
	opacityEvent.forEach(function(el){
		el.addEventListener('click', function () {
			document.querySelector('html').style.overflow = "initial";
			document.querySelector('.menu-main').classList.remove('current');
			document.querySelector('.opacity_menu').classList.remove('current');
			if(document.querySelector('.dqdt-sidebar, .open-filters, .open-filters')!= null) {
				document.querySelector('.dqdt-sidebar, .open-filters').classList.remove('openf');
				document.querySelector('.open-filters').classList.remove('openf');
				document.querySelector('.open-filters').style.display = "inline-block";
			}
		});
	});
	closeMenu.forEach(function(el){
		el.addEventListener('click', function () {
			document.querySelector('html').style.overflow = "initial";
			document.querySelector('.menu-main').classList.remove('current');
			document.querySelector('.opacity_menu').classList.remove('current');
			if(document.querySelector('.dqdt-sidebar, .open-filters, .open-filters')!= null) {
				document.querySelector('.dqdt-sidebar, .open-filters').classList.remove('openf');
				document.querySelector('.open-filters').classList.remove('openf');
				document.querySelector('.open-filters').style.display = "inline-block";
			}
		});
	});
	categoryNav.forEach(function(el){
		el.addEventListener('click', function () {
			document.querySelector('html').style.overflow = "hidden";
			document.querySelector('.menu-main').classList.add('current');
			document.querySelector('.opacity_menu').classList.toggle('current');
			if(document.querySelector('.dqdt-sidebar, .open-filters')!= null) {
				document.querySelector('.open-filters').style.display = "none";
				document.querySelector('.open-filters').classList.remove('openf');
				document.querySelector('.dqdt-sidebar, .open-filters').classList.remove('openf');
			}
		});
	});
	if (wDWs < 991) {
		var openFilter = document.querySelectorAll('.open-filters');

		openFilter.forEach(function(el){
			el.addEventListener('click', function () {
				this.classList.toggle('openf');
				document.querySelector('.dqdt-sidebar').classList.toggle('openf');
				document.querySelector('.opacity_menu').classList.toggle('current');
			});
		});
		$(window).scroll(function () {
			if ( $(this).scrollTop() > 150 && !$('.nav-mb').hasClass('active') ) {
				$('.nav-mb').addClass('active');
			} else if ( $(this).scrollTop() <= 150 ) {
				$('.nav-mb').removeClass('active');
			}
		});
	}
	if (wDWs < 768){
		$(document).on('click', '.title-menu', function(){
			$(this).toggleClass('active');
		})
	}
}
function awe_convertVietnamese(str) { 
	str= str.toLowerCase();
	str= str.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ/g,"a"); 
	str= str.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g,"e"); 
	str= str.replace(/ì|í|ị|ỉ|ĩ/g,"i"); 
	str= str.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ/g,"o"); 
	str= str.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g,"u"); 
	str= str.replace(/ỳ|ý|ỵ|ỷ|ỹ/g,"y"); 
	str= str.replace(/đ/g,"d"); 
	str= str.replace(/!|@|%|\^|\*|\(|\)|\+|\=|\<|\>|\?|\/|,|\.|\:|\;|\'| |\"|\&|\#|\[|\]|~|$|_/g,"-");
	str= str.replace(/-+-/g,"-");
	str= str.replace(/^\-+|\-+$/g,""); 
	return str; 
} window.awe_convertVietnamese=awe_convertVietnamese;
function awe_category(){
	if (document.querySelectorAll('.nav-category .fa-plus') != undefined) {
		var faplus = document.querySelectorAll('.nav-category .fa-plus'),
			faminus = document.querySelectorAll('.nav-category .fa-minus');
		faplus.forEach(function(fap) {
			fap.addEventListener('click', function () { 
				fap.classList.toggle('fa-minus');
				fap.parentNode.classList.toggle('active');
			})
		})
		faminus.forEach(function(fam) {
			fam.addEventListener('click', function () { 
				fam.classList.toggle('fa-plus');
				fam.parentNode.classList.toggle('active');
			})
		})
	}
} window.awe_category=awe_category;
function awe_backtotop() { 
	window.onscroll = function() {scrollFunction()};
	function scrollFunction() {
		if (document.body.scrollTop > 200 || document.documentElement.scrollTop > 200) {
			document.querySelector('.backtop').classList.add('show');
		} else {
			document.querySelector('.backtop').classList.remove('show');
		}
	}
	document.querySelector('.backtop').addEventListener("click", function(){
		scrollTo(0, 300);
	});
	function scrollTo(element, duration) {
		var e = document.documentElement;
		if (e.scrollTop === 0) {
			var t = e.scrollTop;
			++e.scrollTop;
			e = t + 1 === e.scrollTop-- ? e : document.body;
		}
		scrollToC(e, e.scrollTop, element, duration);
	}
	function scrollToC(element, from, to, duration) {
		if (duration <= 0) return;
		if (typeof from === "object") from = from.offsetTop;
		if (typeof to === "object") to = to.offsetTop;

		scrollToX(element, from, to, 0, 1 / duration, 20, easeOutCuaic);
	}

	function scrollToX(element, xFrom, xTo, t01, speed, step, motion) {
		if (t01 < 0 || t01 > 1 || speed <= 0) {
			element.scrollTop = xTo;
			return;
		}
		element.scrollTop = xFrom - (xFrom - xTo) * motion(t01);
		t01 += speed * step;
		setTimeout(function() {
			scrollToX(element, xFrom, xTo, t01, speed, step, motion);
		}, step);
	}

	function easeOutCuaic(t) {
		t--;
		return t * t * t + 1;
	}

} window.awe_backtotop=awe_backtotop;
// function search_smart(){
// 	$('.evo_sidebar_search .evo-search-form input[type="text"]').bind('focusin focusout', function(e){
// 		$(this).closest('.evo-search').toggleClass('focus', e.type == 'focusin');
// 	});
// 	var preLoadLoadGif = $('<div class="spinner-border text-primary" role="status"><span class="sr-only">Loading...</span></div>');
// 	var searchTimeoutThrottle = 500;
// 	var searchTimeoutID = -1;
// 	var currReqObj = null;
// 	var $resultsBox = $('<div class="results-box" />').appendTo('.search-form');
// 	$('.search-form input[type="text"]').bind('keyup change', function(){
// 		if($(this).val().length > 2 && $(this).val() != $(this).data('oldval')) {
// 			$(this).data('oldval', $(this).val());
// 			if(currReqObj != null) currReqObj.abort();
// 			clearTimeout(searchTimeoutID);
// 			var $form = $(this).closest('form');
// 			var term = '*' + $form.find('input[name="query"]').val() + '*';
// 			var linkURL = $form.attr('action') + '?query=' + term + '&type=product';
// 			$resultsBox.html('<div class="evo-loading"><div class="spinner-border text-primary" role="status"><span class="sr-only">Loading...</span></div></div>');
// 			searchTimeoutID = setTimeout(function(){
// 				currReqObj = $.ajax({
// 					url: $form.attr('action'),
// 					async: false,
// 					data: {
// 						type: 'product',
// 						view: 'json',
// 						q: term
// 					},
// 					dataType: 'json',
// 					success: function(data){
// 						currReqObj = null;
// 						if(data.results_total == 0) {
// 							$resultsBox.html('<div class="note">Không có kết quả tìm kiếm</div>');
// 						} else {
// 							$resultsBox.empty();
// 							$.each(data.results, function(index, item){
// 								var xshow = 'wholesale';
// 								if(!(item.title.toLowerCase().indexOf(xshow) > -1)) {
// 									var $row = $('<a class="clearfix"></a>').attr('href', item.url).attr('title', item.title);
// 									$row.append('<div class="img"><img src="' + item.thumb + '" /></div>');
// 									$row.append('<div class="d-title">'+item.title+'</div>');
// 									$row.append('<div class="d-title d-price">'+item.price+'</div>');
// 									$resultsBox.append($row);
// 								}
// 							});
// 							$resultsBox.append('<a href="' + linkURL + '" class="note" title="Xem tất cả">Xem tất cả</a>');
// 						}
// 					}
// 				});
// 			}, searchTimeoutThrottle);
// 		} else if ($(this).val().length <= 2) {
// 			$resultsBox.empty();
// 		}
// 	}).attr('autocomplete', 'off').data('oldval', '').bind('focusin', function(){
// 		$resultsBox.fadeIn(200);
// 	}).bind('click', function(e){
// 		e.stopPropagation();
// 	});
// 	$('body').bind('click', function(){
// 		$resultsBox.fadeOut(200);
// 	});
// } window.search_smart=search_smart;
$('.addThis_iconContact .item-contact,.addThis_listSharing .addThis_close').on('click', function(e){
	if($('.addThis_listSharing').hasClass('active')){
		$('.addThis_listSharing').removeClass('active');
		$('.addThis_listSharing').fadeOut(150);				
	}
	else{		
		$('.addThis_listSharing').fadeIn(100);
		$('.addThis_listSharing').addClass('active');
	}
});
window.theme = window.theme || {};
theme.compare = (function (){
	var compareButtonClass = '.js-btn-compare',
		compareRemoveButtonClass = '.js-remove-compare',
		$compareShowButton = $('.site-header__compare'),
		$compareCount = $('.js-compare-count'),
		$compareContainer = $('.js-compare-content'),
		$compareProduct = $('.compare-product'),
		$compareSpecification = $('.compare-specification'),
		compareObject = JSON.parse(localStorage.getItem('localCompare')) || [],
		alertClass='alert-success',
		evoCheckProductType='',
		evoDefaultProductType='';
	function updateCompare(self) {
		var productHandle = $(self).data('handle'),
			productType = $(self).data('type'),
			alertText = '';
		var isAdded = $.inArray(productHandle,compareObject) !== -1 ? true:false;
		evoCheckProductType = $(self).data('type');
		if (isAdded) {
			compareObject.splice(compareObject.indexOf(productHandle), 1);
			alertText = 'Đã xóa khỏi dánh sách so sánh';
			alertClass = 'alert-success';
		}else{
			if(compareObject.length === 3){
				alertText = 'So sánh tối đa 3 sản phẩm';
				alertClass = 'alert-danger';
			}else{
				if(evoDefaultProductType == ''){
					alertClass = 'alert-success';
					compareObject.push(productHandle);
					alertText = 'Đã thêm vào danh sách so sánh';
				}else{
					if(compareObject.length > 0){
						if(evoDefaultProductType != evoCheckProductType){
							alertText = 'Sản phẩm so sánh phải cùng loại';
							alertClass = 'alert-danger';
						}else{
							alertClass = 'alert-success';
							compareObject.push(productHandle);
							alertText = 'Đã thêm vào danh sách so sánh';
						}
					}else{
						alertClass = 'alert-success';
						compareObject.push(productHandle);
						alertText = 'Đã thêm vào danh sách so sánh';
					}
				}
			}
		}
		localStorage.setItem('localCompare', JSON.stringify(compareObject)); 
		new Notify ({
			status: 'success',title: 'So sánh sản phẩm',text: alertText,
			effect: 'slide',speed: 300,showIcon: true,showCloseButton: true,autoclose: true,autotimeout: 2000,gap: 20,distance: 20,type: 1,position: 'right top',
		})
		$compareCount.text(compareObject.length);
		var evoFirstCompareProductHandle = compareObject[0];
		Bizweb.getProduct(evoFirstCompareProductHandle,function(product){
			evoDefaultProductType = product.product_type;
		});
	};
	function loadCompare(){
		var compareGrid;
		//$compareContainer.html('');
		$compareProduct.html('');
		$compareSpecification.html('');
		if (compareObject.length > 0){
			$compareShowButton.removeClass('d-none');
			compareGrid = compareObject.length === 1? 'col' : 'col';
			for (var i = 0; i < compareObject.length; i++) { 
				var productHandle = compareObject[i];
				Bizweb.getProduct(productHandle,function(product){
					var htmlProduct = '', htmlSpecification = '',
						productComparePrice = Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(product.variants[0].compare_at_price),
						productAvailable = product.available ? "Còn hàng" : "Hết hàng",
						productAvailableClass = product.available ? 'alert-success' : 'alert-danger',
						productVendorHTML = product.vendor !== null ? '<a href="/collections/vendors?q='+ product.vendor +'">'+ product.vendor +'</a>' : '<span>Đang cập nhật</span>';
					if(product.featured_image != null){
						var src = Bizweb.resizeImage(product.featured_image, 'large');
					}else{
						var src = "//bizweb.dktcdn.net/thumb/large/assets/themes_support/noimage.gif";
					}
					if(product.variants[0].price > 0 ){
						var productPrice = Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(product.variants[0].price);
					}else{
						var productPrice = "Liên hệ";
					}
					if(product.content != null){
						var productContent = product.content;
						if(productContent.includes('####')){
							var productMainContent = productContent.split('<p>####</p>')[1];
						}else{
							var productMainContent = "<div class='no-content'>Nội dung đang cập nhật</div>";
						}
					}else{
						var productMainContent = "<div class='no-content'>Nội dung đang cập nhật</div>";
					}
					htmlProduct += '<div class="compare-item '+compareGrid+' col-6 col-lg-3 col-md-4"></div>';
					htmlProduct += '<div class="compare-item compare-item-info '+compareGrid+' col-6 col-lg-3 col-md-4">';
					htmlProduct += '	<div class="compage-image"><button class="js-remove-compare" data-handle="'+product.alias+'" title="Xóa"><span>x</span></button>';
					htmlProduct += '	<a href="'+ product.url +'" title="'+ product.name +'">';
					htmlProduct += '		<img src="'+ src +'" alt="'+ product.name +'" />';
					htmlProduct += '	</a></div>';
					htmlProduct += '	<h3><a href="'+ product.url +'" title="'+ product.name +'">'+ product.name +'</a></h3>';
					htmlProduct += '	<div class="group-price"><span class="price"> '+ productPrice +'</span>';
					if(product.variants[0].compare_at_price > product.variants[0].price ){
						htmlProduct += '	<s class="old-price">'+ productComparePrice +'</s></div>';
					}
					htmlProduct += '<div class="clearfix"></div><span class='+ productAvailableClass +'> '+ productAvailable +'</span>';
					htmlProduct += '</div>';
					$compareProduct.append(htmlProduct);
					htmlSpecification += '<div class="compare-item '+compareGrid+' col-6 col-lg-3 col-md-4">';
					htmlSpecification +=productMainContent.replace("Thông số kỹ thuật", "");
					htmlSpecification += '</div>';
					$compareSpecification.append(htmlSpecification);
					$('.compare-specification h6, .compare-specification p').remove();
				});
			}
			var evoFirstCompareProductHandle = compareObject[0];
			Bizweb.getProduct(evoFirstCompareProductHandle,function(product){
				evoDefaultProductType = product.product_type;
				var countcomparecell = $('.compare-specification .compare-item:nth-child(1) table tr').length;
				for (var i = 1; i <= countcomparecell; i++){
					var height1 = $(".compare-specification .compare-item:nth-child(1) table tr:nth-child("+i+")").height();
					var height2 = $(".compare-specification .compare-item:nth-child(2) table tr:nth-child("+i+")").height();
					var height3 = $(".compare-specification .compare-item:nth-child(3) table tr:nth-child("+i+")").height();
					var setHeight = Math.max(height1, height2, height3);
					$(".compare-specification .compare-item:nth-child(1) table tr:nth-child("+i+")").height(setHeight);
					$(".compare-specification .compare-item:nth-child(2) table tr:nth-child("+i+")").height(setHeight);
					$(".compare-specification .compare-item:nth-child(3) table tr:nth-child("+i+")").height(setHeight);
				}
			});
		}else{
			$compareContainer.html('<div class="alert alert-warning">Vui lòng chọn sản phẩm để so sánh</div>');
			$compareShowButton.addClass('d-none');
			evoDefaultProductType = '';
		}
		$(compareButtonClass).each(function(){
			var productHandle = $(this).data('handle');
			var status = $.inArray(productHandle,compareObject) !== -1 ? 'added' : '';
			$(this).removeClass('added').addClass(status);
		});
		$compareCount.text(compareObject.length);
	}
	$(document).on('click',compareButtonClass,function (event) {
		event.preventDefault();
		updateCompare(this);
		loadCompare();
	});
	$(document).on('click',compareRemoveButtonClass,function(){
		var productHandle = $(this).data('handle');
		compareObject.splice(compareObject.indexOf(productHandle), 1);
		localStorage.setItem('localCompare', JSON.stringify(compareObject)); 
		loadCompare();
	});
	loadCompare();
	$(document).on('Bizweb:section:load', loadCompare);
	return{
		load:loadCompare
	}
})()

function horizontalNav () {
	return {
		wrapper: $('.header .menu-main'),
		navigation: $('.header .menu-main .item_big'),
		item: $('.header .menu-main .item_big>li'),
		arrows: $('.navigation-arrows'),
		scrollStep: 0,
		totalStep: 0,
		transform: function(){
			return `translateY(-${this.scrollStep*100}%)` 
		},
		onCalcNavOverView: function(){
			let itemHeight = this.item.eq(0).outerHeight(),
				navHeight = this.navigation.height()
			return Math.ceil(navHeight/itemHeight)
		},
		handleArrowClick: function(e){
			this.totalStep = this.onCalcNavOverView()
			this.scrollStep = $(e.target).hasClass('prev') ? this.scrollStep - 1 : this.scrollStep + 1
			this.handleScroll()
		},
		handleScroll: function(){
			this.arrows.find('i').removeClass('disabled')
			if(this.totalStep - 1 <= this.scrollStep ){
				this.arrows.find('.next').addClass('disabled')
				this.scrollStep = this.totalStep - 1
			}
			if(this.scrollStep <= 0){
				this.arrows.find('.prev').addClass('disabled')
				this.scrollStep = 0
			}
			this.item.find('.a-img').css('transform', this.transform())
		},
		init:function(){
			this.totalStep = this.onCalcNavOverView()
			if(this.totalStep > 1){
				this.wrapper.addClass('overflow')
			} 
			this.handleScroll()
			this.arrows.find('i').click((e)=>this.handleArrowClick(e))
		}
	}	
}