function sort(data){
    if(data.value == 1) {
        sortByPrice();
    }
}

function sortByPrice(){
    var lis = document.getElementsByClassName("product___box");
    var lis_txt = [];
    for (var i =0; i<lis.length; i++){
        lis_txt[i] = lis[i].innerHTML;
    }

    function sortRule(i, ii) {
        var reg = /(?!<span class=\"item_price\">)\d+(?=<\/span>)/;
        var price_i = parseInt(i.match(reg));
        var price_ii = parseInt(ii.match(reg));
        if (price_i > price_ii) return 1;
        else if (price_i < price_ii) return -1;
        else return 0;
    }
    lis_txt.sort(sortRule);

    for (var i=0; i<lis_txt.length; i++){
        if ((i+1)%3==0) {lis_txt[i] = '<li class="product___box">'+lis_txt[i]+'</li>';}
        else {lis_txt[i] = '<li class="product___box">'+lis_txt[i]+'</li>';}
    }

    var txt = lis_txt.join('');

    document.getElementById("goods").innerHTML = txt;
}

