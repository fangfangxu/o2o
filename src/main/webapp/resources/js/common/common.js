function changeVerifyCode(img) {
    // img.src="../Kaptcha?"+Math.floor(Math.random()*100)  ;
    img.src = "../Kaptcha?";

}

//http://localhost:8080/shopadmin/getshopbyid?shopId=87   在地址栏敲入以上：那么就会取出来shopId
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return decodeURIComponent(r[2]);
    }
    return '';
}