/**!
 * blogedit.html的页面脚本
 *
 */
// 严格模式
"use strict";
// DOM 加载完再执行
$(function () {

    var editor = editormd("my-editormd", {//注意1：这里的就是上面的DIV的id属性值
        width: "100%",
        height: 640,
        syncScrolling: "single",
        path: "/lib/",//注意2：你的路径
        theme : "dark",
        previewTheme : "dark",
        editorTheme : "pastel-on-dark",
        // markdown : md,
        codeFold : true,
        //syncScrolling : false,
        saveHTMLToTextarea : true,    // 保存 HTML 到 Textarea
        searchReplace : true,
        //watch : false,                // 关闭实时预览
        htmlDecode : "style,script,iframe|on*",            // 开启 HTML 标签解析，为了安全性，默认不开启
        //toolbar  : false,             //关闭工具栏
        //previewCodeHighlight : false, // 关闭预览 HTML 的代码块高亮，默认开启
        emoji : true,
        taskList : true,
        tocm            : true,         // Using [TOCM]
        tex : true,                   // 开启科学公式TeX语言支持，默认关闭
        flowChart : true,             // 开启流程图支持，默认关闭
        sequenceDiagram : true       // 开启时序/序列图支持，默认关闭,

    });

    $("#submitBlog").click(function () {
        console.info($("#title").val());
        var htmlContent = editor.getHTML();
        $.ajax({
            url: '/u/' + $(this).attr("userName") + '/blogs/edit',
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({
                "id": $('#blogId').val(),
                "title": $('#title').val(),
                "summary": $('#summary').val(),
                "tags": $('#catalogSelect').val(),
                "content": $('#my-editormd-markdown-doc').val(),
                "htmlContent": htmlContent,
                "catalog": {"id": $('#catalogSelect').val()}
            }),
            // beforeSend: function(request) {
            //     request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token
            // },
            success: function (data) {
                if (data.success) {
                    // 成功后，重定向
                    // console.info(data.success + " " + data.data);
                    window.location = data.data;
                } else {
                    toastr.error("error!" + data.message);
                }

            },
            error: function () {
                toastr.error("error!");
            }
        });
    });

    // 初始化下拉
    $('.form-control-chosen').chosen();


    // 初始化标签
    $('.form-control-tag').tagsInput({
        'defaultText': '输入标签'

    });

    $("#uploadImage").click(function () {
        $.ajax({
            url: 'http://localhost:8081/upload',
            type: 'POST',
            cache: false,
            data: new FormData($('#uploadformid')[0]),
            processData: false,
            contentType: false,
            success: function (data) {
                var mdcontent = $("#md").val();
                $("#md").val(mdcontent + "\n![](" + data + ") \n");

            }
        }).done(function (res) {
            $('#file').val('');
        }).fail(function (res) {
        });
    })

    // // 发布博客
    // $("#submitBlog").click(function () {
    //
    //     // 获取 CSRF Token
    //     // var csrfToken = $("meta[name='_csrf']").attr("content");
    //     // var csrfHeader = $("meta[name='_csrf_header']").attr("content");
    //     console.info($('#title').val());
    //     // $.ajax({
    //     //     url: '/u/' + $(this).attr("userName") + '/blogs/edit',
    //     //     type: 'POST',
    //     //     contentType: "application/json; charset=utf-8",
    //     //     data: JSON.stringify({
    //     //         "id": $('#blogId').val(),
    //     //         "title": $('#title').val(),
    //     //         "content": $('#my-editormd-markdown-doc').val(),
    //     //         "contentHtml": $('#my-editormd-html-code').val(),
    //     //         "catalog": "1"
    //     //     }),
    //     //     // beforeSend: function(request) {
    //     //     //     request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token
    //     //     // },
    //     //     success: function (data) {
    //     //         if (data.success) {
    //     //             // 成功后，重定向
    //     //             // console.info(data.success + " " + data.data);
    //     //             window.location = data.data;
    //     //         } else {
    //     //             toastr.error("error!" + data.message);
    //     //         }
    //     //
    //     //     },
    //     //     error: function () {
    //     //         toastr.error("error!");
    //     //     }
    //     });
    // })


});