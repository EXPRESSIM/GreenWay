ExpressIM.AdminUserController = Class.create();
ExpressIM.AdminUserController.prototype = Class.extend({
    _initialize: function (options) {
        this._view = "User";
        this._modelType = "user";
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
                gridOptions: "width:200,field:'status'",
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