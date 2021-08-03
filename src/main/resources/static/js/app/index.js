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

        $('#btn-insert-opertime-ui').on('click', function () {
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

        $('#btn-insert-opertime').on('click', function () {
            _this.insertOpertime();
        });

        $('#btn-insert-holiday-ui').on('click', function () {
            let $formHoliday = $(".form-holiday");
            let holidayList = {"holiday":"휴일자",
                               "name":"휴일명",
                               "alldayYn":"종일여부",
                               "startTime":"시작시간",
                               "endTime":"종료시간"};
            $formHoliday.empty();
            let html = "<div class='form-group'>";
            for (let key in holidayList) {
                if (key == 'alldayYn') {
                    html += "<div class='form-check'>"
                          + "<input class='form-check-input' type='checkbox' name='"+key+"' id='"+key+"'>"
                          + "<label class='form-check-label' for='"+key+"'>"
                          + holidayList[key]
                          + "</label>"
                          + "</div>";
                } else {
                    html += "<div class='input-group input-group-sm'>"
                          + "<div class='input-group-prepend'>"
                          + "<span class='input-group-text'>"
                          + holidayList[key]
                          + "</span>"
                          + "</div>"
                          + "<input type='text' class='form-control' name='"+key+"'>"
                          + "</div>";
                }
            }
            $formHoliday.append(html);
            $('#alldayYn').on('click', function() {
                if ($('#alldayYn').is(":checked")) {
                    $('#alldayYn').val('Y');
                } else {
                    $('#alldayYn').val(null);
                }
            });
        });

        $('#btn-insert-holiday').on('click', function () {
            _this.insertHoliday();
        });

        $('#btn-search-group').on('click', function () {
            _this.searchGroup();
        });

        $('#btn-join-group').on('click', function () {
            _this.joinGroup();
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
    insertOpertime : function () {
        let array = $(".form-opertime").serializeArray();
        let param = {};
        for (key in array) {
            param[array[key].name]=array[key].value;
        }
        $.ajax({
            type: 'POST',
            url: '/api/v1/opertime',
            dataType: 'json',
            contentType:'application/json;',
            data: JSON.stringify(param)
        }).done(function() {
            alert('시간이 등록되었습니다.');
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    insertHoliday : function () {
        let array = $(".form-holiday").serializeArray();
        let param = {};
        for (key in array) {
            param[array[key].name]=array[key].value;
        }
        $.ajax({
            type: 'POST',
            url: '/api/v1/holiday',
            dataType: 'json',
            contentType:'application/json;',
            data: JSON.stringify(param)
        }).done(function() {
            alert('휴일이 등록되었습니다.');
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    searchGroup : function () {
        $.ajax({
            type: 'GET',
            url: '/api/v1/group',
            dataType: 'json',
            contentType:'application/json;',
            data: {
                name : $("#form-join-group").find("#name").val()
            }
        }).done(function(result) {
            let $tbody = $("#form-join-group").find("table tbody");
            $tbody.empty();
            let html = "";
            for (let key in result) {
                html += "<tr>"
                      + "<td>"
                      + "<input type='checkbox' name='_selected_' id='"
                      + result[key].groupId
                      + "'>"
                      + "</td>"
                      + "<td>"
                      + result[key].name
                      + "</td>"
                      + "</tr>";
            }
            $tbody.append(html);
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    joinGroup : function () {
        $.ajax({
            type: 'POST',
            url: '/api/v1/user-group/'+$("input:checkbox[name='_selected_']:checked").attr('id'),
            dataType: 'json',
            contentType:'application/json;'
        }).done(function(result) {
            console.log(result);
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();