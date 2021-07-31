var main = {
    init : function () {
        var _this = this;

        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });

        $('#btn-insert-group').on('click', function () {
            _this.insertGroup();
        });

        $('#btn-insert-schedule-ui').on('click', function () {
            let $formOpertime = $(".form-opertime");
            let weekdayList = {"sun":"일요일",
                               "mon":"월요일",
                               "tue":"화요일",
                               "wed":"수요일",
                               "thu":"목요일",
                               "fri":"금요일",
                               "sat":"토요일"};
            $formOpertime.empty();
            let html = "<div class='form-group'>";
            for (let key in weekdayList) {
                html += "<div class='input-group input-group-sm'>"
                      + "<div class='input-group-prepend'>"
                      + "<span class='input-group-text'>"
                      + weekdayList[key]
					  + "</span>"
                      + "</div>"
                      + "<input type='text' class='form-control' name='"+key+"StartTime'>"
                      + "~"
                      + "<input type='text' class='form-control' name='"+key+"EndTime'>"
                      + "</div>";
            }
            $formOpertime.append(html);
        });

        $('#btn-insert-schedule').on('click', function () {
            _this.insertSchedule();
        });

		$(".accordion-box .title").on('click',function(){
		  $(this).next(".accordion-box .con").slideToggle(100);
		});
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };
        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };
        var id = $('#id').val();
        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();
        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    insertGroup : function () {
        var param = {
            name: $("#form-insert-group").find("#name").val(),
            useYn: "Y"
        };
        $.ajax({
            type: 'POST',
            url: '/api/v1/group',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(param)
        }).done(function(result) {
            alert('그룹이 등록되었습니다.');
            window.location.href = '/group/login?groupId='+result.groupId
                                 + '&roleId='+result.roleId;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    insertSchedule : function () {
        let array = $(".form-opertime").serializeArray();
        let param = {};
        for (key in array) {
            param[array[key].name]=array[key].value;
        }
        $.ajax({
            type: 'POST',
            url: '/api/v1/schedule',
            dataType: 'json',
            contentType:'application/json;',
            data: JSON.stringify(param)
        }).done(function() {
            alert('시간이 등록되었습니다.');
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();