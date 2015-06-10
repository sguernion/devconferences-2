var React = require('react');

var YoutubeLink = React.createClass({

    render: function () {
        var youtubeId = this.props.id;
        var name = this.props.name;
        return (
            <i className="fa fa-youtube">
                <a href={'https://www.youtube.com/channel/' + youtubeId}> {name}</a>
            </i>
        )
    }
});