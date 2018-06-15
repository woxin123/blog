/*!
 * blog.html页面脚本
 *
 */
"use strict";

$(function() {
   $.catalog("#catalog", ".post-content");

   // 删除博客
    $(".blog-content-container").on("click", ".blog-delete-blog", function () {

       $.ajax({
           url:blogUrl,
           type: 'DELETE',
           success: function (data) {
               if (data.success) {
                  // 成功之后，重定向
                   window.location = data.data;
               } else {
                  toastr.error("error!");
               }
           }
       });
    });
});