EXPRESSWAY.QuestionController = Class.create();
EXPRESSWAY.QuestionController.prototype = Class.extend({
    _initialize: function (options) {
        this._view = "EXPRESSWAY";
        this._fields = [
            {
                label: "ID",
                dbField: "ID",
                property: "Id",
                gridOptions: "width:100,field:'Id'",
                isSearchField: false
            },
            {
                label: "用户名",
                dbField: "CUSOTMER_ID",
                property: "CustomerId",
                gridOptions: "field:'customerId',width:150,align:'right'",
                isSearchField: false,
                isKeyField: false
            },
            {
                label: "标题",
                dbField: "TITLE",
                property: "Title",
                gridOptions: "width:200,field:'Title'",
                isSearchField: true,
                isKeyField: false
            },
            {
                label: "状态",
                dbField: "STATUS",
                property: "Status",
                gridOptions: "field:'status',width:100,align:'right'",
                isSearchField: false,
                isKeyField: false
            },
            {
                label: "创建时间",
                dbField: "CREATE_TIME",
                property: "CreateTime",
                gridOptions: "field:'customer',width:150,align:'right'",
                isSearchField: false,
                isKeyField: false
            }
        ];
    },

    _bindEvents: function () {
        this._searchGrid = this.find("questionGrid");
        this._searchGrid.datagrid({
            title: '问题列表',
            loadMsg: '处理中,请等待...',
            remotesort: true,
            pageSize: 10,
            pagination: true,
            rownumbers: false,
            fitColumns: true,
            url: 'frontcontroller.aspx?action=searchquestion&MODEL_TYPE=questions',
            singleSelect: true,
            toolbar: '#tb',
            footer: '#ft',
            width: 722,
            height: 400,
            columns: [[
                            {
                                field: 'Id',
                                title: 'ID',
                                width: 50,
                                sortable: true
                            },
                            {
                                field: 'Title',
                                title: '标题',
                                width: 230,
                                sortable: true
                            },
                            {
                                field: 'ExamResultNum',
                                title: '检测结果条码',
                                width: 100
                            },
                            {
                                field: 'Status',
                                title: '状态',
                                align: 'right',
                                width: 100,
                                formatter:
                                    function (value, row, index) {
                                         if (value == 'P')
                                             return "待回答";
                                         if (value == 'A')
                                             return "已解答";
                                         if (value == 'W')
                                             return "已删除";
                                         else
                                             return "<span style=\"color:red\" >未知</span>";
                                    }
                            },
                            {
                                field: 'CustomerName',
                                title: '提问者昵称',
                                align: 'right',
                                width: 150
                            },
                            {
                                field: 'CustomerID',
                                title: '提问者ID',
                                align: 'right',
                                width: 80,
                                sortable: true
                            },
                            {
                                field: 'CreateDate',
                                title: '提问时间',
                                width: 150,
                                formatter: formatDatebox,
                                sortable: true
                            }
            ]],
            onDblClickRow: (function () {
                var qid = this.find("questionGrid").datagrid("getSelected")["Id"];
                this._getReplyInfo(qid);
            }).bind(this)
        });

        this._btnRemove = this.find("btnRemoveQuestion");
        this._btnRemove.linkbutton({
            text: '删除问题',
            iconCls: 'icon-remove',
            onClick: (function () {
                this._removeQuestion();
            }).bind(this)
        });
        
        this._btnSearch = this.find("btnSearch");
        this._btnSearch.linkbutton({
            onClick: (function() {
                this._searchQuestion();
            }).bind(this)
        });

        this._startTime = this.find("txtCreateTimeStart");
        this._startTime.datebox("textbox").bind("focus", (function() {
            this._setFieldValue(this.find("txtCreateTimeStart"),"");
        }).bind(this)).bind(this);
        this._endTime = this.find("txtCreateTimeEnd");
        this._endTime.datebox("textbox").bind("focus", (function () {
            this._setFieldValue(this.find("txtCreateTimeEnd"), "");
        }).bind(this)).bind(this);
    },

    _getReplyInfo: function (qid) {
        $.ajax({
            type: "post",
            url: EXPRESSWAY.frontcontroller + "?action=getquestioninfo&MODEL_TYPE=questions&questionId=" + qid,
            success: (function (data, textStatus) {
                var json = eval("(" + data + ")");
                if (json.remoteMessage) {
                    this._showRemoteMessage(json);
                } else if (json.Id && json.Id > 0) {
                    this._showQuestionWindow(json);
                } else {

                }
            }).bind(this)
        });
    },

    _showQuestionWindow: function (record) {
        if (!this._questionW) {
            var professorColor = "rgb(153, 217, 234)";
            var customerColor = "rgb(176, 241, 146)";
            var html = "<div class=\"easyui-layout\" data-options=\"fit:true\"> <div data-options=\"region:'center'\" style=\"padding:5px;\"><div class=\"easyui-layout\" data-options=\"fit:true\" style=\"word-wrap: break-word; word-break: normal;\"><div data-options=\"region:'center',border:false\" style=\"font-size: 16px;width:500px;height:300px\">";
            var contentHtml ='<div class="easyui-panel" title="' +  record.CustomerName +'的问题" style="width:100%;height:150px;overflow:auto;padding:10px 10px 10px 10px;">' + record.QuestionContent + '</div>';
            contentHtml += '<div class="easyui-panel" title="回复" style="width:100%;height:230px;overflow:auto;"><table id="'+this.getTask().taskIdx+'_reply_table" style="width:100%;">';
            for (var i = 0; i < record.Replies.length; i++)
            {
                if (record.Replies[i].ProfessorId) {
                    contentHtml += "<tr ><td valign='middle'><table style='width:100%;'><tr><td style='width:1px;'><div style='float:left;white-space:nowrap;'>(专家) " + record.Replies[i].ProfessorName + "</div></td>";
                    contentHtml += '<td><div class="tooltip tooltip-right" tabindex="-1" style="max-width:400px;position:relative;display: block;left:10px;float:left;background-color:' + professorColor + ';border-color: ' + professorColor + ';box-shadow: 2px 2px 4px ' + professorColor +';"><div class="tooltip-content">' + unescape(record.Replies[i].Answer) + '</div><div class="tooltip-arrow-outer" style="border-right-color: ' + professorColor + ';"></div><div class="tooltip-arrow" style="border-right-color:' + professorColor +';"></div></div>';
                    contentHtml +="</td></tr></table></td><tr>";
                } else {
                    contentHtml += "<tr ><td valign='middle'><table style='width:100%;'><tr>";
                    contentHtml += '<td><div class="tooltip tooltip-left" tabindex="-1" style="max-width:400px;position:relative;display: block;right:10px;float:right;background-color:' + customerColor + ';border-color: ' + customerColor + ';box-shadow: 2px 2px 4px ' + customerColor +';"><div class="tooltip-content">' + unescape(record.Replies[i].Answer) + '</div><div class="tooltip-arrow-outer" style="border-left-color: ' + customerColor + ';"></div><div class="tooltip-arrow" style="border-left-color:' + customerColor +';"></div></div></td>';
                    contentHtml +="<td style='width:1px;'><div style='float:right;white-space:nowrap;'>(注册用户) " + record.CustomerName  + "</div></td></tr></table></td><tr>";
                }
            }
            contentHtml += "</table></div>";
            var endhtml = "</div><div data-options=\"region:'south'\" style=\"padding:5px; height: 180px;\"><textarea id='" + this.getTask().taskIdx + "_answer_richtext' data-index=\"answer\"></textarea></div></div></div><div data-options=\"region:'south'\" style=\"text-align:right;padding: 5px; height: 40px\"><a id='" + this.getTask().taskIdx + "_btn_ok' data-index=\"btnReply\" data-options=\"iconCls:'icon-ok'\" class=\"easyui-linkbutton\" href=\"javascript:void(0)\"  style=\"width:100px\">确定</a><a id='" + this.getTask().taskIdx + "_btn_cancel' data-index=\"btnCancel\" data-options=\"iconCls:'icon-undo'\" class=\"easyui-linkbutton\" href=\"javascript:void(0)\" style=\"width:100px\">取消</a></div></div>";
            

            $("#taskContainer").append("<div id='" + this.getTask().taskIdx + "_question_window'>" + html + contentHtml + endhtml + "</div>");
            this._questionW = $('#' + this.getTask().taskIdx + "_question_window").window({
                title: record.Title,
                width: 650,
                height: 650,
                padding: 5,
                onOpen: (function () {
                    $.parser.parse($('#' + this.getTask().taskIdx + '_question_window'), function(){
                    });
                    this._richEditor = $('#' + this.getTask().taskIdx + "_answer_richtext").cleditor({ width: 600, height: 140 });
                    this._btnOK = $('#' + this.getTask().taskIdx + '_btn_ok');
                    this._btnCancel = $('#' + this.getTask().taskIdx + '_btn_cancel');
                    this._btnOK.linkbutton({
                        onClick: (function() {
                            $('#' + this.getTask().taskIdx + "_answer_richtext").cleditor()[0].updateTextArea();
                            var replys = escape($('#' + this.getTask().taskIdx + "_answer_richtext").val());
                            if (replys) {
                                this._addReply(record.Id, replys);
                            } else {
                                $.messager.alert('错误', "请填写回复内容", 'error', function () { });
                                return;
                            }

                        }).bind(this)
                    });
                    this._btnCancel.linkbutton({
                        onClick: (function () {
                            this._questionW.window("close");
                        }).bind(this)
                    });
                }).bind(this),
                onClose: (function () {
                    this._questionW.window("destroy");
                    this._questionW = null;
                    //refresh search grid
                    this._refreshDatagrid();

                }).bind(this),
                onMove: (function () {
                   $.parser.parse($('#' + this.getTask().taskIdx + '_question_window'), function(){
                         
                    });
                }).bind(this)
            });
        } else {
            this._questionW.window("open");
        }
        
    },
   
    _searchQuestion: function() {
            this.find("questionGrid").datagrid("load", {
            title: this._getFieldValue(this.find("textTitle")),
            startDate: this._getFieldValue(this.find("txtCreateTimeStart")),
            endDate: this._getFieldValue(this.find("txtCreateTimeEnd")),
            status: this._getFieldValue(this.find("comboStatus")),
            pageNumber: 1
        });
    },

    _addReply: function(qid,reply) {
        $.ajax({
            type: "post",
            url: EXPRESSWAY.frontcontroller + "?action=replyquestion&MODEL_TYPE=questions&questionId=" + qid,
            data: {"Answer":reply},
            success: (function (data, textStatus) {
                if (data && data.length > 0) {
                    var json = eval("(" + data + ")");
                    if (json.remoteMessage) {
                        this._showRemoteMessage(json);
                    } else {
                        var replyTable = "<tr ><td valign='middle'><table style='width:100%;'><tr><td style='width:1px;'><div style='float:left;white-space:nowrap;'>(专家) " + json.ProfessorName + "</div></td>";
                        replyTable += '<td><div class="tooltip tooltip-right" tabindex="-1" style="max-width:400px;position:relative;display: block;left:10px;float:left;background-color:rgb(153, 217, 234);border-color: rgb(153, 217, 234);box-shadow: 2px 2px 4px rgb(153, 217, 234);"><div class="tooltip-content">' + unescape(reply) + '</div><div class="tooltip-arrow-outer" style="border-right-color: rgb(153, 217, 234);"></div><div class="tooltip-arrow" style="border-right-color:rgb(153, 217, 234);"></div></div>';
                        replyTable += "</td></tr></table></td><tr>";
                        $('#' + this.getTask().taskIdx + '_reply_table').append(replyTable);
                        $('#' + this.getTask().taskIdx + "_answer_richtext").html('');
                        $('#' + this.getTask().taskIdx + "_answer_richtext").val('');
                        $('#' + this.getTask().taskIdx + "_answer_richtext").cleditor()[0].updateFrame();
                    }
                } else {

                }
            }).bind(this)
        });
    },

    _removeQuestion: function () {
        var row = this.find("questionGrid").datagrid('getSelected');
        if (row) {
            $.messager.confirm('确认', '确认删除?', (function() {
                var qid = this.find("questionGrid").datagrid('getSelected')["Id"];
                var status = this.find("questionGrid").datagrid('getSelected')["Status"];
                if (status == 'W') {
                    $.messager.alert('错误', "该问题已经删除,不能重复删除", 'error', function() {
                        return;
                    });
                }
                $.ajax({
                    url: EXPRESSWAY.frontcontroller + "?action=removequestion&MODEL_TYPE=questions&questionId=" + qid,
                    success: (function (data, textStatus) {
                        if (data && data.length > 0) {
                            var json = eval("(" + data + ")");
                            if (json.remoteMessage) {
                                this._showRemoteMessage(json);
                            }  else {

                            }
                        }
                        this._refreshDatagrid();
                    }).bind(this)
                });
            }).bind(this));
        } else {
            $.messager.alert('错误', "请选择需要删除的问题", 'error', function () { });
        }
    },

    _refreshDatagrid: function() {
        this.find("questionGrid").datagrid('reload');
    }

}, EXPRESSWAY.Controller.prototype);

$(document).ready(function () {
    var controller = new EXPRESSWAY.QuestionController({});
    
});

function formatDatebox(val) {
    var re = /-?\d+/;
    var m = re.exec(val);
    var d = new Date(parseInt(m[0]));
    // 按【2012-02-13 09:09:09】的格式返回日期
    return d.format("yyyy-MM-dd hh:mm:ss");
}

Date.prototype.format = function (fmt) { 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}