document.getElementById("number").foreach(function(num){
            if(num != 0){
                var obj = num.parent().siblings().childNotes.value;
                $.post("/collect",{"obj":obj},function(data){alert(data)},"json");
            }
})
document.getElementById("number").foreach(function(num){
    if(num != 0){
        var obj = num.parent.siblings.childNotes;
        $.post("/collect",{"obj":obj},function(data){
            alert(data);
        },"json");
    }
});
document.getElementById.childNotes