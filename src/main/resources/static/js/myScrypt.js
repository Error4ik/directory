var basic_part_url = 'http://localhost:8080/directory';
var count = 0;

function createField() {
    count++;
    var element = document.getElementById("row" + (count - 1));
    var cloneElement = element.cloneNode(true);
    cloneElement.id = "row" + count;
    var inputs = cloneElement.getElementsByTagName("input");
    inputs[0].value = '';
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

function createModel(id) {
    $.ajax({
        url: basic_part_url + '/model/create',
        method: 'POST',
        data: {"id": id, "data": getFormDataCreateModel().toString()},
        success: function () {
            location.reload()
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function getFormDataCreateModel() {
    var returnArray = [];

    $.map($('#myForm').serializeArray(), function (n, i) {
        if (i === 0) {
            returnArray.push(n['value']);
        } else {
            returnArray.push(n['name']);
            returnArray.push(n['value']);
        }
    });
    return returnArray;
}

function getFormDataCreateDirectory() {
    var returnArray = [];

    $.map($('#create_directory').serializeArray(), function (n, i) {
        returnArray.push(n['value']);
    });
    return returnArray;
}

function createDirectory() {
    $.ajax({
        url: basic_part_url + '/directory/create',
        method: 'POST',
        data: {"data": getFormDataCreateDirectory().toString()},
        success: function () {
            location.reload()
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function addNewAttribute(id) {
    $.ajax({
        url: basic_part_url + '/attribute/add',
        method: "POST",
        data: {"id": id, "name": $("#attrName").val()},
        success: function (data) {
            location.reload();
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function updateAttribute(id) {
    $.ajax({
        url: basic_part_url + '/attribute/edit',
        method: "POST",
        data: {"id": id, "name": $("#updateName" + id).val()},
        success: function (data) {
            location.reload();
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function editModel(id) {
    $.ajax({
        url: basic_part_url + '/model/edit',
        method: 'POST',
        data: {"id": id, "name": $("#edit-model-name" + id).val()},
        success: function (data) {
            location.reload();
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function editDirectory(id) {
    $.ajax({
        url: basic_part_url + '/directory/update',
        method: 'POST',
        data: {"id": id, "name": $("#name").val(), "description": $("#description").val()},
        success: function (data) {
            location.reload()
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function editProperty(id) {
    $.ajax({
        url: basic_part_url + '/property/edit',
        method: "POST",
        data: {"id": id, "value": $("#edit-property-value" + id).val()},
        success: function (data) {
            location.reload()
        },
        error: function (e) {
            console.log("ERROR: ", e)
        }
    });
}

function deleteAttribute(attrId, dirId) {
    $.ajax({
        url: basic_part_url + '/attribute/delete',
        method: "POST",
        data: {"attrId": attrId, "dirId": dirId},
        success: function (data) {
            location.reload()
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function deleteDirectory(id) {
    $.ajax({
        url: basic_part_url + '/directory/delete',
        method: "POST",
        data: {"id": id},
        success: function (data) {
            location.reload()
        },
        error: function (e) {
            console.log("ERROR: ", e)
        }
    });
}

