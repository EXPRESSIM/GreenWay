ExpressIM.SummaryReportController = Class.create();
ExpressIM.SummaryReportController.prototype = Class.extend({
    _initialize: function (options) {
        this._view = "SummaryReport";
    },

    _getParams: function(){
    	var p = "?summaryType=" + this._getFieldValue(this.find("summaryType"));
    	return p;
    },
    
    _bindEvents: function () {
    	 this._btnRun = this.find("run");
    	 this._btnRun.linkbutton({
             onClick: (function () {
            	 window.open ('modules/reporting/rptSummary.html' + this._getParams(), 'newwindow', 'height=600px, width=800px, scrollbars=yes, resizable=yes');
             }).bind(this)
         });
    	 
    	 this._btnCancel = this.find("cancel");
    	 this._btnCancel.linkbutton({
             onClick: (function () {
                 this.getTask().terminate();
             }).bind(this)
         });
    }
}, ExpressIM.Controller.prototype);




$(document).ready(function () {
    var controller = new ExpressIM.SummaryReportController({});
});