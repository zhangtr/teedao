var father=[];
var child=[];
var treeholder=$('.page-sidebar-menu');
var Menu = function () {
    return {
        init: function () {
            var menujson=eval(menus);
            for(var i=0;i<menujson.length;i++){
                if(menujson[i].parentId == 0){//parentId=0 根目录
                    father.push(menujson[i]);
                }else{
                    child.push(menujson[i]);
                }
            }
            for(var i=0;i<father.length;i++){
                if(father[i].url!="#"){
                    treeholder.append("<li class=\"nav-item item-link\"><a href=\"javascript:goPage('"+father[i].url+"');\" class=\"nav-link\"><i class=\"fa "+father[i].icon+"\"></i><span class=\"title\">"+father[i].name+"</span></a></li>");
                }else{
                    treeholder.append("<li class=\"nav-item\" id=\""+father[i].id+"-0"+"\"><a class=\"nav-link nav-toggle\" href=\"javascript:;\"><i class=\"fa "+father[i].icon+"\"></i><span class=\"title\">"+father[i].name+"</span><span class=\"arrow\"></span></a></li>");
                    iter(father[i].id);
                }
            }
            $("li.item-link").click(function () {
                $("li.nav-item").each(function(){
                    if ($(this).is(".active")) {
                        $(this).removeClass("active open");
                        $(this).parent().parent().removeClass("open active");
                        $(this).parent().parent().find("span.selected").remove();
                    }
                });
                $(this).addClass("active open");
                $(this).parent().parent().addClass("active open");
                $(this).children().append("<span class=\"selected\"></span>");
            });
        }
    };
}();

function iter(id){
    $("#"+id+"-0").append("<ul class=\"sub-menu\" id=\""+id+"-1"+"\"></ul>");
    for(var i=0;i<child.length;i++){
        if(child[i].parentId==id){
            if (child[i].url!='#') {
                $("#"+id+"-1").append("<li class=\"nav-item item-link\"><a href=\"javascript:goPage('"+child[i].url+"');\" class=\"nav-link\"><i class=\"fa "+child[i].icon+"\"></i><span class=\"title\">"+child[i].name+"</span></a></li>");
            }else{
                $("#"+id+"-1").append("<li class=\"nav-item\" id=\""+child[i].id+"-0"+"\"><a class=\"nav-link\" href=\"javascript:;\"><i class=\"fa "+child[i].icon+"\"></i><span class=\"title\">"+child[i].name+"</span><span class=\"arrow\"></span></a></li>");
                iter(child[i].id);
            }
        }
    }
}
function goPage(leaf_url) {
    document.getElementById("centerFrame").src =leaf_url;
}

jQuery(document).ready(function() {
    Menu.init();
});