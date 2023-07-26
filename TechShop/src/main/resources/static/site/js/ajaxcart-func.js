/*============================================================================
  Ajaxcartfy - thuongdq
==============================================================================*/
window.theme = window.theme || {};
var wW = $(window).width();
var timeout;


$(document).on('click','.backdrop__body-backdrop___1rvky.active, .cart_btn-close', function() {   
	$('.backdrop__body-backdrop___1rvky, .cart-sidebar, #popup-cart-desktop, .popup-cart-mobile').removeClass('active');
	$('.CartSideContainer').html('');
	return false;
})



Bizweb.addItemFromForm = function(form, callback, errorCallback) {
	var params = {
		type: 'POST',
		url: '/cart/add.js',
		data: jQuery(form).serialize(),
		dataType: 'json',
		success: function(line_item) {
			if ((typeof callback) === 'function') {
				callback(line_item, form);
				new Notify ({
					status: 'success',title: 'Thêm vào giỏ hàng thành công',text: 'bấm <a style="color:#2196f3" href="/cart">vào đây</a> để tới trang giỏ hàng',
					effect: 'slide',speed: 300,showIcon: true,showCloseButton: true,autoclose: true,autotimeout: 2000,gap: 20,distance: 20,type: 1,position: 'right top',
				})
				
				if (wW > 1200) {
					//$('#popup-cart-desktops, .cart-sidebar, .backdrop__body-backdrop___1rvky').addClass('active');
				}else {
					//$('.popup-cart-mobile, .backdrop__body-backdrop___1rvky').addClass('active');
					//AddCartMobile(line_item);
				}
			}
			/*
      else {
        Bizweb.onItemAdded(line_item, form);
		$('#popup-cart-desktops, .cart-sidebar, .backdrop__body-backdrop___1rvky').addClass('active');
      }
	  */
		},
		error: function(XMLHttpRequest, textStatus) {
			if ((typeof errorCallback) === 'function') {
				errorCallback(XMLHttpRequest, textStatus);
			}
			else {
				Bizweb.onError(XMLHttpRequest, textStatus);
			}
		}
	};
	jQuery.ajax(params);
};
	
/*========================
Popup cart mobile
=========================*/
function AddCartMobile(line_item) {
	//console.log(line_item);
	$('.bodycart-mobile').html('');
	var imagepop = Bizweb.resizeImage(line_item.image, 'compact');
	if(imagepop=="null" || imagepop =='' || imagepop ==null){
		imagepop = 'https://bizweb.dktcdn.net/thumb/compact/assets/themes_support/noimage.gif';
	}
	var variant_title = line_item.variant_title;
	if (variant_title === 'Default Title')
		variant_title = '';
	var carttem = ''
	+ '<div class="thumb-1x1">'
	+ '<img src="'+imagepop+'" alt="'+line_item.title+'">'
	+ '</div>'
	+ '<div class="body_content">'
	+ '<h4 class="product-title">'+line_item.title+'</h4>'
	+ '<div class="product-new-price">'
	+ '<b>'+ Bizweb.formatMoney(line_item.price, "{{store.money_format}}") + '</b>'
	+ '<span>'+variant_title+'</span>'
	+ '</div>'
	+ '</div>';
	$('.bodycart-mobile').append(carttem);
}

/*============================================================================
  Override POST to cart/change.js. Returns cart JSON.
    - Use product's line in the cart instead of ID so custom
      product properties are supported.
==============================================================================*/
Bizweb.changeItem = function(line, quantity, callback) {
	var params = {
		type: 'POST',
		url: '/cart/change.js',
		data: 'quantity=' + quantity + '&line=' + line,
		dataType: 'json',
		success: function(cart) {
			if ((typeof callback) === 'function') {
				callback(cart);
			}
			else {
				Bizweb.onCartUpdate(cart);
			}
		},
		error: function(XMLHttpRequest, textStatus) {
			Bizweb.onError(XMLHttpRequest, textStatus);
		}
	};
	jQuery.ajax(params);
};

/*============================================================================
  GET cart.js returns the cart in JSON.
==============================================================================*/
Bizweb.getCart = function(callback) {
	jQuery.getJSON('/cart.js', function (cart, textStatus) {
		if ((typeof callback) === 'function') {
			callback(cart);
		}
		else {
			Bizweb.onCartUpdate(cart);
		}
	});
};

/*============================================================================
  Ajax Bizweb Add To Cart
==============================================================================*/
var ajaxCart = (function(module, $) {

	'use strict';

	// Public functions
	var init, loadCart;

	// Private general variables
	var settings, isUpdating, $body;

	// Private plugin variables
	var $formContainer, $errorsContainer, $addToCart, $cartCountSelector, $nameItemAdd, $cartCostSelector, $cartContainer, $cartContainerMobile, $cartContainerPage, $cartPopup, $cartContainerHeader, $cartContainerSidebar, $countItem;

	// Private functions
	var updateCountPrice, formOverride, itemAddedCallback, itemAddedNoti, itemErrorCallback, cartUpdateCallback, buildCart, cartCallback, adjustCart, adjustCartCallback, qtySelectors, validateQty;

	/*============================================================================
    Initialise the plugin and define global options
  ==============================================================================*/
	init = function (options) {

		// Default settings
		settings = {
			formSelector       		: '[data-cart-form]',
			errorSelector      		: '.product-single__errors',
			cartContainer      		: '.CartSideContainer, .CartPageContainer, .CartHeaderContainer, .cartPopupContainer, .CartMobileContainer',
			cartContainerSidebar  	: '.CartSideContainer',
			cartContainerPage  		: '.CartPageContainer',
			cartContainerMobile  	: '.CartMobileContainer',
			cartContainerHeader  	: '.CartHeaderContainer',
			cartContainerPopup  	: '.cartPopupContainer',
			addToCartSelector  		: '.add_to_cart',
			countItem			 	: '.count_item_pr',
			cartCountSelector  		: '.count_item_pr',
			nameItemAdd  			: '.cart-popup-name',
			cartCostSelector   		: null,
			moneyFormat        		: theme.settings.moneyFormat,
			disableAjaxCart    		: false,
			enableQtySelectors 		: true
		};

		// Override defaults with arguments
		$.extend(settings, options);

		// Select DOM elements
		$formContainer     		= $(settings.formSelector);
		$errorsContainer   		= $(settings.errorSelector);
		$cartContainer     		= $(settings.cartContainer);
		$cartContainerSidebar 	= $(settings.cartContainerSidebar);
		$cartContainerPage 		= $(settings.cartContainerPage);
		$cartContainerMobile 	= $(settings.cartContainerMobile);
		$cartContainerHeader 	= $(settings.cartContainerHeader);
		$cartPopup				= $(settings.cartContainerPopup);
		$addToCart         		= $formContainer.find(settings.addToCartSelector);
		$nameItemAdd 			= $(settings.nameItemAdd);
		$cartCountSelector 		= $(settings.cartCountSelector);
		$cartCostSelector  		= $(settings.cartCostSelector);
		$countItem 		   		= $(settings.countItem);

		// General Selectors
		$body = $('body');

		// Track cart activity status
		isUpdating = false;

		// Setup ajax quantity selectors on the any template if enableQtySelectors is true
		if (settings.enableQtySelectors) {
			qtySelectors();
		}

		// Take over the add to cart form submit action if ajax enabled
		if (!settings.disableAjaxCart && $addToCart.length) {
			formOverride();
		}

		// Run this function in case we're using the quantity selector outside of the cart
		adjustCart();
	};

	loadCart = function () {
		Bizweb.getCart(cartUpdateCallback);
	};

	updateCountPrice = function (cart) {
		if ($cartCountSelector) {
			$cartCountSelector.html(cart.item_count).removeClass('hidden-count');

			if (cart.item_count === 0) {
				$cartCountSelector.addClass('hidden-count');
			}
		}

		if ($cartCostSelector) {
			$cartCostSelector.html(Bizweb.formatMoney(cart.total_price, settings.moneyFormat));
		}
	};

	formOverride = function () {
		$formContainer.on('submit', function(evt) {
			evt.preventDefault();
			$addToCart.removeClass('is-added').addClass('is-adding');
			$('.qty-error').remove();
			Bizweb.addItemFromForm(evt.target, itemAddedCallback, itemErrorCallback);
		});
	};

	itemAddedCallback = function (product) {
		$addToCart.removeClass('is-adding').addClass('is-added');
		Bizweb.getCart(cartUpdateCallback);
		$nameItemAdd.html(product.title).attr('href', product.url, 'title', product.title);
	};

	itemErrorCallback = function (XMLHttpRequest, textStatus) {
		var data = eval('(' + XMLHttpRequest.responseText + ')');
		$addToCart.removeClass('is-adding is-added');

		$cartContainer.trigger('ajaxCart.updatedQty');

		if (!!data.message) {
			if (data.status == 422) {
				$errorsContainer.html('<div class="errors qty-error">'+ data.description +'</div>')
			}
		}
	};

	cartUpdateCallback = function (cart) {
		// Update quantity and price
		updateCountPrice(cart);
		buildCart(cart);
		
	};

	buildCart = function (cart) {
		// Start with a fresh cart div
		var itemListScrollTop = $('.CartHeaderContainer .ajaxcart__inner').scrollTop(),
			itemPopupScrollTop = $('.cartPopupContainer .ajaxcart__inner').scrollTop(),
			itemSideCartScrollTop = $('.CartSideContainer .ajaxcart__inner').scrollTop();
		$cartContainer.empty();
		$('.formVAT, .colrightvat').removeClass('d-none');
		$('.colcartleft').addClass('col-lg-9');
		// Show empty cart
		if (cart.item_count === 0) {
			$cartContainer
				.append('<div class="cart--empty-message"><svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" id="Capa_1" x="0px" y="0px" viewBox="0 0 201.387 201.387" style="enable-background:new 0 0 201.387 201.387;" xml:space="preserve"> <g> <g> <path d="M129.413,24.885C127.389,10.699,115.041,0,100.692,0C91.464,0,82.7,4.453,77.251,11.916    c-1.113,1.522-0.78,3.657,0.742,4.77c1.517,1.109,3.657,0.78,4.768-0.744c4.171-5.707,10.873-9.115,17.93-9.115    c10.974,0,20.415,8.178,21.963,19.021c0.244,1.703,1.705,2.932,3.376,2.932c0.159,0,0.323-0.012,0.486-0.034    C128.382,28.479,129.679,26.75,129.413,24.885z"/> </g> </g> <g> <g> <path d="M178.712,63.096l-10.24-17.067c-0.616-1.029-1.727-1.657-2.927-1.657h-9.813c-1.884,0-3.413,1.529-3.413,3.413    s1.529,3.413,3.413,3.413h7.881l6.144,10.24H31.626l6.144-10.24h3.615c1.884,0,3.413-1.529,3.413-3.413s-1.529-3.413-3.413-3.413    h-5.547c-1.2,0-2.311,0.628-2.927,1.657l-10.24,17.067c-0.633,1.056-0.648,2.369-0.043,3.439s1.739,1.732,2.97,1.732h150.187    c1.231,0,2.364-0.662,2.97-1.732S179.345,64.15,178.712,63.096z"/> </g> </g> <g> <g> <path d="M161.698,31.623c-0.478-0.771-1.241-1.318-2.123-1.524l-46.531-10.883c-0.881-0.207-1.809-0.053-2.579,0.423    c-0.768,0.478-1.316,1.241-1.522,2.123l-3.509,15c-0.43,1.835,0.71,3.671,2.546,4.099c1.835,0.43,3.673-0.71,4.101-2.546    l2.732-11.675l39.883,9.329l-6.267,26.795c-0.43,1.835,0.71,3.671,2.546,4.099c0.263,0.061,0.524,0.09,0.782,0.09    c1.55,0,2.953-1.062,3.318-2.635l7.045-30.118C162.328,33.319,162.176,32.391,161.698,31.623z"/> </g> </g> <g> <g> <path d="M102.497,39.692l-3.11-26.305c-0.106-0.899-0.565-1.72-1.277-2.28c-0.712-0.56-1.611-0.816-2.514-0.71l-57.09,6.748    c-1.871,0.222-3.209,1.918-2.988,3.791l5.185,43.873c0.206,1.737,1.679,3.014,3.386,3.014c0.133,0,0.27-0.009,0.406-0.024    c1.87-0.222,3.208-1.918,2.988-3.791l-4.785-40.486l50.311-5.946l2.708,22.915c0.222,1.872,1.91,3.202,3.791,2.99    C101.379,43.261,102.717,41.564,102.497,39.692z"/> </g> </g> <g> <g> <path d="M129.492,63.556l-6.775-28.174c-0.212-0.879-0.765-1.64-1.536-2.113c-0.771-0.469-1.696-0.616-2.581-0.406L63.613,46.087    c-1.833,0.44-2.961,2.284-2.521,4.117l3.386,14.082c0.44,1.835,2.284,2.964,4.116,2.521c1.833-0.44,2.961-2.284,2.521-4.117    l-2.589-10.764l48.35-11.626l5.977,24.854c0.375,1.565,1.775,2.615,3.316,2.615c0.265,0,0.533-0.031,0.802-0.096    C128.804,67.232,129.932,65.389,129.492,63.556z"/> </g> </g> <g> <g> <path d="M179.197,64.679c-0.094-1.814-1.592-3.238-3.41-3.238H25.6c-1.818,0-3.316,1.423-3.41,3.238l-6.827,133.12    c-0.048,0.934,0.29,1.848,0.934,2.526c0.645,0.677,1.539,1.062,2.475,1.062h163.84c0.935,0,1.83-0.384,2.478-1.062    c0.643-0.678,0.981-1.591,0.934-2.526L179.197,64.679z M22.364,194.56l6.477-126.293h143.701l6.477,126.293H22.364z"/> </g> </g> <g> <g> <path d="M126.292,75.093c-5.647,0-10.24,4.593-10.24,10.24c0,5.647,4.593,10.24,10.24,10.24c5.647,0,10.24-4.593,10.24-10.24    C136.532,79.686,131.939,75.093,126.292,75.093z M126.292,88.747c-1.883,0-3.413-1.531-3.413-3.413s1.531-3.413,3.413-3.413    c1.882,0,3.413,1.531,3.413,3.413S128.174,88.747,126.292,88.747z"/> </g> </g> <g> <g> <path d="M75.092,75.093c-5.647,0-10.24,4.593-10.24,10.24c0,5.647,4.593,10.24,10.24,10.24c5.647,0,10.24-4.593,10.24-10.24    C85.332,79.686,80.739,75.093,75.092,75.093z M75.092,88.747c-1.882,0-3.413-1.531-3.413-3.413s1.531-3.413,3.413-3.413    s3.413,1.531,3.413,3.413S76.974,88.747,75.092,88.747z"/> </g> </g> <g> <g> <path d="M126.292,85.333h-0.263c-1.884,0-3.413,1.529-3.413,3.413c0,0.466,0.092,0.911,0.263,1.316v17.457    c0,12.233-9.953,22.187-22.187,22.187s-22.187-9.953-22.187-22.187V88.747c0-1.884-1.529-3.413-3.413-3.413    s-3.413,1.529-3.413,3.413v18.773c0,15.998,13.015,29.013,29.013,29.013s29.013-13.015,29.013-29.013V88.747    C129.705,86.863,128.176,85.333,126.292,85.333z"/> </g> </g> <g> </g> <g> </g> <g> </g> <g> </g> <g> </g> <g> </g> <g> </g> <g> </g> <g> </g> <g> </g> <g> </g> <g> </g> <g> </g> <g> </g> <g> </g> </svg><p>Không có sản phẩm nào trong giỏ hàng của bạn</p></div>');
			$countItem.html('0');
			$('.colrightvat').addClass('d-none');
			//$('.cartbar-mobile').attr('data-count-pr', '0');
			$('.formVAT').addClass('d-none');
			$('.colcartleft').addClass('col-lg-12');
			cartCallback(cart);
			return;
		}

		// Handlebars.js cart layout
		var wW = $(window).width(),
			items = [],
			item = {},
			data = {},
			//sourceSideCart = $("#SideCartTemplate").html(), 
			sourceCartTemp = $("#CartTemplate").html(),
			sourceCartMobileTemp = $("#CartMobileTemplate").html(), 
			sourceCartHeaderTemp = $("#CartHeaderTemplate").html(), 
			//sourceCartPopTemp = $("#CartPopupTemplate").html(), 
			//templateSideCart = Handlebars.compile(sourceSideCart), 
			templateCartPage = Handlebars.compile(sourceCartTemp),
			//templateCartPop = Handlebars.compile(sourceCartPopTemp), 
			templateCartMobile = Handlebars.compile(sourceCartMobileTemp),
			templateCartHeader = Handlebars.compile(sourceCartHeaderTemp); 

		// Add each item to our handlebars.js data
		$.each(cart.items, function(index, cartItem) {
			console.log(cartItem);
			// lấy ảnh check xem có ảnh không
			var prodImg = Bizweb.resizeImage(cartItem.image, 'compact');
			if(prodImg=="null" || prodImg =='' || prodImg ==null){
				prodImg = 'https://bizweb.dktcdn.net/thumb/compact/assets/themes_support/noimage.gif';
			}

			// lấy properties cart
			if (cartItem.properties !== null) {
				$.each(cartItem.properties, function(key, value) {
					if (key.charAt(0) === '_' || !value) {
						delete cartItem.properties[key];
					}
				});
			}

			var unitPrice = null;
			if (cartItem.unit_price_measurement) {
				unitPrice = {
					addRefererenceValue:
					cartItem.unit_price_measurement.reference_value !== 1,
					price: Bizweb.formatMoney(
						cartItem.unit_price, settings.moneyFormat
					),
					reference_value: cartItem.unit_price_measurement.reference_value,
					reference_unit: cartItem.unit_price_measurement.reference_unit
				};
			}
			//console.log(cartItem, 'hdhdh');
			// Create item's data object and add to 'items' array
			// check variant title có không
			var variant_title = cartItem.variant_title;
			if (variant_title === 'Default Title')
				variant_title = '';
			// gán giá trị cho từng key của template
			//console.log(cartItem);
			item = {
				key: cartItem.key,
				line: index + 1, // Bizweb uses a 1+ index in the API
				url: cartItem.url,
				key: cartItem.key,
				img: prodImg,
				name: cartItem.title,
				variation: variant_title,
				sellingPlanAllocation: cartItem.selling_plan_allocation,
				properties: cartItem.properties,
				itemAdd: cartItem.quantity + 1,
				itemMinus: cartItem.quantity - 1,
				itemQty: cartItem.quantity,
				price: Bizweb.formatMoney(cartItem.price, settings.moneyFormat),
				vendor: cartItem.vendor,
				unitPrice: unitPrice,
				linePrice: Bizweb.formatMoney(cartItem.line_price, settings.moneyFormat),
				originalLinePrice: Bizweb.formatMoney(cartItem.original_line_price, settings.moneyFormat)
			};

			items.push(item);
		});

		// Gather all cart data and add to DOM
		// Xuất items, ghi chú, tổng giá
		data = {
			items: items,
			note: cart.note,
			totalPrice: Bizweb.formatMoney(cart.total_price, settings.moneyFormat)
		}

		if (wW < 1199) {
			$cartContainerMobile.append(templateCartMobile(data));  // chèn line item vào template cart mobile
		}
		if (wW > 992) {
			$cartContainerHeader.append(templateCartHeader(data));  // chèn line item vào template cart header
		}
		if (wW > 1200) {
			$cartContainerPage.append(templateCartPage(data));  // chèn line item vào template cart page
			//$cartPopup.append(templateCartPop(data));  // chèn line item vào template cart header
			//$cartContainerSidebar.append(templateSideCart(data)); // chèn line item vào template cart sidebar
		}
		$countItem.html(cart.item_count);  // Đếm số lượng sp đang có trong giỏ hàng
		$('.cartbar-mobile').attr('data-count-pr', cart.item_count);

		$('.CartHeaderContainer .ajaxcart__inner').scrollTop(itemListScrollTop);
		$('.cartPopupContainer .ajaxcart__inner').scrollTop(itemPopupScrollTop);
		$('.CartSideContainer .ajaxcart__inner').scrollTop(itemSideCartScrollTop);
		cartCallback(cart);
	};
	cartCallback = function(cart) {
		$cartContainer.trigger('ajaxCart', cart);
	};

	adjustCart = function () {
		// Delegate all events because elements reload with the cart

		// Thêm giảm số lượng
		$cartContainer.on('click', '.items-count', function() {
			if (isUpdating) return;


			var $el = $(this),
				line = $el.data('line'),
				$qtySelector = $el.siblings('.number-sidebar'),
				qty = parseInt($qtySelector.val().replace(/\D/g, ''));

			var qty = validateQty(qty);

			// Add or subtract from the current quantity
			if ($el.hasClass('ajaxcart__qty--plus')) {
				qty += 1;
			} else {
				qty -= 1;
				if (qty <= 0) qty = 0;
			}

			// If it has a data-line, update the cart.
			// Otherwise, just update the input's number
			if (line) {
				updateQuantity(line, qty);
			} else {
				$qtySelector.val(qty);
			}
		});

		// Update quantity based on input on change
		$cartContainer.on('change', '.number-sidebar', function() {
			if (isUpdating) return;

			var $el = $(this),
				line = $el.data('line'),
				qty = parseInt($el.val().replace(/\D/g, ''));

			var qty = validateQty(qty);

			// If it has a data-line, update the cart
			if (line) {
				updateQuantity(line, qty);
			}
		});

		$cartContainer.on('click', '.remove-item-cart', function(evt) {
			var $el = $(this),
				line = $el.data('line'),
				qty = 0;
			if(line) {
				updateQuantity(line, qty);
			}
		});

		$cartContainer.on('focus', '.number-sidebar', function(evt) {
			var $el = $(evt.target);
			$el[0].setSelectionRange(0, $el[0].value.length);
		});

		// Prevent cart from being submitted while quantities are changing
		$cartContainer.on('submit', 'form.ajaxcart', function(evt) {
			if (isUpdating) {
				evt.preventDefault();
			}
		});

		// Highlight the text when focused
		$cartContainer.on('focus', '.items-count', function() {
			var $el = $(this);
			setTimeout(function() {
				$el.select();
			}, 50);
		});

		function updateQuantity(line, qty) {
			isUpdating = true;

			// Add activity classes when changing cart quantities
			var $product = $('.ajaxcart__product[data-line="' + line + '"]').addClass('is-loading');

			if (qty === 0) {
				$product.parent().addClass('is-removed');
			}

			// Slight delay to make sure removed animation is done
			setTimeout(function() {
				Bizweb.changeItem(line, qty, adjustCartCallback);
			}, 10);

			$cartContainer.trigger('ajaxCart.updatingQty');
		}

		// Save note anytime it's changed
		$cartContainer.on('change', 'textarea[name="note"]', function() {
			var newNote = $(this).val();

			// Update the cart note in case they don't click update/checkout
			Bizweb.updateCartNote(newNote, function(cart) {});
		});
	};

	adjustCartCallback = function (cart) {
		// Update quantity and price
		updateCountPrice(cart);

		// Reprint cart on short timeout so you don't see the content being removed
		setTimeout(function() {
			isUpdating = false;

			Bizweb.getCart(buildCart);
		}, 150)
	};

	qtySelectors = function() {
		// Change number inputs to JS ones, similar to ajax cart but without API integration.
		// Make sure to add the existing name and id to the new input element
		var numInputs = $('input[type="number"][data-ajax-qty]');

		// Qty selector has a minimum of 1 on the product page
		// and 0 in the cart (determined on qty click)
		var qtyMin = 0;

		if (numInputs.length) {
			numInputs.each(function() {
				var $el = $(this),
					currentQty = $el.val(),
					inputName = $el.attr('name'),
					inputId = $el.attr('id');

				var itemAdd = currentQty + 1,
					itemMinus = currentQty - 1,
					itemQty = currentQty;

				var source   = $("#JsQty").html(),
					template = Handlebars.compile(source),
					data = {
						key: $el.data('id'),
						itemQty: itemQty,
						itemAdd: itemAdd,
						itemMinus: itemMinus,
						inputName: inputName,
						inputId: inputId
					};

				// Append new quantity selector then remove original
				$el.after(template(data)).remove();
			});

			// Setup listeners to add/subtract from the input
			$('.js-qty__adjust').on('click', function() {
				var $el = $(this),
					id = $el.data('id'),
					$qtySelector = $el.siblings('.js-qty__num'),
					qty = parseInt($qtySelector.val().replace(/\D/g, ''));

				var qty = validateQty(qty);
				qtyMin = $body.hasClass('template-product') ? 1 : qtyMin;

				// Add or subtract from the current quantity
				if ($el.hasClass('js-qty__adjust--plus')) {
					qty += 1;
				} else {
					qty -= 1;
					if (qty <= qtyMin) qty = qtyMin;
				}

				// Update the input's number
				$qtySelector.val(qty);
			});
		}
	};

	validateQty = function (qty) {
		if((parseFloat(qty) == parseInt(qty)) && !isNaN(qty)) {
			// We have a valid number!
		} else {
			// Not a number. Default to 1.
			qty = 1;
		}
		return qty;
	};

	module = {
		init: init,
		load: loadCart
	};

	return module;

}(ajaxCart || {}, jQuery));


$(window).on('load', function() {
	ajaxCart.init({
		moneyFormat: theme.settings.moneyFormat
	});
	ajaxCart.load();
});