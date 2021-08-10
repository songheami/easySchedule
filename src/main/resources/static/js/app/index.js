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

		$("#groupJoin_wrapper").find("a").click(function (){
			$("#groupJoin_wrapper").find("a").each(function () {
				$("#"+this.attributes.getNamedItem("aria-controls").value).removeClass("show");
			});
		});


        $("#form-insert-group").find("#name").on("propertychange change keyup paste input", function(){
			$("#form-insert-group").find("button").attr("disabled","disabled");
			if (this.value.length == 0) {
				$("#form-insert-group").find("#name").removeClass().addClass("form-control");
			} else {
			    _this.checkGroup();
			}
		});

		$("#form-join-group").find("#name").on("propertychange change keyup paste input", function(){
		    _this.searchGroup();
		});

        $('#groupOpertime_wrapper input.form-control').on("propertychange change keyup paste input", function(){
             onChangeGroupOpertimeInput(this);
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
    checkGroup : function() {
        $.ajax({
            type: 'GET',
            url: '/api/v1/group/'+$("#form-insert-group").find("#name").val(),
            dataType: 'json',
            contentType:'application/json;'
        }).done(function(result) {
            if (result>0) {
                $("#form-insert-group").find("#name").removeClass().addClass("form-control is-invalid");
            } else {
                $("#form-insert-group").find("#name").removeClass().addClass("form-control is-valid");
                $("#form-insert-group").find("button").removeAttr("disabled");
            }
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
            let $container = $("#form-join-group").find(".container");
            $container.empty();
            let html = "";
            for (let key in result) {
                html += "<div class='row'>"
                      + "<div class='col-11'>"
                      + result[key].name
                      + "</div>"
                      + "<button type='button' class='btn btn-outline-light btn-sm' onclick='joinGroup("
                      + result[key].groupId
                      + ")'>가입</button>"
                      + "</div>";
            }
            $container.append(html);
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();

function joinGroup(groupId) {
    $.ajax({
        type: 'POST',
        url: '/api/v1/user-group/'+groupId,
        dataType: 'json',
        contentType:'application/json;'
    }).done(function(result) {
        console.log(result);
    }).fail(function (error) {
        alert(JSON.stringify(error));
    });
}

function addOpertime() {
     if ($(".form-opertime.empty").length > 0) return;
     let $addOpertime = $("#addOpertime");
     let weekdayList = {"sun":"일요일",
                        "mon":"월요일",
                        "tue":"화요일",
                        "wed":"수요일",
                        "thu":"목요일",
                        "fri":"금요일",
                        "sat":"토요일"};
     let html = "<div class='form-group'>";
     for (let key in weekdayList) {
         html += "<div>"
               + "<form class='form-opertime empty'>"
               + "<div class='form-group'>"
               + "<div class='input-group input-group-sm'>"
               + "<div class='input-group-prepend input-group-sm'>"
               + "<span class='input-group-text'>"
               + weekdayList[key]
               + "</span>"
               + "<input type='text' class='form-control' name='"
               + key + "StartTime"
               + "'>"
               + "<input type='text' class='form-control' name='"
               + key + "EndTime"
               + "'>"
               + "</div>"
               + "</div>";
     }
     $addOpertime.before(html);
     $('#groupOpertime_wrapper input.form-control').on("propertychange change keyup paste input", function(){
         onChangeGroupOpertimeInput(this);
     });
 }

 function onChangeGroupOpertimeInput(_this) {
    let inputVal = $(_this).val().replace(/[^0-9]/g,'').substr(0,4);
     if (inputVal.length == 4) {
         let hour = parseInt(inputVal.substr(0,2));
         let min = parseInt(inputVal.substr(2,4));
         if (hour<0||hour>24||min<0||min>=60) {
             $(_this).val(null);
             alert("시간 정보가 유효하지 않습니다.");
             return;
         }
         inputVal = (hour<10?"0"+hour:hour)+":"+(min<10?"0"+min:min);
     }
     $(_this).val(inputVal);
}