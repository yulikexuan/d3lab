function createSoccerViz() {

    d3.csv("/api/data/worldcup", data => {
        overallTeamViz(data);
    });

    function overallTeamViz(incomingData) {
        d3.select("svg")
            .append("g")
            .attr("id", "teamsG")
            .attr("transform", "translate(50,300)")
            .selectAll("g")
            .data(incomingData)
            .enter()
            .append("g")
            .attr("class", "overallG")
            .attr("transform", (d, i) => "translate(" + (i * 50) + ", 0)" );

        var teamG = d3.selectAll("g.overallG");

        teamG
            .append("circle")
            .attr("r", 20);

        teamG
            .append("text")
            .style("text-anchor", "middle")
            .attr("y", 40)
            .text(d => d.team);
    }

}///:~