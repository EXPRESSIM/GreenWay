
ExpressIM.ChartModel = Class.create();
ExpressIM.ChartModel.prototype = Class.extend({
    load: function (summaryType,startDate,endDate) {
            $.ajax({
                url: 'ChartAction?startDate='+startDate+'&endDate='+endDate+'&summaryType='+summaryType,
                type: 'post',
                dataType: 'json',
                cache: false,
                success: (function (data) {
                    this.data = data;
                    this.RecordNumber = this.data.length;
                    this.notifyObserver("post_load_data",this.data);
                }.bind(this))
            })
    }
}, ExpressIM.DataStore.prototype);