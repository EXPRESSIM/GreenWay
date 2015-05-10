ExpressIM.AdminUserController = Class.create();
ExpressIM.AdminUserController.prototype = Class.extend({
    _initialize: function (options) {
        this._view = "User";
        this._modelType = "user";
        this._lockKeyField = true;
        this._fields = [
            { label: "ID",
                dbField: "ID",
                property: "Id",
                gridOptions: "width:100,field:'Id'",
                isSearchField: false
            },
            { label: "用户名",
                dbField: "USER_NAME",
                property: "Username",
                gridOptions: "width:200,field:'Username'",
                isSearchField: true,
                isKeyField: true
            },
            { label: "姓名",
                dbField: "NAME",
                property: "Name",
                gridOptions: "width:200,field:'Name'",
                isSearchField: true,
                isKeyField: false
            },
            { label: "状态",
                dbField: "STATUS",
                property: "Status",
                gridOptions: "width:200,field:'Status'",
                isSearchField: true,
                isKeyField: false
            }
        ];
    },

    _bindEvents: function () {
        this.on("OnCreateNew", this, (function (ctx) {
            this.find("STATUS").combobox("setValue", "A");
            this._restrictPassword();
        }));

        this.on("OnReset", this, (function (ctx) {
            this.find("STATUS").combobox("setValue", "A");
        }));

        this.on("OnReadComplete", this, (function (ctx) {
            var password = this.find("PASSWORD");
            var value = password.textbox("getValue");
            this._restrictPassword();
        }));

    },

    _restrictPassword: function () {
        this.find("PASSWORD").textbox('textbox').attr("maxlength", 16);
    }
}, ExpressIM.MaintenanceController.prototype);


$(document).ready(function () {
    var controller = new ExpressIM.AdminUserController({});
});