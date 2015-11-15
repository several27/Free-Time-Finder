<!DOCTYPE html>
<html>
<head>
<#include "header.ftl">
</head>

<body>

<#include "nav.ftl">

<div class="container">
    <div class="row">
	    <div class="col-md-12">
            <div class="ct-chart ct-golden-section" id="chart"></div>
            <script>
                new Chartist.Line('#chart', {
	                labels: [${labels}],
                    series: [{
	                    name: 'series-1',
	                    data: [${data}]
                    }]},
		                {
			                'series-1': {lineSmooth: Chartist.Interpolation.step()}
		                });
            </script>
	    </div>
    </div>
</div>

</body>
</html>
