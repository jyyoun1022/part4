var actionForm =$("form");
//삭제버튼
$(".removeBtn").click(function(){

    actionForm.attr("action","/guestbook/remove").attr("method","post").submit();

});

//수정버튼
$(".modifyBtn").click(function(){
console.log("수정버튼클릭");
    if(!confirm("수정하시겠습니까?")){
        return;
    }
     actionForm.attr("action","/guestbook/modify").attr("method","post").submit();
        console.log("여기까지오니?")



});

//리스트 돌아가기
$(".listBtn").click(function(){

    var pageInfo = $("input[name='page']");

    actionForm.empty();
    actionForm.append(pageInfo);
    // console.log(actionForm.html());
    actionForm.attr("action","/guestbook/list").attr("method","get").submit();

})



