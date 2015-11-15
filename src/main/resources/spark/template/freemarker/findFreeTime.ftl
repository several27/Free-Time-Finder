<!DOCTYPE html>
<html>
<head>
<#include "header.ftl">
</head>

<body>

<#include "nav.ftl">

<div class="container">
    <div class="row">
        <form class="form-inline" role="form">
            <div class="form-group">
                <label for="year">Date:</label>
                <input type="text" name="date" id="date" class="form-control pickadate">
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="ct-chart ct-golden-section" id="chart"></div>
            <script>
                new Chartist.Line('#chart', {
                            labels: [${labels}],
                            series: [{
                                name: 'series-1',
                                data: [${data}]
                            }]
                        },
                        {
                            'series-1': {lineSmooth: Chartist.Interpolation.step()}
                        });

                jQuery('.pickadate').pickadate({
                    formatSubmit: 'yyyy/mm/dd'
                });
            </script>
        </div>
    </div>
</div>

</body>
</html>
