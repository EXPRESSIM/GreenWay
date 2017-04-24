
ExpressIM.CheckChartController = Class.create();
ExpressIM.CheckChartController.prototype = Class.extend({
    _initialize: function (options) {
        this._view = "CheckChart";
    },

    _getParams: function(){
        var p = "?startDate=" + this._getFieldValue(this.find("startDate")) + "&endDate=" + this._getFieldValue(this.find("endDate")) + "&vCount=" + this._getFieldValue(this.find("vCount")) + "&vAmount=" + this._getFieldValue(this.find("vAmount"));
        return p;
    },

    _bindEvents: function () {
        this._btnRun = this.find("run");
        this._btnRun.linkbutton({
            onClick: (function () {
                this.openNewTagWin('modules/reporting/rptSummaryChart.html');
                //window.open ('modules/reporting/rptSummary.html' + this._getParams(), 'newwindow', 'height=510px, width=900px, scrollbars=yes, resizable=yes');
            }).bind(this)
        });

        this._btnCancel = this.find("cancel");
        this._btnCancel.linkbutton({
            onClick: (function () {
                this.getTask().terminate();
            }).bind(this)
        });
    },

    openNewTagWin: function(url) {
        var params = "<input type='hidden' name='startDate' value='" + this._getFieldValue(this.find("startDate")) + "'/>"
            + "<input type='hidden' name='endDate' value='" + this._getFieldValue(this.find("endDate")) + "'/>"
            + "<input type='hidden' name='summaryType' value='" + this._getFieldValue(this.find("summaryType")) + "'/>";
        $("#tagWinContainer").html("<form id='hiddenlink' method ='get' action='"+url+"' target='_blank'>" + params + "</form>");
        var s=document.getElementById("hiddenlink");
        s.submit();
    }
}, ExpressIM.ReportController.prototype);


$(document).ready(function () {
    var controller = new ExpressIM.CheckChartController({});
});