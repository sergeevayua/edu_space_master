export const optFromInput = {
  chart: {
    zoomType: 'x',
    type: 'column',
    resetZoomButton: {
      theme: {
        fill: 'white',
        stroke: 'silver',
      }
    }
  },
  tooltip: {
    xDateFormat: '%B %Y',
    nullFormat: 'No data available',
    shared: true,
    split: false,
    enabled: true,
    positioner: function () {
      return {x: 40, y: -10};
    },
    style: {
      fontSize: '14px'
    },
    headerFormat: '<span style="font-size: 12px">{point.key}</span><br/>',
    shadow: false,
    borderWidth: 0,
    backgroundColor: '#eeeeee'
  },
  time: {
    useUTC: true
  },
  credits: {
    enabled: false
  },
  title: {
    text: undefined
  },
  yAxis: {
    allowDecimals: false,
    title: {
      text: 'Кол-во курсов'
    },
    labels: {
      style: {
        fontSize: '14px'
      }
    },
    showEmpty: true
  },
  xAxis: {
    type: 'category',
    title: {
      text: 'Группы курсов'
    },
    labels: {
      style: {
        fontSize: '14px'
      }
    },
    showEmpty: true
  },
  series: [],
  colorAxis: {
    visible: false
  },
  legend: {
    enabled: false
  },
  plotOptions: {
    series: {
      showInLegend: true,
      connectNulls: false,
      marker: {
        enabled: false,
        radius: 2,
        states: {
          hover: {
            enabled: true,
            radius: 2.5
          }
        }
      }
    },
  },
  navigation: {
    buttonOptions: {
      enabled: false
    }
  }
};
