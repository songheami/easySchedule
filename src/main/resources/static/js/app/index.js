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

        $('#btn-group-create').on('click', function () {
            _this.createGroup();
        });

        $("#form-create-group").find("#name").on("propertychange change keyup paste input", function(){
			$("#form-create-group").find("button").attr("disabled","disabled");
			if (this.value.length == 0) {
				$("#form-create-group").find("#name").removeClass().addClass("form-control");
			} else {
			    _this.checkGroup();
			}
		});

		$("#form-group-join-staff").find("#name").on("propertychange change keyup paste input", function(){
		    _this.searchStaffGroup();
		});

		$("#form-group-join-member").find("#name").on("propertychange change keyup paste input", function(){
		    _this.searchMemberGroup();
		});

        $('#opertime_wrapper .btn-add-opertime').on('click', function () {
            $("#"+this.id).before('<div class="input-box">'
                                 +'<input type="text" name="startTime" placeholder="00:00">'
                                 +'<input type="text" name="endTime" placeholder="00:00">'
                                 +'<input type="hidden" name="opertimeId" value="">'
                                 +'<input type="hidden" name="dayCode" value="'+this.id.charAt(this.id.length-1)+'">'
                                 +'</div>');
        });

        // 시간 입력 포맷
        $('#opertime_wrapper input[name$="Time"]').on('input', function(){
            var thisVal = $(this).val().replace(/\s|\D/g, '');
            thisVal = thisVal.replace(/(\d{2})/gi, '$1:');
            thisVal = thisVal.substr(0,5);
            $(this).val(thisVal);
        });

        $('.opertime_button #btn-insert-opertime').on('click', function () {
            _this.insertOpertime();
        });
    },
    createGroup : function () {
        var param = {
            name: $("#form-create-group").find("#name").val(),
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
            window.location.href = '/schedule?groupId='+result.groupId+'&roleId='+result.roleId;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    checkGroup : function() {
        $.ajax({
            type: 'GET',
            url: '/api/v1/group/'+$("#form-create-group").find("#name").val(),
            dataType: 'json',
            contentType:'application/json;'
        }).done(function(result) {
            if (result>0) {
                $("#form-create-group").find("#name").removeClass().addClass("form-control is-invalid");
            } else {
                $("#form-create-group").find("#name").removeClass().addClass("form-control is-valid");
                $("#form-create-group").find("button").removeAttr("disabled");
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    searchStaffGroup : function () {
        $.ajax({
            type: 'GET',
            url: '/api/v1/group',
            dataType: 'json',
            contentType:'application/json;',
            data: {
                role: 'staff',
                name : $("#form-group-join-staff").find("#name").val()
            }
        }).done(function(result) {
            let $box = $("#form-group-join-staff").find(".box");
            $box.empty();
            $("#form-group-join-staff").find("#name").removeClass();

            let html = "";
            for (let key in result) {
                html += "<div>"
                      + result[key].name
                      + "</div>"
                      + "<button type='button' class='btn btn-outline-light' onclick='joinGroup("
                      + "2, "
                      + result[key].groupId
                      + ")'>가입</button>";
            }
            $box.append(html);
            if (result.length == 0) {
                $("#form-group-join-staff").find("#name").addClass("form-control is-invalid");
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    searchMemberGroup : function () {
        $.ajax({
            type: 'GET',
            url: '/api/v1/group',
            dataType: 'json',
            contentType:'application/json;',
            data: {
                role: 'member',
                name : $("#form-group-join-member").find("#name").val()
            }
        }).done(function(result) {
            let $box = $("#form-group-join-member").find(".box");
            $box.empty();
            $("#form-group-join-member").find("#name").removeClass();

            let html = "";
            for (let key in result) {
                html += "<div>"
                      + result[key].name
                      + "</div>"
                      + "<button type='button' class='btn btn-outline-light' onclick='joinGroup("
                      + "3, "
                      + result[key].groupId
                      + ")'>가입</button>";
            }
            $box.append(html);
            if (result.length == 0) {
                $("#form-group-join-member").find("#name").addClass("form-control is-invalid");
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    insertOpertime : function () {
        let array =  $(".form-opertime").serializeArray();
        let param = [];
        let detailParam = {};
        for (key in array) {
            detailParam[array[key].name] = array[key].value;
            if (array[key].name == 'dayCode') {
                param.push(detailParam);
                detailParam = {};
            }
        }
        $.ajax({
            type: 'POST',
            url: '/api/v1/opertime',
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

function joinGroup(roleId, groupId) {
    let param = {
        roleId: roleId,
        groupId: groupId,
        useYn : "Y"
    };
    $.ajax({
        type: 'POST',
        url: '/api/v1/userGroup',
        contentType:'application/json;',
        data: JSON.stringify(param)
    }).done(function() {
        alert("그룹 가입이 완료되었습니다");
        window.location.href = '/schedule?groupId='+result.groupId+'&roleId='+result.roleId;
    }).fail(function (error) {
        alert(JSON.stringify(error));
    });
}