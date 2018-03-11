const React = require('react');

export default class Temperature extends React.PureComponent {
    render() {
        const style = {
            backgroundColor: this.props.color
        };
        return (
            <div className="box">
                <div className="box-inside">
                    <div className="room">{this.props.room}</div>
                    <div className="temperature">{this.props.temperature.current}&#176;</div>
                    <div className="bottom" style={style}>
                        <div className="avg-temp">
                            {this.props.temperature.avg}&#176;
                        </div>
                        <div className="min-max-temp">
                            <div className="max-temp">{this.props.temperature.max}&#176;</div>
                            <div className="min-temp">{this.props.temperature.min}&#176;</div>

                        </div>
                    </div>
                </div>
            </div>
        )
    }
}