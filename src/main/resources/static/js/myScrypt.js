var basic_part_url = 'http://localhost:8080/directory';
var count = 0;

function createField() {
    count++;
    var element = document.getElementById("row" + (count - 1));
    var cloneElement = element.cloneNode(true);
    cloneElement.id = "row" + count;
    var inputs = cloneElement.getElementsByTagName("input");
    inputs[0].value = '';
    inputs[1].value = '';
    element.parentNode.appendChild(cloneElement);
    return false;
}

function deleteField() {
    if (count !== 0) {
        var element = document.getElementById("row" + count);
        var parent = element.parentNode;
        parent.removeChild(element);
        count--;
    }
    return false;
}

function createModel() {
    $.ajax({
        url: basic_part_url + '/model/create',
        method: 'POST',
        data: {"data": getFormData().toString()},
        success: function () {
            location.reload()
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function getFormData() {
    var returnArray = [];

    $.map($('#myForm').serializeArray(), function (n, i) {
        returnArray.push(n['value']);
    });
    return returnArray;
}
