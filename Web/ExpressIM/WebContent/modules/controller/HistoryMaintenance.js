ExpressIM.HistoryMaintenanceController = Class.create();
ExpressIM.HistoryMaintenanceController.prototype = Class.extend({
    _initialize: function (options) {
        this._view = "HistoryMaintenance";
        this._modelType = "history";
        this._lockKeyField = true;
        this._fields = [
                        { label: "ID",
                            property: "id",
                            gridOptions: "width:100,field:'id'",
                            isSearchField: false
                        },
                        { label: "用户名",
                            property: "username",
                            gridOptions: "width:200,field:'username'",
                            isSearchField: true,
                            isKeyField: true
                        },
                        { label: "姓名",
                            property: "name",
                            gridOptions: "width:200,field:'name'",
                            isSearchField: true,
                            isKeyField: false
                        },
                        { label: "状态",
                            property: "status",
                            gridOptions: "width:200,field:'status',formatter:ExpressIM.UIComponent.DataGridFormater.UserStatus",
                            isSearchField: true,
                            isKeyField: false
                        },
                        { label: "用户组",
                            property: "type",
                            gridOptions: "width:200,field:'type',formatter:ExpressIM.UIComponent.DataGridFormater.UserGroup",
                            isSearchField: true,
                            isKeyField: false
                        }
                    ];
    },

    _bindEvents: function () {
    	this._tollCollectorLookup = new ExpressIM.UIComponent.Lookup({
            searchbox: this.find("tollCollector"),
            modelType: "user",
            id: this.getTask().taskIdx + "_tollCollector",
            fields: this._fields
        });
    	
    	this._tollCollectorLookup.on("OnPreLookup", this, function (ctx) {
    		 ctx.params.tollCollectorOnly = "true";
    	});
    	
    	this._operatorLookup = new ExpressIM.UIComponent.Lookup({
            searchbox: this.find("operatorInfo"),
            modelType: "user",
            id: this.getTask().taskIdx + "_operatorInfo",
            searchIDField: this.find("operatorId"),
            fields: this._fields
        });
    	
    	this._operatorLookup.on("OnPreLookup", this, function (ctx) {
   		 	ctx.params.tollCollectorOnly = "true";
    	});
    	
    	this._leaderLookup = new ExpressIM.UIComponent.Lookup({
            searchbox: this.find("leaderInfo"),
            modelType: "user",
            id: this.getTask().taskIdx + "_leaderInfo",
            searchIDField: this.find("leaderId"),
            fields: this._fields
        });
    	
    	this._leaderLookup.on("OnPreLookup", this, function (ctx) {
   		 	ctx.params.leaderOnly = "true";
    	});
    	
        this.find('fileupload').fileupload({
            dataType: 'text'
        });
    	
        for (var i = 1; i <= 4; i++) {
        	this.find("add_img_" + i).click(
	           (function () {
	               var btn = this.find('fileupload')[0];
	               var url = "uploadsnap?imageIdx=" + i;
	               this.find('fileupload').fileupload(
	                        'option',
	                        'url',
	                        url
	                );
	               btn.click();

	           }).bind(this)
	        );
        }
        
        this.on("OnCreateNew", this, (function (ctx) {
            
        }));

        this.on("OnReset", this, (function (ctx) {
          
        }));
    }
}, ExpressIM.Controller.prototype);




$(document).ready(function () {
    var controller = new ExpressIM.HistoryMaintenanceController({});
});