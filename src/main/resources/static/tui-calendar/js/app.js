'use strict';

/* eslint-disable */
/* eslint-env jquery */
/* global moment, tui, chance */
/* global findCalendar, CalendarList, ScheduleList, generateSchedule */

(function(window, Calendar) {
    var cal, resizeThrottled;
    var useCreationPopup = false;
    var useDetailPopup = false;
    var datePicker, selectedCalendar;
    var finalOpTimeList = [];

    cal = new Calendar('#calendar', {
        defaultView: 'week',
        taskView: false,
        scheduleView: ['time'],
        useCreationPopup: useCreationPopup,
        useDetailPopup: useDetailPopup,
        calendars: CalendarList,
        template: {
            milestone: function(model) {
                return '<span class="calendar-font-icon ic-milestone-b"></span> <span style="background-color: ' + model.bgColor + '">' + model.title + '</span>';
            },
            allday: function(schedule) {
                return getTimeTemplate(schedule, true);
            },
            time: function(schedule) {
                return getTimeTemplate(schedule, false);
            }
        }
    });

    // event handlers
    cal.on({
        'clickMore': function(e) {
            console.log('clickMore', e);
        },
        'clickSchedule': function(e) {
            // block time 선택 X
            if (e.schedule.calendarId == -1) return;
            // 스케줄 선택
            showSchedulePopUp("old", e);
        },
        'clickDayname': function(date) {
            console.log('clickDayname', date);
        },
        'beforeCreateSchedule': function(e) {
            if (checkTimeValidity(e.start, e.end)) {
                showSchedulePopUp("new", e);
            } else {
                alert("선택한 시간에 예약이 불가합니다.");
            }
        },
        'beforeUpdateSchedule': function(e) {
            var schedule = e.schedule;
            var changes = e.changes;

            console.log('beforeUpdateSchedule', e);

            if (changes && !changes.isAllDay && schedule.category === 'allday') {
                changes.category = 'time';
            }

            cal.updateSchedule(schedule.id, schedule.calendarId, changes);
            refreshScheduleVisibility();
        },
        'beforeDeleteSchedule': function(e) {
            console.log('beforeDeleteSchedule', e);
            cal.deleteSchedule(e.schedule.id, e.schedule.calendarId);
        },
        'afterRenderSchedule': function(e) {
            var schedule = e.schedule;
            // var element = cal.getElement(schedule.id, schedule.calendarId);
            // console.log('afterRenderSchedule', element);
        },
        'clickTimezonesCollapseBtn': function(timezonesCollapsed) {
            console.log('timezonesCollapsed', timezonesCollapsed);

            if (timezonesCollapsed) {
                cal.setTheme({
                    'week.daygridLeft.width': '77px',
                    'week.timegridLeft.width': '77px'
                });
            } else {
                cal.setTheme({
                    'week.daygridLeft.width': '60px',
                    'week.timegridLeft.width': '60px'
                });
            }

            return true;
        }
    });

    /** 운영 가능한 시간 확인 */
    function checkTimeValidity (startDate, endDate) {
        for (let i=0; i<finalOpTimeList.length; i++) {
            if (finalOpTimeList[i].dayCode==startDate.getDay() && finalOpTimeList[i].dayCode==endDate.getDay()) {
                let startTime = startDate.getHours()*100+startDate.getMinutes();
                let endTime = endDate.getHours()*100+endDate.getMinutes();
                let opStartTime = Number(finalOpTimeList[i].startTime.replace(":",""));
                let opEndTime = Number(finalOpTimeList[i].endTime.replace(":",""));
                if (opStartTime<=startTime&&startTime<opEndTime
                    && opStartTime<=endTime&&endTime<opEndTime) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get time template for time and all-day
     * @param {Schedule} schedule - schedule
     * @param {boolean} isAllDay - isAllDay or hasMultiDates
     * @returns {string}
     */
    function getTimeTemplate(schedule, isAllDay) {
        var html = [];
        var start = moment(schedule.start.toUTCString());
        if (!isAllDay) {
            html.push('<strong>' + start.format('HH:mm') + '</strong> ');
        }
        if (schedule.isPrivate) {
            html.push('<span class="calendar-font-icon ic-lock-b"></span>');
            html.push(' Private');
        } else {
            if (schedule.isReadOnly) {
                html.push('<span class="calendar-font-icon ic-readonly-b"></span>');
            } else if (schedule.recurrenceRule) {
                html.push('<span class="calendar-font-icon ic-repeat-b"></span>');
            } else if (schedule.attendees.length) {
                html.push('<span class="calendar-font-icon ic-user-b"></span>');
            } else if (schedule.location) {
                html.push('<span class="calendar-font-icon ic-location-b"></span>');
            }
            html.push(' ' + schedule.title);
        }

        return html.join('');
    }

    /**
     * A listener for click the menu
     * @param {Event} e - click event
     */
    function onClickMenu(e) {
        var target = $(e.target).closest('a[role="menuitem"]')[0];
        var action = getDataAction(target);
        var options = cal.getOptions();
        var viewName = '';

        switch (action) {
            case 'toggle-daily':
                viewName = 'day';
                break;
            case 'toggle-weekly':
                viewName = 'week';
                break;
            case 'toggle-monthly':
                options.month.visibleWeeksCount = 0;
                viewName = 'month';
                break;
            case 'toggle-weeks2':
                options.month.visibleWeeksCount = 2;
                viewName = 'month';
                break;
            case 'toggle-weeks3':
                options.month.visibleWeeksCount = 3;
                viewName = 'month';
                break;
            case 'toggle-narrow-weekend':
                options.month.narrowWeekend = !options.month.narrowWeekend;
                options.week.narrowWeekend = !options.week.narrowWeekend;
                viewName = cal.getViewName();

                target.querySelector('input').checked = options.month.narrowWeekend;
                break;
            case 'toggle-start-day-1':
                options.month.startDayOfWeek = options.month.startDayOfWeek ? 0 : 1;
                options.week.startDayOfWeek = options.week.startDayOfWeek ? 0 : 1;
                viewName = cal.getViewName();

                target.querySelector('input').checked = options.month.startDayOfWeek;
                break;
            case 'toggle-workweek':
                options.month.workweek = !options.month.workweek;
                options.week.workweek = !options.week.workweek;
                viewName = cal.getViewName();

                target.querySelector('input').checked = !options.month.workweek;
                break;
            default:
                break;
        }

        cal.setOptions(options, true);
        cal.changeView(viewName, true);

        setDropdownCalendarType();
        setRenderRangeText();
        setSchedules();
    }

    /**
     * A listener for click the staff
     * @param {Event} e - click event
     */
    function onClickStaff(e) {
        var target = $(e.target).closest('a[role="staffItem"]')[0];
        var action = getDataAction(target);
        var $staffName = $("#staffName");
        $staffName.val(action);
        $staffName.text(e.target.text);
    }

    function onClickNavi(e) {
        var action = getDataAction(e.target);

        switch (action) {
            case 'move-prev':
                cal.prev();
                break;
            case 'move-next':
                cal.next();
                break;
            case 'move-today':
                cal.today();
                break;
            default:
                return;
        }

        setRenderRangeText();
        setSchedules();
    }

    function onChangeNewScheduleCalendar(e) {
        var target = $(e.target).closest('a[role="menuitem"]')[0];
        var calendarId = getDataAction(target);
        changeNewScheduleCalendar(calendarId);
    }

    function changeNewScheduleCalendar(calendarId) {
        var calendarNameElement = document.getElementById('calendarName');
        var calendar = findCalendar(calendarId);
        var html = [];

        html.push('<span class="calendar-bar" style="background-color: ' + calendar.bgColor + '; border-color:' + calendar.borderColor + ';"></span>');
        html.push('<span class="calendar-name">' + calendar.name + '</span>');

        calendarNameElement.innerHTML = html.join('');

        selectedCalendar = calendar;
    }

    function createNewSchedule(event) {
        var start = event.start ? new Date(event.start.getTime()) : new Date();
        var end = event.end ? new Date(event.end.getTime()) : moment().add(1, 'hours').toDate();

        if (useCreationPopup) {
            cal.openCreationPopup({
                start: start,
                end: end
            });
        }
    }
    function saveNewSchedule(scheduleData) {
        var calendar = scheduleData.calendar || findCalendar(scheduleData.calendarId);
        var schedule = {
            id: String(chance.guid()),
            title: scheduleData.title,
            isAllDay: scheduleData.isAllDay,
            start: scheduleData.start,
            end: scheduleData.end,
            category: scheduleData.isAllDay ? 'allday' : 'time',
            dueDateClass: '',
            color: calendar.color,
            bgColor: calendar.bgColor,
            dragBgColor: calendar.bgColor,
            borderColor: calendar.borderColor,
            location: scheduleData.location,
            raw: {
                class: scheduleData.raw['class']
            },
            state: scheduleData.state
        };
        if (calendar) {
            schedule.calendarId = calendar.id;
            schedule.color = calendar.color;
            schedule.bgColor = calendar.bgColor;
            schedule.borderColor = calendar.borderColor;
        }

        cal.createSchedules([schedule]);

        refreshScheduleVisibility();
    }

    function showSchedulePopUp(psStatus, e) {
        if (psStatus == "old") {
            $("#scheduleModal #scheduleModalTitle").text("스케줄 변경하기");
            $("#scheduleModal #scheduleId").val(e.schedule.id);
            $("#scheduleModal #staffName").val(e.schedule.calendarId);
            $("#scheduleModal #title").val(e.schedule.title);
            $("#scheduleModal #startTime").val(dateToString(e.schedule.start));
            $("#scheduleModal #endTime").val(dateToString(e.schedule.end));
            $("#btn-cancel-schedule").show();
        } else if (psStatus == "new") {
            $("#scheduleModal #scheduleModalTitle").text("스케줄 새로 만들기");
            $("#scheduleModal #scheduleId").val("");
            $("#scheduleModal #staffName").val("");
            $("#scheduleModal #title").val("");
            $("#scheduleModal #startTime").val(dateToString(e.start.toDate()));
            $("#scheduleModal #endTime").val(dateToString(e.end.toDate()));
            $("#btn-cancel-schedule").hide();
        } else {
            $("#scheduleModal #scheduleModalTitle").text("스케줄 새로 만들기");
            $("#scheduleModal #scheduleId").val("");
            $("#scheduleModal #staffName").val("");
            $("#scheduleModal #title").val("");
            $("#scheduleModal #startTime").val("");
            $("#scheduleModal #endTime").val("");
            $("#btn-cancel-schedule").hide();
        }

        $('#scheduleModal').modal('show');
    }

    function dateToString(date) {
        let year = date.getFullYear();
        let month = date.getMonth()+1;
        let day = date.getDate();
        let hour = date.getHours();
        let min = date.getMinutes();

        return year+"-"+(month<10?"0"+month:month)+"-"+(day<10?"0"+day:day)
            +" "+(hour<10?"0"+hour:hour)+":"+(min<10?"0"+min:min);
    }

    function onChangeCalendars(e) {
        var calendarId = e.target.value;
        var checked = e.target.checked;
        var viewAll = document.querySelector('.lnb-calendars-item input');
        var calendarElements = Array.prototype.slice.call(document.querySelectorAll('#calendarList input'));
        var allCheckedCalendars = true;

        if (calendarId === 'all') {
            allCheckedCalendars = checked;

            calendarElements.forEach(function(input) {
                var span = input.parentNode;
                input.checked = checked;
                span.style.backgroundColor = checked ? span.style.borderColor : 'transparent';
            });

            CalendarList.forEach(function(calendar) {
                calendar.checked = checked;
            });
        } else {
            findCalendar(calendarId).checked = checked;

            allCheckedCalendars = calendarElements.every(function(input) {
                return input.checked;
            });

            if (allCheckedCalendars) {
                viewAll.checked = true;
            } else {
                viewAll.checked = false;
            }
        }

        refreshScheduleVisibility();
    }

    function refreshScheduleVisibility() {
        var calendarElements = Array.prototype.slice.call(document.querySelectorAll('#calendarList input'));

        CalendarList.forEach(function(calendar) {
            cal.toggleSchedules(calendar.id, !calendar.checked, false);
        });

        cal.render(true);

        calendarElements.forEach(function(input) {
            var span = input.nextElementSibling;
            span.style.backgroundColor = input.checked ? span.style.borderColor : 'transparent';
        });
    }

    function setDropdownCalendarType() {
        var calendarTypeName = document.getElementById('calendarTypeName');
        var calendarTypeIcon = document.getElementById('calendarTypeIcon');
        var options = cal.getOptions();
        var type = cal.getViewName();
        var iconClassName;

        if (type === 'day') {
            type = 'Daily';
            iconClassName = 'calendar-icon ic_view_day';
        } else if (type === 'week') {
            type = 'Weekly';
            iconClassName = 'calendar-icon ic_view_week';
        } else if (options.month.visibleWeeksCount === 2) {
            type = '2 weeks';
            iconClassName = 'calendar-icon ic_view_week';
        } else if (options.month.visibleWeeksCount === 3) {
            type = '3 weeks';
            iconClassName = 'calendar-icon ic_view_week';
        } else {
            type = 'Monthly';
            iconClassName = 'calendar-icon ic_view_month';
        }

        calendarTypeName.innerHTML = type;
    }

    function currentCalendarDate(format) {
      var currentDate = moment([cal.getDate().getFullYear(), cal.getDate().getMonth(), cal.getDate().getDate()]);

      return currentDate.format(format);
    }

    function setRenderRangeText() {
        var renderRange = document.getElementById('renderRange');
        var options = cal.getOptions();
        var viewName = cal.getViewName();

        var html = [];
        if (viewName === 'day') {
            html.push(currentCalendarDate('YYYY.MM.DD'));
        } else if (viewName === 'month' &&
            (!options.month.visibleWeeksCount || options.month.visibleWeeksCount > 4)) {
            html.push(currentCalendarDate('YYYY.MM'));
        } else {
            html.push(moment(cal.getDateRangeStart().getTime()).format('YYYY.MM.DD'));
            html.push(' ~ ');
            html.push(moment(cal.getDateRangeEnd().getTime()).format(' MM.DD'));
        }
        renderRange.innerHTML = html.join('');
    }

    function makeBlock(opTimeList, day) {
        let date = new Date(cal.getDateRangeStart()._date);
        if (day) date.setDate(date.getDate()+day);
        let blockStartDate = new Date(date.getFullYear(), date.getMonth(), date.getDate(), 0, 0);
        let blockEndDate = new Date(date.getFullYear(), date.getMonth(), date.getDate(), 23, 59);
        for (let i=0; i<opTimeList.length; i++) {
            let startTime = opTimeList[i].startTime;
            let startDate = new Date(date.getFullYear(), date.getMonth(), date.getDate(),
                startTime.substr(0,2), startTime.substr(3,2)-1);
            makeBlockSchedule(blockStartDate, startDate);
            let endTime = opTimeList[i].endTime;
            let endDate = new Date(date.getFullYear(), date.getMonth(), date.getDate(),
                endTime.substr(0,2), endTime.substr(3,2));
            blockStartDate = endDate;
        }
        makeBlockSchedule(blockStartDate, blockEndDate);
    }

    function makeBlockSchedule(startDate, endDate) {
        var schedule = new ScheduleInfo();
        var calendar = findCalendar("-1");
        schedule.calendarId = calendar.id;
        schedule.start = startDate;
        schedule.end = endDate;
        schedule.color = calendar.color;
        schedule.bgColor = calendar.bgColor;
        schedule.category = "time";
        ScheduleList.push(schedule);
    }

    function setSchedules() {
        cal.clear();

        let staffIdList = [];
        CalendarList.forEach(function(calendar) {
            staffIdList.push(Number(calendar.id));
        });

        $.ajax({
            type: 'GET',
            url: '/api/v1/schedule',
            dataType: 'json',
            data: {
                "staffIdList": staffIdList,
                "searchStartTime": dateToString(cal.getDateRangeStart().toDate()),
                "searchEndTime": dateToString(cal.getDateRangeEnd().toDate())
            }
        }).done(function(result) {
            ScheduleList=[];
            result.forEach(function(data) {
                var schedule = new ScheduleInfo();
                var calendar = findCalendar(schedule.calendarId);
                schedule.id = String(data.scheduleId);
                schedule.calendarId = String(data.staffId);
                schedule.title = data.title;
                schedule.start = moment(data.startTime).toDate();
                schedule.end = moment(data.endTime).toDate();
                schedule.color = calendar.color;
                schedule.bgColor = calendar.bgColor;
                schedule.category = "time";
                ScheduleList.push(schedule);
            });
            cal.createSchedules(ScheduleList);
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

        $.ajax({
            type: 'GET',
            url: '/api/v1/opertime',
            dataType: 'json',
            data: {
                "staffIdList": staffIdList
            }
        }).done(function(result) {
            /* 현재 뷰에 따라 스케줄 불가한 시간 표시 */
            ScheduleList=[]; finalOpTimeList=[];
            let dayCodeList=[];
            switch (cal.getViewName()) {
            case 'week':
                dayCodeList = [0,1,2,3,4,5,6];
                break;
            case 'day':
                dayCodeList = [cal.getDateRangeStart().getDay()];
                break;
            }
            for (let i=0; i<dayCodeList.length; i++) {
                let opTimeList = [];
                let beforeDayCode, beforeEndTime;
                result.forEach(function(data, index) {
                    if (beforeDayCode==data.dayCode && beforeEndTime==data.startTime) {
                        finalOpTimeList[finalOpTimeList.length-1].endTime = data.endTime;
                    } else {
                        finalOpTimeList.push(data);
                    }
                    if (data.dayCode == i) {
                        opTimeList.push(data);
                    }
                });
                makeBlock(opTimeList, i);
            }
            cal.createSchedules(ScheduleList);
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

        refreshScheduleVisibility();
    }

    function onSaveSchedule(psStatus) {
        let statCode;

        if (psStatus.target.id.indexOf("cancel")!=-1) {
            if (!confirm("정말 취소하시겠습니까?")) return;
            statCode = "EASY00103";
        } else {
            // 예약 변경 유효성 검사
            if (!checkTimeValidity(new Date($("#scheduleModal #startTime").val()),
                new Date($("#scheduleModal #endTime").val()))) {
                alert("입력한 시간에 예약이 불가합니다.");
                return;
            }
            statCode = "EASY00101";
        }

        var data = {
            statCode: statCode,
            scheduleId: $("#scheduleModal #scheduleId").val(),
            staffId: $("#scheduleModal #staffName").val(),
            title: $("#scheduleModal #title").val(),
            startTime: $("#scheduleModal #startTime").val(),
            endTime: $("#scheduleModal #endTime").val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/schedule',
            dataType: 'json',
            contentType:'application/json;',
            data: JSON.stringify(data)
        }).done(function(result) {
            $('#scheduleModal').modal('hide');
            setSchedules();
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

    function setEventListener() {
        $('#menu-navi').on('click', onClickNavi);
        $('.dropdown-menu a[role="menuitem"]').on('click', onClickMenu);
        $('#lnb-calendars').on('change', onChangeCalendars);

        $('#btn-new-schedule').on('click', showSchedulePopUp);
        $("#btn-cancel-schedule").on('click', onSaveSchedule);
        $("#btn-update-schedule").on('click', onSaveSchedule);

        $('#dropdownMenu-calendars-list').on('click', onChangeNewScheduleCalendar);

        $('.dropdown-menu a[role="staffItem"]').on('click', onClickStaff);

        window.addEventListener('resize', resizeThrottled);
    }

    function getDataAction(target) {
        return target.dataset ? target.dataset.action : target.getAttribute('data-action');
    }

    resizeThrottled = tui.util.throttle(function() {
        cal.render();
    }, 50);

    window.cal = cal;

    setDropdownCalendarType();
    setRenderRangeText();
    setSchedules();
    setEventListener();

    // 시간 입력 포맷
    $('#scheduleModal input[name$="Time"]').on('input', function(){
        var thisVal = $(this).val().replace(/\s|\D/g, '');
        thisVal = thisVal.substr(0,12);
        if (thisVal.length<=4) {
            thisVal = thisVal.replace(/(\d{4})/gi, '$1');
        } else if (thisVal.length<=6) {
            thisVal = thisVal.replace(/(\d{4})(\d{1})/gi, '$1-$2');
        } else if (thisVal.length<=8) {
            thisVal = thisVal.replace(/(\d{4})(\d{2})(\d{1})/gi, '$1-$2-$3');
        } else if (thisVal.length<=10) {
            thisVal = thisVal.replace(/(\d{4})(\d{2})(\d{2})(\d{1})/gi, '$1-$2-$3 $4');
        } else {
            thisVal = thisVal.replace(/(\d{4})(\d{2})(\d{2})(\d{2})(\d{1})/gi, '$1-$2-$3 $4:$5');
        }
        $(this).val(thisVal);
    });
})(window, tui.Calendar);

// set calendars
(function() {
    var calendarList = document.getElementById('calendarList');
    var html = [];
    CalendarList.forEach(function(calendar, index) {
        html.push('<div class="lnb-calendars-item"><label>' +
            '<input type="checkbox" class="tui-full-calendar-checkbox-round" value="' + calendar.id + '" ' + index<=10?'checked':'' + '>' +
            '<span style="border-color: ' + calendar.borderColor + '; background-color: ' + calendar.borderColor + ';"></span>' +
            '<span>' + calendar.name + '</span>' +
            '</label></div>'
        );
    });

    if (screen.width >= 768) {
        cal.changeView('week', true);
        $("#calendarTypeName").text("Weekly");
    } else {
        cal.changeView('day', true);
        $("#calendarTypeName").text("Daily");
    }
})();
