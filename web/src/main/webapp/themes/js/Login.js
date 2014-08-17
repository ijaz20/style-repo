$(document).ready(function() {

    $("#showLogin").on("click",function(){
        $("#showLoginDiv").removeClass("hide");
        $("#showSingupDiv").addClass("hide");
    });

    $("#showSingup").on("click",function(){
        $("#showSingupDiv").removeClass("hide");
        $("#showLoginDiv").addClass("hide");
    });

    $("#loginLink").on("click",function(){
        $("#showLoginDiv").removeClass("hide");
        $("#showSingupDiv").addClass("hide");
    });
});