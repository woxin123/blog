/**!
 *
 */

$(function () {

    // 根据用户名、页面索引、页面大小获取用户列表
    function getBlogsByName(pageIndex, pageSize) {
        $.ajax({
            url: "/u/"+  username  +"/blogs",
            contentType : 'application/json',
            data:{
                "async":true,
                "pageNo":pageIndex,
                "pageSize":pageSize
            },
            success: function(data){
                $("#mainContainer").html(data);
            },
            error : function() {
                toastr.error("error!");
            }
        });
    }

    // 分页
    $.tbpage("#mainContainer", function (pageIndex, pageSize) {
        getBlogsByName(pageIndex, pageSize);
        _pageSize = pageSize;
    });

});