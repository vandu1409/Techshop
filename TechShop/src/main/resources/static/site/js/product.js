
$(".counpon .copy").click(function () {
  const copyText = "Sao chép mã";
  const copiedText = "Đã sao chép";
  const coupon = $(this).data("code");
  const _this = $(this);
  _this.html(`<span>${copiedText}</span>`);
  _this.addClass("disabled");
  setTimeout(function () {
    _this.html(`<span>${copyText}</span>`);
    _this.removeClass("disabled");
  }, 3000);
  navigator.clipboard.writeText(coupon);
});
$(".tabs-title li").on("click", function (e) {
  $(".tabs-title li, .tab-content").removeClass("current");
  $(this).addClass("current");
  $("#" + $(this).data("tab") + "").addClass("current");

  var active = $(this);
  var left = active.position().left;
  var currScroll = $(this).parent(".tabs-title").scrollLeft();
  var contWidth = $(this).parent(".tabs-title").width() / 2;
  var activeOuterWidth = active.outerWidth() / 2;
  left = left + currScroll - contWidth + activeOuterWidth;

  $(this).parent(".tabs-title").animate(
    {
      scrollLeft: left,
    },
    "slow"
  );
});



setTimeout(function () {
  var ch = $(".product_getcontent").height(),
    smore = $(".show-more");
  if (ch > 550) {
    $(".ba-text-fpt").addClass("has-height");
    smore.removeClass("d-none");
  }
}, 200);

$(".btn--view-more").on("click", function (e) {
  e.preventDefault();
  var $this = $(this);
  $this
    .parents(".tab-content")
    .find(".product_getcontent")
    .toggleClass("expanded");
  $(this).toggleClass("active");
  if (!$(this).hasClass("active")) {
    $("html, body").animate(
      { scrollTop: $(".product_getcontent").offset().top - 110 },
      "slow"
    );
  }
  return false;
});

var galleryThumbs = new Swiper(".product-image-detail .gallery-thumbs", {
  spaceBetween: 10,
  slidesPerView: 5,
  freeMode: true,
  lazy: true,
  watchSlidesVisibility: true,
  watchSlidesProgress: true,
  slideToClickedSlide: true,
  breakpoints: {
    300: {
      slidesPerView: 4,
      spaceBetween: 10,
    },
    500: {
      slidesPerView: 4,
      spaceBetween: 10,
    },
    640: {
      slidesPerView: 5,
      spaceBetween: 10,
    },
    768: {
      slidesPerView: 6,
      spaceBetween: 10,
    },
    1024: {
      slidesPerView: 5,
      spaceBetween: 10,
    },
  },
});
var galleryTop = new Swiper(
  ".product-image-detail .gallery-top .swiper-container",
  {
    spaceBetween: 10,
    lazy: true,
    freeMode: true,
    thumbs: {
      swiper: galleryThumbs,
    },
    navigation: {
      nextEl: ".product-image-detail .swiper-button-next",
      prevEl: ".product-image-detail .swiper-button-prev",
    },
  }
);

var slideRelated = new Swiper(".section_product_related .swiper-container", {
  slidesPerView: 5,
  loop: false,
  grabCursor: true,
  spaceBetween: 20,
  roundLengths: true,
  slideToClickedSlide: false,
  navigation: {
    nextEl: ".section_product_related .swiper-button-next",
    prevEl: ".section_product_related .swiper-button-prev",
  },
  autoplay: false,
  breakpoints: {
    300: {
      slidesPerView: 2,
      spaceBetween: 10,
      slidesPerColumn: 2,
      slidesPerColumnFill: "row",
    },
    500: {
      slidesPerView: 2,
    },
    640: {
      slidesPerView: 3,
    },
    768: {
      slidesPerView: 3,
      slidesPerColumn: 1,
    },
    992: {
      slidesPerView: 4,
    },
    1200: {
      slidesPerView: 5,
    },
  },
});

var formProduct = $(".form-product");

var ww = $(window).width();
/*For recent product*/

var variantsize = false;
var alias = "dien-thoai-di-dong-apple-iphone-13-hang-chinh-hang";

var productOptionsSize = 1;
/*end*/

function validate(evt) {
  var theEvent = evt || window.event;
  var key = theEvent.keyCode || theEvent.which;
  key = String.fromCharCode(key);
  var regex = /[0-9]|\./;
  if (!regex.test(key)) {
    theEvent.returnValue = false;
    if (theEvent.preventDefault) theEvent.preventDefault();
  }
}

var selectCallback = function (variant, selector) {
  if (variant) {
    var form = jQuery("#" + selector.domIdPrefix).closest("form");
    for (var i = 0, length = variant.options.length; i < length; i++) {
      var radioButton = form.find(
        '.swatch[data-option-index="' +
          i +
          '"] :radio[value="' +
          variant.options[i] +
          '"]'
      );
      if (radioButton.length) {
        radioButton.get(0).checked = true;
      }
    }
  }
  var addToCart = jQuery(".form-product .btn-cart"),
    btnNow = jQuery(".form-product .btn-buy-now"),
    form = jQuery(".form-product .from-action-addcart"),
    productPrice = jQuery(".details-pro .special-price .product-price"),
    stock = jQuery(".inventory_quantity .a-stock"),
    comparePrice = jQuery(".details-pro .old-price .product-price-old"),
    comparePriceText = jQuery(".details-pro .old-price"),
    savePrice = jQuery(".details-pro .save-price .product-price-save"),
    savePriceText = jQuery(".details-pro .save-price"),
    qtyBtn = jQuery(".form-product .custom-btn-number"),
    BtnSold = jQuery(".form-product .btn-mua"),
    Unit = jQuery(".donvitinh"),
    product_sku = jQuery(".variant-sku");
  if (variant && variant.sku != "" && variant.sku != null) {
    product_sku.html("Mã SP: <strong>" + variant.sku + " </strong>");
  } else {
    product_sku.html("Mã SP: <strong>Cập nhật...</strong>");
  }

  if (variant && variant.weight != "0") {
    $(".dvt").show();
    var unitformart = variant.weight.unit;
    if (unitformart === "g") {
      var unitformat = "Gram";
    } else {
      var unitformat = "Kg";
    }
    Unit.html(variant.weight + "" + unitformat + "");
  } else {
    $(".dvt").hide();
  }

  if (variant && variant.available) {
    if (variant.inventory_management == "bizweb") {
      if (variant.inventory_policy == "continue") {
        stock.html('<span class="a-stock a-stock-out">Còn hàng</span>');
      } else {
        if (variant.inventory_quantity != 0) {
          stock.html(
            '<span class="a-stock">Còn ' +
              variant.inventory_quantity +
              " sản phẩm</span>"
          );
        } else {
          stock.html('<span class="a-stock a-stock-out">Hết hàng</span>');
        }
      }
    } else {
      stock.html('<span class="a-stock">Còn hàng</span>');
    }
    form.removeClass("d-none");
    btnNow.removeAttr("disabled").removeClass("d-none");
    addToCart.html("Cho vào giỏ").removeAttr("disabled");
    BtnSold.removeClass("btnsold");
    qtyBtn.removeClass("d-none");
    if (variant.price == 0) {
      productPrice.html("Liên hệ");
      comparePrice.hide();
      savePrice.hide();
      comparePriceText.hide();
      savePriceText.hide();
      form.addClass("d-none");
    } else {
      form.removeClass("d-none");
      productPrice.html(
        Bizweb.formatMoney(
          variant.price,
          "{{amount_no_decimals_with_comma_separator}}₫"
        )
      );
      addToCart.html("Cho vào giỏ");
      if (variant.compare_at_price > variant.price) {
        comparePrice
          .html(
            Bizweb.formatMoney(
              variant.compare_at_price,
              "{{amount_no_decimals_with_comma_separator}}₫"
            )
          )
          .show();
        savePrice
          .html(
            Bizweb.formatMoney(
              variant.compare_at_price - variant.price,
              "{{amount_no_decimals_with_comma_separator}}₫"
            ) + " <span>so với thị trường</span>"
          )
          .show();
        comparePriceText.show();
        savePriceText.show();
      } else {
        comparePrice.hide();
        savePrice.hide();
        comparePriceText.hide();
        savePriceText.hide();
      }
    }
  } else {
    btnNow.attr("disabled", "disabled").addClass("d-none");
    stock.html('<span class="a-stock a-stock-out">Hết hàng</span>');
    addToCart.html("Hết hàng").attr("disabled", "disabled");
    BtnSold.addClass("btnsold");
    qtyBtn.addClass("d-none");
    if (variant) {
      if (variant.price != 0) {
        form.removeClass("d-none");
        productPrice.html(
          Bizweb.formatMoney(
            variant.price,
            "{{amount_no_decimals_with_comma_separator}}₫"
          )
        );
        if (variant.compare_at_price > variant.price) {
          comparePrice
            .html(
              Bizweb.formatMoney(
                variant.compare_at_price,
                "{{amount_no_decimals_with_comma_separator}}₫"
              )
            )
            .show();
          savePrice
            .html(
              Bizweb.formatMoney(
                variant.compare_at_price - variant.price,
                "{{amount_no_decimals_with_comma_separator}}₫"
              ) + " <span>so với thị trường</span>"
            )
            .show();
          comparePriceText.show();
          savePriceText.show();
        } else {
          comparePrice.hide();
          savePrice.hide();
          comparePriceText.hide();
          savePriceText.hide();
        }
      } else {
        productPrice.html("Liên hệ");
        comparePrice.hide();
        savePrice.hide();
        comparePriceText.hide();
        savePriceText.hide();
        form.addClass("d-none");
      }
    } else {
      productPrice.html("Liên hệ");
      comparePrice.hide();
      savePrice.hide();
      comparePriceText.hide();
      savePriceText.hide();
      form.addClass("d-none");
    }
  }
  /*begin variant image*/
  if (variant && variant.image) {
    var originalImage = jQuery(".product-image-detail .gallery-thumbs img");
    var newImage = variant.image;
    var element = originalImage[0];
    Bizweb.Image.switchImage(
      newImage,
      element,
      function (newImageSizedSrc, newImage, element) {
        $(".product-image-detail .gallery-thumbs .swiper-slide").each(
          function () {
            var $this = $(this);
            var imgThis = $this.find("img").attr("data-image");
            var image_1 = newImageSizedSrc.split("?")[0];
            var image_2 = imgThis.split("?")[0];
            var image_main_1 = image_1.split("products")[1];
            if (image_2.includes(image_main_1)) {
              var pst = $this.attr("data-hash");
              galleryTop.slideTo(pst, 1000, false);
            }
          }
        );
      }
    );
  }
  /*end of variant image*/
};
/*
setTimeout(function(){
    $('.gallery-thumbs .swiper-slide').hover(function(){
        var number = $(this).data('hash');
        galleryTop.slideTo(number, 1000,false);
    })
})
*/




