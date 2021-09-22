var main = {
    init : function () {
        var _this = this;

        $('#btn-update-mypage').on('click', function () {
            _this.updateMypage();
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
            thisVal = thisVal.substr(0,4);
            if (Number(thisVal.substr(2,2)) >= 60) {
                thisVal = thisVal.substr(0,2) + '59';
            }
            if (Number(thisVal)>2400) {
            	thisVal = '2400';
            }
            thisVal = thisVal.replace(/(\d{2})/, '$1:');
            $(this).val(thisVal);
        });

        $('.opertime_button #btn-insert-opertime').on('click', function () {
            _this.insertOpertime();
        });
    },
    updateMypage: function () {
    	var param = {
    		name: $("#form-mypage").find("#name").val(),
    		email: $("#form-mypage").find("#email").val(),
    		phone: $("#form-mypage").find("#phone").val()
    	};
        $.ajax({
            type: 'POST',
            url: '/api/v1/user',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(param)
        }).done(function(result) {
            alert('정보를 수정했습니다.');
        }).fail(function (error) {
            alert(JSON.stringify(error));
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
        let weekday = ["sun","mon","tue","wed","thu","fri","sat"];
        let param = [];
        // 근무시간 Json 데이터 만들기
        for (let i=0; i<weekday.length; i++) {
            let dayList = [];
            let weekDayList = $("."+weekday[i]+"OpertimeList .input-box");
            for (let j=0; j<weekDayList.length; j++) {
                let detailParam = {};
                let jsonData = weekDayList.slice(j,j+1).find("input").serializeArray();
                for (let l=0; l<jsonData.length; l++) {
                    detailParam[jsonData[l].name] = jsonData[l].value;
                }
                dayList.push(detailParam);
            }
            // 근무시간 유효성 판단 (같은 요일 내 중첨 시간 x)
            for (let l=0; l<dayList.length; l++) {
				let lStartTime = Number(dayList[l].startTime.replace(":",""));
				let lEndTime = Number(dayList[l].endTime.replace(":",""));
				if (lStartTime>=lEndTime) {
					alert("시작시간이 종료시간보다 클 수 없습니다.");
					return;
				}
				for (let m=0; m<dayList.length; m++) {
					if (l==m) continue;
					let mStartTime = Number(dayList[m].startTime.replace(":",""));
					let mEndTime = Number(dayList[m].endTime.replace(":",""));
					if ((lStartTime>=mStartTime&&lStartTime<mEndTime)
						||(lEndTime>=mStartTime&&lEndTime<mEndTime)) {
						alert("겹치는 시간이 있습니다, 확인 후 다시 저장해주세요.");
						return;
					}
				}
				param.push(dayList[l]);
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
    if (!confirm("정말 가입하시겠습니까?")) return;

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
    }).done(function(result) {
        alert("그룹 가입이 완료되었습니다");
        window.location.href = '/schedule?groupId='+result.groupId+'&roleId='+result.roleId;
    }).fail(function (error) {
        alert(JSON.stringify(error));
    });
}