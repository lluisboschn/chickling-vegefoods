window.onload=function(){

    if(localStorage.getItem('token')==""){
        alert("You are not logged in!");
        window.location.href="../login.html"
    }

    // let email =
    $.ajax({
        headers: {'Authorization': localStorage.getItem('token')},
        url: 'http://localhost:8080/consumers/'+getEmailFromToken()+'/prodSubscription',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            $(".tbody").empty();
            console.log(data);
            for (k in data) {
                console.log(data[k].id);
                let image = "data:image/png;base64," + data[k].product.image;
                if(data[k].product.image==''){
                    image = "images/inProgess.jpg";
                }

                $(".tbody").append(
                    "<tr>\n " +
                    "   <td> <div>" + data[k].id + " </div> </td>\n " +
                    "   <td> <div ><img src=" + image + "></div> </td>\n " +
                    "   <td> <div>" + data[k].product.name + " </div> </td>\n " +
                    "   <td> <div>" + data[k].product.price + " </div> </td>\n " +
                    "   <td> <div class='amount" + k + "'>" + data[k].amount.toString() + " </div> </td>\n " +
                    "</tr>");
            }
        },
        error: function() {
           // alert('Failed!');
            console.log("failed!");
            },

    });
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
