const React = require('react');
const ReactDOM = require('react-dom');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {bathroomTemp: null, bedroomTemp: null, balconyTemp: null};
    }

    componentDidMount() {
        this.load();
        setInterval(() => {
            this.load();
        }, 20000);
    }

    load() {
        fetch('/temperature/BATHROOM').then(res => res.json()).then(response => {
            this.setState({bathroomTemp: response});
        });

        fetch('/temperature/BEDROOM').then(res => res.json()).then(response => {
            this.setState({bedroomTemp: response});
        });

        fetch('/temperature/BALCONY').then(res => res.json()).then(response => {
            this.setState({balconyTemp: response});
        });
    }

    render() {
        return (
            <div className="root">
                <div className="line">
                    <SpecialField/>
                    <Temperature room="Łazienka" temperature={this.state.bathroomTemp} color="#EE6B33"/>
                    <Temperature room="Sypialnia" temperature={this.state.bedroomTemp} color="#7ABB57"/>
                    <Temperature room="Balkon" temperature={this.state.balconyTemp} color="#6E99CC"/>
                </div>

                <div className="line">
                    <SpecialField/>
                    <Temperature room="?" temperature="?" color="#769BD0"/>
                    <Temperature room="?" temperature="?" color="#EF982E"/>
                    <Temperature room="?" temperature="?" color="#DE1F26"/>
                </div>
            </div>
        )
    }
}

class Temperature extends React.Component {
    render() {
        const style = {
            backgroundColor: this.props.color
        }
        return (
            <div className="box">
                <div className="box-inside">
                    <div className="room">{this.props.room}</div>
                    <div className="temperature">{this.props.temperature}&#176;</div>
                    <div className="bottom" style={style}>
                        {this.props.temperature}&#176;
                    </div>
                </div>
            </div>
        )
    }
}

class SpecialField extends React.Component {
    render() {
        return (
            <div className="special-field">

            </div>
        )
    }
}

ReactDOM.render(
    <App/>
    , document.getElementById('react'));
