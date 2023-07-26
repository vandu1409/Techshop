$(document).ready(function() {

    $(function () {

        apiProvince = (prodvince) => {
            let district;
    
            prodvince.forEach(element => {
                $('#province').append(`<option value="${element.name} " data-id="${element.code}">${element.name}</option>`)
              
            });
            $('#province').change(function () {
                // console.log("Sự kiện change trên trường 'tỉnh' đã được kích hoạt.");
    
                $('#district').html('<option value="-1" data-id="-1">Chọn quận/huyện</option>')
                $('#town').html('<option value = "-1" data-id="-1">  Chọn phường/xã </option>')
                let value = $(this).find(':selected').attr('data-id');
              
                console.log(value);
               

                if(!value){
                    console.log(prodvince);
                    if(prodvince){
                     
                        console.log($(this).val())
                        let selectedProvince = prodvince.find(item => item.name.trim() == $(this).val().trim());
                      
                        value = selectedProvince ? selectedProvince.code : -1;
                    }
                    
                }
                // console.log("Sự kiện change trên trường 'dssss' đã được kích hoạt.");
    
                console.log(value)
                $.each(prodvince, function (index, element) {
                    console.log('district');
                    if (element.code == value) {
                        district = element.districts;   
                        $('#district').empty()
                        $.each(element.districts, function (index, element1) {
                        
                            $('#district').append(`<option value="${element1.name}" data-id="${element1.code}">${element1.name}</option>`)
                        })
    
                    }
                })
            });
            $('#district').change(function () {
                $('#town').html('<option value = "-1" data-id="-1"> Chọn phường/xã </option>')
                let value = $(this).find(':selected').attr('data-id');

             console.log($("#province").val().trim()+' này là tỉnh')
                district =  prodvince.find(item => item.name.trim() == $("#province").val().trim()).districts;

                if(!value || value == -1){
                    console.log("đã bậosf")
                    console.log(district)
                    if(district){
                        console.log("đã bậosf")
                  
                        let selectedDistrict = district.find(item => item.name === $(this).val());
                      
                        value = selectedDistrict ? selectedDistrict.code : -1;
                    }
                    
                }
                console.log(value);

                $.each(district, function (index, element) {
                    if (element.code == value) {
                        element.wards.forEach(element1 => {
                            $('#town').append(`<option value="${element1.name}">${element1.name}</option>`)
                        });
                    }
                })
            });
        }
        fetch('/site/js/data.json')
            .then(response => response.json())
            .then(data => {
                // console.log(data)
                apiProvince(data);
            });
    })
})