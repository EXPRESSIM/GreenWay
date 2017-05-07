
ExpressIM.ChartModel = Class.create();
ExpressIM.ChartModel.prototype = Class.extend({
    load: function (summaryType,startDate,endDate) {
            $.ajax({
                url: 'ChartAction?startDate='+startDate+'&endDate='+endDate+'&summaryType='+summaryType,
                type: 'post',
                dataType: 'json',
                cache: false,
                success: (function (data) {
                    var obj =data.result;
                    var arry = [];
                    $.each(obj, function(key, val) {
                        arry.push({"time":key,"count":val})
                    });
                    this.data = arry;
                    this.RecordNumber = this.data.length;
                    this.notifyObserver("post_load_data",this.data);
                }.bind(this))
            })
    }
}, ExpressIM.DataStore.prototype);