ExpressIM.PrintReportSetController = Class.create();
ExpressIM.PrintReportSetController.prototype = Class.extend({
    _initialize: function (options) {
        this._view = "PrintReportSet";
        this._getPages("N");
        this._recordsCountPerPage = 30;
        this._isAffectation = "N";
    },
    
    _getPages: function(isAffectation){
    	this._isAffectation = isAffectation;
    	var msg = new ExpressIM.UIComponent.LoadingMask({});
        msg.render();
		 $.ajax({
	            type: "post",
	            data: {
	            	    page:1,
	            		rows:1,
	            		isAffectation:isAffectation
	            	  },
	            url: "history",
	            success: (function (data, textStatus) {
	                   msg.destroy();
	                   var pageData = [];
	                   var pageCount = data.total / this._recordsCountPerPage;
	                   if (data.total % this._recordsCountPerPage > 0) {
	                	   pageCount++;
	                   }
	                   for (var i=1; i <= pageCount; i++) {
	                	   pageData[pageData.length] = {value:i, text:i};
	                   }
	                   this.find("page").combobox("loadData", pageData);
	                   if (pageData.length > 0) {
	                	   this.find("page").combobox("setValue", 1);
	                   }
	            }).bind(this)
	    });
    },
    
    _bindEvents: function () {
    	 this._btnRun = this.find("run");
    	 this._btnRun.linkbutton({
             onClick: (function () {
            	 this.openNewTagWin('modules/reporting/rptReportSet.html');
             }).bind(this)
         });
    	 
    	 this._btnCancel = this.find("cancel");
    	 this._btnCancel.linkbutton({
             onClick: (function () {
                 this.getTask().terminate();
             }).bind(this)
         });
    	 
    	 this.find("type").combobox({
             onChange: (function (newValue, oldValue) {
            	 this._getPages(newValue == "是" ? "N" : "Y");
             }).bind(this)
 	   	 });
    },
    
    openNewTagWin: function(url) {
    	var params = "<input type='hidden' name='pageSize' value='" + this._recordsCountPerPage + "'/>"
    			   + "<input type='hidden' name='page' value='" + this._getFieldValue(this.find("page")) + "'/>"
    			   + "<input type='hidden' name='isAffectation' value='" + this._isAffectation + "'/>";
    	$("#tagWinContainer").html("<form id='hiddenlink' method ='get' action='"+url+"' target='_blank'>" + params + "</form>");
        var s=document.getElementById("hiddenlink");
        s.submit();
    }
}, ExpressIM.ReportController.prototype);




$(document).ready(function () {
    var controller = new ExpressIM.PrintReportSetController({});
});