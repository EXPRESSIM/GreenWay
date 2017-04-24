ExpressIM.ChartView = Class.create();
ExpressIM.ChartView.prototype = Class.extend({
    initialize: function (options) {
        this._options = options
        this._container = options.container;
        this._model = options.model;
        this._model.addObserver("refresh", this, this.render);
        this._model.addObserver("post_load_data", this, this.render);
        if (this._initialize) {
            this._initialize(this._options);
        }
    },

    _getDefaultCommonChartSettings: function () {
        return {
            title: {
                text: this._options.titleText ? this._options.titleText : '绿通统计图表',
                x: 'center',
                textStyle: {
                    fontSize: 18,
                    fontWeight: 'bold'
                }
            }
        }
    },

    render: function (data) {
        var settings = Object.assign(this._getChartSettings(data), this._getDefaultCommonChartSettings());
        this._chartComponent = echarts.init(this._container);
        this._chartComponent.setOption(settings);
    },
    
    _getChartSettings: function (data) {
        return {};
    }
}, ExpressIM.EventDelegate.prototype);


ExpressIM.BarChartView = Class.create();
ExpressIM.BarChartView.prototype = Class.extend({
    _getChartSettings: function (data) {
        var xAxisData = [];
        var seriesData = [];
        data.forEach(function (v) {
            if (v.month) {
                xAxisData.push(v.month);
                seriesData.push(v.amount);
            }
        });
        var series = this._options.series.map(function (name) {
            return {"name": name, "type": "bar", "data": seriesData};
        });
        var legendData = series.map(function (item) {
            return item.name;
        });
        return {
            toolbox: {
                show: true,
                feature: {
                    mark: {show: true},
                    magicType: {show: true, type: ['line', 'bar']},
                    saveAsImage: {show: true}
                }
            },
            legend: { data: legendData, x: "left" },
            series: series,
            xAxis: [
                { type: 'category', data: xAxisData }
            ],
            yAxis: [
                { type: 'value' }
            ]
        };
    }
}, ExpressIM.ChartView.prototype);