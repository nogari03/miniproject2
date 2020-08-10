$(document).ready(function(){
    $( "#search" ).on('click',function(e) {
        e.preventDefault();

        let data = { command: 'search',
                     id: $('#input').val()};

        $.ajax({
            type: "post",
            url: "/userInfo",
            data: data,
            dataType: 'json',
            success: function(data) {

                html="";
                html += '<tr>';
                html += '<td>'+'고객ID'+'</td>';
                html += '<td>'+'<input type="text" id="id" value='+data[0].id+'>'+'</td>';
                html += '</tr>';
                html += '<tr>';
                html += '<td>'+'고객PW'+'</td>';
                html += '<td>'+'<input type="text" id="pwd" value='+data[0].pwd+'>'+'</td>';
                html += '</tr>';
                html += '<tr>';
                html += '<td>'+'고객이름'+'</td>';
                html += '<td>'+'<input type="text" id="name" value='+data[0].name+'>'+'</td>';
                html += '</tr>';
                html += '<input type="hidden" id="user_num" value='+data[0].user_num+'>';

                $("#tBody").empty();
                $("#tBody").append(html);

            },
            error: function(err) {
                console.log("error!");
            }
        });
    });

    $("#add").on('click',function (e) {
        e.preventDefault();

        $("#tBody").empty();

        html="";
        html += '<tr>';
        html += '<td>'+'고객ID'+'</td>';
        html += '<td>'+'<input type="text" id="id">'+'</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td>'+'고객PW'+'</td>';
        html += '<td>'+'<input type="text" id="pwd">'+'</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td>'+'고객이름'+'</td>';
        html += '<td>'+'<input type="text" id="name">'+'</td>';
        html += '</tr>';

        $("#tBody").append(html);
    });

    $('#save').on('click',function (e) {
        e.preventDefault();

        $user_num = $('#user_num').val();
        var command;

        if($user_num==null){
            command='insert';
        }else{
            command='update';
        }

        var data = {
            command: command,
            user_num: $user_num,
            id: $('#id').val(),
            pwd: $('#pwd').val(),
            name: $('#name').val()
        };


        $.ajax({
            type: "post",
            url: "/userInfo",
            data: data,
            dataType: 'json',
            success: function() {
                alert('완료되었습니다');
            },
            error: function(err) {
                console.log("error!");
            }
        });
    })
    $('#prev').on('click',function (e) {
        e.preventDefault();

        let data = { command: 'prev',
                     user_num: $('#user_num').val()};

        $.ajax({
            type: "post",
            url: "/userInfo",
            data: data,
            dataType: 'json',
            success: function(data) {

                html="";
                html += '<tr>';
                html += '<td>'+'고객ID'+'</td>';
                html += '<td>'+'<input type="text" id="id" value='+data[0].id+'>'+'</td>';
                html += '</tr>';
                html += '<tr>';
                html += '<td>'+'고객PW'+'</td>';
                html += '<td>'+'<input type="text" id="pwd" value='+data[0].pwd+'>'+'</td>';
                html += '</tr>';
                html += '<tr>';
                html += '<td>'+'고객이름'+'</td>';
                html += '<td>'+'<input type="text" id="name" value='+data[0].name+'>'+'</td>';
                html += '</tr>';
                html += '<input type="hidden" id="user_num" value='+data[0].user_num+'>';

                $("#tBody").empty();
                $("#tBody").append(html);
            },
            error: function(err) {
                console.log("error!");
            }
        });
    });

    $('#next').on('click',function (e) {
        e.preventDefault();

        let data = { command: 'next',
                     user_num: $('#user_num').val()};

        $.ajax({
            type: "post",
            url: "/userInfo",
            data: data,
            dataType: 'json',
            success: function(data) {

                html="";
                html += '<tr>';
                html += '<td>'+'고객ID'+'</td>';
                html += '<td>'+'<input type="text" id="id" value='+data[0].id+'>'+'</td>';
                html += '</tr>';
                html += '<tr>';
                html += '<td>'+'고객PW'+'</td>';
                html += '<td>'+'<input type="text" id="pwd" value='+data[0].pwd+'>'+'</td>';
                html += '</tr>';
                html += '<tr>';
                html += '<td>'+'고객이름'+'</td>';
                html += '<td>'+'<input type="text" id="name" value='+data[0].name+'>'+'</td>';
                html += '</tr>';
                html += '<input type="hidden" id="user_num" value='+data[0].user_num+'>';

                $("#tBody").empty();
                $("#tBody").append(html);
            },
            error: function(err) {
                console.log("error!");
            }
        });
    });
});