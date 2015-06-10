var React = require('react');

var ParleysLink = React.createClass({

    render: function () {
        var parleysId = this.props.id;
        return (
            <i>
                <a href={'https://www.parleys.com/channel/' + parleysId}> {parleysId}</a>
            </i>
        )
    }
});
