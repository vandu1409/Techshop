Bizweb.postLink = function (path, options) {
    if (options == null)
        options = {};

    var method = "post";
    if (options['method'] != null)
        method = options['method'];

    var params = {};
    if (options['parameters'] != null)
        params = options['parameters'];

    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);

    for (var key in params) {
        var hiddenField = document.createElement("input");
        hiddenField.setAttribute("type", "hidden");
        hiddenField.setAttribute("name", key);
        hiddenField.setAttribute("value", params[key]);
        form.appendChild(hiddenField);
    }

    document.body.appendChild(form);
    form.submit();
    document.body.removeChild(form);
};

Bizweb.setSelectorByValue = function (selector, value) {
    for (var i = -1, count = selector.options.length; ++i < count; i) {
        if (value == selector.options[i].value) {
            selector.selectedIndex = i;
            return i;
        }
    }
};

Bizweb.CountrySelector = function (countryElementId, options) {
    this.countryElement = document.getElementById(countryElementId);
    this.initCountry();
};
Bizweb.CountrySelector.prototype.initCountry = function () {
    if (this.countryElement != null) {
        var value = this.countryElement.getAttribute('data-default');
        Bizweb.setSelectorByValue(this.countryElement, value);
    }
};