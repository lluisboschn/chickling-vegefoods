window.onload=function() {
    $.ajax({
        headers: {'Authorization': localStorage.getItem('token')},
        url: "http://localhost:8080/products",
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            for (k in data) {
                let image = "data:image/png;base64," + data[k].image;
                if(data[k].image==''){
                    image = "images/inProgess.jpg";
                }

                $("#allProducts").append(
                    "<div class='wholeProduct'>" +
                    "   <div id='textProduct'>" +
                    "       <div class='producte'+k style='height:265px'>\n "+

                    "               <div class='imageProduct'><img src=" + image + "></div>\n " +
                    "           <div id='centrat' style='height: 110px; /border: 3px solid green;/ text-align:center'>\n "+
                    "               <div style='color:black; display:inline-block; width:245px'>" + data[k].name + " </div>\n " +
                    /*margin: auto;*/
                "               <div style='display:inline-block; width:245px'>" + data[k].price + " € </div> \n " +
                "           </div>" +
                "       </div>" +
                "       <div ><button class ='subscriptionButton' id="+data[k].id+" value="+data[k].id+">" + 'Subscribe' + "</button></div>" +
                "   </div>" +
                "</div>");
                clickFunction(data[k].id, data, k);
            }

        },
        error: function() {
            // alert('Failed!');
            console.log("failed!"); },

    });

    function clickFunction (id, data, k) {


        if(localStorage.getItem('token')==""){
            alert("You are not logged in!");
            window.location.href="../login.html"
        }


        $("#"+id).click(function() {
            console.log("INSIDE with id: "+id);
            console.log(k);
            console.log(data[k]);
            addProd(data[k]);
        });
    }
    function addProd(varProduct) {
        var url = 'http://localhost:8080/prodSubscription';
        var data = {
            id: Math.floor(Math.random() * 100000),
            consumerMail: getEmailFromToken(),
            product: varProduct,
            amount: 1,
            price: varProduct.price,
            consumerId: '1'
        };

        console.log(data);

        $.ajax({
            headers: {'Authorization': localStorage.getItem('token')},
            type: 'POST',
            url: url,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            traditional: true,
            success: function (data) {
            },
            error: function() {
                // alert('Failed!');
                console.log("failed! POST");
            },
        });
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
//let token = decryptToken()
//let authorities = token.authorities;
function decryptToken() {
    let token = localStorage.getItem('token');

    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    let parsed = JSON.parse(jsonPayload);
    console.log("TOKEN: "+parsed);
    return parsed;

}
