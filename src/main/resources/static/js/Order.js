window.onload=function() {
    if(localStorage.getItem('token')==""){
        alert("You are not logged in!");
        window.location.href="../login.html"
    }

    table();

    $(".button").click(function () {
        var idOrder = $("#orderId").val();
        var newAmount = $("#amount").val();
        var url = 'http://localhost:8080/orders/' + idOrder.toString() + '/' + newAmount.toString();



        let data = {"idOrder":idOrder,"amount":newAmount}

        $.ajax({
            headers: {'Authorization': localStorage.getItem('token')},
            type: 'PUT',
            url: url,
            contentType: 'application/json',
            data: JSON.stringify(data), // access in body
        }).done(function () {
            console.log('SUCCESS');
        }).fail(function (msg) {
            console.log('FAIL');
        });


   /*     $.ajax({
            headers: {'Authorization': localStorage.getItem('token')},
            url: url,
            type: 'PUT',
            dataType: 'json',
            success: function (data) {

            },
            error: function () {
                alert('Failed!');
            },

        });*/
        table();
    });

    function table() {
        console.log(getEmailFromToken());
        $.ajax({
            headers: {'Authorization': localStorage.getItem('token')},
            // url: "http://localhost:8080/consumers/"+email+"/prodSubscription",
            url: 'http://localhost:8080/consumers/' + getEmailFromToken() + '/orders',
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                $(".tbody").empty();
                for (k in data) {
                    let image = "data:image/png;base64," + data[k].productId.image;
                    if (data[k].productId.image == "") {
                        image = "images/inProgess.jpg";
                    }

                    $(".tbody").append(
                        "<tr>\n " +
                        "   <td> <div>" + data[k].id + " </div> </td>\n " +
                        "   <td> <div ><img src=" + image + "></div> </td>\n " +
                        "   <td> <div>" + data[k].productId.name + " </div> </td>\n " +
                        "   <td> <div>" + data[k].productId.price + " </div> </td>\n " +
                        "   <td> <div class='amount" + k + "'>" + data[k].amount.toString() + " </div> </td>\n " +
                        "   <td> <div>" + data[k].status + " </div> </td>\n " +
                        "   <td> <div>" + data[k].dat_deliver + " </div> </td>\n " +
                        "   <td> <div>" + data[k].close_date + " </div> </td>\n " +
                        "<td class='modifica'> <button class='b" + k + "'>modificar</button> </td>" +
                        "</tr>");
                }


            },
            error: function () {
                alert('Failed!');
            },

        });
        console.log("OUT!");
    }
}
function getEmailFromToken() {

    if (localStorage.length <= 0) {
        alert("You are not logged in, redirecting...")
        window.location.href = "/login.html"
    }


    let token = localStorage.getItem('token');

    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    let parsed = JSON.parse(jsonPayload);


    let email = parsed.sub;
    email = email.toString();
    return email;
};

function decryptToken() {
    let token = localStorage.getItem('token');

    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    let parsed = JSON.parse(jsonPayload);


    return parsed;

}