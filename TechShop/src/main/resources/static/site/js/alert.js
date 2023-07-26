var MyNotification = {
  
  alertSucess: function(title, text) {
      new Notify({
        status: 'success',
        title: title,
        text: text,
        effect: 'slide',
        speed: 300,
        showIcon: true,
        showCloseButton: true,
        autoclose: true,
        autotimeout: 2000,
        gap: 20,
        distance: 20,
        type: 1,
        position: 'right top'
      });
    },
    alertError: function(title, text) {
      new Notify({
        status: 'error',
        title: title,
        text: text,
        effect: 'slide',
        speed: 300,
        showIcon: true,
        showCloseButton: true,
        autoclose: true,
        autotimeout: 2000,
        gap: 20,
        distance: 20,
        type: 1,
        position: 'right top'
      });
    }
  };
  

  