'use strict';

/* eslint-disable require-jsdoc, no-unused-vars */

var CalendarList = [];
var CalendarStyleList = [{color:'#ffffff',bgColor:'#9e5fff'},
                         {color:'#ffffff',bgColor:'#00a9ff'},
                         {color:'#ffffff',bgColor:'#ff5583'},
                         {color:'#ffffff',bgColor:'#03bd9e'},
                         {color:'#ffffff',bgColor:'#bbdc00'},
                         {color:'#ffffff',bgColor:'#9d9d9d'},
                         {color:'#ffffff',bgColor:'#ffbb3b'},
                         {color:'#ffffff',bgColor:'#ff4040'}];

function CalendarInfo() {
    this.id = null;
    this.name = null;
    this.checked = true;
    this.color = null;
    this.bgColor = null;
    this.borderColor = null;
    this.dragBgColor = null;
}

function addCalendar(calendar) {
    CalendarList.push(calendar);
}

function findCalendar(id) {
    var found;

    CalendarList.forEach(function(calendar) {
        if (calendar.id === id) {
            found = calendar;
        }
    });

    return found || CalendarList[0];
}

function hexToRGBA(hex) {
    var radix = 16;
    var r = parseInt(hex.slice(1, 3), radix),
        g = parseInt(hex.slice(3, 5), radix),
        b = parseInt(hex.slice(5, 7), radix),
        a = parseInt(hex.slice(7, 9), radix) / 255 || 1;
    var rgba = 'rgba(' + r + ', ' + g + ', ' + b + ', ' + a + ')';

    return rgba;
}

(function() {
    $("#calendarList").find(".lnb-calendars-item").each(function (index, item) {
        if (index >= CalendarStyleList.length) return;
        let $this = $("#calendarList #"+item.id);
        var calendar = new CalendarInfo();
        calendar.id = item.id;
        calendar.name = $this.find("span:nth-child(3)").text();
        calendar.color = CalendarStyleList[index].color;
        calendar.bgColor = calendar.dragBgColor = calendar.borderColor = CalendarStyleList[index].bgColor;
        $this.find("span:nth-child(2)").css('color',CalendarStyleList[index].color);
        $this.find("span:nth-child(2)").css('border-color',CalendarStyleList[index].bgColor);
        addCalendar(calendar);
    });
    var calendar = new CalendarInfo();
    calendar.id = String(-1);
    calendar.color = "#bbb";
    calendar.bgColor = "#bbb";
    calendar.borderColor = "#bbb";
    calendar.dragBgColor = "#bbb";
    addCalendar(calendar);
})();
